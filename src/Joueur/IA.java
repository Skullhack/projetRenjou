package Joueur;

import java.util.GregorianCalendar;
import java.util.Random;


public class IA extends Joueur{
	
	protected Random r;
	protected int[][] tabHeuristique;
	
	public IA(){
		super();
		java.util.GregorianCalendar calendar = new GregorianCalendar();
		r = new Random(calendar.getTimeInMillis());
		tabHeuristique = new int[13][13];
		for(int i=0; i<13; i++){
			for(int j=0; i<13; i++)
				tabHeuristique[i][j] = 0;
		}
	}
}
