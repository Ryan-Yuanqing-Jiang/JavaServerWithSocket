package Constants;

public enum HTTPRequestType {
    GET ("GET"),
    POST ("POST"),
    PUT ("PUT"),
    DELETE ("DELETE"),
    HEAD ("HEAD"),
    OPTIONS ("OPTION")
    ;

    private final String requestCode;

    HTTPRequestType(String requestCode) {
        this.requestCode = requestCode;
    }
}
