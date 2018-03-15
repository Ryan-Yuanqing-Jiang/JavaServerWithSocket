package Server;

import Controller.HTTPReceiver;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MultiThreadServer extends SimpleServer {

    private Map<Long, Thread> threadMap = new HashMap<>();

    public MultiThreadServer(int socketPort) throws IOException {
        super(socketPort);
    }

    @Override
    public void run() {
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }

        while(!isStopped()){
            try {
                Socket clientSocket = this.serverSocket.accept();
                this.startThread(clientSocket);
            } catch (IOException e) {
                System.err.println("Terminating - I/O error occurred when waiting for a connection: \n");
                e.getStackTrace();
                return;
            }
        }

        System.out.println("Server Stopped.") ;
    }

    /**
     * Start a thread and register it in the Map
     * @param clientSocket The client socket being connected.
     */
    public void startThread(Socket clientSocket) {
        if (clientSocket.isConnected()) {
            Thread threadToStart = new Thread(new HTTPReceiver(clientSocket));
            threadToStart.start();
            this.threadMap.put(threadToStart.getId(), threadToStart);
        }
    }
}
