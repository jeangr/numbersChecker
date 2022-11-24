package it.gianni.numberschecker.service;

import it.gianni.numberschecker.OM.SouthAfricanMobileNumberOM;
import it.gianni.numberschecker.entity.SouthAfricanMobileNumberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.InputStream;

public interface ICSVService {

    void saveData(InputStream is);

    Page<SouthAfricanMobileNumberOM> getDataPage(Pageable pageable);

    void deleteData();

}
