package CustomException;

public class UnknownRequestTypeException extends InvalidRequestException {
    public UnknownRequestTypeException(String message) {
        super(message);
    }
}
