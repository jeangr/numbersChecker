package it.gianni.numberschecker.service;


import it.gianni.numberschecker.OM.SouthAfricanMobileNumberOM;
import org.springframework.lang.NonNull;

public interface IValidateNumberService {

    @NonNull
    SouthAfricanMobileNumberOM validateNumber(@NonNull String number);
}
