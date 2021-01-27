package command;

public class ResponseContext {
    private final String page;
    private final ResponseType responseType;

    public ResponseContext(String page, ResponseType responseType) {
        this.page = page;
        this.responseType = responseType;
    }

    public String getPage() {
        return page;
    }

    public ResponseType getResponseType() {
        return responseType;
    }
}
