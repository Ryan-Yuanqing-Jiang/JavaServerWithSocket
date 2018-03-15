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
    public SimpleServer(int socketPort) throws IOException {
        this.socketPort = socketPort;
        this.isStopped = false;

        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        this.serverSocket = new ServerSocket(this.socketPort);
    }

    @Override
    public synchronized void stop() throws IOException {
        this.isStopped = true;
        this.serverSocket.close();
    }

    /**
     * Check if running.
     */
    @Override
    public synchronized boolean isStopped() {
        return this.isStopped;
    }
}
