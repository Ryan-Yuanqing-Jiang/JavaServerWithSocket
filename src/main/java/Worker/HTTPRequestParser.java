package Worker;

import Constants.HTTPRequestStandard;
import Constants.HTTPRequestType;
import Factory.RequestFactory;
import Model.HTTPRequest;
import java.util.Map;

// Not sure if this is the best way to utilize generics.
public class HTTPRequestParser implements RequestParser<HTTPRequest> {

    private RequestFactory requestFactory;

    public HTTPRequestParser(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public HTTPRequest parseRequest(
            String requestLine,
            Map<String, String> headers,
            String body) throws InvalidRequestException {

        String[] requestLineFields = requestLine.split(" ");
        if (requestLineFields.length != HTTPRequestStandard.REQUEST_LINE_LENGTH)
            throw new InvalidRequestException("Invalid Request Line - Not all 3 fields are provided");

        HTTPRequestType requestType = parseRequestType(requestLineFields[0]);
        String requestPath = requestLineFields[1];
        String requestProtocol = requestLineFields[2];

        return this.requestFactory.createRequest(requestType, requestPath, requestProtocol, headers, body);
    }

    private HTTPRequestType parseRequestType(String rawType) throws InvalidRequestException {
        System.out.println("Trying to convert type: " + rawType + "---");
        for (HTTPRequestType type: HTTPRequestType.values()) {
            if (type.getRequestCode().equals(rawType)) {
                return type;
            }
        }
        throw new InvalidRequestException("Invalid Request Type - Not matching the HTTP types this server can process");
    }
}
