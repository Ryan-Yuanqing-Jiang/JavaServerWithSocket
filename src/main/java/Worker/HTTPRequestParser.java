package Worker;

import Constants.HTTPRequestStandard;
import Constants.HTTPRequestType;
import CustomException.InvalidRequestException;
import CustomException.UnknownRequestTypeException;

/**
 * Parse strings into request fields.
 */
public class HTTPRequestParser {

    private String requestLine, requestPath, requestProtocol;
    private HTTPRequestType requestType;

    public HTTPRequestParser(String requestLine) throws InvalidRequestException {
        this.requestLine = requestLine;
        String[] lineFields = splitRequestLine(requestLine, HTTPRequestStandard.REQUEST_LINE_LENGTH);
        this.requestType = parseRequestType(lineFields[0]);
        this.requestPath = lineFields[1];
        this.requestProtocol = lineFields[2];
    }

    public String[] splitRequestLine(String requestLine, int lineLength) throws InvalidRequestException {
        String[] requestFields = requestLine.split(" ");
        if (requestFields.length != lineLength)
            throw new InvalidRequestException("Invalid Request Line - Expecting: " + lineLength + " but got " + requestFields);
        for (String field:requestFields) {
            if (field.isEmpty())
                throw new InvalidRequestException("Invalid Request Line - Empty field detected.");
        }
        return requestFields;
    }

    public HTTPRequestType parseRequestType(String rawType) throws UnknownRequestTypeException {
        for (HTTPRequestType type: HTTPRequestType.values()) {
            if (type.getRequestCode().equals(rawType)) {
                return type;
            }
        }
        throw new UnknownRequestTypeException("Unknown Request Type - Not matching the HTTP types this server can process");
    }

    public String getRequestLine() {
        return requestLine;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public String getRequestProtocol() {
        return requestProtocol;
    }

    public HTTPRequestType getRequestType() {
        return requestType;
    }
}
