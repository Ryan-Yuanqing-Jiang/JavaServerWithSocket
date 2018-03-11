package Server;

import java.net.ServerSocket;
import java.net.Socket;

public interface HTTPServer extends Runnable {

    void initSocket(int socketPort) throws RuntimeException;

    /**
     * Runs the server.
     */
    void run();

    /**
     * Stops the server.
     */
    void stop();

    boolean isStopped();
}
