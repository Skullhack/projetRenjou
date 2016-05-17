package Joueur;

import java.util.GregorianCalendar;
import java.util.Random;

public class IA extends Joueur{
	
	Random r;
	
	public IA(){
		java.util.GregorianCalendar calendar = new GregorianCalendar();
		r = new Random(calendar.getTimeInMillis());
	}
		
	
	
}
