package validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateValidateTest {
    String coordinate = "25.3335135";

    @Test
    void coordinateValidateTest() {
        assertTrue(validate(coordinate));
    }

    @Test
    boolean validate(String coordinate) {
        return isNotNull(coordinate) && isAllowableCoordinate(coordinate);

    }

    @Test
    boolean isNotNull(String coordinate) {
        return coordinate != null;
    }

    @Test
    boolean isAllowableCoordinate(String coordinate) {
        Pattern pattern = Pattern.compile("\\d{1,2}(,\\d{3})*\\.\\d*");
        Matcher matcher = pattern.matcher(coordinate);
        return matcher.matches();
    }
}