package validation;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidationTest {
    String email = "xxxx@gmail.com";
    @Test
    void emailValidateTest() {
        assertTrue(validate(email));
    }

    @Test
    boolean validate(String email) {
        return isNotNull(email) && isAllowableEmail(email);
    }

    @Test
    boolean isNotNull(String email) {
        return email != null;
    }
    @Test
    boolean isAllowableEmail(String email) {
        Pattern pattern = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
