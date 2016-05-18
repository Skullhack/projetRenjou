package Joueur;

import java.util.GregorianCalendar;
import java.util.Random;

import Controleur.Coordonnees;
import Enum.TypeCase;


public class IA extends Joueur{
	
	protected Random r;
	protected int[][] tabHeuristique;
	protected int nbLigne;
	protected int nbColonne;
	
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
	
	public boolean caseJouable(Coordonnees p) {
		return (m.getRenjou().getPlateauDeJeu().getPlateau()[p.getLigne()][p.getColonne()] == TypeCase.Jouable);

	}

	public void modifierHeristique(Coordonnees p) {
		int i = p.getLigne();
		int j = p.getColonne();
		if (i > 0) {
			if(caseJouable(new Coordonnees(i-1,j))){
				tabHeuristique[i-1][j] ++;
			}
			if(j >0){
				if(caseJouable(new Coordonnees(i-1,j-1))){
					tabHeuristique[i-1][j-1] ++;
				}
			}
			if(j < nbColonne){
				if(caseJouable(new Coordonnees(i-1,j+1))){
					tabHeuristique[i-1][j+1] ++;
				}
			}
		}
		if(i < nbLigne){
			if(caseJouable(new Coordonnees(i+1,j))){
				tabHeuristique[i+1][j] ++;
			}
			if(j >0){
				if(caseJouable(new Coordonnees(i+1,j-1))){
					tabHeuristique[i+1][j-1] ++;
				}
			}
			if(j < nbColonne){
				if(caseJouable(new Coordonnees(i+1,j+1))){
					tabHeuristique[i+1][j+1] ++;
				}
			}
		}
		
		if(j >0){
			if(caseJouable(new Coordonnees(i,j-1))){
				tabHeuristique[i][j-1] ++;
			}
		}
		if(j < nbColonne){
			if(caseJouable(new Coordonnees(i,j+1))){
				tabHeuristique[i][j+1] ++;
			}
		}
	}
	
	public void initHeuristique() {

		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				Coordonnees p = new Coordonnees(i, j);
				if (caseJouable(p)) {
					modifierHeristique(p);
				}
			}
		}

	}

}
