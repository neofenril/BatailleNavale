
public class PlateauJeu {

	public String plateau[][] = new String[10][10];
	public int nbBateau = 0;
	public PlateauJeu() 
	{
		
		for (int i=0;i<10;i++)
		{
			for(int j = 0;j<10;j++)
			{
				plateau[i][j]="[-]";
			}
		}
	}
	public PlateauJeu(PlateauJeu plat)
	{
		for (int i=0;i<10;i++)
		{
			for(int j = 0;j<10;j++)
			{
				plat.plateau[i][j] = plateau[i][j];
			}
		}
	}
	public void afficherPlateau()
{
		System.out.println("  "+"1"+"\t"+" 2"+"\t"+" 3"+"\t"+" 4"+"\t"+" 5"+"\t"+" 6"+"\t"+" 7"+"\t"+" 8"+"\t"+" 9"+"\t"+"10");
		for (int i=0;i<10;i++)
		{
			System.out.print(i+1);
			for(int j = 0;j<10;j++)
			{
				System.out.print(plateau[i][j]+"\t");
			}
			System.out.println("");
		}
	}
	
	public boolean placerBateau(int tailleBateau,String direction,int pos1,int pos2)
	{
		System.out.println("onest dedans "+pos1+pos2+tailleBateau+direction+this.nbBateau);
		if(nbBateau<5)
		{
			
			if(direction.equals("V"))
			{
				if((tailleBateau+pos1)<11)
				{
					for(int i = tailleBateau;i>=0;i--)
					{
						plateau[pos1-1][pos2-1] = "[B]";
						pos1++;
					}
					System.out.println("BATEAU INSERER");
					this.nbBateau++;
				}
			}
			else if(direction.equals("H"))
			{
				if((tailleBateau+pos2)<11)
				{
					for(int i = tailleBateau;i>0;i--)
					{
						plateau[pos1-1][pos2-1] = "[B]";
						pos2++;
					}
					System.out.println("BATEAU INSERER");
					this.nbBateau++;
				}
			}
			return true;
		}
		else
			return false;
	}
	
	
	public boolean Bomb(int x, int y)
	{
		x-=1;
		y-=1;
		if(x>=0 && x<11 && y>=0 && y<11)
		{
			if(plateau[x][y] == "[-]")
			{
				System.out.println("à coté");
				return false;
			}
			else
			{
				if(plateau[x][y] == "[T]")
				{
					System.out.println("Deja touché pd ! rapelle toi de ce que tu fais !");
					return true;
				}
				else
				{
					plateau[x][y] = "[T]";
					return true;
				}
				
			}
				
		}
		else 
			return false;
	}
	public static void main(String[]Args){
		PlateauJeu plateau = new PlateauJeu();
		plateau.placerBateau(4,"H",6,2);
		plateau.placerBateau(4,"V",4,6);
		plateau.placerBateau(4,"V",5,6);
		plateau.Bomb(10,2);
		plateau.afficherPlateau();
	}
	public int getNbBateau() {
		return this.nbBateau;
	}

}
