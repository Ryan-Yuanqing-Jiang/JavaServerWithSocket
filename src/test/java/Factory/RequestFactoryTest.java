package Factory;

import Constants.HTTPRequestType;
import CustomException.UnknownRequestTypeException;
import Mocks.RequestMockFactory;
import Model.HTTPRequest.HTTPRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestFactoryTest {

    private RequestMockFactory mockFactory = new RequestMockFactory();

    @Test
    public void createValidGETRequest() throws UnknownRequestTypeException {
        RequestFactory factory = new RequestFactory();
        HTTPRequest request = factory.createRequest(
                HTTPRequestType.GET,
                mockFactory.PATH_ROOT,
                mockFactory.PROTOCOL_HTTP,
                mockFactory.mockGETHeaderMap(),
                ""
        );
        assertEquals(HTTPRequestType.GET, request.getType());
        assertEquals(mockFactory.PATH_ROOT, request.getPath());
        assertEquals("", request.getBody());
        for (String key:mockFactory.mockGETHeaderMap().keySet()
                ) {
            assertEquals(mockFactory.mockGETHeaderMap().get(key), request.getHeader(key));
        }
    }

    @Test
    public void createValidPOSTRequest() throws UnknownRequestTypeException {
        RequestFactory factory = new RequestFactory();
        HTTPRequest request = factory.createRequest(
                HTTPRequestType.POST,
                mockFactory.PATH_ROOT,
                mockFactory.PROTOCOL_HTTP,
                mockFactory.mockGETHeaderMap(),
                mockFactory.POST_REQUEST_BODY
        );
        assertEquals(HTTPRequestType.POST, request.getType());
        assertEquals(mockFactory.PATH_ROOT, request.getPath());
        assertEquals(mockFactory.POST_REQUEST_BODY, request.getBody());
        for (String key:mockFactory.mockGETHeaderMap().keySet()
                ) {
            assertEquals(mockFactory.mockGETHeaderMap().get(key), request.getHeader(key));
        }
    }
}