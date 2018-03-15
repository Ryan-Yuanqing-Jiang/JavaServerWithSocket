package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public interface HTTPServer extends Runnable {
    /**
     * Runs the server.
     */
    void run();

    /**
     * Stops the server.
     */
    void stop() throws IOException;

    boolean isStopped();
}
