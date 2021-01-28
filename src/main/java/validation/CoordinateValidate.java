package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoordinateValidate implements Validation {
    @Override
    public boolean validate(String coordinate) {
        return isNotNull(coordinate) && isAllowableCoordinate(coordinate);
    }

    @Override
    public boolean isNotNull(String coordinate) {
        return coordinate != null;
    }

    public boolean isAllowableCoordinate(String coordinate) {
        Pattern pattern = Pattern.compile("\\d{1,2}(,\\d{3})*\\.\\d*");
        Matcher matcher = pattern.matcher(coordinate);
        return matcher.matches();
    }
}
