/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import java.util.ArrayList;
import Enum.*;
import Utilitaire.Coordonnees;
import Utilitaire.Log;
import Utilitaire.Motif;
import Utilitaire.PlateauDeJeu;
import Utilitaire.Tabou;

public class IAFacile extends IA {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IAFacile(TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
		super(type, nbPion, couleurJoueur);
	}
	
	@Override
	public Coordonnees jouer(PlateauDeJeu p) {
		Log.setPlage(200, 300);
		
		Coordonnees c = estCoupGagnant(p);
		if (c.getLigne() == -1) {
			c = empecherCoupGagnant(p);
			if (c.getLigne() == -1) {
				c = pointRandom(p);
			}
		}

		return c;
	}

	private Coordonnees estCoupGagnant(PlateauDeJeu plateau) {

		TypeCase tc = TypeCase.PionBlanc;
		switch(couleur){
		case Blanc:
			tc = TypeCase.PionBlanc;
			break;
		case Noir:
			tc = TypeCase.PionNoir;
			break;
		default:
				break;
		}
		Coordonnees c = new Coordonnees(-1, -1);
		// parcour du tableau pour voir les 5
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				if(plateau.getPlateau()[i][j] == TypeCase.Jouable){
					c = new Coordonnees(i, j);
					if(PartieFinie(plateau, i,j, tc)){
						if(!estCoupTabous(plateau, c)){
							return c;
						}
					}
				}
			}

		}
		c = new Coordonnees(-1, -1);
		
		return c;
	}

	private Coordonnees empecherCoupGagnant(PlateauDeJeu plateau) {

		TypeCase caseAdversaire = TypeCase.PionBlanc;
		TypeCouleur couleurAdversaire = TypeCouleur.Blanc;
		switch (couleur) {
		case Blanc:
			caseAdversaire = TypeCase.PionNoir;
			couleurAdversaire = TypeCouleur.Noir;
			break;
		case Noir:
			caseAdversaire = TypeCase.PionBlanc;
			couleurAdversaire = TypeCouleur.Blanc;
			break;
		default:
			break;
		}
		Coordonnees c = new Coordonnees(-1, -1);
		// parcour du tableau pour les 4 a empecher
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				if(plateau.getPlateau()[i][j] == TypeCase.Jouable){
					c = new Coordonnees(i, j);
					if(PartieFinie(plateau, i,j, caseAdversaire)){
						if(!estCoupTabous(plateau, c)){
							return c;
						}
					}
				}
			}

		}
		
		// parcour du tableau pour les 3 libre a empecher
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				if(plateau.getPlateau()[i][j] == TypeCase.Jouable){
					c = new Coordonnees(i, j);
					Motif m = new Motif(plateau, c);
					if (m.estQuatreLibreContinuIAFacile(couleurAdversaire)) {
						if(!estCoupTabous(plateau, c)){
							return c;
						}
					}
				}
			}

		}
		c = new Coordonnees(-1, -1);
		
		return c;
	}
	
	private  boolean estCoupTabous(PlateauDeJeu plateau, Coordonnees c){
		// a remplacer par proba = r.nextInt(10) si on veut faire 1 chance sur 10 de tomber dans un tabou 
		int proba = 0;
		if(couleur == TypeCouleur.Noir){
			if(!Tabou.estValide(plateau, c, true, true, true)){
				if(proba != 1){

					return true;
				}
			}
		}

		return false;
	}

	private Coordonnees pointRandom(PlateauDeJeu plateau) {
		initHeuristique(plateau);
		ArrayList<Coordonnees> listePoint = new ArrayList<Coordonnees>();
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				int nbHeristique = tabHeuristique[i][j];
				for (int k = 0; k < nbHeristique; k++) {
					listePoint.add(new Coordonnees(i, j));
				}
			}
		}

		if(listePoint.size() == 0){
			listePoint = listePointValide(plateau);
		}
		
		int indiceRandom = r.nextInt(listePoint.size());
		Coordonnees c = listePoint.get(indiceRandom);
		while(estCoupTabous(plateau, c)){
			indiceRandom = r.nextInt(listePoint.size());
			c = listePoint.get(indiceRandom);
		}
		
		return listePoint.get(indiceRandom);
	}

	private ArrayList<Coordonnees> listePointValide(PlateauDeJeu plateau) {
		
		ArrayList<Coordonnees> listePoint = new ArrayList<>();
		for(int i=0; i < nbLigne; i++){
			for(int j=0; j < nbColonne; j++){
				if(plateau.getPlateau()[i][j] == TypeCase.Jouable){
					listePoint.add(new Coordonnees(i,j));
				}
			}
		}
		return listePoint;
	}
}
