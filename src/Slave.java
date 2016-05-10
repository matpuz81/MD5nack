import java.rmi.Naming;
import java.security.MessageDigest;
import java.util.Arrays;

public class Slave {

    public static void main(String[] args) throws Exception {

        String ownip="localhost";
        String ip="localhost";

        System.out.println("Slave starting, listens on IP " +ownip + " for server callback.");

        // This is crucial, otherwise the RPis will not be reachable from the server.
        System.setProperty("java.rmi.server.hostname", ownip);

        // Lookup the server
        ClientCommInterface cci = (ClientCommInterface)Naming.lookup("rmi://" + ip + "/client");

        // Create a communication handler and register it with the server
        // The communication handler is the object that will receive the tasks from the server
        SlaveService ss = new SlaveService();
        System.out.println("Client registers with the server");
        cci.checkSlave(ss);


    }
}
