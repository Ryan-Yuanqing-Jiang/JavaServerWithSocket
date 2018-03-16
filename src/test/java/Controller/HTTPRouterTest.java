package Controller;

import Constants.HTTPRequestType;
import CustomException.UnknownRequestTypeException;
import Factory.RequestFactory;
import Mocks.RequestMockFactory;
import Model.HTTPRequest.HTTPRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class HTTPRouterTest {

    private final RequestMockFactory mockFactory = new RequestMockFactory();
    @Test
    public void returnLogControllerFromGivenRequest() throws UnknownRequestTypeException {
        HTTPRouter router = new HTTPRouter();
        HTTPRequest httpRequest = new RequestFactory().createRequest(
                HTTPRequestType.GET,
                mockFactory.PATH_LOG,
                mockFactory.PROTOCOL_HTTP,
                mockFactory.mockGETHeaderMap(),
                ""
        );

    }
}