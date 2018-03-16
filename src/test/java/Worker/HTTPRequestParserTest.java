package Worker;

import Constants.HTTPRequestType;
import CustomException.InvalidRequestException;
import CustomException.UnknownRequestTypeException;
import Mocks.RequestMockFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPRequestParserTest {

    private final String REQUEST_LINE = "POST / HTTP/1.1";
    private final int REQUEST_FIELDS_LENGTH = 3;
    private final RequestMockFactory mockFactory = new RequestMockFactory();

    @Test
    public void splitRequestLine() throws InvalidRequestException {
        HTTPRequestParser parser = new HTTPRequestParser(REQUEST_LINE);
        String[] lineFields = parser.splitRequestLine(REQUEST_LINE, REQUEST_FIELDS_LENGTH);
        assertEquals(REQUEST_FIELDS_LENGTH, lineFields.length);
        assertEquals("POST", lineFields[0]);
        assertEquals("/", lineFields[1]);
        assertEquals("HTTP/1.1", lineFields[2]);
    }

    @Test (expected = InvalidRequestException.class)
    public void splitInvalidRequestLine() throws InvalidRequestException {
        HTTPRequestParser parser = new HTTPRequestParser(mockFactory.POST_REQUEST_LINE_INVALID);
    }

    @Test
    public void parseRequestType() throws InvalidRequestException {
        HTTPRequestParser parser = new HTTPRequestParser(REQUEST_LINE);
        HTTPRequestType type = parser.parseRequestType("POST");
        assertEquals(HTTPRequestType.POST, type);
        type = parser.parseRequestType("GET");
        assertEquals(HTTPRequestType.GET, type);
    }

    @Test (expected = UnknownRequestTypeException.class)
    public void parseUnknownRequestType() throws InvalidRequestException {
        HTTPRequestParser parser = new HTTPRequestParser(REQUEST_LINE);
        HTTPRequestType type = parser.parseRequestType("UNKNOWN");
    }

    @Test
    public void getRequestLine() throws InvalidRequestException {
        HTTPRequestParser parser = new HTTPRequestParser(REQUEST_LINE);
        assertEquals(REQUEST_LINE, parser.getRequestLine());
    }

    @Test
    public void getRequestPath() throws InvalidRequestException {
        HTTPRequestParser parser = new HTTPRequestParser(REQUEST_LINE);
        assertEquals("/", parser.getRequestPath());
    }

    @Test
    public void getRequestProtocol() throws InvalidRequestException {
        HTTPRequestParser parser = new HTTPRequestParser(REQUEST_LINE);
        assertEquals("HTTP/1.1", parser.getRequestProtocol());
    }

    @Test
    public void getRequestType() throws InvalidRequestException {
        HTTPRequestParser parser = new HTTPRequestParser(REQUEST_LINE);
        assertEquals(HTTPRequestType.POST, parser.getRequestType());
    }
}