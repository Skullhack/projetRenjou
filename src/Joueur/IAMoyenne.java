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

public class IAMoyenne extends IA {
	ArrayList<Coordonnees> coups;
	int profondeurMax;

	public IAMoyenne(TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
		super(type, nbPion, couleurJoueur);
		init();
	}

	private void init() {
		coups = new ArrayList<>();
		profondeurMax = 3;
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
		Log.setPlage(49, 51);
		coups.clear();
		PlateauDeJeu pdj = p.clone();
		Log.print(50, p.toString() + "\nnbPionNoir= " + p.getNbPionNoir() + "\nnbPionBlanc= " + p.getNbPionBlanc());
		Log.print(50, pdj.toString() + "\nnbPionNoir= " + pdj.getNbPionNoir() + "\nnbPionBlanc= " + pdj.getNbPionBlanc());

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
					if (PartieFinie(pdj, c, tc)) {
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
				Coordonnees c = new Coordonnees(i,j);
				if (pdj.getTypeCaseTableauParLigneColonne(i,j) == TypeCase.PionBlanc
						|| pdj.getTypeCaseTableauParLigneColonne(i,j) == TypeCase.PionNoir) {
					Motif m = new Motif(pdj, c);

					
					if (pdj.getTypeCaseTableau(c) == TypeCase.PionBlanc && !coupGagnantBlanc) {
						// POUR BLANC
						for(int k=0;k<mr.length;k++){
							if(!presenceMotifBlanc[k]){
								if(mr[k].verif(m, blanc)){
									Log.print(501, "i= " + i + " j= "+j+ " dans Blanc " + mr[k].name());
									presenceMotifBlanc[k] = true;
								}
							}
						}
						
						coupGagnantBlanc = CoupGagnant(presenceMotifBlanc);
						coupGagnant2CoupsBlanc = CoupGagnant2Coups(presenceMotifBlanc);
						Log.print(501, "CGB = "+coupGagnantBlanc + " CG2CB = " + coupGagnant2CoupsBlanc );
					}

					
					if (pdj.getTypeCaseTableau(c) == TypeCase.PionNoir && !coupGagnantNoir) {
						// POUR NOIR
						for(int k=0;k<mr.length;k++){
							if(!presenceMotifNoir[k]){
								if(mr[k].verif(m, noir)){
									Log.print(501, "i= " + i + " j= "+j+ " dans Noir " + mr[k].name());
									presenceMotifNoir[k] = true;
								}
							}
						}
						coupGagnantNoir = CoupGagnant(presenceMotifNoir);
						coupGagnant2CoupsNoir = CoupGagnant2Coups(presenceMotifNoir);
						Log.print(501, "CGN = "+coupGagnantNoir + " CG2CN = " + coupGagnant2CoupsNoir );
					}

				}

			}
		}
		
		
		
		// si c'est a blanc de jouer 
		if(tc == TypeCase.PionBlanc){
			//si l'ia est blanc
			if(couleur == TypeCouleur.Blanc){
				if(coupGagnantBlanc){
					return 20000;
				}else if(coupGagnantNoir){
					return -20000;
				}else if(coupGagnant2CoupsBlanc){
					return 20000;
				}else if(coupGagnant2CoupsNoir){
					return -20000;
				}else{
					return Valeur(presenceMotifBlanc) - Valeur(presenceMotifNoir);
				}
			}else{ // si l'ia est noir
				if(coupGagnantBlanc){
					return -20000;
				}else if(coupGagnantNoir){
					return 20000;
				}else if(coupGagnant2CoupsBlanc){
					return -20000;
				}else if(coupGagnant2CoupsNoir){
					return 20000;
				}else{
					return Valeur(presenceMotifNoir) - Valeur(presenceMotifBlanc);
				}
			}
		}else{ // si c'est a noir de jouer
			// si ia.couleur = noir
			if(couleur == TypeCouleur.Noir){
				if(coupGagnantNoir){
					return 20000;
				}else if(coupGagnantBlanc){
					return -20000;
				}else if(coupGagnant2CoupsNoir){
					return 20000;
				}else if(coupGagnant2CoupsBlanc){
					return -20000;
				}else{
					return Valeur(presenceMotifNoir) - Valeur(presenceMotifBlanc);
				}
			}else{ // si l'ia est blanc
				if(coupGagnantNoir){
					return -20000;
				}else if(coupGagnantBlanc){
					return 20000;
				}else if(coupGagnant2CoupsNoir){
					return -20000;
				}else if(coupGagnant2CoupsBlanc){
					return 20000;
				}else{
					return Valeur(presenceMotifBlanc) - Valeur(presenceMotifNoir);
				}
			}
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
					if (PartieFinie(pdj, c, tc)) {
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
					if (PartieFinie(pdj, c, tc)) {
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
	
	private boolean CoupGagnant(boolean[] tab){
		return 	tab[MotifsReconnus.estQuatreLibre.ordinal()];
	}

	private boolean CoupGagnant2Coups(boolean[] tab){
		return 	tab[MotifsReconnus.estTroisFoisTroisLibreLibre.ordinal()] || 
				tab[MotifsReconnus.estTroisLibreLibre.ordinal()] ||
				tab[MotifsReconnus.estQuatreFoisTrois.ordinal()];
	}
	
	private int Valeur(boolean[] tab){
		int v = 0;
		
		if(tab[MotifsReconnus.estTroisFoisDeuxLibreLibre.ordinal()]){
			Log.print(501, "+5000");
			v +=5000;
		}
		else if(tab[MotifsReconnus.estTroisFoisDeuxLibre.ordinal()]){
			Log.print(501, "+600");
			v +=600;
		}
		else if(tab[MotifsReconnus.estDeuxFoisDeuxLibreLibre.ordinal()]){
			Log.print(501, "+500");
			v +=500;
		}
		else if(tab[MotifsReconnus.estTroisFoisTrois.ordinal()]){
			Log.print(501, "+100");
			v +=100;
		}
		else if(tab[MotifsReconnus.estTroisFoisDeux.ordinal()]){
			Log.print(501, "+75");
			v +=75;
		}
		else if(tab[MotifsReconnus.estDeuxFoisDeuxLibre.ordinal()]){
			Log.print(501, "+50");
			v +=50;
		}
		else if(tab[MotifsReconnus.estTroisLibre.ordinal()]){
			Log.print(501, "+50");
			v +=50;
		}
		else if(tab[MotifsReconnus.estDeuxLibreLibre.ordinal()]){
			Log.print(501, "+5");
			v +=5;
		}
		else if(tab[MotifsReconnus.estDeuxFoisDeux.ordinal()]){
			Log.print(501, "+5");
			v +=5;
		}
		else if(tab[MotifsReconnus.estDeuxLibre.ordinal()]){
			Log.print(501, "+1");
			v +=1;
		}
		
		Log.print(501, "valeur= " + v);
		return v;
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
