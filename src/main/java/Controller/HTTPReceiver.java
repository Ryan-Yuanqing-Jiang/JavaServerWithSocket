package Controller;

import CustomException.InvalidRequestException;
import Factory.RequestFactory;
import Model.HTTPRequest.HTTPRequest;
import Worker.HTTPRequestParser;
import Worker.HTTPRequestReader;

import java.io.*;
import java.net.Socket;

/**
 * This class should take the client request from server class and direct to the correct service.
 */
public class HTTPReceiver implements Runnable {
    private Socket clientSocket;

    public HTTPReceiver(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public synchronized void run() {
        InputStream input;
        OutputStream output;
        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();

            BufferedReader inputReader = createReaderFromInput(input);

            if (inputReader != null) {
                HTTPRequest request = processClientInput(inputReader);
                byte[] responseDocument = ("<html><body>" +
                        "Singlethreaded Server: " +
                        request.getBody() +
                        "</body></html>").getBytes("UTF-8");

                byte[] responseHeader =
                        ("HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/html; charset=UTF-8\r\n" +
                                "Content-Length: " + responseDocument.length +
                                "\r\n\r\n").getBytes("UTF-8");
                output.write(responseHeader);
                output.write(responseDocument);
            }
            output.close();
            input.close();
            clientSocket.close();
            System.out.println("Connection closing, Request finished.");
            return;

        } catch (IOException e) {
            System.err.println("I/O Error - Couldn't Fetch/Close InputStream from Connection");
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            System.err.println(e.getMessage());
        }
    }

    public HTTPRequest processClientInput(BufferedReader inputReader) throws InvalidRequestException {
        HTTPRequestReader requestReader = new HTTPRequestReader(inputReader);
        HTTPRequestParser requestParser = new HTTPRequestParser(requestReader.getRequestLine());
        RequestFactory requestFactory = new RequestFactory();

        HTTPRequest request = requestFactory.createRequest(
                requestParser.getRequestType(),
                requestParser.getRequestPath(),
                requestParser.getRequestProtocol(),
                requestReader.getHeaders(),
                requestReader.getBody()
        );
        return request;
    }

    /**
     * Returns null when input stream is empty.
     * @param input InputStream from client socket.
     * @return
     */
    private BufferedReader createReaderFromInput(InputStream input) {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
        try {
            if (!inputReader.ready())
                return null;
        } catch (IOException e) {
            System.err.println("I/O Error - BufferedReader Error from Connection");
            e.getStackTrace();
        }
        return inputReader;
    }
}
