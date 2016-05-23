package Utilitaire;

import Controleur.*;
import Enum.*;

public class InfosAlignement {

	int nbNoir;
	int nbBlanc;
	boolean libreNoir;
	boolean libreBlanc;
	boolean continuNoir;
	boolean continuBlanc;
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
		continuBlanc = false;
		
	}
	
	public InfosAlignement(PlateauDeJeu pdj, Coordonnees c, TypeDirection td) {
		nbNoir = 0;
		nbBlanc = 0;
		continuNoir = false;
		continuBlanc = false;
		libreNoir = true;
		libreBlanc = true;
		ligne = c.getLigne();
		colonne = c.getColonne();
		direction = td;
		nbLignes = 15;
		nbColonnes = 15;
		
		
		boolean noirFini = false;
		boolean blancFini = false;
		boolean bordure = !incremente();
			
		Log.print(795, "0 - "+ (!bordure || (noirFini && blancFini)) +" - bordure= " + bordure + " noirFini= " + noirFini + " blancFini= "+ blancFini + " ligne= " +ligne+ " colonne= " + colonne);
		
		while(!bordure && (!noirFini || !blancFini)){
			Log.print(795, "1 - "+ (!bordure || (noirFini && blancFini)) +" - bordure= " + bordure + " noirFini= " + noirFini + " blancFini= "+ blancFini + " ligne= " +ligne+ " colonne= " + colonne);

			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.PionBlanc){
				noirFini = true;
				libreNoir = false;
				nbBlanc++;
			}else if(tc == TypeCase.PionNoir){
				blancFini = true;
				libreBlanc = false;
				nbNoir++;
			}else if(tc == TypeCase.Jouable){
				
				if(!continuNoir){
					continuNoir = true;
				}else{
					noirFini = true;
				}
				
				if(!continuBlanc){
					continuBlanc = true;
				}else{
					blancFini = true;
				}
				
				
			}
			Log.print(795, "2 - "+ (bordure || (noirFini && blancFini)) +" - bordure= " + bordure + " noirFini= " + noirFini + " blancFini= "+ blancFini + " ligne= " +ligne+ " colonne= " + colonne);
			bordure = incremente();
			Log.print(795, "3 - "+ (bordure || (noirFini && blancFini)) +" - bordure= " + bordure + " noirFini= " + noirFini + " blancFini= "+ blancFini + " ligne= " +ligne+ " colonne= " + colonne);

		}
		
		if(bordure){
			libreNoir = false;
			libreBlanc = false;
		}
		
		
		
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
			return ligne >= 0 && ligne < nbLignes;
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

	
	public int getNbNoir() {
		return nbNoir;
	}
	public int getNbBlanc() {
		return nbBlanc;
	}
	public boolean estLibreNoir() {
		return libreNoir;
	}
	public boolean estLibreBlanc() {
		return libreBlanc;
	}
	public boolean estContinuNoir() {
		return continuNoir;
	}
	public boolean estContinuBlanc() {
		return continuBlanc;
	}

	public void setNbNoir(int nbNoir) {
		this.nbNoir = nbNoir;
	}
	public void setNbBlanc(int nbBlanc) {

		this.nbBlanc = nbBlanc;
	}
	public void setLibreNoir(boolean libreNoir) {
		this.libreNoir = libreNoir;
	}
	public void setLibreBlanc(boolean libreBlanc) {
		this.libreBlanc = libreBlanc;
	}
	public void setContinuNoir(boolean continuNoir) {
		this.continuNoir = continuNoir;
	}
	public void setContinuBlanc(boolean continuBlanc) {
		this.continuBlanc = continuBlanc;
	}



	public String toString(){
		String buffer ="Infos Alignement " + direction + "\n";
		buffer += "Pions Noirs Alignes: " + nbNoir + " est libre? " + libreNoir + " est continu? "+ continuNoir +"\n";
		buffer += "Pions Blancs Alignes: " + nbBlanc + " est libre? " + libreBlanc + " est continu? "+ continuBlanc;
		
		return buffer;
		
	}
	
}


