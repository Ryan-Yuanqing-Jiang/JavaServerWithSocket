package Model.HTTPResponse;

import Constants.HTTPResponseStatus;

public interface HTTPResponse {
    HTTPResponseStatus getStatus();
    String getContentType();
    String getBody();
}
