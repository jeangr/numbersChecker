package it.gianni.numberschecker.service;

import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import it.gianni.numberschecker.exception.CSVException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVSouthAfricaService implements CSVService {

    private final ValidatorNumberService validateNumberService;

    public CSVSouthAfricaService(ValidatorNumberService validateNumberService) {
        this.validateNumberService = validateNumberService;
    }

    @Override
    public List<SouthAfricanMobileNumberOM> csvToNumbers(@NonNull InputStream is) {
        return this.convert(is);
    }

    @NonNull
    private List<SouthAfricanMobileNumberOM> convert(@NonNull InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<SouthAfricanMobileNumberOM> numbers = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                SouthAfricanMobileNumberOM number = validateNumberService.validateNumber(csvRecord.get(1));
                number.setId(Long.parseLong(csvRecord.get(0)));
                numbers.add(number);
            }

            return numbers;
        } catch (Exception e) {
            throw new CSVException("Fail to parse CSV file - " + e.getMessage());
        }
    }

}
