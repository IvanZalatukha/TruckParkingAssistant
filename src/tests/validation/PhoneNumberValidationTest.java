package validation;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberValidationTest {

    @Test
    void phoneNumberValidationTest() {
        assertTrue(validate("80293592629"));
    }


    @Test
    public boolean validate(String phoneNumber) {
        return isNotNull(phoneNumber) && isAllowablePhoneNumber(phoneNumber);
    }

    @Test

    public boolean isNotNull(String phoneNumber) {
        return phoneNumber != null;
    }
    @Test
    private boolean isAllowablePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("\\d{11}");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

}