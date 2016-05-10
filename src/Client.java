import java.rmi.Naming;
import java.security.MessageDigest;
import java.util.Arrays;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    static ServerCommInterface sci;
    static String teamName = "MD5nack";
    static int slaveCnt=2;

	public static void main(String[] args) throws Exception {

        String ownip = "localhost";
        String serverip = "localhost";
        String ip = "localhost";

        System.setProperty("java.security.policy","file:security.policy");
        System.setProperty("java.rmi.server.hostname", ownip);
        System.setSecurityManager(new SecurityManager());

        Registry reg = LocateRegistry.createRegistry(1100);
        ClientCommHandler cch = new ClientCommHandler();
        Naming.bind("rmi://localhost/client", cch);



        // This is crucial, otherwise the RPis will not be reachable from the server.

        System.out.println("Waiting for " + slaveCnt + " slaves");
    /*

        // Lookup the server
        System.out.println("Listens on IP " + ownip + " for server callback.");
        sci = (ServerCommInterface)Naming.lookup("rmi://" + serverip + "/server");
        // Create a communication handler and register it with the server
        // The communication handler is the object that will receive the tasks from the server
        System.out.println("Client registers with the server");
        sci.register(teamName, cch);*/
    }
}
