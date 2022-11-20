package it.gianni.numberschecker.service;

import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICSVService {

    void saveInDb(MultipartFile file);

    List<SouthAfricanMobileNumberEntity> getData();

}
