package Worker;

import java.io.IOException;

public class InvalidRequestException extends IOException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
