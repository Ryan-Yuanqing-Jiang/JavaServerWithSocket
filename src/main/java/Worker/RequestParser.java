package Worker;

import java.util.Map;

public interface RequestParser<T> {
    T parseRequest(String requestLine, Map<String, String> headers, String body) throws InvalidRequestException ;
}
