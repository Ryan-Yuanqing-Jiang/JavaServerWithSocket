package Model;

import Constants.HTTPRequestType;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHTTPRequest implements HTTPRequest{
    private HTTPRequestType type;
    private String path;
    private String protocol;
    private Map<String, String> headers;
    private String body;

    public AbstractHTTPRequest(HTTPRequestType type, String path, String protocol, Map<String, String> headers, String body) {
        this.type = type;
        this.path = path;
        this.protocol = protocol;
        this.headers = headers;
        this.body = body;
    }

    @Override
    public HTTPRequestType getType() {
        return this.type;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getProtocol() {
        return this.protocol;
    }

    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public String getHeader(String headerKey) {
        return headers.get(headerKey);
    }
}
