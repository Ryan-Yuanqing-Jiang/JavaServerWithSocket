//package Mocks;
//
//import Constants.HTTPRequestType;
//import Model.HTTPRequest.HTTPRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class MockHTTPRequests {
//    public final String PATH_ROOT = "/";
//    public final String PROTOCOL_HTTP = "HTTP/1.1";
//    public final String POSTRequestBody = "{\n" +
//            "\t\"name\": \"ryan\",\n" +
//            "\t\"age\": 25\n" +
//            "}";
//
//    public Map<String, String> mockPOSTHeaderMap() {
//        Map<String, String> headerMap = new HashMap<>();
//        headerMap.put("Host", "localhost:8888");
//        headerMap.put("Content-Type", "application/json");
//        headerMap.put("Cache-Control", "no-cache");
//        headerMap.put("Postman-Token", "0c091702-819a-40bd-925d-45f23423ad5b");
//        return headerMap;
//    }
//
//    public Map<String, String> mockGETHeaderMap() {
//        Map<String, String> headerMap = new HashMap<>();
//        headerMap.put("Host", "localhost:8888");
//        headerMap.put("Cache-Control", "no-cache");
//        headerMap.put("Postman-Token", "0c091702-819a-40bd-925d-45f23423ad5b");
//        return headerMap;
//    }
//}
