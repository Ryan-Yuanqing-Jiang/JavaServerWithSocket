package Model;

import Constants.HTTPRequestType;

import java.util.Map;

public class HTTPRequest {
    private HTTPRequestType type;
    private String path;
    private String protocol;
    private Map<String, String> headers;
    private String body;

    public HTTPRequest(HTTPRequestType type, String path, String protocol, Map<String, String> headers, String body) {
        this.type = type;
        this.path = path;
        this.protocol = protocol;
        this.headers = headers;
        this.body = body;
    }

    public HTTPRequestType getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public String getBody() {
        return body;
    }
}
