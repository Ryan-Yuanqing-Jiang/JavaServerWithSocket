package Model.HTTPRequest;

import Constants.HTTPRequestType;
import Mocks.RequestMockFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class POSTRequestTest {

    private HTTPRequest request;
    private RequestMockFactory mockFactory = new RequestMockFactory();

    @Before
    public void setUp() {
        request = new POSTRequest(
                mockFactory.PATH_ROOT,
                mockFactory.PROTOCOL_HTTP,
                mockFactory.mockPOSTHeaderMap(),
                mockFactory.POST_REQUEST_BODY
        );
    }

    @Test
    public void getType() {
        assertEquals(HTTPRequestType.POST, request.getType());
    }

    @Test
    public void getPath() {
        assertEquals(mockFactory.PATH_ROOT, request.getPath());
    }

    @Test
    public void getBody() {
        assertEquals(mockFactory.POST_REQUEST_BODY, request.getBody());
    }

    @Test
    public void getHeader() {
        for (String key:mockFactory.mockPOSTHeaderMap().keySet()
             ) {
            assertEquals(mockFactory.mockPOSTHeaderMap().get(key), request.getHeader(key));
        }
    }
}