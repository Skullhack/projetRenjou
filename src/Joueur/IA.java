package Joueur;

import java.util.GregorianCalendar;
import java.util.Random;

import Controleur.Coordonnees;
import Controleur.Moteur;
import Controleur.MoteurObserveur;
import Controleur.PlateauDeJeu;
import Enum.TypeCase;
import Enum.TypeCouleur;
import Enum.TypeJoueur;


public class IA extends Joueur {
	
	protected Random r;
	protected int[][] tabHeuristique;
	protected int nbLigne;
	protected int nbColonne;
	
    public IA(Moteur moteur, TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
        super(moteur, type, nbPion, couleurJoueur);
		java.util.GregorianCalendar calendar = new GregorianCalendar();
		r = new Random(calendar.getTimeInMillis());
		nbLigne = m.getRenjou().getPlateauDeJeu().getLignes();
		nbColonne = m.getRenjou().getPlateauDeJeu().getColonnes();
		tabHeuristique = new int[nbLigne][nbColonne];
		for(int i=0; i< nbLigne; i++){
			for(int j=0; j< nbColonne; j++){
				tabHeuristique[i][j] = 0;
			}
		}		
	}
	

	public boolean caseJouable(Coordonnees p, PlateauDeJeu plateau) {
		return (plateau.getPlateau()[p.getLigne()][p.getColonne()] == TypeCase.Jouable);

	}

	public void modifierHeristique(Coordonnees p, PlateauDeJeu plateau) {
		m.printTrace(15,"Dans modifier Heuristique");

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
		m.printTrace(15,"Dans init Heuristique");

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
