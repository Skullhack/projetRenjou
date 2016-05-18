/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import Controleur.Coordonnees;
import Controleur.Moteur;
import Enum.TypeCouleur;
import Enum.TypeJoueur;

public class IAMoyenne extends IA {
    
	public IAMoyenne(Moteur moteur, TypeJoueur type, int nbPion, TypeCouleur couleurJoueur){
		super(moteur, type,nbPion,couleurJoueur);

		//this.afficherTabHeuristique();
		ameliorerTabHeuristique();
	}
	
	
	
	
	public void ameliorerTabHeuristique() {

		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				Coordonnees p = new Coordonnees(i, j);
				if (caseJouable(p)) {
					tabHeuristique[i][j]--;
				}
			}
		}

	}
	
	public boolean caseJouable(Coordonnees p) {
		int i = p.getLigne();
		int j = p.getColonne();
		if (i > 0) {
			if(tabHeuristique[i-1][j] > 0){
				return true;
			}
			if(j > 0){
				if(tabHeuristique[i-1][j-1] > 0){
					 return true;
				}
			}
			if(j < nbColonne){
				if(tabHeuristique[i-1][j+1] > 0){
					return true;
				}
			}
		}
		if(i < nbLigne){
			if(tabHeuristique[i+1][j] > 0){
				return true;
			}
			if(j >0){
				if(tabHeuristique[i+1][j-1] > 0){
					return true;
				}
			}
			if(j < nbColonne){
				if(tabHeuristique[i+1][j+1] > 0){
					return true;
				}
			}
		}
		
		if(j >0){
			if(tabHeuristique[i][j-1] > 0){
				 return true;
			}
		}
		if(j < nbColonne){
			if(tabHeuristique[i][j+1] > 0){
				 return true;
			}
		}
		
		return false;
	}
	
	
}
