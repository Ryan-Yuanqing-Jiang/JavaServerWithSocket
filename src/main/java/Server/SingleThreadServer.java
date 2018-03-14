package Server;

import Factory.RequestFactory;
import Model.HTTPRequest;
import Worker.HTTPRequestParser;
import Worker.HTTPRequestReader;
import Worker.InvalidRequestException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadServer extends SimpleServer {


    /**
     * Making sure that the server can not be instantiated without a port.
     *
     * @param socketPort
     */
    public SingleThreadServer(int socketPort) {
        super(socketPort);
    }

    @Override
    public void run() throws RuntimeException {
        // Not sure what this is doing, should ask
        // My guess is that it's preventing other threads from accessing
        // this object and then put this running function into the current thread.
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        super.initSocket(this.socketPort);

        // If the server is still running,
        // here comes the classic server loop.
        while (!this.isStopped) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                processRequest(clientSocket);
                clientSocket.close();
                System.out.println("Finishing");
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server is stopped");
                    return;
                }
                throw new RuntimeException("Couldn't get client connection", e);
            }
        }
    }

    protected synchronized void processRequest(Socket clientSocket) throws IOException {
        System.out.println("Started Processing---");
        InputStream input  = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();
        long time = System.currentTimeMillis();
//        if (input.read() <= 0) {
//            System.out.println("filtered ######");
//            clientSocket.close();
//            return;
//        }

        byte[] responseDocument = ("<html><body>" +
                "Singlethreaded Server: " +
                time +
                "</body></html>").getBytes("UTF-8");

        byte[] responseHeader =
                ("HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length: " + responseDocument.length +
                        "\r\n\r\n").getBytes("UTF-8");

        HTTPRequestReader requestReader;
        HTTPRequestParser requestParser;
        HTTPRequest httpRequest;
        try {
            requestReader = new HTTPRequestReader(input);
            requestParser = new HTTPRequestParser(new RequestFactory());
            httpRequest = requestParser.parseRequest(requestReader.getRequestLine(), requestReader.getHeaders(), requestReader.getBody());

//            System.out.println(httpRequest.getType().getRequestCode());
//            System.out.println(httpRequest.getPath());
//            System.out.println(httpRequest.getBody());

            output.write(responseHeader);
            output.write(responseDocument);
        } catch (InvalidRequestException e) {
            System.err.println("Request Processing Error: ");
            System.err.println(e.getMessage());
            return;
        } finally {
            output.close();
            input.close();
        }
    }

}
