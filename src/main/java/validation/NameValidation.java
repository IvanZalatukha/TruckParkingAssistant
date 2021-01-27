package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidation implements Validation {
    @Override
    public boolean validate(String name) {
        return isNotNull(name) && isAllowableName(name);
    }

    @Override
    public boolean isNotNull(String name) {
        return name != null;
    }

    private boolean isAllowableName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{4,12}$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean theNameIsNotOffensive(String name) {
        return false;
    }
}
