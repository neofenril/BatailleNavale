import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameInterface extends Remote {

	// public PlateauJeu getPlateau() throws RemoteException;
	
	public void sendBomb (int x, int y) throws RemoteException;
	
	public void setAdversaire(GameInterface g) throws RemoteException;
	
	public  PlateauJeu getPlateau() throws RemoteException;
	
	public GameInterface getPlateauAdversaire() throws RemoteException;

	public void aToi() throws RemoteException;
}
