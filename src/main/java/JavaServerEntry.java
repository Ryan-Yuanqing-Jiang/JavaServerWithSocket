import Constants.ServerConfigs;
import Server.HTTPServer;
import Server.MultiThreadServer;

public class JavaServerEntry {
    public static void main(String[] args) throws Exception {
        HTTPServer server = new MultiThreadServer(ServerConfigs.PORT);
        new Thread(server).start();
    }
}
