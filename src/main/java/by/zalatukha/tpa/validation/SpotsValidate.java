package by.zalatukha.tpa.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpotsValidate implements Validation {
    @Override
    public boolean validate(String spots) {
        return isNotNull(spots) && isAllowableSpots(spots);
    }

    @Override
    public boolean isNotNull(String spots) {
        return spots != null;
    }

    public boolean isAllowableSpots(String spots) {
        Pattern pattern = Pattern.compile("^\\d{1,3}");
        Matcher matcher = pattern.matcher(spots);
        return matcher.matches();
    }
}
