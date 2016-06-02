package Utilitaire;

import Controleur.*;
import Enum.*;

/*
 * Cette classe permet d'avoir des informations sur lorganisation des pions autour d'une coordonnee
 */

public class InfosAlignement implements java.io.Serializable {

	int nbNoir;
	int nbBlanc;	
	int nbNoirNonContinu;
	int nbBlancNonContinu;
	boolean libreNoir;
	boolean libreBlanc;
	boolean libre2casesNoir;
	boolean libre2casesBlanc;
	private int ligne;
	private int colonne;
	private TypeDirection direction;
	int nbLignes;
	int nbColonnes;

	public InfosAlignement() {
	
		nbNoir = 0;
		nbBlanc = 0;
		libreNoir = false;
		libreBlanc = false;
		
	}
	
	public InfosAlignement(PlateauDeJeu pdj, Coordonnees c, TypeDirection td) {
		ligne = c.getLigne();
		colonne = c.getColonne();
		direction = td;
		nbLignes = 15;
		nbColonnes = 15;
		entreeAutomate(pdj);		
	}

	public InfosAlignement(TypeDirection td) {
		ligne = 0;
		colonne = 0;
		direction = td;
		nbLignes = 15;
		nbColonnes = 15;
	}

	
	
	private boolean incremente(){
		switch(direction){
		case Gauche:
			colonne--;
			return colonne >= 0;
		case Droite:
			colonne++;
			return colonne < nbColonnes;
		case Haut:
			ligne--;
			return ligne >= 0;
		case Bas:
			ligne++;
			return ligne < nbLignes;		
		case DiagonaleHautGauche:
			ligne--;
			colonne--;
			return ligne >= 0 && colonne >= 0;
		case DiagonaleHautDroite:
			ligne--;
			colonne++;
			return ligne >= 0 && colonne < nbColonnes;
		case DiagonaleBasGauche:
			ligne++;
			colonne--;
			return ligne < nbLignes && colonne >= 0;
		case DiagonaleBasDroite:
			ligne++;
			colonne++;
			return ligne < nbLignes && colonne < nbColonnes;
		default:
			return false;
		}

	}
	
	public void setInfoAlignement(PlateauDeJeu pdj, int l, int c){
		ligne = l;
		colonne = c;
		entreeAutomate(pdj);
		
	}

	public void entreeAutomate(PlateauDeJeu pdj){
		Log.print(795, "Dans entreeAutomate");
		
		nbNoir = 0;
		nbBlanc = 0;
		nbNoirNonContinu = 0;
		nbBlancNonContinu = 0;
		libreNoir = true;
		libreBlanc = true;
		libre2casesNoir = true;
		libre2casesBlanc = true;
		boolean bordure = !incremente();

		if(bordure){
			libreNoir = false;
			libreBlanc = false;
			libre2casesNoir = false;
			libre2casesBlanc = false;
			noirEtBlancBloque1Case(pdj);
		}else{
			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.PionNoir){
				nbNoir++;
				libreBlanc = false;
				libre2casesBlanc = false;
				noirContinu(pdj);
			}else if( tc == TypeCase.PionBlanc){
				nbBlanc++;
				libreNoir = false;
				libre2casesNoir = false;
				blancContinu(pdj);
			}else if(tc == TypeCase.Jouable){
				libreBlanc = true;
				libreNoir = true;
				noirEtBlancVide(pdj);
			}else{
				Log.print(795, "Dans entree automate: Cas impossible?");
			}
		}
	}
	
	private void blancContinu(PlateauDeJeu pdj) {
		Log.print(795, "Dans blancContinu");

		boolean bordure = !incremente();
		
		if(bordure){
			libreBlanc = false;
			libre2casesBlanc = false;
			blancBloque1Case(pdj);
		}else{
			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.PionNoir){
				libreBlanc = false;
				libre2casesBlanc = false;
				blancBloque1Case(pdj);
			}else if( tc == TypeCase.PionBlanc){
				nbBlanc++;
				blancContinu(pdj);
			}else if(tc == TypeCase.Jouable){
				blancVide(pdj);
			}else{
				Log.print(795, "Dans : Cas impossible?");
			}
		}
	}
	private void noirContinu(PlateauDeJeu pdj) {
		Log.print(795, "Dans noirContinu");

		boolean bordure = !incremente();
		
		if(bordure){
			libreNoir = false;
			libre2casesNoir = false;
			noirBloque1Case(pdj);
		}else{
			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.PionNoir){
				nbNoir++;
				noirContinu(pdj);
			}else if( tc == TypeCase.PionBlanc){
				libreNoir = false;
				libre2casesNoir = false;
				noirBloque1Case(pdj);
			}else if(tc == TypeCase.Jouable){
				libreNoir = true;
				noirVide(pdj);
			}else{
			Log.print(795, "Dans : Cas impossible?");
			}
		}
	}

	private void blancVide(PlateauDeJeu pdj) {
		Log.print(795, "Dans blancVide");
		boolean bordure = !incremente();
		if(bordure){
			libre2casesBlanc = false;
			blancBloque2Case(pdj);
		}else{
			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.PionNoir){
				libre2casesBlanc = false;
				noirBloque2Case(pdj);
			}else if( tc == TypeCase.PionBlanc){
				nbBlancNonContinu++;
				libreBlanc = false;
				libre2casesNoir = false;
				noirNonContinu(pdj);
			}else if(tc == TypeCase.Jouable){
				libre2casesBlanc = true;
				noirLibre2Case(pdj);
			}else{
				Log.print(795, "Dans : Cas impossible?");
			}
		}
	}
	private void noirVide(PlateauDeJeu pdj) {
		Log.print(795, "Dans noirVide");
		boolean bordure = !incremente();
		if(bordure){
			libre2casesNoir = false;
			noirBloque2Case(pdj);
		}else{
			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.PionNoir){
				nbNoirNonContinu++;
				libreNoir = false;
				libre2casesBlanc = false;
				noirNonContinu(pdj);
			}else if( tc == TypeCase.PionBlanc){
				libre2casesNoir = false;
				noirBloque2Case(pdj);
			}else if(tc == TypeCase.Jouable){
				libre2casesNoir = true;
				noirLibre2Case(pdj);
			}else{
				Log.print(795, "Dans : Cas impossible?");
			}
		}	
	}

	private void blancNonContinu(PlateauDeJeu pdj) {
		Log.print(795, "Dans blancNonContinu");
		boolean bordure = !incremente();
		if(bordure){
			libreBlanc = true;
			libre2casesBlanc = false;
			blancBloque1Case(pdj);
		}else{
			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.PionNoir){
				libreBlanc = true;
				libre2casesBlanc = false;
				blancBloque1Case(pdj);
			}else if( tc == TypeCase.PionBlanc){
				nbBlancNonContinu++;
				blancNonContinu(pdj);
			}else if(tc == TypeCase.Jouable){
				libreBlanc = true;
				libre2casesBlanc = true;
				blancLibre(pdj);
			}else{
				Log.print(795, "Dans : Cas impossible?");
			}
		}
	}
	private void noirNonContinu(PlateauDeJeu pdj) {
		Log.print(795, "Dans noirNonContinu");
		boolean bordure = !incremente();
		if(bordure){
			libreNoir = false;
			libre2casesNoir = false;
			noirBloque1Case(pdj);
		}else{
			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.PionNoir){
				nbNoirNonContinu++;
				libreNoir = false;
				noirNonContinu(pdj);
			}else if( tc == TypeCase.PionBlanc){
				libreNoir = false;
				libre2casesNoir = false;
				noirBloque1Case(pdj);
			}else if(tc == TypeCase.Jouable){
				libreNoir = true;
				libre2casesNoir = false;
				noirLibre(pdj);
			}else{
				Log.print(795, "Dans : Cas impossible?");
			}
		}		
	}

	private void noirLibre(PlateauDeJeu pdj) {
		Log.print(795, "Dans noirLibre");
		boolean bordure = !incremente();
		if(bordure){
			libre2casesNoir = false;
			noirBloque1Case(pdj);
		}else{
			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.PionNoir){
				libre2casesNoir = true;
				noirLibre2Case(pdj);
			}else if( tc == TypeCase.PionBlanc){
				libre2casesNoir = false;
				noirBloque2Case(pdj);
			}else if(tc == TypeCase.Jouable){
				libre2casesNoir = true;
				noirLibre2Case(pdj);
			}else{
				Log.print(795, "Dans : Cas impossible?");
			}
		}	
		
	}
	private void blancLibre(PlateauDeJeu pdj) {
		Log.print(795, "Dans blancLibre");
		boolean bordure = !incremente();
		if(bordure){
			libre2casesBlanc = false;
			blancBloque1Case(pdj);
		}else{
			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.PionNoir){
				libre2casesBlanc = false;
				blancBloque2Case(pdj);
			}else if( tc == TypeCase.PionBlanc){
				libre2casesBlanc = true;
				blancLibre2Case(pdj);
			}else if(tc == TypeCase.Jouable){
				libre2casesBlanc = true;
				blancLibre2Case(pdj);
			}else{
				Log.print(795, "Dans : Cas impossible?");
			}
		}	
		
	}

	private void blancLibre2Case(PlateauDeJeu pdj) {
		Log.print(795, "Dans blancLibre2Case");
		sortieAutomate(pdj);
	}
	private void noirLibre2Case(PlateauDeJeu pdj) {
		Log.print(795, "Dans noirLibre2Case");
		sortieAutomate(pdj);
	}
	
	private void blancBloque1Case(PlateauDeJeu pdj) {
		Log.print(795, "Dans blancBloque1Case");
		sortieAutomate(pdj);	
	}
	private void noirBloque1Case(PlateauDeJeu pdj) {
		Log.print(795, "Dans noirBloque1Case");
		sortieAutomate(pdj);
	}
	
	private void blancBloque2Case(PlateauDeJeu pdj) {
		Log.print(795, "Dans blancBloque2Case");
		sortieAutomate(pdj);		
	}
	private void noirBloque2Case(PlateauDeJeu pdj) {
		Log.print(795, "Dans noirBloque2Case");
		sortieAutomate(pdj);		
	}

	private void noirEtBlancBloque1Case(PlateauDeJeu pdj) {
		Log.print(795, "Dans noirEtBlancBloque1Case");
		sortieAutomate(pdj);
		
	}
	private void noirEtBlancVide(PlateauDeJeu pdj) {
		Log.print(795, "Dans noirEtBlancVide");
		boolean bordure = !incremente();
		if(bordure){
			libre2casesNoir = false;
			libre2casesBlanc = false;
			noirEtBlancBloque1Case(pdj);
		}else{
			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.PionNoir){
				nbNoirNonContinu++;
				libreNoir = false;
				libre2casesBlanc = false;
				noirNonContinu(pdj);
			}else if( tc == TypeCase.PionBlanc){
				nbBlancNonContinu++;
				libreBlanc = false;
				libre2casesNoir = false;
				blancNonContinu(pdj);
			}else if(tc == TypeCase.Jouable){
				libre2casesNoir = true;
				libre2casesBlanc = true;
				noirEtBlancLibre(pdj);
			}else{
				Log.print(795, "Dans : Cas impossible?");
			}
		}
	}
	private void noirEtBlancLibre(PlateauDeJeu pdj) {
		Log.print(795, "Dans noirEtBlancLibre");
		sortieAutomate(pdj);
	}

	private void sortieAutomate(PlateauDeJeu pdj) {
		Log.print(795, "Dans sortieAutomate");
	}



	public int getNbPion(TypeCouleur c) {
		if(c == TypeCouleur.Blanc)
			return nbBlanc;
		return nbNoir;
	}
	public int getNbPionNonContinu(TypeCouleur c) {
		if(c == TypeCouleur.Blanc)
			return nbBlancNonContinu;
		return nbNoirNonContinu;
	}
	
	public boolean estLibre(TypeCouleur c) {
		if(c == TypeCouleur.Blanc)
			return libreBlanc;	
		return libreNoir;
	}

	public boolean estLibre2Cases(TypeCouleur c) {
		if(c == TypeCouleur.Blanc)
			return libre2casesBlanc;
		return libre2casesNoir;
	}

	public void setPion(int nb, TypeCouleur c) {
		if(c == TypeCouleur.Blanc)
			this.nbBlanc = nb;
		this.nbNoir = nb;
	}

	public void setLibre(boolean libre,TypeCouleur c) {
		if(c == TypeCouleur.Blanc)
			this.libreBlanc = libre;
		this.libreNoir = libre;
	}

	public void setLibre2Cases(boolean libre2Cases,TypeCouleur c) {
		if(c == TypeCouleur.Blanc)
			this.libre2casesBlanc = libre2Cases;
		this.libre2casesNoir = libre2Cases;
	}




	public String toString(){
		String buffer ="Infos Alignement " + direction + "\n";
		buffer += "Pions Noirs Alignes: " + nbNoir + " + " + nbNoirNonContinu + " est libre? " + libreNoir + " a deux cases? " + libre2casesNoir + "\n";
		buffer += "Pions Blancs Alignes: " + nbBlanc + " + " + nbBlancNonContinu + " est libre? " + libreBlanc + " a deux cases? " + libre2casesBlanc;
		
		return buffer;
	}
	
}


