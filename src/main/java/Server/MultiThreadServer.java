package Server;

import Controller.HTTPReceiver;

import java.io.IOException;
import java.net.Socket;

public class MultiThreadServer extends SimpleServer {

    public MultiThreadServer(int socketPort) {
        super(socketPort);
    }

    @Override
    public void run() {
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        initSocket(this.socketPort);
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            new Thread(
                    new HTTPReceiver(
                            clientSocket)
            ).start();
        }
        System.out.println("Server Stopped.") ;
    }
}
