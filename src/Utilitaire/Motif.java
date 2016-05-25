package Utilitaire;

import Enum.TypeCouleur;
import Enum.TypeDirection;

public class Motif {
	
	private InfosAlignement infoGauche;
	private InfosAlignement infoDroite;
	private InfosAlignement infoHaut;
	private InfosAlignement infoBas;
	private InfosAlignement infoDiagonaleHautGauche;
	private InfosAlignement infoDiagonaleHautDroite;
	private InfosAlignement infoDiagonaleBasGauche;
	private InfosAlignement infoDiagonaleBasDroite;
	
	
	public Motif(PlateauDeJeu pdj, Coordonnees c){
		init(pdj,c);
	}

	public InfosAlignement getInfoGauche(){
		return infoGauche;
	}
	public InfosAlignement getInfoDroite(){
		return infoDroite;
	}
	public InfosAlignement getInfoHaut(){
		return infoHaut;
	}
	public InfosAlignement getInfoBas(){
		return infoBas;
	}
	public InfosAlignement getInfoDiagonaleHautGauche(){
		return infoDiagonaleHautGauche;
	}
	public InfosAlignement getInfoDiagonaleHautDroite(){
		return infoDiagonaleHautDroite;
	}
	public InfosAlignement getInfoDiagonaleBasGauche(){
		return infoDiagonaleBasGauche;
	}
	public InfosAlignement getInfoDiagonaleBasDroite(){
		return infoDiagonaleBasDroite;
	}
	
	
	private void init(PlateauDeJeu r, Coordonnees c){
		infoGauche = new InfosAlignement(r, c, TypeDirection.Gauche);
		infoDroite = new InfosAlignement(r, c, TypeDirection.Droite);
		infoHaut = new InfosAlignement(r, c, TypeDirection.Haut);
		infoBas = new InfosAlignement(r, c, TypeDirection.Bas);
		infoDiagonaleHautGauche = new InfosAlignement(r, c, TypeDirection.DiagonaleHautGauche);
		infoDiagonaleHautDroite = new InfosAlignement(r, c, TypeDirection.DiagonaleHautDroite);
		infoDiagonaleBasGauche = new InfosAlignement(r, c, TypeDirection.DiagonaleBasGauche);
		infoDiagonaleBasDroite = new InfosAlignement(r, c, TypeDirection.DiagonaleBasDroite);
	}
	
	

	// A appliquer que sur noir!!!!

	// -
	// -
	// -
	public boolean estTroisDiagonaleDroite(TypeCouleur typeCouleur) {
		// Log.print(1010, infoDiagonaleHautGauche.toString());
		return (infoDiagonaleHautGauche.estLibre(typeCouleur) && infoDiagonaleBasDroite.estLibre(typeCouleur)
				&& (infoDiagonaleHautGauche.estLibre2Cases(typeCouleur) || infoDiagonaleBasDroite.estLibre2Cases(typeCouleur))
				&& (nbPionDiagonaleDroite(typeCouleur) == 3));
	}

	// -
	// -
	// -
	public boolean estTroisDiagonaleGauche(TypeCouleur typeCouleur){	
		return (infoDiagonaleHautDroite.estLibre(typeCouleur) && infoDiagonaleBasGauche.estLibre(typeCouleur) && (infoDiagonaleHautDroite.estLibre2Cases(typeCouleur) || infoDiagonaleBasGauche.estLibre2Cases(typeCouleur)) && ( nbPionDiagonaleGauche(typeCouleur) == 3));
	}
	
	public boolean estTroisVerticale(TypeCouleur typeCouleur){
		
		return (infoHaut.estLibre(typeCouleur) && infoBas.estLibre(typeCouleur) && (infoHaut.estLibre2Cases(typeCouleur) || infoBas.estLibre2Cases(typeCouleur)) && (nbPionVerticale(typeCouleur) == 3));
	}
	
	public boolean estTroisHorizontale(TypeCouleur typeCouleur){
		
		return (infoDroite.estLibre(typeCouleur) && infoGauche.estLibre(typeCouleur) && (infoDroite.estLibre2Cases(typeCouleur) || infoGauche.estLibre2Cases(typeCouleur)) &&( nbPionHorizontale(typeCouleur)== 3));
	}

	// -
	// -
	// -
	// -
	//   -
	//     -
	//       -
	public boolean estQuatreDiagonaleDroite(TypeCouleur typeCouleur){
		return ((infoDiagonaleHautGauche.estLibre(typeCouleur) || infoDiagonaleBasDroite.estLibre(typeCouleur)) && (nbPionDiagonaleDroite(typeCouleur) == 4));
	}

	// -
	// -
	// -
	// -
	public boolean estQuatreDiagonaleGauche(TypeCouleur typeCouleur){	
		return ((infoDiagonaleHautDroite.estLibre(typeCouleur) || infoDiagonaleBasGauche.estLibre(typeCouleur)) && (nbPionDiagonaleGauche(typeCouleur) == 4));
	}

	public boolean estQuatreVerticale(TypeCouleur typeCouleur) {
		return ((infoHaut.estLibre(typeCouleur) || infoBas.estLibre(typeCouleur)) && (nbPionVerticale(typeCouleur) == 4));
	}

	public boolean estQuatreHorizontale(TypeCouleur typeCouleur) {
		return ((infoDroite.estLibre(typeCouleur) || infoGauche.estLibre(typeCouleur)) && (nbPionHorizontale(typeCouleur) == 4));
	}

	public boolean estQuatreQuatreVerticale(TypeCouleur typeCouleur){
		if(infoHaut.getNbPion(typeCouleur) + infoHaut.getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		if(infoBas.getNbPion(typeCouleur) + infoBas.getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		
		if(nbPionVerticaleContinu(typeCouleur) + infoHaut.getNbPionNonContinu(typeCouleur) == 4 ){
			if(nbPionVerticaleContinu(typeCouleur) + infoBas.getNbPionNonContinu(typeCouleur) == 4){
				return true;
			}
		}
		return false;
	}
	
	public boolean estQuatreQuatreHorizontale(TypeCouleur typeCouleur){
		
		if(infoDroite.getNbPion(typeCouleur) + infoDroite.getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		if(infoGauche.getNbPion(typeCouleur) + infoGauche.getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		
		if(nbPionHorizontaleContinu(typeCouleur) + infoDroite.getNbPionNonContinu(typeCouleur) == 4 ){
			if(nbPionVerticaleContinu(typeCouleur) + infoGauche.getNbPionNonContinu(typeCouleur) == 4){
				return true;
			}
		}
		return false;
	}
	
	public boolean estQuatreQuatreDiagonaleGauche(TypeCouleur typeCouleur){
		
		if(infoDiagonaleHautDroite.getNbPion(typeCouleur) + infoDiagonaleHautDroite.getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		if(infoDiagonaleBasGauche.getNbPion(typeCouleur) + infoDiagonaleBasGauche.getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		
		if(nbPionDiagonaleGaucheContinu(typeCouleur) + infoDiagonaleHautDroite.getNbPionNonContinu(typeCouleur) == 4 ){
			if(nbPionVerticaleContinu(typeCouleur) + infoDiagonaleBasGauche.getNbPionNonContinu(typeCouleur) == 4){
				return true;
			}
		}
		return false;
	}
	
	public boolean estQuatreQuatreDiagonaleDroite(TypeCouleur typeCouleur){
		
		if(infoDiagonaleHautGauche.getNbPion(typeCouleur) + infoDiagonaleHautGauche.getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		if(infoDiagonaleBasDroite.getNbPion(typeCouleur) + infoDiagonaleBasDroite.getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		
		if(nbPionDiagonaleDroiteContinu(typeCouleur) + infoDiagonaleHautGauche.getNbPionNonContinu(typeCouleur) == 4 ){
			if(nbPionVerticaleContinu(typeCouleur) + infoDiagonaleBasDroite.getNbPionNonContinu(typeCouleur) == 4){
				return true;
			}
		}
		return false;
	}
	
	public int nbPionDiagonaleDroite(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautGauche.getNbPionNonContinu(typeCouleur) + infoDiagonaleBasDroite.getNbPionNonContinu(typeCouleur)
				+ infoDiagonaleHautGauche.getNbPion(typeCouleur) + infoDiagonaleBasDroite.getNbPion(typeCouleur) + 1);
	}

	private boolean estQuatre(TypeCouleur typeCouleur){
		return estQuatreDiagonaleDroite(typeCouleur) || estQuatreDiagonaleGauche(typeCouleur) || estQuatreHorizontale(typeCouleur) || estQuatreVerticale(typeCouleur);
	}
	private boolean estTrois(TypeCouleur typeCouleur){
		return estTroisDiagonaleDroite(typeCouleur) || estTroisDiagonaleGauche(typeCouleur) || estTroisHorizontale(typeCouleur) || estTroisVerticale(typeCouleur);
	}
	
	public int nbPionDiagonaleGauche(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautDroite.getNbPionNonContinu(typeCouleur) + infoDiagonaleBasGauche.getNbPionNonContinu(typeCouleur)
				+ infoDiagonaleHautDroite.getNbPion(typeCouleur) + infoDiagonaleBasGauche.getNbPion(typeCouleur) + 1);
	}

	public int nbPionVerticale(TypeCouleur typeCouleur) {
		return (infoHaut.getNbPionNonContinu(typeCouleur) + infoBas.getNbPionNonContinu(typeCouleur) + 1 + infoHaut.getNbPion(typeCouleur)
				+ infoBas.getNbPion(typeCouleur));
	}

	public int nbPionHorizontale(TypeCouleur typeCouleur) {
		return (infoDroite.getNbPionNonContinu(typeCouleur) + infoGauche.getNbPionNonContinu(typeCouleur) + 1 + infoDroite.getNbPion(typeCouleur)
				+ infoGauche.getNbPion(typeCouleur));
	}

	public int nbPionDiagonaleDroiteContinu(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautGauche.getNbPion(typeCouleur) + infoDiagonaleBasDroite.getNbPion(typeCouleur) + 1);
	}

	public int nbPionDiagonaleGaucheContinu(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautDroite.getNbPion(typeCouleur) + infoDiagonaleBasGauche.getNbPion(typeCouleur) + 1);
	}

	public int nbPionVerticaleContinu(TypeCouleur typeCouleur) {
		return (infoHaut.getNbPion(typeCouleur) + infoBas.getNbPion(typeCouleur) + 1);
	}

	public int nbPionHorizontaleContinu(TypeCouleur typeCouleur) {
		return (infoDroite.getNbPion(typeCouleur) + infoGauche.getNbPion(typeCouleur) + 1);
	}

}
