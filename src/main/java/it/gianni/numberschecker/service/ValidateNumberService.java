package it.gianni.numberschecker.service;

import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidateNumberService implements IValidateNumberService{

    private final String PREFIX_SOUTH_AFRICA = "2783";
    private final String CORRECT_PATTERN = "^2783([0-9]{7})$";

    private final String ACCEPTABLE_PATTERN = "^([0-9]{7})$";

    @Override
    @NonNull
    public SouthAfricanMobileNumberEntity validateNumber(@NonNull Long id, @NonNull String number) {
        Assert.notNull(id, "id is null");
        Assert.notNull(number, "number is null");

        SouthAfricanMobileNumberEntity entity = new SouthAfricanMobileNumberEntity();
        entity.setId(id);
        entity.setOriginalNumber(number);

        Pattern correctPattern = Pattern.compile(CORRECT_PATTERN);
        Pattern acceptablePattern = Pattern.compile(ACCEPTABLE_PATTERN);

        Matcher correctMatcher = correctPattern.matcher(number);
        Matcher acceptableMatcher = acceptablePattern.matcher(number);

        if(correctMatcher.matches()){
            entity.setValid(true);
        } else if(acceptableMatcher.matches()) {
            entity.setValid(true);
            entity.setCorrectedNumber(String.format("%s%s", PREFIX_SOUTH_AFRICA, number)); //TO_DO
        } else {
            entity.setValid(false);
        }

        return entity;
    }

}
