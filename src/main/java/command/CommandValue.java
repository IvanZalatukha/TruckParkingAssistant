package command;

public enum CommandValue {
    GO_TO_MAP_PAGE("goToMapPage"),
    GO_TO_MAIN_PAGE("goToMainPage"),
    ERROR("error"),
    PARKING_PAGINATION_PAGE("goToPaginationPage"),
    ADMIN_PAGE("goToAdiminPage"),
    SIGN_IN_PAGE("goSignInPage"),
    LOGIN_USER("login"),
    ADMIN_MAP("adminMap"),
    OUR_SERVICE("ourService"),
    MESSAGE_FROM_USER("messageFromUser"),
    REGISTRATION_PAGE("goToRegistrationPage"),
    REGISTRATION_USER("registrationUser"),
    FORGOT_PASSWORD("forgotPassword"),
    FORGOT_PASSWORD_PAGE("forgotPasswordPage"),
    CHECKBOXES("goToCheckBoxes"),
    SWITCH_LOCALE("switchLocale"),
    CONNECT_WITH_US("connectWithUs");

    private final String value;

    CommandValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

