	import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Game extends UnicastRemoteObject implements GameInterface {
	
	static private PlateauJeu plateau;
	private GameInterface plateauAdversaire=null;
	
	public Game(PlateauJeu plateau)throws RemoteException {
		this.plateau = plateau;
	}
	public PlateauJeu getPlateau() throws RemoteException {
		return this.plateau;
	}
	@Override
	public void sendBomb(int x, int y) throws RemoteException {
		
		boolean touche = plateau.Bomb(x,y);
		if(touche)
			System.out.println("Touché ma guieule :-)");
		else
			System.out.println("Noob !");
		
	}
	@Override
	public void setAdversaire(GameInterface g) throws RemoteException {
		plateauAdversaire = g;
		
	}
	
	public void aToi() throws RemoteException{
		getPlateau().afficherPlateau();
		System.out.println("Entrez la position de larguage de bombe Commandant ! ");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		getPlateauAdversaire().sendBomb(x,y);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getPlateauAdversaire().aToi();
		
		
	}
	
	public GameInterface getPlateauAdversaire() throws RemoteException {
		return plateauAdversaire;
	}


	

}
