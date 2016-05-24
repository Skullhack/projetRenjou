package Utilitaire;

import Controleur.*;
import Enum.*;

public class InfosAlignement {

	int nbNoir;
	int nbBlanc;	
	int nbNoirNonContinu;
	int nbBlancNonContinu;
	boolean libreNoir;
	boolean libreBlanc;
	boolean libre2casesNoir;
	boolean libre2casesBlanc;
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
		nbNoirNonContinu = 0;
		nbBlancNonContinu = 0;
		continuNoir = true;
		continuBlanc = true;
		libreNoir = true;
		libreBlanc = true;
		libre2casesNoir = false;;
		libre2casesBlanc = false;
		ligne = c.getLigne();
		colonne = c.getColonne();
		direction = td;
		nbLignes = 15;
		nbColonnes = 15;
		
		
		boolean noirFini = false;
		boolean blancFini = false;
		boolean caseVideNoir = false;
		boolean caseVideBlanc = false;
		TypeCase casePrecedent = TypeCase.Jouable;
		boolean bordure = !incremente();
			
		Log.print(795, "0 - "+ (!bordure || (noirFini && blancFini)) +" - bordure= " + bordure + " noirFini= " + noirFini + " blancFini= "+ blancFini + " ligne= " +ligne+ " colonne= " + colonne);
		
		while(!bordure && !(noirFini && blancFini)){
			Log.print(795, "1 - "+ (!bordure || (noirFini && blancFini)) +" - bordure= " + bordure + " noirFini= " + noirFini + " blancFini= "+ blancFini + " ligne= " +ligne+ " colonne= " + colonne);

			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.PionBlanc){
				noirFini = true;
				libreNoir = false;
				if(caseVideBlanc){
					continuBlanc = false;
					Log.print(795, "blanc non continu");
				}
				
				if(!blancFini){
					if(caseVideBlanc){
						nbBlancNonContinu++;
					}
					else{
						nbBlanc++;
					}
					Log.print(795, "pionBlanc: " + nbBlanc + " nbBlancNonContinu: " + nbBlancNonContinu);
				}
			}else if(tc == TypeCase.PionNoir && !noirFini){
				blancFini = true;
				libreBlanc = false;
				if(caseVideNoir){
					continuNoir = false;
					Log.print(795, "noir non continu");
				}
				if(!noirFini){
						if(caseVideNoir){
							nbNoirNonContinu++;
						}
						else{
							nbNoir++;
						}
					Log.print(795, "pionNoir: " + nbNoir + " nbNoirNonContinu: " + nbNoirNonContinu );
				}
			}else if(tc == TypeCase.Jouable){
				
				if(!noirFini){
					if(!caseVideNoir){
						caseVideNoir = true;
						Log.print(795, "caseVideNoir");
					}else{
						if(casePrecedent == TypeCase.Jouable){
							libre2casesNoir = true;
						}
						noirFini = true;
						Log.print(795, "noirFini");
					}
				}
				
				if(!blancFini){
					if(!caseVideBlanc){
						caseVideBlanc = true;
						Log.print(795, "caseVideBlanc");
					}else{
						if(casePrecedent == TypeCase.Jouable){
							libre2casesBlanc = true;
						}
						blancFini = true;
						Log.print(795, "blancFini");
					}
				}
				
				
			}
			casePrecedent = tc;
			Log.print(795, "2 - "+ (!bordure || (noirFini && blancFini)) +" - bordure= " + bordure + " noirFini= " + noirFini + " blancFini= "+ blancFini + " ligne= " +ligne+ " colonne= " + colonne);
			bordure = !incremente();
			Log.print(795, "3 - "+ (!bordure || (noirFini && blancFini)) +" - bordure= " + bordure + " noirFini= " + noirFini + " blancFini= "+ blancFini + " ligne= " +ligne+ " colonne= " + colonne);
			
		}
		
		if(bordure){
			libreNoir = false;
			libreBlanc = false;
		}else if(casePrecedent == TypeCase.Jouable){
			TypeCase tc = pdj.getTypeCaseTableau(new Coordonnees(ligne,colonne));
			if(tc == TypeCase.Jouable && nbBlanc == 0 && nbBlancNonContinu == 0){
				libre2casesNoir = true;	
			}
			
			if(tc == TypeCase.Jouable && nbNoir == 0 && nbNoirNonContinu == 0){
				libre2casesBlanc = true;	
			}
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
	public int getNbNoirNonContinu() {
		return nbNoirNonContinu;
	}
	public int getNbBlancNonContinu() {
		return nbBlancNonContinu;
	}
	
	public boolean estLibreNoir() {
		return libreNoir;
	}
	public boolean estLibreBlanc() {
		return libreBlanc;
	}
	public boolean estLibre2CasesNoir() {
		return libre2casesNoir;
	}
	public boolean estLibre2CasesBlanc() {
		return libre2casesBlanc;
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
	public void setLibre2CasesNoir(boolean libre2CasesNoir) {
		this.libre2casesNoir = libre2CasesNoir;
	}
	public void sestLibre2CasesBlanc(boolean libre2CasesBlanc) {
		this.libre2casesBlanc = libre2CasesBlanc;
	}	
	public void setContinuNoir(boolean continuNoir) {
		this.continuNoir = continuNoir;
	}
	public void setContinuBlanc(boolean continuBlanc) {
		this.continuBlanc = continuBlanc;
	}



	public String toString(){
		String buffer ="Infos Alignement " + direction + "\n";
		buffer += "Pions Noirs Alignes: " + nbNoir + " + " + nbNoirNonContinu + " est libre? " + libreNoir + " a deux cases? " + libre2casesNoir + " est continu? "+ continuNoir +"\n";
		buffer += "Pions Blancs Alignes: " + nbBlanc + " + " + nbBlancNonContinu + " est libre? " + libreBlanc + " a deux cases? " + libre2casesBlanc + " est continu? "+ continuBlanc;
		
		return buffer;
	}
	
}


