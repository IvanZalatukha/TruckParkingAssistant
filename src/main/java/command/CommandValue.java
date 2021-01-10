package command;

public enum CommandValue {
    GO_TO_MAP_PAGE("goToMapPage"),
    GO_TO_MAIN_PAGE("goToMainPage"),
    ERROR("error"),
    PARKING_PAGINATION_PAGE("goToPaginationPage"),
    ADMIN_PAGE("goToAdiminPage"),
    SIGN_IN_PAGE("goSignInPage"),
    REGISTRATION_PAGE("goToRegistrationPage"),
//    SUGGEST("suggest"),
//    CANCEL_SUGGESTION("cancelSuggestion"),
//    BID("bid"),
//    CHANGE_LOCAL("changeLocal"),
//    FORWARD("forward"),
//    SUGGEST_FROM_CART("suggestFromCart"),
//    GO_TO_ADMIN_PAGE("goToAdminPage"),
//    ACCEPT_REJECT_LOT("acceptRejectLot"),
//    LOCK_UNLOCK_BIDDER("lockUnlockBidder"),
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

