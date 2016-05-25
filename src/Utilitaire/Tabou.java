/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import java.util.ArrayList;

import com.sun.glass.ui.CommonDialogs.Type;

import Controleur.MoteurObserveur;
import Controleur.Renjou;
import Enum.TypeCouleur;
import Enum.TypeDirection;
import Enum.TypeTabous;

/**
 *
 * @author michauad
 */
public class Tabou implements InterfaceTabou, java.io.Serializable {
	private boolean troisFoistrois;
	private boolean quatreFoisQuatre;
	private boolean sixSept;
	private InfosAlignement infoGauche;
	private InfosAlignement infoDroite;
	private InfosAlignement infoHaut;
	private InfosAlignement infoBas;
	private InfosAlignement infoDiagonaleHautGauche;
	private InfosAlignement infoDiagonaleHautDroite;
	private InfosAlignement infoDiagonaleBasGauche;
	private InfosAlignement infoDiagonaleBasDroite;

	// Constructeur
	public Tabou(ArrayList<TypeTabous> listeTabous) {

		this.troisFoistrois = false;
		this.quatreFoisQuatre = false;
		this.sixSept = false;

		for (TypeTabous typeTabou : listeTabous) {
			switch (typeTabou) {
			case TROIS_TROIS:
				this.troisFoistrois = true;
				break;
			case QUATRE_QUATRE:
				this.quatreFoisQuatre = true;
				break;
			case SIX_SEPT:
				this.sixSept = true;
				break;
			default:
				break;

			}
		}
	}
	public Tabou(PlateauDeJeu p, Coordonnees c, boolean troisFoistrois, boolean quatreFoisQuatre, boolean sixSept) {

		this.troisFoistrois = troisFoistrois;
		this.quatreFoisQuatre = quatreFoisQuatre;
		this.sixSept = sixSept;
		init(p,c);
	}
	
	public Tabou(boolean troisFoistrois, boolean quatreFoisQuatre, boolean sixSept) {

		this.troisFoistrois = troisFoistrois;
		this.quatreFoisQuatre = quatreFoisQuatre;
		this.sixSept = sixSept;
	}

	public ArrayList<TypeTabous> getListeTabous() {
		ArrayList<TypeTabous> listeTabous = new ArrayList<TypeTabous>();
		if (troisFoistrois) {
			listeTabous.add(TypeTabous.TROIS_TROIS);

		}
		if (quatreFoisQuatre) {
			listeTabous.add(TypeTabous.QUATRE_QUATRE);

		}
		if (sixSept) {
			listeTabous.add(TypeTabous.SIX_SEPT);

		}
		return listeTabous;
	}

	public void setListeTabous(ArrayList<TypeTabous> listeTabous) {
		this.troisFoistrois = false;
		this.quatreFoisQuatre = false;
		this.sixSept = false;

		for (TypeTabous typeTabou : listeTabous) {
			switch (typeTabou) {
			case TROIS_TROIS:
				this.troisFoistrois = true;
				break;
			case QUATRE_QUATRE:
				this.quatreFoisQuatre = true;
				break;
			case SIX_SEPT:
				this.sixSept = true;
				break;
			default:
				break;

			}
		}
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
	@Override
	// A appliquer que sur noir!!!!
	public boolean estValide(PlateauDeJeu r, Coordonnees c) {
		// initialisation des infos selon les directions
		Log.print(1010, "r = " + r + "c" + c);

		TypeCouleur typeCouleur = TypeCouleur.Noir;
		init(r,c);

		Log.print(1010, infoDiagonaleHautGauche.toString());
		Log.print(1010, infoDiagonaleHautDroite.toString());
		Log.print(1010, infoDiagonaleBasGauche.toString());
		Log.print(1010, infoDiagonaleBasDroite.toString());

		boolean estValide = true;
		if (troisFoistrois) {
			estValide = estValide && !troisFoisTrois(typeCouleur);
		}
		if (quatreFoisQuatre) {
			estValide = estValide && !quatreFoisQuatre(typeCouleur);
		}
		if (sixSept) {
			estValide = estValide && !sixSept(typeCouleur);
		}
		return estValide;
	}

	public boolean estValide(PlateauDeJeu r, Coordonnees c, boolean troisFoisTroisAtribut, boolean quatreFoisQuatreAtribut, boolean sixSeptAtribut, TypeCouleur typeCouleur) {
		// initialisation des infos selon les directions
		Log.print(1010, "r = " + r + "c" + c);
		init(r,c);

		Log.print(1010, infoDiagonaleHautGauche.toString());
		Log.print(1010, infoDiagonaleHautDroite.toString());
		Log.print(1010, infoDiagonaleBasGauche.toString());
		Log.print(1010, infoDiagonaleBasDroite.toString());

		boolean estValide = true;
		if (troisFoisTroisAtribut) {
			estValide = estValide && !troisFoisTrois(typeCouleur);
		}
		if (quatreFoisQuatreAtribut) {
			estValide = estValide && !quatreFoisQuatre(typeCouleur);
		}
		if (sixSeptAtribut) {
			estValide = estValide && !sixSept(typeCouleur);
		}
		return estValide;
	}

	
	public boolean sixSept(TypeCouleur typeCouleur) {
		if ((nbPionDiagonaleDroiteContinu(typeCouleur) > 5)) {
			return true;
		}
		if (nbPionDiagonaleGaucheContinu(typeCouleur) > 5) {
			return true;
		}
		if (nbPionHorizontaleContinu(typeCouleur) > 5) {
			return true;
		}
		if (nbPionVerticaleContinu(typeCouleur) > 5) {
			return true;
		}

		return false;
	}

	public boolean troisFoisTrois(TypeCouleur typeCouleur) {
		if (estTroisDiagonaleDroite(typeCouleur)) {
			if (estTroisDiagonaleGauche(typeCouleur)) {
				return true;
			} else if (estTroisHorizontale(typeCouleur)) {
				return true;
			} else if (estTroisVerticale(typeCouleur)) {

				return true;
			}

		} else if (estTroisDiagonaleGauche(typeCouleur)) {
			if (estTroisHorizontale(typeCouleur)) {
				return true;
			} else if (estTroisVerticale(typeCouleur)) {
				return true;
			}
		} else if (estTroisHorizontale(typeCouleur)) {
			if (estTroisVerticale(typeCouleur)) {
				return true;
			}
		}

		return false;
	}

	public boolean quatreFoisQuatre(TypeCouleur typeCouleur) {

		if(estQuatreQuatreDiagonaleDroite(typeCouleur)){
			return true;
		}
		if(estQuatreQuatreDiagonaleGauche(typeCouleur)){
			return true;
		}
		if(estQuatreQuatreHorizontale(typeCouleur)){
			return true;
		}
		if(estQuatreQuatreVerticale(typeCouleur)){
			return true;
		}
		
		// test quand le 4*4 est dans des directions différentes cf
		// testQuatreQuatreBasique1
		if (estQuatreDiagonaleDroite(typeCouleur)) {
			if (estQuatreDiagonaleGauche(typeCouleur)) {
				return true;
			} else if (estQuatreHorizontale(typeCouleur)) {
				return true;
			} else if (estQuatreVerticale(typeCouleur)) {
				return true;
			}

		} else if (estQuatreDiagonaleGauche(typeCouleur)) {
			if (estQuatreHorizontale(typeCouleur)) {
				return true;
			} else if (estQuatreVerticale(typeCouleur)) {
				return true;
			}
		} else if (estQuatreHorizontale(typeCouleur)) {
			if (estQuatreVerticale(typeCouleur)) {
				return true;
			}
		}

		return false;
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
