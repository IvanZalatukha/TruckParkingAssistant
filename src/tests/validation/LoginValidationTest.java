package validation;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class LoginValidationTest {
    @Test
    void loginValidationTest() {
        assertTrue(validate("MikeINick"));
    }


    @Test
    boolean validate(String login) {
        return isNotNull(login) && isAllowableLogin(login);
    }

    @Test
    boolean isNotNull(String login) {
        return login != null;
    }
    @Test
    boolean isAllowableLogin(String login) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{4,12}$");
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }
}