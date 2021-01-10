package command;

public enum JspPath {

    ERROR_PAGE("/jsp/error.jsp"),
    MAIN_PAGE("/jsp/main.jsp"),
    MAP_PAGE("/jsp/map.jsp"),
    ADMIN_PAGE("/jsp/admin.jsp"),
    PARKING_PAGINATION_PAGE("/jsp/parkingPagination.jsp"),
    SIGN_IN_PAGE("/jsp/signIn.jsp"),
    REGISTRATION_PAGE("/jsp/registration.jsp");



    private String path;

    JspPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
