package it.gianni.numberschecker.service;

import it.gianni.numberschecker.OM.SouthAfricanMobileNumberOM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.InputStream;

public interface ICSVService {

    /**
     * Save csv data into database.
     *
     * @param is {@link InputStream}
     */
    void saveData(InputStream is);

    /**
     * Returns the list of numbers paginated.
     *
     * @param pageable {@link Pageable}
     * @return a list of {@link SouthAfricanMobileNumberOM}
     */
    Page<SouthAfricanMobileNumberOM> getDataPage(Pageable pageable);

    /**
     * Delete data from database.
     *
     */
    void deleteData();

}
