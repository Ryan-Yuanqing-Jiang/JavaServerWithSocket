package Model.HTTPRequest;

import Constants.HTTPRequestType;
import Mocks.RequestMockFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GETRequestTest {

    private HTTPRequest request;
    private RequestMockFactory mockFactory = new RequestMockFactory();

    @Before
    public void setUp() {
        request = new GETRequest(
                mockFactory.PATH_ROOT,
                mockFactory.PROTOCOL_HTTP,
                mockFactory.mockGETHeaderMap()
        );
    }

    @Test
    public void getType() {
        assertEquals(HTTPRequestType.GET, request.getType());
    }

    @Test
    public void getPath() {
        assertEquals(mockFactory.PATH_ROOT, request.getPath());
    }

    @Test
    public void getBody() {
        assertEquals("", request.getBody());
    }

    @Test
    public void getHeader() {
        for (String key:mockFactory.mockGETHeaderMap().keySet()
                ) {
            assertEquals(mockFactory.mockGETHeaderMap().get(key), request.getHeader(key));
        }
    }
}