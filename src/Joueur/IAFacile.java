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
		Coordonees p = jouer();
		// m.operationJouer(p, type);

	}

	public Coordonees jouer() {
		Coordonees c = estCoupGagnant();
		if(c.getLigne() == -1){
			ArrayList<Coordonees> listePoint = new ArrayList<Coordonees>();
			for(int i=0; i<nbLigne; i++){
				for(int j=0; j<nbColonne; j++){
					
				}
			}
			
			initHeuristique();
		}
		
		return c;
	}

	public boolean caseJouable(Coordonees p) {
		return (m.getRenjou().getPlateauDeJeu().getPlateau()[p.getLigne()][p.getColonne()] == TypeCase.CaseJouable);

	}

	public void modifierHeristique(Coordonees p) {
		int i = p.getLigne();
		int j = p.getColonne();
		if (i > 0) {
			if(caseJouable(new Coordonees(i-1,j))){
				tabHeuristique[i-1][j] ++;
			}
			if(j >0){
				if(caseJouable(new Coordonees(i-1,j-1))){
					tabHeuristique[i-1][j-1] ++;
				}
			}
			if(j < nbColonne){
				if(caseJouable(new Coordonees(i-1,j+1))){
					tabHeuristique[i-1][j+1] ++;
				}
			}
		}
		if(i < nbLigne){
			if(caseJouable(new Coordonees(i+1,j))){
				tabHeuristique[i+1][j] ++;
			}
			if(j >0){
				if(caseJouable(new Coordonees(i+1,j-1))){
					tabHeuristique[i+1][j-1] ++;
				}
			}
			if(j < nbColonne){
				if(caseJouable(new Coordonees(i+1,j+1))){
					tabHeuristique[i+1][j+1] ++;
				}
			}
		}
		
		if(j >0){
			if(caseJouable(new Coordonees(i,j-1))){
				tabHeuristique[i][j-1] ++;
			}
		}
		if(j < nbColonne){
			if(caseJouable(new Coordonees(i,j+1))){
				tabHeuristique[i][j+1] ++;
			}
		}
	}

	public void initHeuristique() {

		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				Coordonees p = new Coordonees(i, j);
				if (caseJouable(p)) {
					modifierHeristique(p);
				}
			}
		}

	}

	public Coordonees estCoupGagnant(){
		Coordonees c = new Coordonees(-1,-1);
		
		return c;
	}
	
	public Coordonees estCoupPerdant(){
		Coordonees c = new Coordonees(-1,-1);
		
		return c;		
	}
	
}
