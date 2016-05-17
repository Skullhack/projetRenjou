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

	int nbLigne = 13;
	int nbColonne = 13;

	public IAFacile(TypeJoueur type, int nbPion, Moteur m) {
		super();
		this.m = m;
		this.type = type;
		this.nbPion = nbPion;

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		Coordonnees p = jouer();
		// m.operationJouer(p, type);

	}

	public Coordonnees jouer() {
		Coordonnees c = estCoupGagnant();
		if(c.getLigne() == -1){
			c = empecheCoupGagnant();
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
		Coordonnees c = new Coordonnees(-1,-1);
		
		return c;
	}
	
	public Coordonnees empecheCoupGagnant(){
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
