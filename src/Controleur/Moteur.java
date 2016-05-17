/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Enum.TypeCase;
import Enum.TypeJoueur;
import Joueur.Joueur;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author michauad
 */
public class Moteur implements InterfaceMoteur {
	private Renjou renjou;

	// Constructeur
	public Moteur(int nbJoueurs) {
		Joueur[] tableauJoueurs = new Joueur[nbJoueurs];
		// tabJ[0] = new Humain(this, 1000);
		// tabJ[1] = new IAMoyen(this,1000);
		// System.out.println("this: " + this);

		this.renjou = new Renjou(tableauJoueurs);

	}

	@Override
	public Renjou getRenjou() {
		return this.renjou;
	}

	public void setRenjou(Renjou renjou) {
		this.renjou = renjou;
	}

	@Override
	public void sauvegarder(String nomFichier) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void charger(String nomFichier) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public boolean coupTabou(Renjou renjou, Coordonnees c) {

		// se baser sur la methode estValide qui existe dans la classe Renjou.
		// On part du principe que ça fonctionne
		boolean coupValide = true;
		// if
		// (renjou.getPlateauDeJeu().getPlateau()[c.getLigne()][c.getColonne()]
		// != TypeCase.CaseJouable) {
		// coupValide = false;
		// } else {
		// ArrayList<Tabou> tabousJeu = renjou.getTabouJeu();
		// for (Tabou tabous : tabousJeu) {
		// if (!tabous.estValide(renjou, c)) {
		// coupValide = false;
		// break;
		// }
		// }
		//
		// }
		return coupValide;

	}

	@Override
	public void operationJouer(Coordonnees c, TypeJoueur j) {

		if (j == renjou.getJoueurs()[renjou.getJoueurCourant()].getType()) {
			if (caseJouable(renjou, c)) {
				// mettre à jour le plateau
				// mettre à jour la liste annuler
				// maj des cases tabous avec prise en compte du cas des 60 pions
				//maj du joueur suivant
			} else if (caseTabou(renjou, c)) {
				// perdu;
			}

			if (partieFinie(renjou)) {
				// afficher joueur victorieux
			}
		}

	}

	public boolean caseJouable(Renjou renjou, Coordonnees c) {
		return (renjou.getPlateauDeJeu().getPlateau()[c.getLigne()][c.getColonne()] == TypeCase.CaseJouable);
	}

	public boolean caseTabou(Renjou renjou, Coordonnees c) {
		return (renjou.getPlateauDeJeu().getPlateau()[c.getLigne()][c.getColonne()] == TypeCase.CaseTabou);
	}

	@Override
	public void nouvellePartie(TypeJoueur[] tabTypeJoueurs) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void joueurSuivant() {
		int joueurSuivant = this.renjou.getJoueurCourant() + 1;
		this.renjou.setJoueurCourant(joueurSuivant);
		if (this.renjou.getJoueurCourant() >= this.renjou.getNbJoueurs()) {
			this.renjou.setJoueurCourant(0);
		}
	}

	@Override
	public boolean partieFinie(Renjou renjou) {

		// si 5 pions sont alignes
		// si le joueur courant n 'a plus de pions

		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	// fonction perso de vue
	public void afficherPlateauJeu() {
		for (int i = 0; i < renjou.getPlateauDeJeu().getLignes(); i++) {
			for (int j = 0; j < renjou.getPlateauDeJeu().getColonnes(); j++) {
				if (renjou.getPlateauDeJeu().getPlateau()[i][j] == TypeCase.CaseTabou) {
					System.out.print("X");
				} else if (renjou.getPlateauDeJeu().getPlateau()[i][j] == TypeCase.CaseJouable) {
					System.out.print("O");
				} else if (renjou.getPlateauDeJeu().getPlateau()[i][j] == TypeCase.CasePionBlanc) {
					System.out.print("B");
				} else if (renjou.getPlateauDeJeu().getPlateau()[i][j] == TypeCase.CasePionNoir) {
					System.out.print("N");
				}
			}
			System.out.println("");
		}
	}

}
