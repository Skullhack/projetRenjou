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
import Utilitaire.PlateauDeJeu;


public class IAMoyenne extends IA {
	ArrayList<Coordonnees> coups;
	Random r;
	int profondeurMax;
	
	public IAMoyenne(Moteur moteur, TypeJoueur type, int nbPion, TypeCouleur couleurJoueur){
		super(moteur, type,nbPion,couleurJoueur);
		coups = new ArrayList<>();
		int seed = 12345;
		r = new Random(seed);
		profondeurMax = 3;
	}
	
	public Point play(int[][] plateau, int couleurJoueur, boolean tabou3x3, boolean tabou4x4, boolean tabouOverline){
		initPlateauEtCouleur(plateau, couleurJoueur);
		Coordonnees c = Jouer(pdj);
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
	

	public Coordonnees Jouer(PlateauDeJeu p){
		Log.print(66, "Dans Jouer IAMoyenne");
		coups.clear();
		PlateauDeJeu pdj =  p.clone();
		
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
						// on peut couper là, le coup est gagnant.
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
		
		if(this.couleur == TypeCouleur.Blanc)
			return EvaluationSansTabous(pdj);
		
		return EvaluationAvecTabous(pdj);
	}

	public int EvaluationSansTabous(PlateauDeJeu pdj) {
		// TODO Auto-generated method stub
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
						// on peut couper là, le coup est perdant.
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
						// on peut couper là, le coup est gagnant.
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

	


	
	@Override
	public void actualiser() {
		Log.print(3, "IAMoyenne notifiee");
		
		if(!AmonTour()){
			Log.print(9, "Ce n'est pas a mon tour de jouer");
			return;
		}
		
		Jouer(m.getRenjou().getPlateauDeJeu());
	}
	
	public boolean AmonTour(){
		return m.getRenjou().getJoueurs()[m.getRenjou().getJoueurCourant()] == this;
	}

	
}
