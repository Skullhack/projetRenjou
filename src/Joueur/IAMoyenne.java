/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import java.util.ArrayList;

import Controleur.*;
import Enum.*;


public class IAMoyenne extends IA {
	ArrayList<Coordonnees> coups;

	public IAMoyenne(Moteur moteur, TypeJoueur type, int nbPion, TypeCouleur couleurJoueur){
		super(moteur, type,nbPion,couleurJoueur);
		coups = new ArrayList<>();
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
	

	public void Jouer(){
		m.printTrace(66, "Dans Jouer IAMoyenne");
		
		PlateauDeJeu pdj =  m.getRenjou().getPlateauDeJeu().clone();
		
		m.printTrace(19, afficherTabHeuristique());
		initHeuristique(pdj);

		int profondeur = 3;
		int valeur = -100000;
		int valeurtemp;
		TypeCase tc =  this.getTypeCase(this.couleur);
		
		// parcours des coups jouable
		for(int i=0;i<nbLigne;i++){
			for(int j=0;j<nbColonne;j++){
				Coordonnees c = new Coordonnees(i,j);
				//Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un pion existant
				if(EstJouable(pdj, c)){
					pdj.ajouter(c,tc);
					
					if(PartieFinie(pdj,c)){
						// on peut couper là, le coup est gagnant... mais comment on renvoie la coordonnées n'est pas encore géré
						valeurtemp = 10000;
					}else{
						valeurtemp = EvaluerCoupAdversaire(pdj,profondeur -1, this.autreTypeCase(tc));
					}
					pdj.enlever(c);
					if(valeurtemp == valeur){
						coups.add(c);
					}
					else if(valeurtemp > valeur){
						coups.clear();
						coups.add(c);
						valeur = valeurtemp;
					}
					
				}
			}
		}
		
		
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
					
					if(PartieFinie(pdj, c)){
						// on peut couper là, le coup est perdant.
						return -10000;
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
		m.printTrace(66, "partiefinie diago i= " + i + " j= " + j + " somme= " +somme);
		while(i>0 && j>0 && pdj.getTypeCaseTableau(new Coordonnees(--i,--j)) == tc){
			somme++;
			m.printTrace(66, "partiefinie diago i= " + i + " j= " + j + " somme= " +somme);
		}
		i = c.getLigne();
		j = c.getColonne();
		m.printTrace(66, "partiefinie diago i= " + i + " j= " + j + " somme= " +somme);
		while(i<pdj.getLignes()-1 && j<pdj.getColonnes()-1 && pdj.getTypeCaseTableau(new Coordonnees(++i,++j)) == tc){
			somme++;			
			m.printTrace(66, "partiefinie diago i= " + i + " j= " + j + " somme= " +somme);
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
		m.printTrace(66, "partiefinie vertical i= " + i + " j= " + j + " somme= " +somme);
		while(i>0 && pdj.getTypeCaseTableau(new Coordonnees(--i,j)) == tc){
			somme++;
			m.printTrace(66, "partiefinie vertical i= " + i + " j= " + j + " somme= " +somme);
		}
		i = c.getLigne();
		j = c.getColonne();
		m.printTrace(66, "partiefinie vertical i= " + i + " j= " + j + " somme= " +somme);
		while(i<pdj.getLignes()-1 && pdj.getTypeCaseTableau(new Coordonnees(++i,j)) == tc){
			somme++;
			m.printTrace(66, "partiefinie vertical i= " + i + " j= " + j + " somme= " +somme);
		}
		
		if(somme >= 4)
			return true;
		m.printTrace(6, c+ " ne donne pas lieu a une partie finie");
		return false;
	}

	
	
	public boolean EstJouable(PlateauDeJeu pdj, Coordonnees c) {
		if(EstBlancOuNoir(pdj,c))
			return false;
		
		for(int i = c.getLigne()-2;i<=c.getLigne()+2;i++){
			for(int j = c.getColonne()-2;j<=c.getColonne()+2;j++){
				if(i>=0 && i<= nbLigne-1 && j>=0 && j<=nbColonne-1 && (c.getLigne() != i || c.getColonne() != j)){
					m.printTrace(11, "i=" + i + " j=" +j + " c= " + c );
					if(EstBlancOuNoir(pdj,new Coordonnees(i,j))){
						m.printTrace(1, "est un pion!" );
						return true;
					}
				}
			}
		}
				
				
		return false;
	}

	public boolean EstBlancOuNoir(PlateauDeJeu pdj, Coordonnees c){
		m.printTrace(66, "Dans EstBlancOuNoir => " + pdj.getTypeCaseTableau(c));
		return pdj.getTypeCaseTableau(c) == TypeCase.PionBlanc || pdj.getTypeCaseTableau(c) == TypeCase.PionNoir; 
	}

	


	
	@Override
	public void actualiser() {
		m.printTrace(3, "IAMoyenne notifiee");
		
		if(!AmonTour()){
			m.printTrace(9, "Ce n'est pas a mon tour de jouer");
			return;
		}
		
		Jouer();
	}
	
	public boolean AmonTour(){
		return m.getRenjou().getJoueurs()[m.getRenjou().getJoueurCourant()] == this;
	}

	
}
