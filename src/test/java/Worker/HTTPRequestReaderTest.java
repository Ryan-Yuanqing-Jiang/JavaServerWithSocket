package Worker;

import CustomException.InvalidRequestException;
import Mocks.RequestMockFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Map;

import static org.junit.Assert.*;

public class HTTPRequestReaderTest {

    private final String REQUEST_LINE = "POST /path HTTP/1.1";
    private final RequestMockFactory mockFactory = new RequestMockFactory();
    private BufferedReader validReader, invalidReader;

    @Before
    public void setUp() {
        String validRequest = mockFactory.mockRawPOSTRequest("post");
        String emptyRequest = "";
        this.validReader = new BufferedReader(new StringReader(validRequest));
        this.invalidReader = new BufferedReader(new StringReader(emptyRequest));
    }

    @Test
    public void getRequestLine() throws InvalidRequestException {
        HTTPRequestReader reader = new HTTPRequestReader(this.validReader);
        assertEquals(REQUEST_LINE, reader.getRequestLine());
    }

    @Test
    public void getHeaders() throws InvalidRequestException {
        HTTPRequestReader reader = new HTTPRequestReader(this.validReader);
        Map<String, String> mockHeader = mockFactory.mockPOSTHeaderMap();
        for (String key:reader.getHeaders().keySet()) {
            assertEquals(mockHeader.get(key), reader.getHeaders().get(key));
        }
    }

    @Test
    public void getBody() throws InvalidRequestException {
        HTTPRequestReader reader = new HTTPRequestReader(this.validReader);
        assertEquals(mockFactory.POST_REQUEST_BODY, reader.getBody());
    }

    @Test(expected = InvalidRequestException.class)
    public void getEmptyRequest() throws InvalidRequestException {
        HTTPRequestReader reader = new HTTPRequestReader(this.invalidReader);
    }
}