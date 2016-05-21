package Controleur;

public class Log implements java.io.Serializable {

	int plageBasse;
	int plageHaute;
	
	public Log(){
		plageBasse = 0;
		plageHaute = 0;
	}
	
	public void setNiveau(int plageHaute){
		plageBasse = 0;
		this.plageHaute = plageHaute;
	}
	
	public void setPlage(int plageBasse, int plageHaute){
		this.plageBasse = plageBasse;
		this.plageHaute = plageHaute;
	}
	
	public void print(int niveau, String message){
		if(niveau >= plageBasse && niveau <= plageHaute)
			System.out.println(message);
	}
	
}
