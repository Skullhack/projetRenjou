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
		m.printTrace(8, "Dans Jouer IAMoyenne");
		
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
		return 0;
	}

	public int EvaluationAvecTabous(PlateauDeJeu pdj) {
		// TODO Auto-generated method stub
		return 0;
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
		
		return this.PionAlentourRecursif(pdj, c, 1);
	}

	public boolean PionAlentour(PlateauDeJeu pdj, Coordonnees p, int profondeur){
		m.printTrace(66, "Dans pionAlentour");
		if(EstBlancOuNoir(pdj, p)){
			m.printTrace(66, "true (pionAlentour => EstBlancOuNoir)");
			return true;
		}else if(PionAlentourRecursif(pdj, p, profondeur -1)){
			m.printTrace(66, "true (pionAlentour => caseJouable)");
			return true;
		}
		
		m.printTrace(66, "false");
		return false;
	}
	
	public boolean PionAlentourRecursif(PlateauDeJeu pdj, Coordonnees p, int profondeur) {
		m.printTrace(66, "Dans caseJouable");	
		
		if (profondeur < 0){
			m.printTrace(66, "profondeur < 0");
			return false;
		}
		
		int i = p.getLigne();
		int j = p.getColonne();

		if (i > 0) {
			m.printTrace(66, "i > 0");
			if(PionAlentour(pdj, new Coordonnees(i-1,j), profondeur)){
				return true;
			}
			
			if(j > 0){
				m.printTrace(66, "j > 0");
				if(PionAlentour(pdj, new Coordonnees(i-1,j-1), profondeur)){
					 return true;	
				}
			}
			
			if(j < nbColonne -1){
				m.printTrace(66, "j < nbColonne -1");
				if(PionAlentour(pdj, new Coordonnees(i-1,j+1), profondeur)){
					return true;
				}
			}
			
		}
		
		if(i < nbLigne -1){
			m.printTrace(66, "i < nbLigne -1");
			if(PionAlentour(pdj, new Coordonnees(i+1,j), profondeur)){
				return true;
			}
			
			if(j >0){
				m.printTrace(66, "j > 0");
				if(PionAlentour(pdj, new Coordonnees(i+1,j-1), profondeur)){
					return true;
				}
			}
				
			if(j < nbColonne -1){
				m.printTrace(66, "j < nbColonne -1");
				if(PionAlentour(pdj, new Coordonnees(i+1,j+1), profondeur)){
					return true;
				}
			}
				
			
		}
		
		if(j >0){
			m.printTrace(66, "j > 0");
			if(PionAlentour(pdj, new Coordonnees(i,j-1), profondeur)){
				 return true;
			}
		}
			
		
		if(j < nbColonne -1){
			m.printTrace(66, "nbColonne -1");
			if(PionAlentour(pdj, new Coordonnees(i,j+1), profondeur)){
				 return true;
			}
		}
			
		
		m.printTrace(66, "false (caseJouable)");
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
