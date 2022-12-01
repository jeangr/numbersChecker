package it.gianni.numberschecker.service;

import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import org.springframework.lang.NonNull;

import java.io.InputStream;
import java.util.List;

public interface CSVService {

    /**
     * Save csv data into database.
     *
     * @param is {@link InputStream}
     * @return
     */
    List<SouthAfricanMobileNumberOM> csvToNumbers(@NonNull InputStream is);

}
