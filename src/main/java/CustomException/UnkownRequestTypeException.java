package CustomException;

public class UnkownRequestTypeException extends InvalidRequestException {
    public UnkownRequestTypeException(String message) {
        super(message);
    }
}
