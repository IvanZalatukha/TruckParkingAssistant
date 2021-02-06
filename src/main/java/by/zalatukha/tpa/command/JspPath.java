package by.zalatukha.tpa.command;

public enum JspPath {

    ERROR_PAGE("/jsp/error.jsp"),
    MAIN_PAGE("/jsp/main.jsp"),
    MAP_PAGE("/jsp/map2.jsp"),
    ADMIN_PAGE("/jsp/newAdminPage.jsp"),
    PARKING_PAGINATION_PAGE("/jsp/parkingPagination.jsp"),
    SIGN_IN_PAGE("/jsp/signIn.jsp"),
    REGISTRATION_PAGE("/jsp/registration.jsp"),
    CONNECT_WITH_US_PAGE("/jsp/connectWithUs.jsp"),
    OUR_SERVICE("/jsp/ourService.jsp"),
    FORGOT_PASSWORD("/jsp/forgotPassword.jsp");

    private final String path;

    JspPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
