package Mocks;

import Constants.HTTPRequestType;
import CustomException.InvalidRequestException;
import CustomException.UnkownRequestTypeException;
import Factory.RequestFactory;
import Model.HTTPRequest.GETRequest;
import Model.HTTPRequest.HTTPRequest;
import Model.HTTPRequest.POSTRequest;

import java.util.HashMap;
import java.util.Map;

public class HTTPRequestMockFactory extends RequestFactory {
    public final String PATH_ROOT = "/";
    public final String PROTOCOL_HTTP = "HTTP/1.1";
    public final String POSTRequestBody = "{\n" +
            "\t\"name\": \"ryan\",\n" +
            "\t\"age\": 25\n" +
            "}";

    public Map<String, String> mockPOSTHeaderMap() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Host", "localhost:8888");
        headerMap.put("Content-Type", "application/json");
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Postman-Token", "0c091702-819a-40bd-925d-45f23423ad5b");
        return headerMap;
    }

    public Map<String, String> mockGETHeaderMap() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Host", "localhost:8888");
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Postman-Token", "0c091702-819a-40bd-925d-45f23423ad5b");
        return headerMap;
    }

    @Override
    public HTTPRequest createRequest(HTTPRequestType requestType,
                                     String requestPath,
                                     String requestProtocol,
                                     Map<String, String> headers,
                                     String body) throws UnkownRequestTypeException {
        switch (requestType) {
            case GET:
                return mockGETRequest();
            case POST:
                return mockPOSTRequest();
            default:
                throw new UnkownRequestTypeException("Unkown HTTP Request Type - Trying to mock unknown http type");
        }
    }

    private GETRequest mockGETRequest() {
        return new GETRequest(PATH_ROOT, PROTOCOL_HTTP, mockGETHeaderMap());
    }

    private POSTRequest mockPOSTRequest() {
        final String requestBody = "{\n" +
                "\t\"name\": \"ryan\",\n" +
                "\t\"age\": 25\n" +
                "}";
        final Map<String, String> headerMap = mockPOSTHeaderMap();

        return new POSTRequest(PATH_ROOT, PROTOCOL_HTTP, headerMap, requestBody);
    }
}
