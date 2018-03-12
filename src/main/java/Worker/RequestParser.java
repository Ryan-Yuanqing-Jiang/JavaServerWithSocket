package Worker;

import java.io.InputStream;

public interface RequestParser<T> {
    T parseRequest(InputStream input) throws InvalidRequestException ;
}
