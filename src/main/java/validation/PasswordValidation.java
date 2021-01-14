package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation implements Validation {
    @Override
    public boolean validate(String password) {
        return isNotNull(password) && isAllowableLogin(password);
    }

    @Override
    public boolean isNotNull(String password) {
        return password != null;
    }

    private boolean isAllowableLogin(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
