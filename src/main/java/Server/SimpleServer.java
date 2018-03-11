package Server;

import Constants.ServerConfigs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This interface is trying to regulate how servers generally look like.
 */
public abstract class SimpleServer implements HTTPServer {

    protected boolean isStopped;
    protected int socketPort;
    protected ServerSocket serverSocket;
    protected Thread runningThread;

    /**
     * Making sure that the server can not be instantiated without a port.
     * @param socketPort
     */
    public SimpleServer(int socketPort) {
        this.socketPort = socketPort;
        this.isStopped = false;
        this.runningThread = null;
    }

    /**
     * Initialise the server socket on the given port.
     * @param socketPort The port to listen to.
     * @throws RuntimeException Exception when can't listen to the port.
     */
    @Override
    public void initSocket(int socketPort) throws RuntimeException {
        try {
            this.serverSocket = new ServerSocket(socketPort);
        } catch (IOException e) {
            throw new RuntimeException("Can NOT initiate port" + socketPort, e);
        }
    }

    @Override
    public synchronized void stop() throws RuntimeException {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't close the server", e);
        }
    }

    /**
     * Check if running.
     */
    @Override
    public synchronized boolean isStopped() {
        return this.isStopped;
    }
}
