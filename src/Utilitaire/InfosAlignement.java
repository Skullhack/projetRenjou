package Utilitaire;

import Controleur.*;
import Enum.*;

public class InfosAlignement {

	int nbNoir;
	int nbBlanc;
	boolean noirLibre;
	boolean blancLibre;
	boolean caseVideNoir;
	boolean caseVideBlanc;
	private int ligne;
	private int colonne;
	private TypeDirection direction;
	
	int nbLignes;
	int nbColonnes;

	public InfosAlignement() {
	
		nbNoir = 0;
		nbBlanc = 0;
		noirLibre = false;
		blancLibre = false;
		caseVideBlanc = false;
		
	}
	
	public InfosAlignement(PlateauDeJeu pdj, Coordonnees c, TypeDirection td) {
		nbNoir = 0;
		nbBlanc = 0;
		caseVideNoir = false;
		caseVideBlanc = false;
		noirLibre = true;
		blancLibre = true;
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
				noirLibre = false;
				nbBlanc++;
			}else if(tc == TypeCase.PionNoir){
				blancFini = true;
				blancLibre = false;
				nbNoir++;
			}else if(tc == TypeCase.Jouable){
				
				if(!caseVideNoir){
					caseVideNoir = true;
				}else{
					noirFini = true;
				}
				
				if(!caseVideBlanc){
					caseVideBlanc = true;
				}else{
					blancFini = true;
				}
				
				
			}
			Log.print(795, "2 - "+ (bordure || (noirFini && blancFini)) +" - bordure= " + bordure + " noirFini= " + noirFini + " blancFini= "+ blancFini + " ligne= " +ligne+ " colonne= " + colonne);
			bordure = incremente();
			Log.print(795, "3 - "+ (bordure || (noirFini && blancFini)) +" - bordure= " + bordure + " noirFini= " + noirFini + " blancFini= "+ blancFini + " ligne= " +ligne+ " colonne= " + colonne);

		}
		
		if(bordure){
			noirLibre = false;
			blancLibre = false;
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

	public void setNbNoir(int nbNoir) {
		this.nbNoir = nbNoir;
	}

	public int getNbBlanc() {
		return nbBlanc;
	}

	public void setNbBlanc(int nbBlanc) {
		this.nbBlanc = nbBlanc;
	}

	public boolean isNoirLibre() {
		return noirLibre;
	}

	public void setNoirLibre(boolean noirLibre) {
		this.noirLibre = noirLibre;
	}

	public boolean isBlancLibre() {
		return blancLibre;
	}

	public void setBlancLibre(boolean blancLibre) {
		this.blancLibre = blancLibre;
	}

	public boolean isCaseVideNoir() {
		return caseVideNoir;
	}

	public void setCaseVideNoir(boolean caseVideNoir) {
		this.caseVideNoir = caseVideNoir;
	}

	public boolean isCaseVideBlanc() {
		return caseVideBlanc;
	}

	public void setCaseVideBlanc(boolean caseVideBlanc) {
		this.caseVideBlanc = caseVideBlanc;
	}

	
}


