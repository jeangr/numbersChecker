package it.gianni.numberschecker.service;


import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import org.springframework.lang.NonNull;

public interface IValidateNumberService {

    @NonNull
    SouthAfricanMobileNumberEntity validateNumber(@NonNull Long id, @NonNull String number);
}
