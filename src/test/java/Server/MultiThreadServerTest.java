package Server;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MultiThreadServerTest {

    HTTPServer server;
    final int HTTP_PORT = 8888;

    @Before
    public void setUp() throws IOException {
        System.out.println("Testing Starts --- " + HTTP_PORT);
        server = new MultiThreadServer(HTTP_PORT);
    }

//    @Test
//    public void run() {
//    }
//
//    @Test
//    public void startThread() {
//
//    }

    @Test
    public void stop() throws IOException {
        server.stop();
        assertEquals(true, server.isStopped());
    }

    @Test
    public void isStopped() throws IOException {
        assertEquals(false, server.isStopped());
        server.stop();
        assertEquals(true, server.isStopped());
    }
}