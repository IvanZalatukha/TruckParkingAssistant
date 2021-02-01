package validation;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class SpotsValidateTest {

    @Test
    void spotsValidateTest() {
        assertTrue(validate("22"));
    }
    @Test
    boolean validate(String spots) {
        return isNotNull(spots) && isAllowableSpots(spots);
    }

    @Test
    boolean isNotNull(String spots) {
        return spots != null;
    }
    @Test
    boolean isAllowableSpots(String spots) {
        Pattern pattern = Pattern.compile("^\\d{1,3}");
        Matcher matcher = pattern.matcher(spots);
        return matcher.matches();
    }
}