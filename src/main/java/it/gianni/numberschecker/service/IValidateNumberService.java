package it.gianni.numberschecker.service;


import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface IValidateNumberService {

    @NonNull
    SouthAfricanMobileNumberEntity validateNumber(@Nullable Long id, @NonNull String number);
}
