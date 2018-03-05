package Server;

import java.net.ServerSocket;

public class SingleThreadServer extends SimpleServer {

    private ServerSocket serverSocket;

    @Override
    public void run() {}

    @Override
    public void stop() {
        this.isStopped = false;
    }

    @Override
    public boolean isStopped() {
        return false;
    }
}
