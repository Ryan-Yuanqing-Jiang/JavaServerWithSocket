package Model;

import Constants.HTTPRequestType;

import java.util.Map;

public class GETRequest extends AbstractHTTPRequest {
    public GETRequest(String path, String protocol, Map<String, String> headers) {
        super(HTTPRequestType.GET, path, protocol, headers, "");
    }
}
