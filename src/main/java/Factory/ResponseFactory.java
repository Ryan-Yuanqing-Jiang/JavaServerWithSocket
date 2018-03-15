package Factory;

import Model.HTTPRequest.HTTPRequest;

import java.io.UnsupportedEncodingException;

public class ResponseFactory {
    public byte[] getDummyHeader() throws UnsupportedEncodingException {
        byte[] responseHeader =
                ("HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length: " + 930715 +
                        "\r\n\r\n").getBytes("UTF-8");
        return responseHeader;
    }

    public byte[] getDummyDocument(HTTPRequest request) throws UnsupportedEncodingException {
        byte[] responseDocument = ("<html><body>" +
                "Server: " +
                request.getType().getRequestCode() +
                "</body></html>").getBytes("UTF-8");
        return responseDocument;
    }
}
