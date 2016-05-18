/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import java.util.ArrayList;
import java.util.Observable;

import Controleur.*;
import Enum.*;

public class IAFacile extends IA {

	protected int nbLigne = 13;
	protected int nbColonne = 13;

	public IAFacile(TypeJoueur type, int nbPion, Moteur m) {
		super();
		this.m = m;
		this.type = type;
		this.nbPion = nbPion;

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(m.getRenjou().getJoueurs()[m.getRenjou().getJoueurCourant()] == this){
			Coordonnees p = jouer();
			//m.operationJouer(p, type);
	
		}

	}

	public Coordonnees jouer() {
		Coordonnees c = estCoupGagnant();
		if(c.getLigne() == -1){
			c = empecherCoupGagnant();
			if(c.getLigne() == -1){
				c = pointRandom();
			}
		}
		
		return c;
	}

	public boolean caseJouable(Coordonnees p) {
		return (m.getRenjou().getPlateauDeJeu().getPlateau()[p.getLigne()][p.getColonne()] == TypeCase.CaseJouable);

	}

	public void modifierHeristique(Coordonnees p) {
		int i = p.getLigne();
		int j = p.getColonne();
		if (i > 0) {
			if(caseJouable(new Coordonnees(i-1,j))){
				tabHeuristique[i-1][j] ++;
			}
			if(j >0){
				if(caseJouable(new Coordonnees(i-1,j-1))){
					tabHeuristique[i-1][j-1] ++;
				}
			}
			if(j < nbColonne){
				if(caseJouable(new Coordonnees(i-1,j+1))){
					tabHeuristique[i-1][j+1] ++;
				}
			}
		}
		if(i < nbLigne){
			if(caseJouable(new Coordonnees(i+1,j))){
				tabHeuristique[i+1][j] ++;
			}
			if(j >0){
				if(caseJouable(new Coordonnees(i+1,j-1))){
					tabHeuristique[i+1][j-1] ++;
				}
			}
			if(j < nbColonne){
				if(caseJouable(new Coordonnees(i+1,j+1))){
					tabHeuristique[i+1][j+1] ++;
				}
			}
		}
		
		if(j >0){
			if(caseJouable(new Coordonnees(i,j-1))){
				tabHeuristique[i][j-1] ++;
			}
		}
		if(j < nbColonne){
			if(caseJouable(new Coordonnees(i,j+1))){
				tabHeuristique[i][j+1] ++;
			}
		}
	}

	public void initHeuristique() {

		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				Coordonnees p = new Coordonnees(i, j);
				if (caseJouable(p)) {
					modifierHeristique(p);
				}
			}
		}

	}

	public Coordonnees estCoupGagnant(){
		
		TypeCase typeCase =  TypeCase.CasePionBlanc;
		switch(couleur){
			case Blanc :
				typeCase = TypeCase.CasePionBlanc;
				break;
			case Noir :
				typeCase = TypeCase.CasePionNoir;
				break;
			default :
				break;
		}
		
		// en 3 boucles pour horizont, vert, diago à optimiser
		Coordonnees c = coupGagnantHorizontal(typeCase);
		if(c.getLigne() == -1){
			c = coupGagnantVertical(typeCase);
			if(c.getLigne() == -1){
				c = coupGagnantDiagonal(typeCase);
			}
		}

		
		return c;
	}
	
	// a implémenter
	private Coordonnees coupGagnantDiagonal(TypeCase typeCase) {
		// TODO Auto-generated method stub
		return null;
	}

	private Coordonnees coupGagnantHorizontal(TypeCase typeCase){
		
		for(int i=0; i<nbLigne; i++){
			for(int j=0; j<nbColonne; j++){
				int compteur = 0 ; //compteur pour aller jusqua 5 point alignés 
				while((j<nbColonne) && (m.getRenjou().getPlateauDeJeu().getPlateau()[i][j] == typeCase)){
					compteur++;
					j++;
				}
				if(compteur == 4){
					if((j < nbColonne) && (m.getRenjou().getPlateauDeJeu().getPlateau()[i][j] == TypeCase.CaseJouable)){
						return (new Coordonnees(i,j));
					}else if((j-5 < 0) && (m.getRenjou().getPlateauDeJeu().getPlateau()[i][j] == TypeCase.CaseJouable)){
						return (new Coordonnees(i,j-5));
					}
				}
				
			}
		}
		
		return (new Coordonnees(-1,-1));
	}

	private Coordonnees coupGagnantVertical(TypeCase typeCase){
		
		for(int j=0; j<nbColonne; j++){
			for(int i=0; j<nbLigne; i++){
				int compteur = 0 ; //compteur pour aller jusqua 5 point alignés 
				while((i<nbLigne) && (m.getRenjou().getPlateauDeJeu().getPlateau()[i][j] == typeCase)){
					compteur++;
					i++;
				}
				if(compteur == 4){
					if((i < nbLigne) && (m.getRenjou().getPlateauDeJeu().getPlateau()[i][j] == TypeCase.CaseJouable)){
						return (new Coordonnees(i,j));
					}else if((i-5 < 0) && (m.getRenjou().getPlateauDeJeu().getPlateau()[i][j] == TypeCase.CaseJouable)){
						return (new Coordonnees(i,j-5));
					}
				}
				
			}
		}
		return (new Coordonnees(-1,-1));
	}
	
	// a implémenter
	public Coordonnees empecherCoupGagnant(){
		Coordonnees c = new Coordonnees(-1,-1);
		
		return c;		
	}
	
	public Coordonnees pointRandom(){
		initHeuristique();
		ArrayList<Coordonnees> listePoint = new ArrayList<Coordonnees>();
		for(int i=0; i<nbLigne; i++){
			for(int j=0; j<nbColonne; j++){
				int nbHeristique = tabHeuristique[i][j];
				for(int k=0; k <nbHeristique; k++){
					listePoint.add(new Coordonnees(i,j));
				}
			}
		}
		
		int indiceRandom = r.nextInt(listePoint.size());
		return listePoint.get(indiceRandom);
	}
}
