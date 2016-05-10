import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

class ClientCommHandler extends UnicastRemoteObject implements ClientCommInterface {

	private static final long serialVersionUID = 1008837264497538584L;
	public LinkedList<SlaveServiceInterface> slaves = new LinkedList();

	protected ClientCommHandler() throws RemoteException {
	}

	
	@Override
	public void publishProblem(byte[] hash, int problemsize) {
		if (hash==null) System.out.println("Problem is empty!");
		else System.out.println(" Client received new problem of size " + problemsize);
		byte[] currProblem = hash;
		int currProblemSize = problemsize;

		String solution = null;
		long start = System.currentTimeMillis();
		for(int i=0; i<slaves.size(); i++)
		{
			try {
				String s = slaves.get(i).work(currProblem,currProblemSize,slaves.size(),i);
				if(s!=null) solution=s;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(solution);
		System.out.println(System.currentTimeMillis() - start+ "ms");

	}
	@Override
	public void checkSlave(SlaveServiceInterface cs) throws Exception {
		if(slaves.contains(cs)) {
			slaves.remove(cs);
		}
		else{
			slaves.add(cs);
		}
	}
}
