package Server;

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
    public void run() {
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
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server is stopped");
                    return;
                }
                // How to handle exception?
                // Is runtime exception the one to do the job?
                throw new RuntimeException("Couldn't get client connection", e);
            }

            try {
                processRequest(clientSocket);
            } catch (IOException e) {
                System.out.println("");
            }
        }
    }

    protected void processRequest(Socket clientSocket) throws IOException {
        InputStream input  = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();
        long time = System.currentTimeMillis();

        byte[] responseDocument = ("<html><body>" +
                "Singlethreaded Server: " +
                time +
                "</body></html>").getBytes("UTF-8");

        byte[] responseHeader =
                ("HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length: " + responseDocument.length +
                        "\r\n\r\n").getBytes("UTF-8");

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        // reader.lines().forEach(line -> System.out.println(line));
                String line = reader.readLine();
        try {
            while (!line.isEmpty()) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (NullPointerException e) {

        }
        line = reader.readLine();
        System.out.println("Body");
        try {
            while (!line.isEmpty()) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (NullPointerException e) {

        }

        output.write(responseHeader);
        output.write(responseDocument);
        output.close();
        input.close();

    }

}
