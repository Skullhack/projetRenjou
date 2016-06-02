/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import java.awt.Point;
import java.util.ArrayList;
import Enum.*;
import Utilitaire.Coordonnees;
import Utilitaire.Log;
import Utilitaire.Motif;
import Utilitaire.MotifsReconnus;
import Utilitaire.PlateauDeJeu;
import Utilitaire.Tabou;

public class IAMoyenne extends IA {
	ArrayList<Coordonnees> coups;
	int profondeurMax;
	Motif motif;
	MotifsReconnus[] mr;
	boolean[] presenceMotifBlanc;
	boolean[] presenceMotifNoir;

	public IAMoyenne(TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
		super(type, nbPion, couleurJoueur);
		init();
	}

	private void init() {
		coups = new ArrayList<>();
		profondeurMax = 3;
		motif = new Motif();
		mr = MotifsReconnus.values();
		presenceMotifBlanc = new boolean[mr.length];
		presenceMotifNoir = new boolean[mr.length];
	}

	public Point play(int[][] plateau, int couleurJoueur, boolean tabou3x3, boolean tabou4x4, boolean tabouOverline) {
		initPlateauEtCouleur(plateau, couleurJoueur);
		Coordonnees c = jouer(pdj);
		return new Point(c.getColonne(), c.getLigne());
	}

	public TypeCase getTypeCase(TypeCouleur tc) {
		if (tc == TypeCouleur.Blanc)
			return TypeCase.PionBlanc;

		return TypeCase.PionNoir;
	}

	public TypeCase getAutreTypeCase(TypeCouleur tc) {
		if (tc == TypeCouleur.Blanc)
			return TypeCase.PionNoir;

		return TypeCase.PionBlanc;
	}

	public TypeCase autreTypeCase(TypeCase tc) {
		if (tc == TypeCase.PionBlanc)
			return TypeCase.PionNoir;

		return TypeCase.PionBlanc;
	}

	@Override
	public Coordonnees jouer(PlateauDeJeu p) {
		Log.print(66, "Dans Jouer IAMoyenne");

		coups.clear();
		PlateauDeJeu pdj = p.clone();
		Log.print(50, p.toString() + "\nnbPionNoir= " + p.getNbPionNoir() + "\nnbPionBlanc= " + p.getNbPionBlanc());
		Log.print(50,
				pdj.toString() + "\nnbPionNoir= " + pdj.getNbPionNoir() + "\nnbPionBlanc= " + pdj.getNbPionBlanc());

		if (premierTour(pdj)) {
			return new Coordonnees(7, 7);
		}

		if (secondTour(pdj)) {
			for (int i = 6; i < 8; i++) {
				for (int j = 6; j < 8; j++) {
					if (i != 7 || j != 7) {
						coups.add(new Coordonnees(i, j));
					}
				}
			}

			r.nextInt(coups.size());
			return coups.get(r.nextInt(coups.size()));
		}

		int profondeur = profondeurMax;
		int valeur = -100000;
		int valeurtemp;
		TypeCase tc = this.getTypeCase(this.couleur);

		// parcours des coups jouable
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {

				// Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un
				// pion existant
				if (EstJouable(pdj, i,j, 2) && (tc != TypeCase.PionNoir || Tabou.estValide(pdj, i, j, true, true, true))) {
					pdj.ajouter(i,j, tc);
					Log.print(1, pdj.toString());
					if (PartieFinie(pdj, i,j, tc)) {
						// on peut couper l�, le coup est gagnant.
						Log.print(1, "dans jouer " + i + " " + j + " gagnant en profondeur " + (profondeurMax - profondeur));
						return new Coordonnees(i,j);
					} else {
						// valeurtemp = 0;
						valeurtemp = EvaluerCoupAdversaire(pdj, profondeur - 1, this.autreTypeCase(tc));
						Log.print(1, "valeurtemp=" + valeurtemp + " i= " + i + " j= " + j);

					}
					pdj.enlever(i,j);
					if (valeurtemp == valeur) {
						Log.print(1, "dans egal valeurtemp=" + valeurtemp + " i= " + i + " j= " + j);
						coups.add(new Coordonnees(i,j));
						Log.print(1, coups.toString());
					} else if (valeurtemp > valeur) {
						Log.print(1, "dans sup valeurtemp=" + valeurtemp + " i= " + i + " j= " + j);
						coups.clear();
						coups.add(new Coordonnees(i,j));
						valeur = valeurtemp;
						Log.print(1, coups.toString());
					}

				}
			}
		}
		Log.print(1, coups.toString());

		if (coups.isEmpty()) {
			return new Coordonnees(-1, -1);
		}

		r.nextInt(coups.size());
		return coups.get(r.nextInt(coups.size()));

	}


	public int Evaluation(PlateauDeJeu pdj, TypeCase tc) {

		TypeCouleur blanc = TypeCouleur.Blanc;
		TypeCouleur noir = TypeCouleur.Noir;
		viderPresenceMotif();
		boolean coupGagnantBlanc = false;
		boolean coupGagnantNoir = false;
		
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				if (pdj.getTypeCaseTableau(i, j) == TypeCase.PionBlanc
						|| pdj.getTypeCaseTableau(i, j) == TypeCase.PionNoir) {
					motif.setMotif(pdj, i,j);
					if (pdj.getTypeCaseTableau(i,j) == TypeCase.PionBlanc && !coupGagnantBlanc) {
						// POUR BLANC
						for (int k = 0; k < mr.length; k++) {
							if (!presenceMotifBlanc[k]) {
								if (mr[k].verif(motif, blanc)) {
									Log.print(1, "i= " + i + " j= " + j + " dans Blanc " + mr[k].name());
									presenceMotifBlanc[k] = true;
								}
							}
						}

						// Log.print(501, "CGB = "+coupGagnantBlanc + " CG2CB =
						// " + coupGagnant2CoupsBlanc );
						coupGagnantBlanc = CoupGagnant(presenceMotifBlanc);
					}

					if (pdj.getTypeCaseTableau(i,j) == TypeCase.PionNoir && !coupGagnantNoir) {
						// POUR NOIR
						for (int k = 0; k < mr.length; k++) {
							if (!presenceMotifNoir[k]) {
								if (mr[k].verif(motif, noir)) {
									Log.print(1, "i= " + i + " j= " + j + " dans Noir " + mr[k].name());
									presenceMotifNoir[k] = true;
								}
							}
						}
						// Log.print(501, "CGN = "+coupGagnantNoir + " CG2CN = "
						// + coupGagnant2CoupsNoir );
						coupGagnantNoir = CoupGagnant(presenceMotifNoir);
					}

				}

			}
		}
		if(couleur == TypeCouleur.Blanc){
			return Valeur(presenceMotifBlanc) - Valeur(presenceMotifNoir);
		}else{
			return Valeur(presenceMotifNoir) - Valeur(presenceMotifBlanc);
		}
	}

	private void viderPresenceMotif() {
		for(int i=0;i<mr.length;i++){
			presenceMotifBlanc[i] = false;
			presenceMotifNoir[i] = false;
		}
		
	}

	public int Evaluation2(PlateauDeJeu pdj, TypeCase tc) {

		TypeCouleur blanc = TypeCouleur.Blanc;
		TypeCouleur noir = TypeCouleur.Noir;
		MotifsReconnus[] mr = MotifsReconnus.values();
		boolean[] presenceMotifBlanc = new boolean[mr.length];
		boolean[] presenceMotifNoir = new boolean[mr.length];
		boolean coupGagnantBlanc = false;
		boolean coupGagnantNoir = false;
		boolean coupGagnant2CoupsBlanc = false;
		boolean coupGagnant2CoupsNoir = false;

		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				Coordonnees c = new Coordonnees(i, j);
				if (pdj.getTypeCaseTableau(i, j) == TypeCase.PionBlanc
						|| pdj.getTypeCaseTableau(i, j) == TypeCase.PionNoir) {
					Motif m = new Motif(pdj, c);

					if (pdj.getTypeCaseTableau(c) == TypeCase.PionBlanc && !coupGagnantBlanc) {
						// POUR BLANC
						for (int k = 0; k < mr.length; k++) {
							if (!presenceMotifBlanc[k]) {
								if (mr[k].verif(m, blanc)) {
									Log.print(501, "i= " + i + " j= " + j + " dans Blanc " + mr[k].name());
									presenceMotifBlanc[k] = true;
								}
							}
						}

						coupGagnantBlanc = CoupGagnant(presenceMotifBlanc);
						coupGagnant2CoupsBlanc = CoupGagnant2Coups(presenceMotifBlanc);
						// Log.print(501, "CGB = "+coupGagnantBlanc + " CG2CB =
						// " + coupGagnant2CoupsBlanc );
					}

					if (pdj.getTypeCaseTableau(c) == TypeCase.PionNoir && !coupGagnantNoir) {
						// POUR NOIR
						for (int k = 0; k < mr.length; k++) {
							if (!presenceMotifNoir[k]) {
								if (mr[k].verif(m, noir)) {
									Log.print(501, "i= " + i + " j= " + j + " dans Noir " + mr[k].name());
									presenceMotifNoir[k] = true;
								}
							}
						}
						coupGagnantNoir = CoupGagnant(presenceMotifNoir);
						coupGagnant2CoupsNoir = CoupGagnant2Coups(presenceMotifNoir);
						// Log.print(501, "CGN = "+coupGagnantNoir + " CG2CN = "
						// + coupGagnant2CoupsNoir );
					}

				}

			}
		}

		// si c'est a blanc de jouer
		if (tc == TypeCase.PionBlanc) {
			// si l'ia est blanc
			if (couleur == TypeCouleur.Blanc) {
				if (coupGagnantBlanc) {
					return 30000;
				} else if (coupGagnantNoir) {
					return -30000;
				} else if (coupGagnant2CoupsBlanc) {
					return 20000;
				} else if (coupGagnant2CoupsNoir) {
					return -20000;
				} else {
					return Valeur(presenceMotifBlanc) - Valeur(presenceMotifNoir);
				}
			} else { // si l'ia est noir
				if (coupGagnantBlanc) {
					return -30000;
				} else if (coupGagnantNoir) {
					return 30000;
				} else if (coupGagnant2CoupsBlanc) {
					return -20000;
				} else if (coupGagnant2CoupsNoir) {
					return 20000;
				} else {
					return Valeur(presenceMotifNoir) - Valeur(presenceMotifBlanc);
				}
			}
		} else { // si c'est a noir de jouer
					// si ia.couleur = noir
			if (couleur == TypeCouleur.Noir) {
				if (coupGagnantNoir) {
					return 20000;
				} else if (coupGagnantBlanc) {
					return -20000;
				} else if (coupGagnant2CoupsNoir) {
					return 20000;
				} else if (coupGagnant2CoupsBlanc) {
					return -20000;
				} else {
					return Valeur(presenceMotifNoir) - Valeur(presenceMotifBlanc);
				}
			} else { // si l'ia est blanc
				if (coupGagnantNoir) {
					return -20000;
				} else if (coupGagnantBlanc) {
					return 20000;
				} else if (coupGagnant2CoupsNoir) {
					return -20000;
				} else if (coupGagnant2CoupsBlanc) {
					return 20000;
				} else {
					return Valeur(presenceMotifBlanc) - Valeur(presenceMotifNoir);
				}
			}
		}

	}

	public int EvaluerCoupAdversaire(PlateauDeJeu pdj, int profondeur, TypeCase tc) {
		if (profondeur == 0)
			return Evaluation(pdj, tc);

		int valeur = 1000000;
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {

				// Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un
				// pion existant
				if (EstJouable(pdj, i,j, 2) && (tc != TypeCase.PionNoir || Tabou.estValide(pdj, i,j, true, true, true))) {

					pdj.ajouter(i,j, tc);
					// Log.print(695, pdj.toString());
					if (PartieFinie(pdj, i,j, tc)) {
						// on peut couper l�, le coup est perdant.
						pdj.enlever(i,j);

						// Log.print(695, "dans evalCoupAdv " + c + " gagnant en
						// profondeur " + (profondeurMax -profondeur));
						return -40000;
					} else {
						int eval = EvaluerCoupIA(pdj, profondeur - 1, this.autreTypeCase(tc));
						// Log.print(1, "evalIA: "+eval + " valeur: " + valeur +
						// " prend min ");
						if(eval < valeur)
							valeur = eval;
					}
					pdj.enlever(i,j);
				}
			}
		}

		return valeur;
	}

	public int EvaluerCoupIA(PlateauDeJeu pdj, int profondeur, TypeCase tc) {
		if (profondeur == 0)
			return Evaluation(pdj, tc);

		int valeur = -1000000;
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				// Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un
				// pion existant
				if (EstJouable(pdj, i,j, 2) && (tc != TypeCase.PionNoir || Tabou.estValide(pdj, i,j, true, true, true))) {
					pdj.ajouter(i,j, tc);
					if (PartieFinie(pdj, i,j, tc)) {
						// on peut couper l�, le coup est gagnant.
						pdj.enlever(i,j);
						// Log.print(695, "dans evalCoupIA " + c + " gagnant en
						// profondeur " + (profondeurMax -profondeur));
						return 40000;
					} else {
						int eval = EvaluerCoupAdversaire(pdj, profondeur - 1, this.autreTypeCase(tc));
						// Log.print(1, "evalAd: "+eval + " valeur: " + valeur +
						// " prend max ");
						
						if(eval > valeur)
							valeur = eval;
					}
					pdj.enlever(i,j);
				}
			}
		}
		return valeur;
	}

	private boolean CoupGagnant(boolean[] tab) {
		return tab[MotifsReconnus.estQuatreLibre.ordinal()];
	}

	private boolean CoupGagnant2Coups(boolean[] tab) {
		return tab[MotifsReconnus.estTroisFoisTroisLibreLibre.ordinal()]
				|| tab[MotifsReconnus.estTroisLibreLibre.ordinal()] || tab[MotifsReconnus.estQuatreFoisTrois.ordinal()];
	}

	private int Valeur(boolean[] tab) {
		int v = 0;
		
		if (tab[MotifsReconnus.estQuatreLibre.ordinal()]) {
			v += 20000;
		} else if (tab[MotifsReconnus.estTroisFoisTroisLibreLibre.ordinal()]) {
			v += 10000;
		} else if (tab[MotifsReconnus.estTroisLibreLibre.ordinal()]) {
			v += 10000;
		} else if (tab[MotifsReconnus.estQuatreFoisTrois.ordinal()]) {
			v += 10000;
		} else if (tab[MotifsReconnus.estTroisFoisDeuxLibreLibre.ordinal()]) {
			v += 5000;
		} else if (tab[MotifsReconnus.estTroisFoisDeuxLibre.ordinal()]) {
			v += 600;
		} else if (tab[MotifsReconnus.estDeuxFoisDeuxLibreLibre.ordinal()]) {
			v += 500;
		} else if (tab[MotifsReconnus.estTroisFoisTroisLibre.ordinal()]) {
			v += 100;
		}else if (tab[MotifsReconnus.estTroisFoisDeux.ordinal()]) {
			v += 75;
		} else if (tab[MotifsReconnus.estTroisLibre.ordinal()]) {
			v += 50;
		} 
		
		Log.print(505, "valeur= " + v);
		return v;
	}

	private int Valeur2(boolean[] tab) {
		int v = 0;

		if (tab[MotifsReconnus.estTroisFoisDeuxLibreLibre.ordinal()]) {
			// Log.print(501, "+5000");
			v += 5000;
		} else if (tab[MotifsReconnus.estTroisFoisDeuxLibre.ordinal()]) {
			// Log.print(501, "+600");
			v += 600;
		} else if (tab[MotifsReconnus.estDeuxFoisDeuxLibreLibre.ordinal()]) {
			// Log.print(501, "+500");
			v += 500;
		}else if (tab[MotifsReconnus.estTroisFoisDeux.ordinal()]) {
			// Log.print(501, "+75");
			v += 75;
		}else if (tab[MotifsReconnus.estTroisLibre.ordinal()]) {
			// Log.print(501, "+50");
			v += 50;
		} 

		Log.print(505, "valeur= " + v);
		return v;
	}

}
