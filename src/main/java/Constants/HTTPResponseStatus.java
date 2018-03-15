package Constants;

public enum HTTPResponseStatus {
    OK ("200", "OK"),
    BAD_REQUEST ("400", "Bad Request"),
    NOT_FOUND ("404", "Not Found"),
    INTERNAL_SERVER_ERROR ("500", "Internal Server Error"),
    SERVICE_UNAVAILAVLE ("503", "Service Unavailable"),
    ;

    private final String statusCode;
    private final String statusText;

    HTTPResponseStatus(String statusCode, String statusText) {
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }
}
