import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class MainClient {

	static PlateauJeu plateauClient;

	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		System.setSecurityManager(new SecurityManager());
		Scanner	s	=	new Scanner(System.in); 
		System.out.println("Appuyer sur entrer pour créer votre plateau");
		//String	name=	s.nextLine().trim();
		// Création d'un plateau que je vais affecter au Game
		plateauClient = new PlateauJeu();
		boolean continueCreation = true;
		while (continueCreation){
			continueCreation = creationBateau();
		}
		GameInterface client = new Game(plateauClient); 
		GameInterface server = (GameInterface)Naming.lookup("rmi://10.10.184.132:42000/ABC"); 
		client.setAdversaire(server);
		server.setAdversaire(client); 
		server.aToi();

	}

	public static boolean creationBateau(){
		int limiteBateau = plateauClient.getNbBateau();
		if (limiteBateau < 1)
		{
			System.out.println("Selectionnés la taille de votre bateau (on évite de tricher, entre 3 et 5 svp !!) "); 
			Scanner	taille	= new Scanner(System.in); 
			int tailleBat = taille.nextInt();
			System.out.println("Voulez vous le place à la verticale ou à l'horizontale ? (H/V)");
			Scanner direction = new Scanner(System.in);
			String directionBateau = direction.nextLine();
			System.out.println("Selectionné le point de départ de la position de votre bateau");
			Scanner position = new Scanner(System.in);
			int x = position.nextInt();
			int y = position.nextInt();
			boolean retour = plateauClient.placerBateau(tailleBat,directionBateau,x,y);
			return retour;
		}
		else
			return false;
	}

}

