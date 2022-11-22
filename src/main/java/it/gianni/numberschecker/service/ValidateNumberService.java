package it.gianni.numberschecker.service;

import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidateNumberService implements IValidateNumberService{

    private static final String PREFIX_SOUTH_AFRICA = "27";
    private static final String CORRECT_PATTERN = "^27([0-9]{9})$";
    private static final String ACCEPTABLE_PATTERN = "^([0-9]{9})$";

    @Override
    @NonNull
    public SouthAfricanMobileNumberEntity validateNumber(@Nullable Long id, @NonNull String number) {
        Assert.notNull(number, "number is null");

        SouthAfricanMobileNumberEntity entity = new SouthAfricanMobileNumberEntity();
        if(id != null) {
            entity.setId(id);
        }
        entity.setOriginalNumber(number);

        Pattern correctPattern = Pattern.compile(CORRECT_PATTERN);
        Pattern acceptablePattern = Pattern.compile(ACCEPTABLE_PATTERN);

        Matcher correctMatcher = correctPattern.matcher(number);
        Matcher acceptableMatcher = acceptablePattern.matcher(number);

        if(correctMatcher.matches()){
            entity.setValid(true);
        } else if(acceptableMatcher.matches()) {
            entity.setValid(true);
            entity.setCorrectedNumber(String.format("%s%s", PREFIX_SOUTH_AFRICA, number));
        } else {
            entity.setValid(false);
        }

        return entity;
    }

}
