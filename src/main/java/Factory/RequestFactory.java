package Factory;

import Constants.HTTPRequestType;
import Model.HTTPRequest;

import java.util.Map;

public class RequestFactory {
    private static RequestFactory ourInstance = new RequestFactory();

    public static RequestFactory getInstance() {
        return ourInstance;
    }

    private RequestFactory() {
    }

    public HTTPRequest generateRequest(String[] requestLine, Map<String, String> requestHeader, String requestBody) {
        HTTPRequestType type = HTTPRequestType.valueOf(requestLine[0]);
        String path = requestLine[1];
        String protocol = requestLine[2];

        return new HTTPRequest(type, path, protocol, requestHeader, requestBody);
    }
}
