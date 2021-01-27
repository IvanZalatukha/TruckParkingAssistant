package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidation implements Validation {
    @Override
    public boolean validate(String login) {
        return isNotNull(login) && isAllowableLogin(login);
    }

    @Override
    public boolean isNotNull(String login) {
        return login != null;
    }

    private boolean isAllowableLogin(String login) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{4,12}$");
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

}
