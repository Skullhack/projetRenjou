package Joueur;

import java.util.GregorianCalendar;
import java.util.Random;

import Controleur.Moteur;
import Controleur.MoteurObserveur;
import Enum.TypeCase;
import Enum.TypeCouleur;
import Enum.TypeJoueur;
import Utilitaire.Coordonnees;
import Utilitaire.Log;
import Utilitaire.PlateauDeJeu;


public class IA extends Joueur {
	
	protected Random r;
	protected int[][] tabHeuristique;
	protected int nbLigne;
	protected int nbColonne;
	
	
    public IA(TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
        super(type, nbPion, couleurJoueur);
		java.util.GregorianCalendar calendar = new GregorianCalendar();
		r = new Random(calendar.getTimeInMillis());
		init();
	}
	
    private void init(){
		nbLigne = 15;
		nbColonne = 15;
		tabHeuristique = new int[nbLigne][nbColonne];
		for(int i=0; i< nbLigne; i++){
			for(int j=0; j< nbColonne; j++){
				tabHeuristique[i][j] = 0;
			}
		}	
    	
    }
    public void setSeed(int seed){
    	r = new Random(seed);
    }
	public boolean caseJouable(Coordonnees p, PlateauDeJeu plateau) {
		return (plateau.getPlateau()[p.getLigne()][p.getColonne()] == TypeCase.Jouable);

	}
	
	public boolean premierTour(PlateauDeJeu pdj){
		return pdj.getNbPionBlanc() == 0 && pdj.getNbPionNoir() == 0;
	}
	
	public boolean secondTour(PlateauDeJeu pdj){
		return pdj.getNbPionBlanc() == 0 && pdj.getNbPionNoir() == 1;
	}
	
	public void modifierHeristique(Coordonnees p, PlateauDeJeu plateau) {
		Log.print(15,"Dans modifier Heuristique");

		int i = p.getLigne();
		int j = p.getColonne();
		if (i > 0) {
			if(caseJouable(new Coordonnees(i-1,j), plateau)){
				tabHeuristique[i-1][j] ++;
			}
			if(j >0){
				if(caseJouable(new Coordonnees(i-1,j-1), plateau)){
					tabHeuristique[i-1][j-1] ++;
				}
			}
			if(j < nbColonne-1){
				if(caseJouable(new Coordonnees(i-1,j+1), plateau)){
					tabHeuristique[i-1][j+1] ++;
				}
			}
		}
		if(i < nbLigne-1){
			if(caseJouable(new Coordonnees(i+1,j), plateau)){
				tabHeuristique[i+1][j] ++;
			}
			if(j >0){
				if(caseJouable(new Coordonnees(i+1,j-1), plateau)){
					tabHeuristique[i+1][j-1] ++;
				}
			}
			if(j < nbColonne-1){
				if(caseJouable(new Coordonnees(i+1,j+1), plateau)){
					tabHeuristique[i+1][j+1] ++;
				}
			}
		}
		
		if(j >0){
			if(caseJouable(new Coordonnees(i,j-1), plateau)){
				tabHeuristique[i][j-1] ++;
			}
		}
		if(j < nbColonne-1){
			if(caseJouable(new Coordonnees(i,j+1), plateau)){
				tabHeuristique[i][j+1] ++;
			}
		}
	}
	
	public String afficherTabHeuristique(){
		String buff = "";
		for(int i=0; i<13; i++){
			for(int j=0; j<13; j++){
				if(tabHeuristique[i][j] == -1)
					buff += "-";
				else
					buff += tabHeuristique[i][j];
			}
			buff += "\n";
		}
		return buff;
	}
	
	public void initHeuristique(PlateauDeJeu plateau) {
		Log.print(15,"Dans init Heuristique");

		for(int i=0; i< nbLigne; i++){
			for(int j=0; j< nbColonne; j++){
				tabHeuristique[i][j] = 0;
			}
		}
		
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				Coordonnees p = new Coordonnees(i, j);
				if ((plateau.getPlateau()[i][j] == TypeCase.PionBlanc) || (plateau.getPlateau()[i][j] == TypeCase.PionNoir)) {
					modifierHeristique(p, plateau);
				}
			}
		}

	}

}
