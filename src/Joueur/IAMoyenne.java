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
    
	public IAMoyenne(Moteur moteur, TypeJoueur type, int nbPion, TypeCouleur couleurJoueur){
		super(moteur, type,nbPion,couleurJoueur);
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
		TypeCase tc =  this.getTypeCase(this.couleur);
		ArrayList<Coordonnees> Coups = new ArrayList<Coordonnees>();
		// parcours des coups jouable

		for(int i=0;i<nbLigne;i++){
			for(int j=0;j<nbColonne;j++){
				Coordonnees c = new Coordonnees(i,j);
				//Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un pion existant
				if(EstJouable(pdj, c)){
					pdj.ajouter(c,tc);
					int valeurtemp = EvaluerCoupAdversaire(pdj,profondeur -1, this.autreTypeCase(tc));
					if(valeurtemp == valeur){
						Coups.add(c);
					}
					else if(valeurtemp > valeur){
						Coups.clear();
						Coups.add(c);
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
		if(profondeur == 0 || PartieFinie())
			return Evaluation(pdj);
		
		int valeur = 10000;
		for(int i=0;i<nbLigne;i++){
			for(int j=0;j<nbColonne;j++){
				Coordonnees c = new Coordonnees(i,j);
				//Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un pion existant
				if(EstJouable(pdj, c)){
					pdj.ajouter(c,tc);
					valeur =  Math.min(valeur, EvaluerCoupIA(pdj,profondeur -1, this.autreTypeCase(tc)));
				}
			}
		}
		return valeur;
	}

	public int EvaluerCoupIA(PlateauDeJeu pdj, int profondeur, TypeCase tc) {
		if(profondeur == 0 || PartieFinie())
			return Evaluation(pdj);
			
		int valeur = -10000;
		for(int i=0;i<nbLigne;i++){
			for(int j=0;j<nbColonne;j++){
				Coordonnees c = new Coordonnees(i,j);
				//Heuristique, on ne cherche pas a jouer a plus de 2 cases d'un pion existant
				if(EstJouable(pdj, c)){
					pdj.ajouter(c,tc);
					valeur =  Math.max(valeur, EvaluerCoupAdversaire(pdj,profondeur -1, this.autreTypeCase(tc)));
				}
			}
		}
		return valeur;
	}
	
	private boolean PartieFinie() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	public boolean EstJouable(PlateauDeJeu pdj, Coordonnees c) {
		if(EstBlancOuNoir(pdj,c))
			return true;
		
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
		m.printTrace(66, "Dans EstBlancOuNoir");
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
