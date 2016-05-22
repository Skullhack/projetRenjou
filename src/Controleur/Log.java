package Controleur;

import java.io.File;
import java.io.FileWriter;

public class Log implements java.io.Serializable {

	int plageBasse;
	int plageHaute;
    FileWriter fw;

	public Log(){
		plageBasse = 0;
		plageHaute = 0;
		try{
			new File ("Logs.txt").delete();
			fw = new FileWriter (new File ("Logs.txt"), true);
			
		}catch(Exception e){
			System.out.println(e);
		}
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
		if(niveau >= plageBasse && niveau <= plageHaute){
			try{
			fw.write(message+"\n");
			fw.flush();
			}catch(Exception e){
				System.out.println(e);
			}
			System.out.println(message);
		}
	}
	
}
