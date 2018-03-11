package Controller;

import Model.HTTPRequest;
import Worker.HTTPRequestParser;
import Worker.InvalidRequestException;
import Worker.RequestParser;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * This class should take the client request from server class and direct to the correct service.
 */
public class HTTPRouter implements Runnable {
    private Socket clientSocket;
    private RequestParser<HTTPRequest> parser;

    public HTTPRouter(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.parser = new HTTPRequestParser();
    }

    @Override
    public synchronized void run() {
        // Read in all the lines first
        InputStream input = null;
        HTTPRequest requestObj;
        System.out.println("ROUTER" + Thread.currentThread().toString());
        try {
            input = clientSocket.getInputStream();
            requestObj = parser.parseRequest(input);
            if (requestObj == null)
                throw new InvalidRequestException("HTTP Request generation error");

            // Demo trial.
            OutputStream output = clientSocket.getOutputStream();
            output.write(requestObj.getType().toString().getBytes());
            output.close();
        } catch (IOException e) {
            System.err.println("Incoming Request Processing Error: ");
            System.err.println(e.getMessage());
        }

    }


}
