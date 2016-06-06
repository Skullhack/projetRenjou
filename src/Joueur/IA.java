package Joueur;

import java.awt.Point;
import java.util.GregorianCalendar;
import java.util.Random;
import Enum.TypeCase;
import Enum.TypeCouleur;
import Enum.TypeJoueur;
import Utilitaire.Coordonnees;
import Utilitaire.Log;
import Utilitaire.PlateauDeJeu;


public class IA extends Joueur {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public boolean PartieFinie(PlateauDeJeu pdj, int l, int c, TypeCase tc) {


		// diago hautgauchebasdroit
		int somme = 0;
		int i = l;
		int j = c;
		// Log.print(66, "partiefinie diago i= " + i + " j= " + j + " somme= "
		// +somme);
		while (i > 0 && j > 0 && pdj.getTypeCaseTableau(--i, --j) == tc) {
			somme++;
			// Log.print(66, "partiefinie diago i= " + i + " j= " + j + " somme=
			// " +somme);
		}
		i = l;
		j = c;
		// Log.print(66, "partiefinie diago i= " + i + " j= " + j + " somme= "
		// +somme);
		while (i < pdj.getLignes() - 1 && j < pdj.getColonnes() - 1
				&& pdj.getTypeCaseTableau(++i, ++j) == tc) {
			somme++;
			// Log.print(66, "partiefinie diago i= " + i + " j= " + j + " somme=
			// " +somme);
		}

		if (somme >= 4)
			return true;

		// diago hautdroitbasgauche
		somme = 0;
		i = l;
		j = c;
		while (i > 0 && j < pdj.getColonnes() - 1 && pdj.getTypeCaseTableau(--i, ++j) == tc) {
			somme++;
		}
		i = l;
		j = c;
		while (i < pdj.getLignes() - 1 && j > 0 && pdj.getTypeCaseTableau(++i, --j) == tc) {
			somme++;
		}

		if (somme >= 4)
			return true;

		// horizontal
		somme = 0;
		i = l;
		j = c;
		while (j < pdj.getColonnes() - 1 && pdj.getTypeCaseTableau(i, ++j) == tc) {
			somme++;
		}
		i = l;
		j = c;
		while (j > 0 && pdj.getTypeCaseTableau(i, --j) == tc) {
			somme++;
		}

		if (somme >= 4)
			return true;

		// vertical
		somme = 0;
		i = l;
		j = c;
		// Log.print(66, "partiefinie vertical i= " + i + " j= " + j + " somme=
		// " +somme);
		while (i > 0 && pdj.getTypeCaseTableau(--i, j) == tc) {
			somme++;
			// Log.print(66, "partiefinie vertical i= " + i + " j= " + j + "
			// somme= " +somme);
		}
		i = l;
		j = c;
		// Log.print(66, "partiefinie vertical i= " + i + " j= " + j + " somme=
		// " +somme);
		while (i < pdj.getLignes() - 1 && pdj.getTypeCaseTableau(++i, j) == tc) {
			somme++;
			// Log.print(66, "partiefinie vertical i= " + i + " j= " + j + "
			// somme= " +somme);
		}

		if (somme >= 4)
			return true;
		// Log.print(6, c+ " ne donne pas lieu a une partie finie");
		return false;
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
	
	public boolean EstJouable(PlateauDeJeu pdj, Coordonnees c, int distanceMax) {
		return EstJouable(pdj, c.getLigne(), c.getColonne(), distanceMax);
	}
	
	public boolean EstJouable(PlateauDeJeu pdj, int l, int c, int distanceMax) {
		if (EstBlancOuNoir(pdj, l,c))
			return false;

		for (int i = l - distanceMax; i <= l + distanceMax; i++) {
			for (int j = c - distanceMax; j <= c + distanceMax; j++) {
				if (i >= 0 && i <= nbLigne - 1 && j >= 0 && j <= nbColonne - 1
						&& (l != i || c != j)) {
					// Log.print(11, "i=" + i + " j=" +j + " c= " + c );
					if (EstBlancOuNoir(pdj, i,j)) {
						// Log.print(1, "est un pion!" );
						return true;
					}
				}
			}
		}

		return false;
	}
	
	public boolean EstBlancOuNoir(PlateauDeJeu pdj, Coordonnees c) {
		// Log.print(66, "Dans EstBlancOuNoir => " + pdj.getTypeCaseTableau(c));
		return EstBlancOuNoir(pdj, c.getLigne(), c.getColonne());
	}
	
	public boolean EstBlancOuNoir(PlateauDeJeu pdj, int l, int c) {
		// Log.print(66, "Dans EstBlancOuNoir => " + pdj.getTypeCaseTableau(c));
		return pdj.getTypeCaseTableau(l,c) == TypeCase.PionBlanc || pdj.getTypeCaseTableau(l,c) == TypeCase.PionNoir;
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

	@Override
	public Point play(int[][] plateau, int couleurJoueur, boolean tabou3x3, boolean tabou4x4, boolean tabouOverline){
		initPlateauEtCouleur(plateau, couleurJoueur);
		Coordonnees c = jouer(pdj);
		return new Point(c.getColonne(), c.getLigne());
	}
	

	@Override
	public Coordonnees jouer(PlateauDeJeu pdj) {
		// TODO Auto-generated method stub
		return null;
	}

}
