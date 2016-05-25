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

	
	public IAMoyenne(TypeJoueur type, int nbPion, TypeCouleur couleurJoueur){
		super(type,nbPion,couleurJoueur);
		init();
	}
	
	private void init(){
		coups = new ArrayList<>();
		profondeurMax = 3;
	}
	
	public Point play(int[][] plateau, int couleurJoueur, boolean tabou3x3, boolean tabou4x4, boolean tabouOverline){
		initPlateauEtCouleur(plateau, couleurJoueur);
		Coordonnees c = jouer(pdj);
		return new Point(c.getColonne(), c.getLigne());
	}
	
	public TypeCase getTypeCase(TypeCouleur tc){
		if(tc == TypeCouleur.Blanc)
			return TypeCase.PionBlanc;
		
		return TypeCase.PionNoir;
	}
	
	public TypeCase getAutreTypeCase(TypeCouleur tc){
		if(tc == TypeCouleur.Blanc)
			return TypeCase.PionNoir;
		
		return TypeCase.PionBlanc;
	}
	
	public TypeCase autreTypeCase(TypeCase tc){
		if(tc == TypeCase.PionBlanc)
			return TypeCase.PionNoir;
		
		return TypeCase.PionBlanc;
	}	
	
	@Override
	public Coordonnees jouer(PlateauDeJeu p){
		Log.print(66, "Dans Jouer IAMoyenne");
		coups.clear();
		PlateauDeJeu pdj =  p.clone();
		Log.print(1, p.toString() + "\nnbPionNoir= " + p.getNbPionNoir() + "\nnbPionBlanc= " + p.getNbPionBlanc());
		Log.print(1, pdj.toString() + "\nnbPionNoir= " + pdj.getNbPionNoir() + "\nnbPionBlanc= " + pdj.getNbPionBlanc());
		
		if(premierTour(pdj)){
			return new Coordonnees(7,7);
		}
		
		if(secondTour(pdj)){
			for(int i = 6;i<8;i++){
				for(int j= 6; j< 8; j++){
					if(i != 7 || j != 7){
						coups.add(new Coordonnees(i,j));
					}
				}
			}
			
			r.nextInt(coups.size());
			return coups.get(r.nextInt(coups.size()));
		}
		
		
		int profondeur = profondeurMax;
		int valeur = -10000;
		int valeurtemp;
		TypeCase tc =  this.getTypeCase(this.couleur);
		
		// parcours des coups jouable
		for(int i=0;i<nbLigne;i++){
			for(int j=0;j<nbColonne;j++){
				Coordonnees c = new Coordonnees(i,j);
				//Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un pion existant
				if(EstJouable(pdj, c)){
					pdj.ajouter(c,tc);
					Log.print(695, pdj.toString());
					if(PartieFinie(pdj,c)){
						// on peut couper l�, le coup est gagnant.
						Log.print(695, "dans jouer " + c + " gagnant en profondeur " + (profondeurMax -profondeur));
						return c;
					}else{
						//valeurtemp = 0;
						valeurtemp = EvaluerCoupAdversaire(pdj,profondeur -1, this.autreTypeCase(tc));
					}
					pdj.enlever(c);
					if(valeurtemp == valeur){
						Log.print(695, "dans egal valeurtemp=" + valeurtemp + " " + c);
						coups.add(c);
						Log.print(695, coups.toString());
					}
					else if(valeurtemp > valeur){
						Log.print(695, "dans sup valeurtemp=" + valeurtemp + " " + c);
						coups.clear();
						coups.add(c);
						valeur = valeurtemp;
						Log.print(695, coups.toString());
					}
					
				}
			}
		}
		Log.print(695, coups.toString());

		if(coups.isEmpty()){
			return new Coordonnees(-1,-1);
		}
		
		r.nextInt(coups.size());
		return coups.get(r.nextInt(coups.size()));
		
	}
	
	private int Evaluation(PlateauDeJeu pdj) {
		return EvaluationSansTabous(pdj) + EvaluationAvecTabous(pdj);
	}


	
	public int EvaluationSansTabous(PlateauDeJeu pdj) {
		int valeurBlanc = 0;
		int valeurNoir = 0;
		TypeCouleur tc = TypeCouleur.Blanc;
		for(int i=0;i<nbLigne;i++){
			for(int j=0;j<nbColonne; j++){
				Coordonnees c = new Coordonnees(i,j);
				Motif m = new Motif(pdj, c);

				
				// POUR BLANC
				if(m.estTroisFoisTroisLibreLibre(tc) || m.estQuatreFoisQuatre(tc) || m.estSixSept(tc)){
					valeurBlanc += 10000;
				}
				
				if(m.estDeuxFoisDeux(tc)){
					valeurBlanc += 5;
					if(m.estDeuxFoisDeuxLibre(tc)){
						valeurBlanc += 50;
						if(m.estDeuxFoisDeuxLibreLibre(tc)){
							valeurBlanc += 500;
						}
					}
				}
				
				if(m.estTroisFoisDeux(tc)){
					valeurBlanc += 50;
					if(m.estTroisFoisDeuxLibre(tc)){
						valeurBlanc += 600;
						if(m.estTroisFoisDeuxLibreLibre(tc)){
							valeurBlanc += 5000;
						}
					}
				}
				
				if(m.estTrois(tc)){
					valeurBlanc += 5;
					if(m.estTroisLibre(tc)){
						valeurBlanc += 25;
						if(m.estTroisLibreLibre(tc)){
							valeurBlanc += 250;
						}
					}
				}
				
				// POUR NOIR
				if(m.estQuatreFoisTrois(tc)){
					valeurNoir += 10000;
				}
				
				if(m.estTroisFoisTroisLibreLibre(tc) || m.estQuatreFoisQuatre(tc) || m.estSixSept(tc)){
					valeurNoir += 10000;
				}
				
				
				if(m.estDeuxFoisDeux(tc)){
					valeurNoir += 5;
					if(m.estDeuxFoisDeuxLibre(tc)){
						valeurNoir += 50;
						if(m.estDeuxFoisDeuxLibreLibre(tc)){
							valeurNoir += 500;
						}
					}
				}
				
				if(m.estTroisFoisDeux(tc)){
					valeurNoir += 50;
					if(m.estTroisFoisDeuxLibre(tc)){
						valeurNoir += 600;
						if(m.estTroisFoisDeuxLibreLibre(tc)){
							valeurNoir += 5000;
						}
					}
				}
				
				if(m.estTrois(tc)){
					valeurNoir += 5;
					if(m.estTroisLibre(tc)){
						valeurNoir += 25;
						if(m.estTroisLibreLibre(tc)){
							valeurNoir += 250;
						}
					}
				}
				
				
				
			}
		}
			
		
		return 1;
	}

	public int EvaluationAvecTabous(PlateauDeJeu pdj) {
		// TODO Auto-generated method stub
		return 1;
	}
	
	public int EvaluerCoupAdversaire(PlateauDeJeu pdj, int profondeur, TypeCase tc) {
		if(profondeur == 0)
			return Evaluation(pdj);
		
		int valeur = 10000;
		for(int i=0;i<nbLigne;i++){
			for(int j=0;j<nbColonne;j++){
				Coordonnees c = new Coordonnees(i,j);
				//Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un pion existant
				if(EstJouable(pdj, c)){
					pdj.ajouter(c,tc);
					Log.print(695, pdj.toString());
					if(PartieFinie(pdj, c)){
						// on peut couper l�, le coup est perdant.
						pdj.enlever(c);

						Log.print(695, "dans evalCoupAdv " + c + " gagnant en profondeur " + (profondeurMax -profondeur));
						return -20000;
					}else{
						valeur =  Math.min(valeur, EvaluerCoupIA(pdj,profondeur -1, this.autreTypeCase(tc)));
					}
					pdj.enlever(c);
				}
			}
		}
		return valeur;
	}

	public int EvaluerCoupIA(PlateauDeJeu pdj, int profondeur, TypeCase tc) {
		if(profondeur == 0)
			return Evaluation(pdj);
			
		int valeur = -10000;
		for(int i=0;i<nbLigne;i++){
			for(int j=0;j<nbColonne;j++){
				Coordonnees c = new Coordonnees(i,j);
				//Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un pion existant
				if(EstJouable(pdj, c)){
					pdj.ajouter(c,tc);
					if(PartieFinie(pdj, c)){
						// on peut couper l�, le coup est gagnant.
						pdj.enlever(c);
						Log.print(695, "dans evalCoupIA " + c + " gagnant en profondeur " + (profondeurMax -profondeur));
						return 10000;
					}else{
						valeur =  Math.max(valeur, EvaluerCoupAdversaire(pdj,profondeur -1, this.autreTypeCase(tc)));
					}
					pdj.enlever(c);
				}
			}
		}
		return valeur;
	}
	
	public boolean PartieFinie(PlateauDeJeu pdj, Coordonnees c) {
		
		TypeCase tc = pdj.getTypeCaseTableau(c);
		
		//diago hautgauchebasdroit
		int somme = 0;
		int i = c.getLigne();
		int j = c.getColonne();
		Log.print(66, "partiefinie diago i= " + i + " j= " + j + " somme= " +somme);
		while(i>0 && j>0 && pdj.getTypeCaseTableau(new Coordonnees(--i,--j)) == tc){
			somme++;
			Log.print(66, "partiefinie diago i= " + i + " j= " + j + " somme= " +somme);
		}
		i = c.getLigne();
		j = c.getColonne();
		Log.print(66, "partiefinie diago i= " + i + " j= " + j + " somme= " +somme);
		while(i<pdj.getLignes()-1 && j<pdj.getColonnes()-1 && pdj.getTypeCaseTableau(new Coordonnees(++i,++j)) == tc){
			somme++;			
			Log.print(66, "partiefinie diago i= " + i + " j= " + j + " somme= " +somme);
		}
		
		if(somme >= 4)
			return true;
		
		//diago hautdroitbasgauche
		somme = 0;
		i = c.getLigne();
		j = c.getColonne();
		while(i>0 && j<pdj.getColonnes()-1 && pdj.getTypeCaseTableau(new Coordonnees(--i,++j)) == tc){
			somme++;
		}
		i = c.getLigne();
		j = c.getColonne();
		while(i<pdj.getLignes()-1 && j>0 && pdj.getTypeCaseTableau(new Coordonnees(++i,--j)) == tc){
			somme++;
		}
		
		if(somme >= 4)
			return true;
		
		//horizontal
		somme = 0;
		i = c.getLigne();
		j = c.getColonne();
		while(j<pdj.getColonnes()-1 && pdj.getTypeCaseTableau(new Coordonnees(i,++j)) == tc){
			somme++;
		}
		i = c.getLigne();
		j = c.getColonne();
		while(j>0 && pdj.getTypeCaseTableau(new Coordonnees(i,--j)) == tc){
			somme++;
		}
		
		if(somme >= 4)
			return true;
		
		//vertical
		somme = 0;
		i = c.getLigne();
		j = c.getColonne();
		Log.print(66, "partiefinie vertical i= " + i + " j= " + j + " somme= " +somme);
		while(i>0 && pdj.getTypeCaseTableau(new Coordonnees(--i,j)) == tc){
			somme++;
			Log.print(66, "partiefinie vertical i= " + i + " j= " + j + " somme= " +somme);
		}
		i = c.getLigne();
		j = c.getColonne();
		Log.print(66, "partiefinie vertical i= " + i + " j= " + j + " somme= " +somme);
		while(i<pdj.getLignes()-1 && pdj.getTypeCaseTableau(new Coordonnees(++i,j)) == tc){
			somme++;
			Log.print(66, "partiefinie vertical i= " + i + " j= " + j + " somme= " +somme);
		}
		
		if(somme >= 4)
			return true;
		Log.print(6, c+ " ne donne pas lieu a une partie finie");
		return false;
	}

	
	
	public boolean EstJouable(PlateauDeJeu pdj, Coordonnees c) {
		if(EstBlancOuNoir(pdj,c))
			return false;
		
		for(int i = c.getLigne()-2;i<=c.getLigne()+2;i++){
			for(int j = c.getColonne()-2;j<=c.getColonne()+2;j++){
				if(i>=0 && i<= nbLigne-1 && j>=0 && j<=nbColonne-1 && (c.getLigne() != i || c.getColonne() != j)){
					Log.print(11, "i=" + i + " j=" +j + " c= " + c );
					if(EstBlancOuNoir(pdj,new Coordonnees(i,j))){
						Log.print(1, "est un pion!" );
						return true;
					}
				}
			}
		}
				
				
		return false;
	}

	public boolean EstBlancOuNoir(PlateauDeJeu pdj, Coordonnees c){
		Log.print(66, "Dans EstBlancOuNoir => " + pdj.getTypeCaseTableau(c));
		return pdj.getTypeCaseTableau(c) == TypeCase.PionBlanc || pdj.getTypeCaseTableau(c) == TypeCase.PionNoir; 
	}


	
}
