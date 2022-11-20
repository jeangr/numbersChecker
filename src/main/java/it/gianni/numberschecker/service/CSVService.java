package it.gianni.numberschecker.service;

import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import it.gianni.numberschecker.repository.SouthAfricanMobileNumberRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVService implements ICSVService {

  private final SouthAfricanMobileNumberRepository repository;
  private final IValidateNumberService validateNumberService;

  public CSVService(SouthAfricanMobileNumberRepository repository, IValidateNumberService validateNumberService) {
    this.repository = repository;
    this.validateNumberService = validateNumberService;
  }

  @Override
  public void saveInDb(MultipartFile file) {
    try {
      List<SouthAfricanMobileNumberEntity> numbers = this.csvToNumbers(file.getInputStream());
      repository.saveAll(numbers);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  @Override
  public List<SouthAfricanMobileNumberEntity> getData() {
    return repository.findAll();
  }

  private List<SouthAfricanMobileNumberEntity> csvToNumbers(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
         CSVParser csvParser = new CSVParser(fileReader,
                 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<SouthAfricanMobileNumberEntity> numbers = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {

        SouthAfricanMobileNumberEntity number = validateNumberService.validateNumber(Long.parseLong(csvRecord.get(0)), csvRecord.get(1));

        numbers.add(number);
      }

      return numbers;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

}
