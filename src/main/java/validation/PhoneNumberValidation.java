package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidation implements Validation {
    @Override
    public boolean validate(String phoneNumber) {
        return isNotNull(phoneNumber) && isAllowableLogin(phoneNumber);
    }

    @Override
    public boolean isNotNull(String phoneNumber) {
        return phoneNumber != null;
    }

    private boolean isAllowableLogin(String phoneNumber) {
        Pattern pattern = Pattern.compile("\\d{11}");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
