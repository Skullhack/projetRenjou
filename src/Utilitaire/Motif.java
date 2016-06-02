package Utilitaire;

import Enum.TypeCouleur;
import Enum.TypeDirection;


/*
 * Cette classe permet de reconnaître des motif (ex : troisxtrois, qutrextrois...) a partir des info recuperer dans infoAlignement
 * Chaque methodes reconnait un modif qui est son nom
 */
public class Motif {

	private InfosAlignement infoGauche;
	private InfosAlignement infoDroite;
	private InfosAlignement infoHaut;
	private InfosAlignement infoBas;
	private InfosAlignement infoDiagonaleHautGauche;
	private InfosAlignement infoDiagonaleHautDroite;
	private InfosAlignement infoDiagonaleBasGauche;
	private InfosAlignement infoDiagonaleBasDroite;

	
	// Constructeur
	public Motif(PlateauDeJeu pdj, Coordonnees c) {
		init(pdj, c);
	}
	public Motif() {
		infoGauche = new InfosAlignement(TypeDirection.Gauche);
		infoDroite = new InfosAlignement(TypeDirection.Droite);
		infoHaut = new InfosAlignement(TypeDirection.Haut);
		infoBas = new InfosAlignement(TypeDirection.Bas);
		infoDiagonaleHautGauche = new InfosAlignement(TypeDirection.DiagonaleHautGauche);
		infoDiagonaleHautDroite = new InfosAlignement(TypeDirection.DiagonaleHautDroite);
		infoDiagonaleBasGauche = new InfosAlignement(TypeDirection.DiagonaleBasGauche);
		infoDiagonaleBasDroite = new InfosAlignement(TypeDirection.DiagonaleBasDroite);
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

	public void setMotif(PlateauDeJeu pdj, int l, int c){
		infoGauche.setInfoAlignement(pdj, l, c);
		infoDroite.setInfoAlignement(pdj, l, c);
		infoHaut.setInfoAlignement(pdj, l, c);
		infoBas.setInfoAlignement(pdj, l, c);
		infoDiagonaleHautGauche.setInfoAlignement(pdj, l, c);
		infoDiagonaleHautDroite.setInfoAlignement(pdj, l, c);
		infoDiagonaleBasGauche.setInfoAlignement(pdj, l, c);
		infoDiagonaleBasDroite.setInfoAlignement(pdj, l, c);
	}
	
	
	// Getter and Setter
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
		
	public boolean estUn(TypeCouleur typeCouleur) {
		return estUnDiagonaleDroite(typeCouleur) || estUnDiagonaleGauche(typeCouleur)
				|| estUnHorizontale(typeCouleur) || estUnVerticale(typeCouleur);
	}
	public boolean estUnLibre(TypeCouleur typeCouleur) {
		return estUnDiagonaleDroiteLibre(typeCouleur) || estUnDiagonaleGaucheLibre(typeCouleur)
				|| estUnHorizontaleLibre(typeCouleur) || estUnVerticaleLibre(typeCouleur);
	}
	public boolean estUnLibreLibre(TypeCouleur typeCouleur) {
		return estUnDiagonaleDroiteLibreLibre(typeCouleur) || estUnDiagonaleGaucheLibreLibre(typeCouleur)
				|| estUnHorizontaleLibreLibre(typeCouleur) || estUnVerticaleLibreLibre(typeCouleur);
	}
	
	public boolean estUnFoisUn(TypeCouleur typeCouleur) {
		if (estUnDiagonaleDroite(typeCouleur)) {
			if (estUnDiagonaleGauche(typeCouleur)) {
				return true;
			} else if (estUnHorizontale(typeCouleur)) {
				return true;
			} else if (estUnVerticale(typeCouleur)) {

				return true;
			}

		} else if (estUnDiagonaleGauche(typeCouleur)) {
			if (estUnHorizontale(typeCouleur)) {
				return true;
			} else if (estUnVerticale(typeCouleur)) {
				return true;
			}
		} else if (estUnHorizontale(typeCouleur)) {
			if (estUnVerticale(typeCouleur)) {
				return true;
			}
		}

		return false;
	}
	public boolean estUnFoisUnLibre(TypeCouleur typeCouleur) {
		if (estUnDiagonaleDroiteLibre(typeCouleur)) {
			if (estUnDiagonaleGaucheLibre(typeCouleur)) {
				return true;
			} else if (estUnHorizontaleLibre(typeCouleur)) {
				return true;
			} else if (estUnVerticaleLibre(typeCouleur)) {

				return true;
			}

		} else if (estUnDiagonaleGaucheLibre(typeCouleur)) {
			if (estUnHorizontaleLibre(typeCouleur)) {
				return true;
			} else if (estUnVerticaleLibre(typeCouleur)) {
				return true;
			}
		} else if (estUnHorizontaleLibre(typeCouleur)) {
			if (estUnVerticaleLibre(typeCouleur)) {
				return true;
			}
		}

		return false;
	}
	public boolean estUnFoisUnLibreLibre(TypeCouleur typeCouleur) {
		if (estUnDiagonaleDroiteLibreLibre(typeCouleur)) {
			if (estUnDiagonaleGaucheLibreLibre(typeCouleur)) {
				return true;
			} else if (estUnHorizontaleLibreLibre(typeCouleur)) {
				return true;
			} else if (estUnVerticaleLibreLibre(typeCouleur)) {

				return true;
			}

		} else if (estUnDiagonaleGaucheLibreLibre(typeCouleur)) {
			if (estUnHorizontaleLibreLibre(typeCouleur)) {
				return true;
			} else if (estUnVerticaleLibreLibre(typeCouleur)) {
				return true;
			}
		} else if (estUnHorizontaleLibreLibre(typeCouleur)) {
			if (estUnVerticaleLibreLibre(typeCouleur)) {
				return true;
			}
		}

		return false;
	}

	public boolean estUnDiagonaleDroite(TypeCouleur typeCouleur) {
		// Log.print(1010, infoDiagonaleHautGauche.toString());
		return nbPionDiagonaleDroite(typeCouleur) == 1;
	}
	public boolean estUnDiagonaleGauche(TypeCouleur typeCouleur) {
		return nbPionDiagonaleGauche(typeCouleur) == 1;
	}
	public boolean estUnVerticale(TypeCouleur typeCouleur) {

		return nbPionVerticale(typeCouleur) == 1;
	}
	public boolean estUnHorizontale(TypeCouleur typeCouleur) {

		return nbPionHorizontale(typeCouleur) == 1;
	}

	
	public boolean estUnDiagonaleDroiteLibre(TypeCouleur typeCouleur) {
		// Log.print(1010, infoDiagonaleHautGauche.toString());
		return (infoDiagonaleHautGauche.estLibre(typeCouleur) || infoDiagonaleBasDroite.estLibre(typeCouleur))
				&& (nbPionDiagonaleDroite(typeCouleur) == 1);
	}
	public boolean estUnDiagonaleGaucheLibre(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautDroite.estLibre(typeCouleur) || infoDiagonaleBasGauche.estLibre(typeCouleur))
				&& (nbPionDiagonaleGauche(typeCouleur) == 1);
	}
	public boolean estUnVerticaleLibre(TypeCouleur typeCouleur) {

		return (infoHaut.estLibre(typeCouleur) || infoBas.estLibre(typeCouleur))
				&& (nbPionVerticale(typeCouleur) == 1);
	}
	public boolean estUnHorizontaleLibre(TypeCouleur typeCouleur) {

		return (infoDroite.estLibre(typeCouleur) || infoGauche.estLibre(typeCouleur))
				&& (nbPionHorizontale(typeCouleur) == 1);
	}

	public boolean estUnDiagonaleDroiteLibreLibre(TypeCouleur typeCouleur) {
		// Log.print(1010, infoDiagonaleHautGauche.toString());
		return (
				(infoDiagonaleHautGauche.estLibre(typeCouleur) && infoDiagonaleBasDroite.estLibre2Cases(typeCouleur))
				|| 
				(infoDiagonaleBasDroite.estLibre(typeCouleur) && (infoDiagonaleHautGauche.estLibre2Cases(typeCouleur)))
				)
				&& (nbPionDiagonaleDroite(typeCouleur) == 1);
	}
	public boolean estUnDiagonaleGaucheLibreLibre(TypeCouleur typeCouleur) {
		return (
				(infoDiagonaleHautDroite.estLibre(typeCouleur) && infoDiagonaleBasGauche.estLibre2Cases(typeCouleur))
				|| 
				(infoDiagonaleBasGauche.estLibre(typeCouleur) && (infoDiagonaleHautDroite.estLibre2Cases(typeCouleur)))
				)
				&& (nbPionDiagonaleDroite(typeCouleur) == 1);
	}
	public boolean estUnVerticaleLibreLibre(TypeCouleur typeCouleur) {
		return (
				(infoHaut.estLibre(typeCouleur) && infoBas.estLibre2Cases(typeCouleur))
				|| 
				(infoBas.estLibre(typeCouleur) && (infoHaut.estLibre2Cases(typeCouleur)))
				)
				&& (nbPionDiagonaleDroite(typeCouleur) == 1);
	}
	public boolean estUnHorizontaleLibreLibre(TypeCouleur typeCouleur) {
		return (
				(infoDroite.estLibre(typeCouleur) && infoGauche.estLibre2Cases(typeCouleur))
				|| 
				(infoGauche.estLibre(typeCouleur) && (infoDroite.estLibre2Cases(typeCouleur)))
				)
				&& (nbPionDiagonaleDroite(typeCouleur) == 1);
	}

	
	public boolean estDeux(TypeCouleur typeCouleur) {
		return estDeuxDiagonaleDroite(typeCouleur) || estDeuxDiagonaleGauche(typeCouleur)
				|| estDeuxHorizontale(typeCouleur) || estDeuxVerticale(typeCouleur);
	}
	public boolean estDeuxLibre(TypeCouleur typeCouleur) {
		return estDeuxDiagonaleDroiteLibre(typeCouleur) || estDeuxDiagonaleGaucheLibre(typeCouleur)
				|| estDeuxHorizontaleLibre(typeCouleur) || estDeuxVerticaleLibre(typeCouleur);
	}
	public boolean estDeuxLibreLibre(TypeCouleur typeCouleur) {
		return estDeuxDiagonaleDroiteLibreLibre(typeCouleur) || estDeuxDiagonaleGaucheLibreLibre(typeCouleur)
				|| estDeuxHorizontaleLibreLibre(typeCouleur) || estDeuxVerticaleLibreLibre(typeCouleur);
	}
	
	public boolean estDeuxFoisDeux(TypeCouleur typeCouleur) {
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
	public boolean estDeuxFoisDeuxLibre(TypeCouleur typeCouleur) {
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
	public boolean estDeuxFoisDeuxLibreLibre(TypeCouleur typeCouleur) {
		if (estDeuxDiagonaleDroiteLibreLibre(typeCouleur)) {
			if (estDeuxDiagonaleGaucheLibreLibre(typeCouleur)) {
				return true;
			} else if (estDeuxHorizontaleLibreLibre(typeCouleur)) {
				return true;
			} else if (estDeuxVerticaleLibreLibre(typeCouleur)) {

				return true;
			}

		} else if (estDeuxDiagonaleGaucheLibreLibre(typeCouleur)) {
			if (estDeuxHorizontaleLibreLibre(typeCouleur)) {
				return true;
			} else if (estDeuxVerticaleLibreLibre(typeCouleur)) {
				return true;
			}
		} else if (estDeuxHorizontaleLibreLibre(typeCouleur)) {
			if (estDeuxVerticaleLibreLibre(typeCouleur)) {
				return true;
			}
		}

		return false;
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

	public boolean estDeuxDiagonaleDroiteLibre(TypeCouleur typeCouleur) {
		// Log.print(1010, infoDiagonaleHautGauche.toString());
		return (infoDiagonaleHautGauche.estLibre(typeCouleur) || infoDiagonaleBasDroite.estLibre(typeCouleur))
				&& (nbPionDiagonaleDroite(typeCouleur) == 2);
	}
	public boolean estDeuxDiagonaleGaucheLibre(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautDroite.estLibre(typeCouleur) || infoDiagonaleBasGauche.estLibre(typeCouleur))
				&& (nbPionDiagonaleGauche(typeCouleur) == 2);
	}
	public boolean estDeuxVerticaleLibre(TypeCouleur typeCouleur) {

		return (infoHaut.estLibre(typeCouleur) || infoBas.estLibre(typeCouleur))
				&& (nbPionVerticale(typeCouleur) == 2);
	}
	public boolean estDeuxHorizontaleLibre(TypeCouleur typeCouleur) {

		return (infoDroite.estLibre(typeCouleur) || infoGauche.estLibre(typeCouleur))
				&& (nbPionHorizontale(typeCouleur) == 2);
	}

	public boolean estDeuxDiagonaleDroiteLibreLibre(TypeCouleur typeCouleur) {
		// Log.print(1010, infoDiagonaleHautGauche.toString());
		return (
				(infoDiagonaleHautGauche.estLibre(typeCouleur) && infoDiagonaleBasDroite.estLibre2Cases(typeCouleur))
				|| 
				(infoDiagonaleBasDroite.estLibre(typeCouleur) && (infoDiagonaleHautGauche.estLibre2Cases(typeCouleur)))
				)
				&& (nbPionDiagonaleDroite(typeCouleur) == 2);
	}
	public boolean estDeuxDiagonaleGaucheLibreLibre(TypeCouleur typeCouleur) {
		return (
				(infoDiagonaleHautDroite.estLibre(typeCouleur) && infoDiagonaleBasGauche.estLibre2Cases(typeCouleur))
				|| 
				(infoDiagonaleBasGauche.estLibre(typeCouleur) && (infoDiagonaleHautDroite.estLibre2Cases(typeCouleur)))
				)
				&& (nbPionDiagonaleGauche(typeCouleur) == 2);
	}
	public boolean estDeuxVerticaleLibreLibre(TypeCouleur typeCouleur) {
		return (
				(infoHaut.estLibre(typeCouleur) && infoBas.estLibre2Cases(typeCouleur))
				|| 
				(infoBas.estLibre(typeCouleur) && (infoHaut.estLibre2Cases(typeCouleur)))
				)
				&& (nbPionVerticale(typeCouleur) == 2);
	}
	public boolean estDeuxHorizontaleLibreLibre(TypeCouleur typeCouleur) {
		return (
				(infoDroite.estLibre(typeCouleur) && infoGauche.estLibre2Cases(typeCouleur))
				|| 
				(infoGauche.estLibre(typeCouleur) && (infoDroite.estLibre2Cases(typeCouleur)))
				)
				&& (nbPionHorizontale(typeCouleur) == 2);
	}

	public boolean estTrois(TypeCouleur typeCouleur) {
		return estTroisDiagonaleDroite(typeCouleur) || estTroisDiagonaleGauche(typeCouleur)
				|| estTroisHorizontale(typeCouleur) || estTroisVerticale(typeCouleur);
	}
	public boolean estTroisLibre(TypeCouleur typeCouleur) {
		return estTroisDiagonaleDroiteLibre(typeCouleur) || estTroisDiagonaleGaucheLibre(typeCouleur)
				|| estTroisHorizontaleLibre(typeCouleur) || estTroisVerticaleLibre(typeCouleur);
	}
	public boolean estTroisLibreLibre(TypeCouleur typeCouleur) {
		return estTroisDiagonaleDroiteLibreLibre(typeCouleur) || estTroisDiagonaleGaucheLibreLibre(typeCouleur)
				|| estTroisHorizontaleLibreLibre(typeCouleur) || estTroisVerticaleLibreLibre(typeCouleur);
	}
	
	
	public boolean estTroisFoisTrois(TypeCouleur typeCouleur) {
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
	public boolean estTroisFoisTroisLibre(TypeCouleur typeCouleur) {
		
		if (estTroisDiagonaleDroiteLibre(typeCouleur)) {
			if (estTroisDiagonaleGaucheLibre(typeCouleur)) {
				return true;
			} else 
				if (estTroisHorizontaleLibre(typeCouleur)) {
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
	public boolean estTroisFoisTroisLibreLibre(TypeCouleur typeCouleur) {
		if (estTroisDiagonaleDroiteLibreLibre(typeCouleur)) {
			if (estTroisDiagonaleGaucheLibreLibre(typeCouleur)) {
				return true;
			} else if (estTroisHorizontaleLibreLibre(typeCouleur)) {
				return true;
			} else if (estTroisVerticaleLibreLibre(typeCouleur)) {
				return true;
			}

		} else if (estTroisDiagonaleGaucheLibreLibre(typeCouleur)) {
			if (estTroisHorizontaleLibreLibre(typeCouleur)) {
				return true;
			} else if (estTroisVerticaleLibreLibre(typeCouleur)) {
				return true;
			}
		} else if (estTroisHorizontaleLibreLibre(typeCouleur)) {
			if (estTroisVerticaleLibreLibre(typeCouleur)) {
				return true;
			}
		}

		return false;
	}

	public boolean estQuatre(TypeCouleur typeCouleur) {
		return estQuatreDiagonaleDroite(typeCouleur) || estQuatreDiagonaleGauche(typeCouleur)
				|| estQuatreHorizontale(typeCouleur) || estQuatreVerticale(typeCouleur);
	}
	public boolean estQuatreLibreContinuIAFacile(TypeCouleur typeCouleur){
		return estQuatreDiagonaleDroiteLibreContinuIAFacile(typeCouleur) || estQuatreDiagonaleGaucheLibreContinuIAFacile(typeCouleur)
				|| estQuatreHorizontaleLibreContinuIAFacile(typeCouleur) || estQuatreVerticaleLibreContinuIAFacile(typeCouleur);

	}
	public boolean estQuatreLibre(TypeCouleur typeCouleur) {
		return estQuatreDiagonaleDroiteLibre(typeCouleur) || estQuatreDiagonaleGaucheLibre(typeCouleur)
				|| estQuatreHorizontaleLibre(typeCouleur) || estQuatreVerticaleLibre(typeCouleur);
	}

	
	public boolean estTroisDiagonaleDroiteLibre(TypeCouleur typeCouleur) {
		// Log.print(1010, infoDiagonaleHautGauche.toString());
		return (infoDiagonaleHautGauche.estLibre(typeCouleur) || infoDiagonaleBasDroite.estLibre(typeCouleur))
				&& (nbPionDiagonaleDroite(typeCouleur) == 3);
	}
	public boolean estTroisDiagonaleGaucheLibre(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautDroite.estLibre(typeCouleur) || infoDiagonaleBasGauche.estLibre(typeCouleur))
				&& (nbPionDiagonaleGauche(typeCouleur) == 3);
	}
	public boolean estTroisVerticaleLibre(TypeCouleur typeCouleur) {

		return (infoHaut.estLibre(typeCouleur) || infoBas.estLibre(typeCouleur))
				&& (nbPionVerticale(typeCouleur) == 3);
	}
	public boolean estTroisHorizontaleLibre(TypeCouleur typeCouleur) {

		return (infoDroite.estLibre(typeCouleur) || infoGauche.estLibre(typeCouleur))
				&& (nbPionHorizontale(typeCouleur) == 3);
	}

	public boolean estTroisDiagonaleDroiteLibreLibre(TypeCouleur typeCouleur) {
		return (
				(infoDiagonaleHautGauche.estLibre(typeCouleur) && infoDiagonaleBasDroite.estLibre2Cases(typeCouleur))
				|| 
				(infoDiagonaleBasDroite.estLibre(typeCouleur) && (infoDiagonaleHautGauche.estLibre2Cases(typeCouleur)))
				)
				&& (nbPionDiagonaleDroite(typeCouleur) == 3);
	}
	public boolean estTroisDiagonaleGaucheLibreLibre(TypeCouleur typeCouleur) {
		return (
				(infoDiagonaleHautDroite.estLibre(typeCouleur) && infoDiagonaleBasGauche.estLibre2Cases(typeCouleur))
				|| 
				(infoDiagonaleBasGauche.estLibre(typeCouleur) && (infoDiagonaleHautDroite.estLibre2Cases(typeCouleur)))
				)
				&& (nbPionDiagonaleGauche(typeCouleur) == 3);

	}
	public boolean estTroisVerticaleLibreLibre(TypeCouleur typeCouleur) {
		return (
				(infoHaut.estLibre(typeCouleur) && infoBas.estLibre2Cases(typeCouleur))
				|| 
				(infoBas.estLibre(typeCouleur) && (infoHaut.estLibre2Cases(typeCouleur)))
				)
				&& (nbPionVerticale(typeCouleur) == 3);
	}
	public boolean estTroisHorizontaleLibreLibre(TypeCouleur typeCouleur) {
		return ((
				(infoDroite.estLibre(typeCouleur) && infoGauche.estLibre2Cases(typeCouleur))
				|| 
				(infoGauche.estLibre(typeCouleur) && (infoDroite.estLibre2Cases(typeCouleur)))
				)
				&& (nbPionHorizontale(typeCouleur) == 3));
	}

	
	public boolean estTroisDiagonaleDroite(TypeCouleur typeCouleur) {
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
	
	
	public boolean estQuatreDiagonaleDroiteLibreContinuIAFacile(TypeCouleur typeCouleur) {
		return ((infoDiagonaleHautGauche.estLibre(typeCouleur) && infoDiagonaleBasDroite.estLibre(typeCouleur))
				&& (nbPionDiagonaleDroiteContinu(typeCouleur) == 4));
	}
	public boolean estQuatreDiagonaleGaucheLibreContinuIAFacile(TypeCouleur typeCouleur) {
		return ((infoDiagonaleHautDroite.estLibre(typeCouleur) && infoDiagonaleBasGauche.estLibre(typeCouleur))
				&& (nbPionDiagonaleGaucheContinu(typeCouleur) == 4));
	}
	public boolean estQuatreVerticaleLibreContinuIAFacile(TypeCouleur typeCouleur) {
		return ((infoHaut.estLibre(typeCouleur) && infoBas.estLibre(typeCouleur))
				&& (nbPionVerticaleContinu(typeCouleur) == 4));
	}
	public boolean estQuatreHorizontaleLibreContinuIAFacile(TypeCouleur typeCouleur) {
		return ((infoDroite.estLibre(typeCouleur) && infoGauche.estLibre(typeCouleur))
				&& (nbPionHorizontaleContinu(typeCouleur) == 4));
	}

	public boolean estQuatreDiagonaleDroiteLibreLibree(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautGauche.estLibre(typeCouleur) && infoDiagonaleBasDroite.estLibre(typeCouleur))
				&& (nbPionDiagonaleDroite(typeCouleur) == 4);
	}
	public boolean estQuatreDiagonaleGaucheLibreLibre(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautDroite.estLibre(typeCouleur) && infoDiagonaleBasGauche.estLibre(typeCouleur))
				&& (nbPionDiagonaleGauche(typeCouleur) == 4);
	}
	public boolean estQuatreVerticaleLibreLibre(TypeCouleur typeCouleur) {
		return (infoHaut.estLibre(typeCouleur) && infoBas.estLibre(typeCouleur))
				&& (nbPionVerticale(typeCouleur) == 4);
	}
	public boolean estQuatreHorizontaleLibreLibre(TypeCouleur typeCouleur) {
		return (infoDroite.estLibre(typeCouleur) && infoGauche.estLibre(typeCouleur))
				&& (nbPionHorizontale(typeCouleur) == 4);
	}


	public boolean estQuatreDiagonaleDroiteLibre(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautGauche.estLibre(typeCouleur) || infoDiagonaleBasDroite.estLibre(typeCouleur))
				&& (nbPionDiagonaleDroite(typeCouleur) == 4);
	}
	public boolean estQuatreDiagonaleGaucheLibre(TypeCouleur typeCouleur) {
		return (infoDiagonaleHautDroite.estLibre(typeCouleur) || infoDiagonaleBasGauche.estLibre(typeCouleur))
				&& (nbPionDiagonaleGauche(typeCouleur) == 4);
	}
	public boolean estQuatreVerticaleLibre(TypeCouleur typeCouleur) {
		return ((infoHaut.estLibre(typeCouleur) || infoBas.estLibre(typeCouleur)))
				&& (nbPionVerticale(typeCouleur) == 4);
	}
	public boolean estQuatreHorizontaleLibre(TypeCouleur typeCouleur) {
		return (infoDroite.estLibre(typeCouleur) || infoGauche.estLibre(typeCouleur))
				&& (nbPionHorizontale(typeCouleur) == 4);
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

	public boolean estQuatreQuatreVerticale(TypeCouleur typeCouleur){
		if(getInfoHaut().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		if(getInfoBas().getNbPionNonContinu(typeCouleur) == 0){
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
		
		if(getInfoDroite().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		if(getInfoGauche().getNbPionNonContinu(typeCouleur) == 0){
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
		
		if(getInfoDiagonaleHautDroite().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		if(getInfoDiagonaleBasGauche().getNbPionNonContinu(typeCouleur) == 0){
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
		
		if(getInfoDiagonaleHautGauche().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		if(getInfoDiagonaleBasDroite().getNbPionNonContinu(typeCouleur) == 0){
			return false;
		}
		
		if(nbPionDiagonaleDroiteContinu(typeCouleur) + getInfoDiagonaleHautGauche().getNbPionNonContinu(typeCouleur) == 4 ){
			if(nbPionVerticaleContinu(typeCouleur) + getInfoDiagonaleBasDroite().getNbPionNonContinu(typeCouleur) == 4){
				return true;
			}
		}
		return false;
	}

	public boolean estQuatreFoisQuatre(TypeCouleur typeCouleur) {

		if(estQuatreQuatreDiagonaleDroite(typeCouleur)){
			Log.print(1010, "estQuatreQuatreDiagonaleDroite " + estQuatreQuatreDiagonaleDroite(typeCouleur));
			return true;
		}
		if(estQuatreQuatreDiagonaleGauche(typeCouleur)){
			Log.print(1010, "estQuatreQuatreDiagonaleGauche " + estQuatreQuatreDiagonaleGauche(typeCouleur));
			return true;
		}
		if(estQuatreQuatreHorizontale(typeCouleur)){
			Log.print(1010, "estQuatreQuatreHorizontale " + estQuatreQuatreHorizontale(typeCouleur));
			return true;
		}
		if(estQuatreQuatreVerticale(typeCouleur)){
			Log.print(1010, "estQuatreQuatreVerticale " + estQuatreQuatreVerticale(typeCouleur));
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
	public boolean estSixSept(TypeCouleur typeCouleur) {
		
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

	public boolean estDeuxFoisUn(TypeCouleur tc) {
		if(estDeuxHorizontale(tc)){
			if(estUnDiagonaleDroite(tc)||estUnVerticale(tc)||estUnDiagonaleGauche(tc)){
				return true;
			}
		}
		if(estDeuxDiagonaleDroite(tc)){
			if(estUnVerticaleLibre(tc)||estUnDiagonaleGauche(tc)||estUnHorizontale(tc)){
				return true;
			}
		}
		if(estDeuxVerticale(tc)){
			if(estUnDiagonaleGauche(tc)||estUnHorizontale(tc)||estUnDiagonaleDroite(tc)){
				return true;
			}
		}
		if(estDeuxDiagonaleGauche(tc)){
			if(estUnVerticale(tc)||estUnHorizontale(tc)||estUnDiagonaleDroite(tc)){
				return true;
			}
		}
		
		return false;
	}
	public boolean estDeuxFoisUnLibre(TypeCouleur tc) {
		if(estDeuxHorizontaleLibre(tc)){
			if(estUnDiagonaleDroiteLibre(tc)||estUnVerticaleLibre(tc)||estUnDiagonaleGaucheLibre(tc)){
				return true;
			}
		}
		if(estDeuxDiagonaleDroiteLibre(tc)){
			if(estUnVerticaleLibreLibre(tc)||estUnDiagonaleGaucheLibre(tc)||estUnHorizontaleLibre(tc)){
				return true;
			}
		}
		if(estDeuxVerticaleLibre(tc)){
			if(estUnDiagonaleGaucheLibre(tc)||estUnHorizontaleLibre(tc)||estUnDiagonaleDroiteLibre(tc)){
				return true;
			}
		}
		if(estDeuxDiagonaleGaucheLibre(tc)){
			if(estUnVerticaleLibre(tc)||estUnHorizontaleLibre(tc)||estUnDiagonaleDroiteLibre(tc)){
				return true;
			}
		}
		
		return false;
	}
	public boolean estDeuxFoisUnLibreLibre(TypeCouleur tc) {
		if(estDeuxHorizontaleLibreLibre(tc)){
			if(estUnDiagonaleDroiteLibreLibre(tc)||estUnVerticaleLibreLibre(tc)||estUnDiagonaleGaucheLibreLibre(tc)){
				return true;
			}
		}
		if(estDeuxDiagonaleDroiteLibreLibre(tc)){
			if(estUnVerticaleLibreLibre(tc)||estUnDiagonaleGaucheLibreLibre(tc)||estUnHorizontaleLibreLibre(tc)){
				return true;
			}
		}
		if(estDeuxVerticaleLibreLibre(tc)){
			if(estUnDiagonaleGaucheLibreLibre(tc)||estUnHorizontaleLibreLibre(tc)||estUnDiagonaleDroiteLibreLibre(tc)){
				return true;
			}
		}
		if(estDeuxDiagonaleGaucheLibreLibre(tc)){
			if(estUnVerticaleLibreLibre(tc)||estUnHorizontaleLibreLibre(tc)||estUnDiagonaleDroiteLibreLibre(tc)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean estTroisFoisDeux(TypeCouleur tc) {
		if(estTroisHorizontale(tc)){
			if(estDeuxDiagonaleDroite(tc)||estDeuxVerticale(tc)||estDeuxDiagonaleGauche(tc)){
				return true;
			}
		}
		if(estTroisDiagonaleDroite(tc)){
			if(estDeuxVerticaleLibre(tc)||estDeuxDiagonaleGauche(tc)||estDeuxHorizontale(tc)){
				return true;
			}
		}
		if(estTroisVerticale(tc)){
			if(estDeuxDiagonaleGauche(tc)||estDeuxHorizontale(tc)||estDeuxDiagonaleDroite(tc)){
				return true;
			}
		}
		if(estTroisDiagonaleGauche(tc)){
			if(estDeuxVerticale(tc)||estDeuxHorizontale(tc)||estDeuxDiagonaleDroite(tc)){
				return true;
			}
		}
		
		return false;
	}
	public boolean estTroisFoisDeuxLibre(TypeCouleur tc) {
		if(estTroisHorizontaleLibre(tc)){
			if(estDeuxDiagonaleDroiteLibre(tc)||estDeuxVerticaleLibre(tc)||estDeuxDiagonaleGaucheLibre(tc)){
				return true;
			}
		}
		if(estTroisDiagonaleDroiteLibre(tc)){
			if(estDeuxVerticaleLibreLibre(tc)||estDeuxDiagonaleGaucheLibre(tc)||estDeuxHorizontaleLibre(tc)){
				return true;
			}
		}
		if(estTroisVerticaleLibre(tc)){
			if(estDeuxDiagonaleGaucheLibre(tc)||estDeuxHorizontaleLibre(tc)||estDeuxDiagonaleDroiteLibre(tc)){
				return true;
			}
		}
		if(estTroisDiagonaleGaucheLibre(tc)){
			if(estDeuxVerticaleLibre(tc)||estDeuxHorizontaleLibre(tc)||estDeuxDiagonaleDroiteLibre(tc)){
				return true;
			}
		}
		
		return false;
	}
	public boolean estTroisFoisDeuxLibreLibre(TypeCouleur tc) {
		if(estTroisHorizontaleLibreLibre(tc)){
			if(estDeuxDiagonaleDroiteLibreLibre(tc)||estDeuxVerticaleLibreLibre(tc)||estDeuxDiagonaleGaucheLibreLibre(tc)){
				return true;
			}
		}
		if(estTroisDiagonaleDroiteLibreLibre(tc)){
			if(estDeuxVerticaleLibreLibre(tc)||estDeuxDiagonaleGaucheLibreLibre(tc)||estDeuxHorizontaleLibreLibre(tc)){
				return true;
			}
		}
		if(estTroisVerticaleLibreLibre(tc)){
			if(estDeuxDiagonaleGaucheLibreLibre(tc)||estDeuxHorizontaleLibreLibre(tc)||estDeuxDiagonaleDroiteLibreLibre(tc)){
				return true;
			}
		}
		if(estTroisDiagonaleGaucheLibreLibre(tc)){
			if(estDeuxVerticaleLibreLibre(tc)||estDeuxHorizontaleLibreLibre(tc)||estDeuxDiagonaleDroiteLibreLibre(tc)){
				return true;
			}
		}
		
		return false;
	}

	public boolean estQuatreFoisTrois(TypeCouleur tc) {
		if(estQuatreHorizontaleLibre(tc)){
			Log.print(1010, "estQuatreHorizontaleLibre " + estQuatreHorizontaleLibre(tc));
			if(estTroisDiagonaleDroiteLibreLibre(tc)||estTroisVerticaleLibreLibre(tc)||estTroisDiagonaleGaucheLibreLibre(tc)){
				return true;
			}
		}
		if(estQuatreDiagonaleDroiteLibre(tc)){
			if(estTroisVerticaleLibreLibre(tc)||estTroisDiagonaleGaucheLibreLibre(tc)||estTroisHorizontaleLibreLibre(tc)){
				return true;
			}
		}
		if(estQuatreVerticaleLibre(tc)){
			if(estTroisDiagonaleDroiteLibreLibre(tc)||estTroisDiagonaleGaucheLibreLibre(tc)||estTroisHorizontaleLibreLibre(tc)){
				return true;
			}
		}
		if(estQuatreDiagonaleGaucheLibre(tc)){
			if(estTroisDiagonaleDroiteLibreLibre(tc)||estTroisVerticaleLibreLibre(tc)||estTroisHorizontaleLibreLibre(tc)){
				return true;
			}
		}
		
		return false;
	}

	public String toString(){
		String buffer = "";
		
		buffer += infoGauche.toString() + "\n";
		buffer += infoDiagonaleHautGauche.toString() + "\n";
		buffer += infoHaut.toString() + "\n";
		buffer += infoDiagonaleHautDroite.toString() + "\n";
		buffer += infoDroite.toString() + "\n";
		buffer += infoDiagonaleBasDroite.toString() + "\n";
		buffer += infoBas.toString() + "\n";
		buffer += infoDiagonaleBasGauche.toString() + "\n";
		return buffer;
	}

}
