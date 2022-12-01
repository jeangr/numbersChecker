package it.gianni.numberschecker.service;

import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DatabaseService {

    /**
     * Save numberd in database.
     *
     * @param oms list of {@link SouthAfricanMobileNumberOM}
     */
    void saveAll(@NonNull List<SouthAfricanMobileNumberOM> oms);

    /**
     * Returns a list of numbers.
     *
     * @return a list of {@link SouthAfricanMobileNumberOM}
     */
    @NonNull
    List<SouthAfricanMobileNumberOM> getAll();

    /**
     * Delete data from database.
     *
     */
    void deleteAll();

}
