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


public class IA extends Joueur implements MoteurObserveur{
	
	protected Random r;
	protected int[][] tabHeuristique;
	protected int nbLigne;
	protected int nbColonne;
	
    public IA(Moteur moteur, TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
        super(moteur, type, nbPion, couleurJoueur);
        moteur.enregistrerObserveur(this);
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
			if(j < nbColonne){
				if(caseJouable(new Coordonnees(i-1,j+1), plateau)){
					tabHeuristique[i-1][j+1] ++;
				}
			}
		}
		if(i < nbLigne){
			if(caseJouable(new Coordonnees(i+1,j), plateau)){
				tabHeuristique[i+1][j] ++;
			}
			if(j >0){
				if(caseJouable(new Coordonnees(i+1,j-1), plateau)){
					tabHeuristique[i+1][j-1] ++;
				}
			}
			if(j < nbColonne){
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
		if(j < nbColonne){
			if(caseJouable(new Coordonnees(i,j+1), plateau)){
				tabHeuristique[i][j+1] ++;
			}
		}
	}
	
	public void afficherTabHeuristique(){
		for(int i=0; i<13; i++){
			for(int j=0; i<13; i++){
				System.out.print(tabHeuristique[i][j]);
			}
			System.out.println();
		}
	}
	
	public void initHeuristique(PlateauDeJeu plateau) {

		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				Coordonnees p = new Coordonnees(i, j);
				if (caseJouable(p, plateau)) {
					m.printTrace(9,"appel a modifier Heuristique");
					modifierHeristique(p, plateau);
				}
			}
		}

	}

	@Override
	public void actualiser() {
		// TODO Auto-generated method stub
		//doit lancer jouer()
		m.printTrace(3, "IA notifiee");

	}

}
