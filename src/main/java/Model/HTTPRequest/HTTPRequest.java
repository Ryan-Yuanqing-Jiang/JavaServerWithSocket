package Model.HTTPRequest;

import Constants.HTTPRequestType;

import java.util.Map;

public interface HTTPRequest {

    HTTPRequestType getType();

    String getPath();

    String getBody();

    String getHeader(String headerKey);
}
