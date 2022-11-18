package it.gianni.numberschecker.service;

import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import it.gianni.numberschecker.repository.SouthAfricanMobileNumberRepository;
import it.gianni.numberschecker.utils.CSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

  private final SouthAfricanMobileNumberRepository repository;

  public CSVService(SouthAfricanMobileNumberRepository repository) {
    this.repository = repository;
  }

  public void saveInDb(MultipartFile file) {
    try {
      List<SouthAfricanMobileNumberEntity> numbers = CSVUtil.csvToNumbers(file.getInputStream());
      repository.saveAll(numbers);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public List<SouthAfricanMobileNumberEntity> getAllNumbers() {
    return repository.findAll();
  }
}
