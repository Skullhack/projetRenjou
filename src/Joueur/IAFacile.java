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

		return null;
	}

	public boolean caseJouable(Coordonees p) {
		boolean retour = (m.getRenjou().getPlateauDeJeu().getPlateau()[p.getLigne()][p.getColonne()] == TypeCase.CasePionBlanc);
		retour = retour && (m.getRenjou().getPlateauDeJeu().getPlateau()[p.getLigne()][p.getColonne()] == TypeCase.CasePionNoir);
		retour = retour && (m.getRenjou().getPlateauDeJeu().getPlateau()[p.getLigne()][p.getColonne()] == TypeCase.CaseInjouable);
		return retour;
	}

	public void modifierHeristique(Coordonees p) {
		int i = p.getLigne();
		int j = p.getColonne();
		if (i > 0) {
			if(j >0){
				tabHeuristique[i][j] ++;
			}else{
				
			}
		}else{
			
		}
	}

	public void initHeuristique() {

		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				Coordonees p = new Coordonees(i, j);
				if (caseJouable(p)) {

				}
			}
		}

	}

}
