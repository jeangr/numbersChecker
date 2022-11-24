package it.gianni.numberschecker.service;

import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.io.InputStream;

public interface CSVService {

    /**
     * Save csv data into database.
     *
     * @param is {@link InputStream}
     */
    void saveData(@NonNull InputStream is);

    /**
     * Returns the list of numbers paginated.
     *
     * @param pageable {@link Pageable}
     * @return a list of {@link SouthAfricanMobileNumberOM}
     */
    Page<SouthAfricanMobileNumberOM> getDataPage(@NonNull Pageable pageable);

    /**
     * Delete data from database.
     *
     */
    void deleteData();

}
