/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import Controleur.*;
import Enum.*;
import Utilitaire.Coordonnees;
import Utilitaire.Log;
import Utilitaire.Motif;
import Utilitaire.PlateauDeJeu;
import Utilitaire.Tabou;

public class IAMoyenne extends IA {
	ArrayList<Coordonnees> coups;
	int profondeurMax;

	public IAMoyenne(TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
		super(type, nbPion, couleurJoueur);
		init();
	}

	private void init() {
		coups = new ArrayList<>();
		profondeurMax = 4;
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
		Log.print(1, p.toString() + "\nnbPionNoir= " + p.getNbPionNoir() + "\nnbPionBlanc= " + p.getNbPionBlanc());
		Log.print(1,
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
				Coordonnees c = new Coordonnees(i, j);
				// Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un
				// pion existant
				if (EstJouable(pdj, c)) {
					pdj.ajouter(c, tc);
					Log.print(1, pdj.toString());
					if (PartieFinie(pdj, c)) {
						// on peut couper l�, le coup est gagnant.
						Log.print(1, "dans jouer " + c + " gagnant en profondeur " + (profondeurMax - profondeur));
						return c;
					} else {
						// valeurtemp = 0;
						valeurtemp = EvaluerCoupAdversaire(pdj, profondeur - 1, this.autreTypeCase(tc));
						Log.print(1, "valeurtemp=" + valeurtemp + " " + c);

					}
					pdj.enlever(c);
					if (valeurtemp == valeur) {
						Log.print(1, "dans egal valeurtemp=" + valeurtemp + " " + c);
						coups.add(c);
						Log.print(1, coups.toString());
					} else if (valeurtemp > valeur) {
						Log.print(1, "dans sup valeurtemp=" + valeurtemp + " " + c);
						coups.clear();
						coups.add(c);
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
		int valeurBlanc = 0;
		TypeCouleur noir = TypeCouleur.Noir;
		int valeurNoir = 0;
		boolean[] presenceMotifBlanc = new boolean[14];
		boolean[] presenceMotifNoir = new boolean[14];
		boolean coupGagnantBlanc = false;
		boolean coupGagnantNoir = false;
		
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				Coordonnees c = new Coordonnees(i,j);
				if (pdj.getTypeCaseTableauParLigneColonne(i,j) == TypeCase.PionBlanc
						|| pdj.getTypeCaseTableauParLigneColonne(i,j) == TypeCase.PionNoir) {
					Motif m = new Motif(pdj, c);

					if (pdj.getTypeCaseTableau(c) == TypeCase.PionBlanc && !coupGagnantBlanc) {

						// POUR BLANC
						if(!presenceMotifBlanc[1]){
							if(m.estTroisFoisTroisLibreLibre(blanc)){
								coupGagnantBlanc = true;
								presenceMotifBlanc[1] = true;
							}
						}

						if(!presenceMotifBlanc[2]){
							if(m.estQuatreFoisQuatre(blanc)){
								coupGagnantBlanc = true;
								presenceMotifBlanc[2] = true;
							}
						}
						
						if(!presenceMotifBlanc[3]){
							if (m.estQuatreFoisTrois(blanc)) {
								coupGagnantBlanc = true;
								presenceMotifBlanc[3] = true;
							}
						}
						
						if(!presenceMotifBlanc[4]){
							if (m.estTroisFoisDeuxLibreLibre(blanc)) {
								valeurBlanc += 5000;
								presenceMotifBlanc[4] = true;
							}
						}
						
						if(!presenceMotifBlanc[5]){
							if (m.estTroisFoisDeuxLibre(blanc)) {
								valeurBlanc += 600;
								presenceMotifBlanc[5] = true;
							}
						}
						
						if(!presenceMotifBlanc[6]){
							if (m.estDeuxFoisDeuxLibreLibre(blanc)) {
								valeurBlanc += 500;
								presenceMotifBlanc[6] = true;
							}
						}
						
						if(!presenceMotifBlanc[7]){
							if (m.estTroisLibreLibre(blanc)) {
								coupGagnantBlanc = true;
								presenceMotifBlanc[7] = true;
							}
						}
						
						if(!presenceMotifBlanc[8]){
							if (m.estTroisFoisDeux(blanc)) {
								valeurBlanc += 50;
								presenceMotifBlanc[8] = true;
							}
						}
						
						if(!presenceMotifBlanc[9]){
							if (m.estDeuxFoisDeuxLibre(blanc)) {
								valeurBlanc += 50;
								presenceMotifBlanc[9] = true;
							}
						}
						
						if(!presenceMotifBlanc[10]){
							if (m.estDeuxLibreLibre(blanc)) {
								valeurBlanc += 5;
								presenceMotifBlanc[10] = true;
							}
						}
						
						if(!presenceMotifBlanc[11]){
							if (m.estDeuxFoisDeux(blanc)) {
								valeurBlanc += 5;
								presenceMotifBlanc[11] = true;
							}
						}

						if(!presenceMotifBlanc[12]){
							if (m.estTrois(blanc)) {
								valeurBlanc += 5;
								presenceMotifBlanc[12] = true;
							}
						}
						if(!presenceMotifBlanc[13]){
							if (m.estTroisLibre(blanc)) {
								valeurBlanc += 25;
								presenceMotifBlanc[13] = true;
							}
						}




					}

					if (pdj.getTypeCaseTableau(c) == TypeCase.PionNoir && !coupGagnantNoir) {
						// POUR NOIR
						if(!presenceMotifNoir[1]){
							if(m.estTroisFoisTroisLibreLibre(noir)){
								coupGagnantNoir = true;
								presenceMotifNoir[1] = true;
							}
						}

						if(!presenceMotifNoir[2]){
							if(m.estQuatreFoisQuatre(noir)){
								coupGagnantNoir = true;
								presenceMotifNoir[2] = true;
							}
						}
						
						if(!presenceMotifNoir[3]){
							if (m.estQuatreFoisTrois(noir)) {
								coupGagnantNoir = true;
								presenceMotifNoir[3] = true;
							}
						}
						
						if(!presenceMotifNoir[4]){
							if (m.estTroisFoisDeuxLibreLibre(noir)) {
								valeurNoir += 5000;
								presenceMotifNoir[4] = true;
							}
						}
						
						if(!presenceMotifNoir[5]){
							if (m.estTroisFoisDeuxLibre(noir)) {
								valeurNoir += 600;
								presenceMotifNoir[5] = true;
							}
						}
						
						if(!presenceMotifNoir[6]){
							if (m.estDeuxFoisDeuxLibreLibre(noir)) {
								valeurNoir += 500;
								presenceMotifNoir[6] = true;
							}
						}
						
						if(!presenceMotifNoir[7]){
							if (m.estTroisLibreLibre(noir)) {
								coupGagnantNoir = true;
								presenceMotifNoir[7] = true;
							}
						}
						
						if(!presenceMotifNoir[8]){
							if (m.estTroisFoisDeux(noir)) {
								valeurNoir += 50;
								presenceMotifNoir[8] = true;
							}
						}
						
						if(!presenceMotifNoir[9]){
							if (m.estDeuxFoisDeuxLibre(noir)) {
								valeurNoir += 50;
								presenceMotifNoir[9] = true;
							}
						}
						
						if(!presenceMotifNoir[10]){
							if (m.estDeuxLibreLibre(noir)) {
								valeurNoir += 5;
								presenceMotifNoir[10] = true;
							}
						}
						
						if(!presenceMotifNoir[11]){
							if (m.estDeuxFoisDeux(noir)) {
								valeurNoir += 5;
								presenceMotifNoir[11] = true;
							}
						}

						if(!presenceMotifNoir[12]){
							if (m.estTrois(noir)) {
								valeurNoir += 5;
								presenceMotifNoir[12] = true;
							}
						}
						if(!presenceMotifNoir[13]){
							if (m.estTroisLibre(noir)) {
								valeurNoir += 25;
								presenceMotifNoir[13] = true;
							}
						}
					}

				}

			}
		}
		
		if(couleur == TypeCouleur.Blanc){
			if(coupGagnantNoir){
				return -20000;
			}
			
			if(coupGagnantBlanc){
				return 20000;
			}
				
			//Log.print(1, "blanc " + valeurBlanc + " - valeurNoir " + valeurNoir );
			return valeurBlanc - valeurNoir;
		}else{
			
			if(coupGagnantBlanc){
				return -20000;
			}
			
			if(coupGagnantNoir){
				return 20000;
			}
			

			//Log.print(1," valeurNoir " + valeurNoir + " - valeurBlanc " + valeurBlanc );
			return valeurNoir - valeurBlanc;
		}
		
		
	}




	public int EvaluerCoupAdversaire(PlateauDeJeu pdj, int profondeur, TypeCase tc) {
		if (profondeur == 0)
			return Evaluation(pdj,tc);

		int valeur = 1000000;
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				Coordonnees c = new Coordonnees(i, j);
				// Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un
				// pion existant
				if (EstJouable(pdj, c)) {
					pdj.ajouter(c, tc);
					// Log.print(695, pdj.toString());
					if (PartieFinie(pdj, c)) {
						// on peut couper l�, le coup est perdant.
						pdj.enlever(c);

						// Log.print(695, "dans evalCoupAdv " + c + " gagnant en
						// profondeur " + (profondeurMax -profondeur));
						return 100000;
					} else {
						int eval = EvaluerCoupIA(pdj, profondeur - 1, this.autreTypeCase(tc));
						// Log.print(1, "evalIA: "+eval + " valeur: " + valeur +
						// " prend min ");
						valeur = Math.min(valeur, eval);
					}
					pdj.enlever(c);
				}
			}
		}

		return valeur;
	}

	public int EvaluerCoupIA(PlateauDeJeu pdj, int profondeur, TypeCase tc) {
		if (profondeur == 0)
			return Evaluation(pdj,tc);

		int valeur = -1000000;
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				Coordonnees c = new Coordonnees(i, j);
				// Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un
				// pion existant
				if (EstJouable(pdj, c)) {
					pdj.ajouter(c, tc);
					if (PartieFinie(pdj, c)) {
						// on peut couper l�, le coup est gagnant.
						pdj.enlever(c);
						// Log.print(695, "dans evalCoupIA " + c + " gagnant en
						// profondeur " + (profondeurMax -profondeur));
						return 100000;
					} else {
						int eval = EvaluerCoupAdversaire(pdj, profondeur - 1, this.autreTypeCase(tc));
						// Log.print(1, "evalAd: "+eval + " valeur: " + valeur +
						// " prend max ");
						valeur = Math.max(valeur, eval);
					}
					pdj.enlever(c);
				}
			}
		}
		return valeur;
	}

	public boolean PartieFinie(PlateauDeJeu pdj, Coordonnees c) {

		TypeCase tc = pdj.getTypeCaseTableau(c);

		// diago hautgauchebasdroit
		int somme = 0;
		int i = c.getLigne();
		int j = c.getColonne();
		// Log.print(66, "partiefinie diago i= " + i + " j= " + j + " somme= "
		// +somme);
		while (i > 0 && j > 0 && pdj.getTypeCaseTableau(new Coordonnees(--i, --j)) == tc) {
			somme++;
			// Log.print(66, "partiefinie diago i= " + i + " j= " + j + " somme=
			// " +somme);
		}
		i = c.getLigne();
		j = c.getColonne();
		// Log.print(66, "partiefinie diago i= " + i + " j= " + j + " somme= "
		// +somme);
		while (i < pdj.getLignes() - 1 && j < pdj.getColonnes() - 1
				&& pdj.getTypeCaseTableau(new Coordonnees(++i, ++j)) == tc) {
			somme++;
			// Log.print(66, "partiefinie diago i= " + i + " j= " + j + " somme=
			// " +somme);
		}

		if (somme >= 4)
			return true;

		// diago hautdroitbasgauche
		somme = 0;
		i = c.getLigne();
		j = c.getColonne();
		while (i > 0 && j < pdj.getColonnes() - 1 && pdj.getTypeCaseTableau(new Coordonnees(--i, ++j)) == tc) {
			somme++;
		}
		i = c.getLigne();
		j = c.getColonne();
		while (i < pdj.getLignes() - 1 && j > 0 && pdj.getTypeCaseTableau(new Coordonnees(++i, --j)) == tc) {
			somme++;
		}

		if (somme >= 4)
			return true;

		// horizontal
		somme = 0;
		i = c.getLigne();
		j = c.getColonne();
		while (j < pdj.getColonnes() - 1 && pdj.getTypeCaseTableau(new Coordonnees(i, ++j)) == tc) {
			somme++;
		}
		i = c.getLigne();
		j = c.getColonne();
		while (j > 0 && pdj.getTypeCaseTableau(new Coordonnees(i, --j)) == tc) {
			somme++;
		}

		if (somme >= 4)
			return true;

		// vertical
		somme = 0;
		i = c.getLigne();
		j = c.getColonne();
		// Log.print(66, "partiefinie vertical i= " + i + " j= " + j + " somme=
		// " +somme);
		while (i > 0 && pdj.getTypeCaseTableau(new Coordonnees(--i, j)) == tc) {
			somme++;
			// Log.print(66, "partiefinie vertical i= " + i + " j= " + j + "
			// somme= " +somme);
		}
		i = c.getLigne();
		j = c.getColonne();
		// Log.print(66, "partiefinie vertical i= " + i + " j= " + j + " somme=
		// " +somme);
		while (i < pdj.getLignes() - 1 && pdj.getTypeCaseTableau(new Coordonnees(++i, j)) == tc) {
			somme++;
			// Log.print(66, "partiefinie vertical i= " + i + " j= " + j + "
			// somme= " +somme);
		}

		if (somme >= 4)
			return true;
		// Log.print(6, c+ " ne donne pas lieu a une partie finie");
		return false;
	}

	public boolean EstJouable(PlateauDeJeu pdj, Coordonnees c) {
		if (EstBlancOuNoir(pdj, c))
			return false;

		for (int i = c.getLigne() - 2; i <= c.getLigne() + 2; i++) {
			for (int j = c.getColonne() - 2; j <= c.getColonne() + 2; j++) {
				if (i >= 0 && i <= nbLigne - 1 && j >= 0 && j <= nbColonne - 1
						&& (c.getLigne() != i || c.getColonne() != j)) {
					// Log.print(11, "i=" + i + " j=" +j + " c= " + c );
					if (EstBlancOuNoir(pdj, new Coordonnees(i, j))) {
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
		return pdj.getTypeCaseTableau(c) == TypeCase.PionBlanc || pdj.getTypeCaseTableau(c) == TypeCase.PionNoir;
	}

}
