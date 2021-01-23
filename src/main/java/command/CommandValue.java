package command;

public enum CommandValue {
    GO_TO_MAP_PAGE("goToMapPage"),
    GO_TO_MAIN_PAGE("goToMainPage"),
    ERROR("error"),
    PARKING_PAGINATION_PAGE("goToPaginationPage"),
    ADMIN_PAGE("goToAdiminPage"),
    SIGN_IN_PAGE("goSignInPage"),
    REGISTRATION_PAGE("goToRegistrationPage"),
    ADMIN_MAP("adminMap"),
    SHOW_PARKING("showParking"),


//    SET_REMOVE_ADMIN_RIGHTS("setRemoveAdminRights"),
//    BID_FROM_CART_PAGE("bidFromCartPage"),
    BID_FROM_LOTS_PAGE("bidFromLotsPage");

    private String value;

    CommandValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

