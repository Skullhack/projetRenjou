package Utilitaire;

import java.io.File;
import java.io.FileWriter;


/*
 * Cette classe est notre classe de trace
 */
public class Log implements java.io.Serializable {

	static int plageBasse;
	static int plageHaute;
    static FileWriter fw;

	private Log(){
		plageBasse = 0;
		plageHaute = 0;
		try{
			new File ("Logs.txt").delete();
			fw = new FileWriter (new File ("Logs.txt"), true);
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void setNiveau(int PlageHaute){
		plageBasse = 0;
		plageHaute = PlageHaute;
	}
	
	public static void setPlage(int PlageBasse, int PlageHaute){
		plageBasse = PlageBasse;
		plageHaute = PlageHaute;
	}
	
	public static void print(int niveau, String message){
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
