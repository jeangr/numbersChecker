package it.gianni.numberschecker.controller;

import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import it.gianni.numberschecker.service.ValidatorNumberService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSControllerAPI {

    private final ValidatorNumberService validateNumberService;

    public JSControllerAPI(ValidatorNumberService validateNumberService) {
        this.validateNumberService = validateNumberService;
    }

    @GetMapping(value = "/api/numbers/validation/{numberToCheck}", produces = "application/json")
    @NonNull
    public SouthAfricanMobileNumberOM checkNumber(@NonNull @PathVariable("numberToCheck") String numberToCheck) {
        return validateNumberService.validateNumber(numberToCheck);
    }
}
