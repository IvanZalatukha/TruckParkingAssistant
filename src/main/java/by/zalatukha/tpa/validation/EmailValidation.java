package by.zalatukha.tpa.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation implements Validation {
    @Override
    public boolean validate(String email) {
        return isNotNull(email) && isAllowableEmail(email);
    }

    @Override
    public boolean isNotNull(String email) {
        return email != null;
    }

    public boolean isAllowableEmail(String email) {
        Pattern pattern = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
