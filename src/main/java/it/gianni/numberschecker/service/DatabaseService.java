package it.gianni.numberschecker.service;

import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DatabaseService {

    void saveAll(@NonNull List<SouthAfricanMobileNumberOM> oms);

    @NonNull
    List<SouthAfricanMobileNumberOM> getAll();

    void deleteAll();

}
