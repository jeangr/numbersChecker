package it.gianni.numberschecker.service;


import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import org.springframework.lang.NonNull;

public interface ValidatorNumberService {

    /**
     * Validate a number.
     *
     * @param number to check
     * @return {@link SouthAfricanMobileNumberOM}
     */
    @NonNull
    SouthAfricanMobileNumberOM validateNumber(@NonNull String number);
}
