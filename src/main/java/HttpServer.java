import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class HttpServer {
    public static void main(String[] args) throws Exception {
        final ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080 ....");
        while (true){
            try {
                Socket clientSocket = server.accept();

                InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                String line = reader.readLine();

                try {
                    while (!line.isEmpty()) {
                        System.out.println(line);
                        line = reader.readLine();
                    }
                } catch (NullPointerException e) {

                } finally {
                    Date today = new Date();
                    String dayStr = "<h1>" + today.toString() + "</h1>";
                    String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + dayStr;
                    clientSocket.getOutputStream().write(httpResponse.getBytes("UTF-8"));

                    clientSocket.close();
                }
            } catch (IOException e) {
                e.getStackTrace();
                System.out.println(e.getCause());
            } finally {

            }
        }
    }
}
