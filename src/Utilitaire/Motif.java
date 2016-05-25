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

	public Motif(PlateauDeJeu pdj, Coordonnees c) {
		init(pdj, c);
	}

	public InfosAlignement getInfoGauche() {
		return infoGauche;
	}

	public InfosAlignement getInfoDroite() {
		return infoDroite;
	}

	public InfosAlignement getInfoHaut() {
		return infoHaut;
	}

	public InfosAlignement getInfoBas() {
		return infoBas;
	}

	public InfosAlignement getInfoDiagonaleHautGauche() {
		return infoDiagonaleHautGauche;
	}

	public InfosAlignement getInfoDiagonaleHautDroite() {
		return infoDiagonaleHautDroite;
	}

	public InfosAlignement getInfoDiagonaleBasGauche() {
		return infoDiagonaleBasGauche;
	}

	public InfosAlignement getInfoDiagonaleBasDroite() {
		return infoDiagonaleBasDroite;
	}

	private void init(PlateauDeJeu r, Coordonnees c) {
		infoGauche = new InfosAlignement(r, c, TypeDirection.Gauche);
		infoDroite = new InfosAlignement(r, c, TypeDirection.Droite);
		infoHaut = new InfosAlignement(r, c, TypeDirection.Haut);
		infoBas = new InfosAlignement(r, c, TypeDirection.Bas);
		infoDiagonaleHautGauche = new InfosAlignement(r, c, TypeDirection.DiagonaleHautGauche);
		infoDiagonaleHautDroite = new InfosAlignement(r, c, TypeDirection.DiagonaleHautDroite);
		infoDiagonaleBasGauche = new InfosAlignement(r, c, TypeDirection.DiagonaleBasGauche);
		infoDiagonaleBasDroite = new InfosAlignement(r, c, TypeDirection.DiagonaleBasDroite);
	}

	
	
	private boolean estDeux(TypeCouleur typeCouleur) {
		return estDeuxDiagonaleDroite(typeCouleur) || estDeuxDiagonaleGauche(typeCouleur)
				|| estDeuxHorizontale(typeCouleur) || estDeuxVerticale(typeCouleur);
	}
	
	private boolean estDeuxLibre(TypeCouleur typeCouleur) {
		return estDeuxDiagonaleDroiteLibre(typeCouleur) || estDeuxDiagonaleGaucheLibre(typeCouleur)
				|| estDeuxHorizontaleLibre(typeCouleur) || estDeuxVerticaleLibre(typeCouleur);
	}

	public boolean DeuxFoisDeuxLibre(TypeCouleur typeCouleur) {
		if (estDeuxDiagonaleDroiteLibre(typeCouleur)) {
			if (estDeuxDiagonaleGaucheLibre(typeCouleur)) {
				return true;
			} else if (estDeuxHorizontaleLibre(typeCouleur)) {
				return true;
			} else if (estDeuxVerticaleLibre(typeCouleur)) {

				return true;
			}

		} else if (estDeuxDiagonaleGaucheLibre(typeCouleur)) {
			if (estDeuxHorizontaleLibre(typeCouleur)) {
				return true;
			} else if (estDeuxVerticaleLibre(typeCouleur)) {
				return true;
			}
		} else if (estDeuxHorizontaleLibre(typeCouleur)) {
			if (estDeuxVerticaleLibre(typeCouleur)) {
				return true;
			}
		}

		return false;
	}
	
	public boolean DeuxFoisDeux(TypeCouleur typeCouleur) {
		if (estDeuxDiagonaleDroite(typeCouleur)) {
			if (estDeuxDiagonaleGauche(typeCouleur)) {
				return true;
			} else if (estDeuxHorizontale(typeCouleur)) {
				return true;
			} else if (estDeuxVerticale(typeCouleur)) {

				return true;
			}

		} else if (estDeuxDiagonaleGauche(typeCouleur)) {
			if (estDeuxHorizontale(typeCouleur)) {
				return true;
			} else if (estDeuxVerticale(typeCouleur)) {
				return true;
			}
		} else if (estDeuxHorizontale(typeCouleur)) {
			if (estDeuxVerticale(typeCouleur)) {
				return true;
			}
		}

		return false;
	}
	
	
	
	
	private boolean estTrois(TypeCouleur typeCouleur) {
		return estTroisDiagonaleDroite(typeCouleur) || estTroisDiagonaleGauche(typeCouleur)
				|| estTroisHorizontale(typeCouleur) || estTroisVerticale(typeCouleur);
	}
	
	private boolean estTroisLibre(TypeCouleur typeCouleur) {
		return estTroisDiagonaleDroiteLibre(typeCouleur) || estTroisDiagonaleGaucheLibre(typeCouleur)
				|| estTroisHorizontaleLibre(typeCouleur) || estTroisVerticaleLibre(typeCouleur);
	}

	public boolean troisFoisTroisLibre(TypeCouleur typeCouleur) {
		if (estTroisDiagonaleDroiteLibre(typeCouleur)) {
			if (estTroisDiagonaleGaucheLibre(typeCouleur)) {
				return true;
			} else if (estTroisHorizontaleLibre(typeCouleur)) {
				return true;
			} else if (estTroisVerticaleLibre(typeCouleur)) {

				return true;
			}

		} else if (estTroisDiagonaleGaucheLibre(typeCouleur)) {
			if (estTroisHorizontaleLibre(typeCouleur)) {
				return true;
			} else if (estTroisVerticaleLibre(typeCouleur)) {
				return true;
			}
		} else if (estTroisHorizontaleLibre(typeCouleur)) {
			if (estTroisVerticaleLibre(typeCouleur)) {
				return true;
			}
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
	
	
	
	private boolean estQuatre(TypeCouleur typeCouleur) {
		return estQuatreDiagonaleDroite(typeCouleur) || estQuatreDiagonaleGauche(typeCouleur)
				|| estQuatreHorizontale(typeCouleur) || estQuatreVerticale(typeCouleur);
	}

	private boolean estQuatreLibre(TypeCouleur typeCouleur) {
		return estQuatreDiagonaleDroiteLibre(typeCouleur) || estQuatreDiagonaleGaucheLibre(typeCouleur)
				|| estQuatreHorizontaleLibre(typeCouleur) || estQuatreVerticaleLibre(typeCouleur);
	}

	
	public boolean estTroisDiagonaleDroiteLibre(TypeCouleur typeCouleur) {
		// Log.print(1010, infoDiagonaleHautGauche.toString());
		return (infoDiagonaleHautGauche.estLibre(typeCouleur) && infoDiagonaleBasDroite.estLibre(typeCouleur)
				&& (infoDiagonaleHautGauche.estLibre2Cases(typeCouleur)
						|| infoDiagonaleBasDroite.estLibre2Cases(typeCouleur))
				&& (nbPionDiagonaleDroite(typeCouleur) == 3));
	}

	public boolean estTroisDiagonaleGaucheLibre(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautDroite.estLibre(typeCouleur) && infoDiagonaleBasGauche.estLibre(typeCouleur)
				&& (infoDiagonaleHautDroite.estLibre2Cases(typeCouleur)
						|| infoDiagonaleBasGauche.estLibre2Cases(typeCouleur))
				&& (nbPionDiagonaleGauche(typeCouleur) == 3));
	}

	public boolean estTroisVerticaleLibre(TypeCouleur typeCouleur) {

		return (infoHaut.estLibre(typeCouleur) && infoBas.estLibre(typeCouleur)
				&& (infoHaut.estLibre2Cases(typeCouleur) || infoBas.estLibre2Cases(typeCouleur))
				&& (nbPionVerticale(typeCouleur) == 3));
	}

	public boolean estTroisHorizontaleLibre(TypeCouleur typeCouleur) {

		return (infoDroite.estLibre(typeCouleur) && infoGauche.estLibre(typeCouleur)
				&& (infoDroite.estLibre2Cases(typeCouleur) || infoGauche.estLibre2Cases(typeCouleur))
				&& (nbPionHorizontale(typeCouleur) == 3));
	}

	
	public boolean estTroisDiagonaleDroite(TypeCouleur typeCouleur) {
		// Log.print(1010, infoDiagonaleHautGauche.toString());
		return nbPionDiagonaleDroite(typeCouleur) == 3;
	}

	public boolean estTroisDiagonaleGauche(TypeCouleur typeCouleur) {
		return nbPionDiagonaleGauche(typeCouleur) == 3;
	}

	public boolean estTroisVerticale(TypeCouleur typeCouleur) {

		return nbPionVerticale(typeCouleur) == 3;
	}

	public boolean estTroisHorizontale(TypeCouleur typeCouleur) {

		return nbPionHorizontale(typeCouleur) == 3;
	}

	
	
	public boolean estDeuxDiagonaleDroiteLibre(TypeCouleur typeCouleur) {
		// Log.print(1010, infoDiagonaleHautGauche.toString());
		return (infoDiagonaleHautGauche.estLibre(typeCouleur) && infoDiagonaleBasDroite.estLibre(typeCouleur)
				&& (infoDiagonaleHautGauche.estLibre2Cases(typeCouleur)
						|| infoDiagonaleBasDroite.estLibre2Cases(typeCouleur))
				&& (nbPionDiagonaleDroite(typeCouleur) == 2));
	}

	public boolean estDeuxDiagonaleGaucheLibre(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautDroite.estLibre(typeCouleur) && infoDiagonaleBasGauche.estLibre(typeCouleur)
				&& (infoDiagonaleHautDroite.estLibre2Cases(typeCouleur)
						|| infoDiagonaleBasGauche.estLibre2Cases(typeCouleur))
				&& (nbPionDiagonaleGauche(typeCouleur) == 2));
	}

	public boolean estDeuxVerticaleLibre(TypeCouleur typeCouleur) {

		return (infoHaut.estLibre(typeCouleur) && infoBas.estLibre(typeCouleur)
				&& (infoHaut.estLibre2Cases(typeCouleur) || infoBas.estLibre2Cases(typeCouleur))
				&& (nbPionVerticale(typeCouleur) == 2));
	}

	public boolean estDeuxHorizontaleLibre(TypeCouleur typeCouleur) {

		return (infoDroite.estLibre(typeCouleur) && infoGauche.estLibre(typeCouleur)
				&& (infoDroite.estLibre2Cases(typeCouleur) || infoGauche.estLibre2Cases(typeCouleur))
				&& (nbPionHorizontale(typeCouleur) == 2));
	}

	
	public boolean estDeuxDiagonaleDroite(TypeCouleur typeCouleur) {
		// Log.print(1010, infoDiagonaleHautGauche.toString());
		return nbPionDiagonaleDroite(typeCouleur) == 2;
	}

	public boolean estDeuxDiagonaleGauche(TypeCouleur typeCouleur) {
		return nbPionDiagonaleGauche(typeCouleur) == 2;
	}

	public boolean estDeuxVerticale(TypeCouleur typeCouleur) {

		return nbPionVerticale(typeCouleur) == 2;
	}

	public boolean estDeuxHorizontale(TypeCouleur typeCouleur) {

		return nbPionHorizontale(typeCouleur) == 2;
	}

	
	
	
	public boolean estQuatreDiagonaleDroiteLibre(TypeCouleur typeCouleur) {
		return ((infoDiagonaleHautGauche.estLibre(typeCouleur) || infoDiagonaleBasDroite.estLibre(typeCouleur))
				&& (nbPionDiagonaleDroite(typeCouleur) == 4));
	}

	public boolean estQuatreDiagonaleGaucheLibre(TypeCouleur typeCouleur) {
		return ((infoDiagonaleHautDroite.estLibre(typeCouleur) || infoDiagonaleBasGauche.estLibre(typeCouleur))
				&& (nbPionDiagonaleGauche(typeCouleur) == 4));
	}

	public boolean estQuatreVerticaleLibre(TypeCouleur typeCouleur) {
		return ((infoHaut.estLibre(typeCouleur) || infoBas.estLibre(typeCouleur))
				&& (nbPionVerticale(typeCouleur) == 4));
	}

	public boolean estQuatreHorizontaleLibre(TypeCouleur typeCouleur) {
		return ((infoDroite.estLibre(typeCouleur) || infoGauche.estLibre(typeCouleur))
				&& (nbPionHorizontale(typeCouleur) == 4));
	}

	
	public boolean estQuatreDiagonaleDroite(TypeCouleur typeCouleur) {
		return nbPionDiagonaleDroite(typeCouleur) == 4;
	}

	public boolean estQuatreDiagonaleGauche(TypeCouleur typeCouleur) {
		return nbPionDiagonaleGauche(typeCouleur) == 4;
	}

	public boolean estQuatreVerticale(TypeCouleur typeCouleur) {
		return nbPionVerticale(typeCouleur) == 4;
	}

	public boolean estQuatreHorizontale(TypeCouleur typeCouleur) {
		return (nbPionHorizontale(typeCouleur) == 4);
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
		if (estQuatreDiagonaleDroiteLibre(typeCouleur)) {
			if (estQuatreDiagonaleGaucheLibre(typeCouleur)) {
				return true;
			} else if (estQuatreHorizontaleLibre(typeCouleur)) {
				return true;
			} else if (estQuatreVerticaleLibre(typeCouleur)) {
				return true;
			}

		} else if (estQuatreDiagonaleGaucheLibre(typeCouleur)) {
			if (estQuatreHorizontaleLibre(typeCouleur)) {
				return true;
			} else if (estQuatreVerticaleLibre(typeCouleur)) {
				return true;
			}
		} else if (estQuatreHorizontaleLibre(typeCouleur)) {
			if (estQuatreVerticaleLibre(typeCouleur)) {
				return true;
			}
		}

		return false;
	}

	public boolean estQuatreQuatreVerticale(TypeCouleur typeCouleur){
		if(getInfoHaut().getNbPion(typeCouleur) + getInfoHaut().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		if(getInfoBas().getNbPion(typeCouleur) + getInfoBas().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		
		if(nbPionVerticaleContinu(typeCouleur) + getInfoHaut().getNbPionNonContinu(typeCouleur) == 4 ){
			if(nbPionVerticaleContinu(typeCouleur) + getInfoBas().getNbPionNonContinu(typeCouleur) == 4){
				return true;
			}
		}
		return false;
	}
	
	public boolean estQuatreQuatreHorizontale(TypeCouleur typeCouleur){
		
		if(getInfoDroite().getNbPion(typeCouleur) + getInfoDroite().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		if(getInfoGauche().getNbPion(typeCouleur) + getInfoGauche().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		
		if(nbPionHorizontaleContinu(typeCouleur) + getInfoDroite().getNbPionNonContinu(typeCouleur) == 4 ){
			if(nbPionVerticaleContinu(typeCouleur) + getInfoGauche().getNbPionNonContinu(typeCouleur) == 4){
				return true;
			}
		}
		return false;
	}
	
	public boolean estQuatreQuatreDiagonaleGauche(TypeCouleur typeCouleur){
		
		if(getInfoDiagonaleHautDroite().getNbPion(typeCouleur) + getInfoDiagonaleHautDroite().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		if(getInfoDiagonaleBasGauche().getNbPion(typeCouleur) + getInfoDiagonaleBasGauche().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		
		if(nbPionDiagonaleGaucheContinu(typeCouleur) + getInfoDiagonaleHautDroite().getNbPionNonContinu(typeCouleur) == 4 ){
			if(nbPionVerticaleContinu(typeCouleur) + getInfoDiagonaleBasGauche().getNbPionNonContinu(typeCouleur) == 4){
				return true;
			}
		}
		return false;
	}
	
	public boolean estQuatreQuatreDiagonaleDroite(TypeCouleur typeCouleur){
		
		if(getInfoDiagonaleHautGauche().getNbPion(typeCouleur) + getInfoDiagonaleHautGauche().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		if(getInfoDiagonaleBasDroite().getNbPion(typeCouleur) + getInfoDiagonaleBasDroite().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		
		if(nbPionDiagonaleDroiteContinu(typeCouleur) + getInfoDiagonaleHautGauche().getNbPionNonContinu(typeCouleur) == 4 ){
			if(nbPionVerticaleContinu(typeCouleur) + getInfoDiagonaleBasDroite().getNbPionNonContinu(typeCouleur) == 4){
				return true;
			}
		}
		return false;
	}

	
	
	public int nbPionDiagonaleDroite(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautGauche.getNbPionNonContinu(typeCouleur)
				+ infoDiagonaleBasDroite.getNbPionNonContinu(typeCouleur)
				+ infoDiagonaleHautGauche.getNbPion(typeCouleur) + infoDiagonaleBasDroite.getNbPion(typeCouleur) + 1);
	}

	public int nbPionDiagonaleGauche(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautDroite.getNbPionNonContinu(typeCouleur)
				+ infoDiagonaleBasGauche.getNbPionNonContinu(typeCouleur)
				+ infoDiagonaleHautDroite.getNbPion(typeCouleur) + infoDiagonaleBasGauche.getNbPion(typeCouleur) + 1);
	}

	public int nbPionVerticale(TypeCouleur typeCouleur) {
		return (infoHaut.getNbPionNonContinu(typeCouleur) + infoBas.getNbPionNonContinu(typeCouleur) + 1
				+ infoHaut.getNbPion(typeCouleur) + infoBas.getNbPion(typeCouleur));
	}

	public int nbPionHorizontale(TypeCouleur typeCouleur) {
		return (infoDroite.getNbPionNonContinu(typeCouleur) + infoGauche.getNbPionNonContinu(typeCouleur) + 1
				+ infoDroite.getNbPion(typeCouleur) + infoGauche.getNbPion(typeCouleur));
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
