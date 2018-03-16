//package Server;
//
//import Factory.RequestFactory;
//import Model.HTTPRequest.HTTPRequest;
//import Worker.HTTPRequestParser;
//import Worker.HTTPRequestReader;
//import CustomException.InvalidRequestException;
//
//import java.io.*;
//import java.net.Socket;
//
//public class SingleThreadServer extends SimpleServer {
//
//
//    /**
//     * This server is just a playground, so not need to really use it in production.
//     *
//     * @param socketPort
//     */
//    public SingleThreadServer(int socketPort) throws IOException {
//        super(socketPort);
//    }
//
//    @Override
//    public void run() throws RuntimeException {
//        // If the server is still running,
//        // here comes the classic server loop.
//        while (!this.isStopped) {
//            Socket clientSocket = null;
//            try {
//                clientSocket = serverSocket.accept();
//                processRequest(clientSocket);
//                clientSocket.close();
//                System.out.println("Finishing");
//            } catch (IOException e) {
//                if (isStopped()) {
//                    System.out.println("Server is stopped");
//                    return;
//                }
//                throw new RuntimeException("Couldn't get client connection", e);
//            }
//        }
//    }
//
//    protected synchronized void processRequest(Socket clientSocket) throws IOException {
//        System.out.println("Started Processing---");
//        InputStream input  = clientSocket.getInputStream();
//        OutputStream output = clientSocket.getOutputStream();
//        long time = System.currentTimeMillis();
//
//        byte[] responseDocument = ("<html><body>" +
//                "Singlethreaded Server: " +
//                time +
//                "</body></html>").getBytes("UTF-8");
//
//        byte[] responseHeader =
//                ("HTTP/1.1 200 OK\r\n" +
//                        "Content-Type: text/html; charset=UTF-8\r\n" +
//                        "Content-Length: " + responseDocument.length +
//                        "\r\n\r\n").getBytes("UTF-8");
//
//        HTTPRequestReader requestReader;
//        HTTPRequestParser requestParser;
//        HTTPRequest httpRequest;
//        BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
//
//        if (!inputReader.ready())
//            return;
//
//        try {
//            requestReader = new HTTPRequestReader(inputReader);
//            requestParser = new HTTPRequestParser(new RequestFactory());
//            httpRequest = requestParser.parseRequest(requestReader.getRequestLine(), requestReader.getHeaders(), requestReader.getBody());
//
//            output.write(responseHeader);
//            output.write(responseDocument);
//        } catch (InvalidRequestException e) {
//            System.err.println("Request Processing Error: ");
//            System.err.println(e.getMessage());
//            return;
//        } finally {
//            output.close();
//            input.close();
//        }
//    }
//
//}
