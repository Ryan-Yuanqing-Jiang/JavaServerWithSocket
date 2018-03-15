package Factory;

import Constants.HTTPRequestType;
import CustomException.UnkownRequestTypeException;
import Model.HTTPRequest.GETRequest;
import Model.HTTPRequest.HTTPRequest;
import Model.HTTPRequest.POSTRequest;
import CustomException.InvalidRequestException;

import java.util.Map;

public class RequestFactory {

    public RequestFactory() {
    }

    /**
     * Generate and return HTTPRequest based on the provided type and fields.
     * @param requestType HTTP Request Type.
     * @param requestPath Path to endpoint.
     * @param requestProtocol Request Protocol.
     * @param headers Request Headers as HashMap.
     * @param body Request Body, can be empty string.
     * @return HTTP Request Object.
     * @throws InvalidRequestException
     */
    public HTTPRequest createRequest(
            HTTPRequestType requestType,
            String requestPath,
            String requestProtocol,
            Map<String, String> headers,
            String body) throws UnkownRequestTypeException
    {

        switch (requestType) {
            case GET:
                return new GETRequest(requestPath, requestProtocol, headers);
            case POST:
                return new POSTRequest(requestPath, requestProtocol, headers, body);
            default:
                throw new UnkownRequestTypeException("Unkown Request Type - Request Parsing Error:  " + requestType.getRequestCode() + " request yet");
        }
    }
}
