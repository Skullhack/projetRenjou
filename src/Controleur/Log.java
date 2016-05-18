package Controleur;

public class Log {

	int niveau;
	
	public Log(){
		niveau = 0;
	}
	
	public void setNiveau(int n){
		niveau = n;
	}
	
	public void print(int niveau, String message){
		if(niveau <= this.niveau)
			System.out.println(message);
	}
	
}
