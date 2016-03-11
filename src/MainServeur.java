import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class MainServeur {

	static PlateauJeu plateauServeur;

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		
		System.setSecurityManager(new SecurityManager());

		System.setProperty("java.rmi.server.hostname","10.10.184.132"); 
		LocateRegistry.createRegistry(42000);
		/**
		 * Création du plateau de jeu, puis demande d'initialisation de la position des bateaux
		 */
		plateauServeur = new PlateauJeu();
		boolean continueCreation = true;
		while (continueCreation){
			continueCreation = creationBateau();
		}
		
		
		Game server	=	new Game(plateauServeur); 
		Naming.rebind("rmi://10.10.184.132:42000/ABC",server); 
		
		System.out.println("Votre Plateau est prêt, en attente de connexion d'un autre joueur");
		plateauServeur.afficherPlateau();
		
		
	}
	public static boolean creationBateau(){
		int limiteBateau = plateauServeur.getNbBateau();
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
			boolean retour = plateauServeur.placerBateau(tailleBat,directionBateau,x,y);
			return retour;
		}
		else
			return false;
		
	}
}

