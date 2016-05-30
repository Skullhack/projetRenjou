/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import Controleur.*;
import Enum.*;
import Utilitaire.Coordonnees;
import Utilitaire.Log;
import Utilitaire.Motif;
import Utilitaire.PlateauDeJeu;

public class IAFacile extends IA {

	public IAFacile(TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
		super(type, nbPion, couleurJoueur);
	}


	public Point play(int[][] plateau, int couleurJoueur, boolean tabou3x3, boolean tabou4x4, boolean tabouOverline){
		initPlateauEtCouleur(plateau, couleurJoueur);
		Coordonnees c = jouer(pdj);
		return new Point(c.getColonne(), c.getLigne());
	}
	
	@Override
	public Coordonnees jouer(PlateauDeJeu p) {
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
					if(PartieFinie(plateau, c, tc)){
						return c;
					}
				}
			}

		}
		c = new Coordonnees(-1, -1);
		
		return c;
	}

	private Coordonnees empecherCoupGagnant(PlateauDeJeu plateau) {

		TypeCouleur couleurAdversaire = TypeCouleur.Blanc;
		switch (couleur) {
		case Blanc:
			couleurAdversaire = TypeCouleur.Noir;
			break;
		case Noir:
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
					Motif m = new Motif(plateau, c);
					if (m.estQuatreLibre(couleurAdversaire)) {
						return c;
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
					if (m.estTroisLibre(couleurAdversaire)) {
						return c;
					}
				}
			}

		}
		c = new Coordonnees(-1, -1);
		
		return c;
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
