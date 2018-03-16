package Mocks;

import java.util.HashMap;
import java.util.Map;

public class RequestMockFactory {
    public final String PATH_ROOT = "/";
    public final String PATH_LOG = "/log";

    public final String PROTOCOL_HTTP = "HTTP/1.1";

    public final String POST_REQUEST_LINE = "POST /path HTTP/1.1";
    public final String POST_REQUEST_LINE_INVALID = "POST /path something random xxx";
    public final String POST_REQUEST_HEADER = "Host: localhost:8888\n" +
            "Content-Type: application/json\n" +
            "Cache-Control: no-cache\n" +
            "content-length: 31\n" +
            "Postman-Token: 33646fc8-3a26-4147-98e9-98b8185e8bbb";
    public final String POST_REQUEST_BODY = "{\n" +
            "\t\"name\": \"ryan\",\n" +
            "\t\"age\": 25\n" +
            "}";

    public String mockRawPOSTRequest(String option) {
        switch (option) {
            case "post":
                return POST_REQUEST_LINE + "\n" + POST_REQUEST_HEADER + "\n" + "\n" + POST_REQUEST_BODY;
            case "invalid_post_line":
                return POST_REQUEST_LINE_INVALID + POST_REQUEST_HEADER + "\n" + "\n" + POST_REQUEST_BODY;
            default:
                return POST_REQUEST_LINE + "\n" + POST_REQUEST_HEADER + "\n" + "\n" + POST_REQUEST_BODY;
        }
    }

    public Map<String, String> mockPOSTHeaderMap() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Host", "localhost:8888");
        headerMap.put("Content-Type", "application/json");
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("content-length", "31");
        headerMap.put("Postman-Token", "33646fc8-3a26-4147-98e9-98b8185e8bbb");
        return headerMap;
    }

    public Map<String, String> mockGETHeaderMap() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Host", "localhost:8888");
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Postman-Token", "0c091702-819a-40bd-925d-45f23423ad5b");
        return headerMap;
    }
}
