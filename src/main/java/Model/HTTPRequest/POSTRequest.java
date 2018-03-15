package Model.HTTPRequest;

import Constants.HTTPRequestType;

import java.util.Map;

public class POSTRequest extends AbstractHTTPRequest {
    public POSTRequest(String path, String protocol, Map<String, String> headers, String body) {
        super(HTTPRequestType.POST, path, protocol, headers, body);
    }
}
