package Model;

import Constants.HTTPRequestType;

import java.util.Map;

public interface HTTPRequest {

    HTTPRequestType getType();

    String getPath();

    String getProtocol();

    String getBody();

    String getHeader(String headerKey);
}
