package Server;

import Constants.ServerConfigs;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This interface is trying to regulate how servers generally look like.
 */
public abstract class SimpleServer implements Runnable{

    protected boolean isStopped;
    private ServerSocket serverSocket;

    public SimpleServer() {
        isStopped = false;
    }

    public void initSocket() {
        try {
            serverSocket = new ServerSocket(ServerConfigs.PORT);
        } catch (IOException e) {
            throw new RuntimeException("Can NOT initiate port" + ServerConfigs.PORT, e);
        }
    }

    /**
     * Runs the server.
     */
    public abstract void run();

    /**
     * Stops the server.
     */
    public abstract void stop();

    /**
     * Check if running.
     */
    public boolean isStopped() {
        return this.isStopped;
    }
}
