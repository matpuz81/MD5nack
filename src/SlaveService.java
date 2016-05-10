import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SlaveService extends UnicastRemoteObject implements SlaveServiceInterface {
    MessageDigest md;
    protected SlaveService() throws RemoteException, NoSuchAlgorithmException {

        md = MessageDigest.getInstance("MD5");
    }
    @Override
    public String work(byte[] problem, int size, int mod, int offset) throws Exception {
        // Then bruteforce try all integers till problemsize
        System.out.println("Starting");
        for (Integer i=offset; i<=size; i+=mod) {
            // Calculate their hash
            byte[] currentHash = md.digest(i.toString().getBytes());
            // If the calculated hash equals the one given by the server, submit the integer as solution
            if (Arrays.equals(currentHash, problem)) {
                System.out.println("client submits solution");
                return i.toString();
            }
        }
        System.out.println("No solution");
        return null;
    }
}


interface SlaveServiceInterface extends Remote {
    String work(byte[] problem, int size, int mod, int offset) throws Exception;
}