/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Enum.TypeCase;
import Enum.TypeJoueur;
import Joueur.Humain;
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
		tableauJoueurs[0] = new Humain();
		tableauJoueurs[1] = new Humain();
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
	public void operationJouer(Coordonnees c, TypeJoueur j) {

		if (j == renjou.getJoueurs()[renjou.getJoueurCourant()].getType()) {
			if (caseJouable(renjou, c)) {

				// mettre à jour le plateau
				// mettre à jour la liste annuler
				TypeCase typeCase = null;
				if (renjou.getJoueurCourant() == 0) {
					typeCase = TypeCase.CasePionNoir;

					System.out.println("PION NOIR !!");

				} else if (renjou.getJoueurCourant() == 1) {
					typeCase = TypeCase.CasePionBlanc;

					System.out.println("PION BLANC !!");
				}
				renjou.getPlateauDeJeu().ajouter(c, typeCase);
				renjou.getListeAnnuler().add(renjou.getPlateauDeJeu());

				joueurSuivant();
				majCasesTabous(renjou);

			} else if (caseTabou(renjou, c)) {
				System.out.println("PERDU !! C'est une case tabou !!");
			}

			if (partieFinie(renjou)) {
				// System.out.println("PARTIE FINIE");
			}
		}

	}

	private boolean caseJouable(Renjou renjou, Coordonnees c) {
		return (renjou.getPlateauDeJeu().getPlateau()[c.getLigne()][c.getColonne()] == TypeCase.CaseJouable);
	}

	private boolean caseTabou(Renjou renjou, Coordonnees c) {
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
		return true;
	}

	public void majCasesTabous(Renjou renjou) {

		if (renjou.getJoueurCourant() == 0) {
			// mettre les cases tabous en fonction de la liste
		}

		else if (renjou.getJoueurCourant() == 1) { // et nbPions == 60

			Coordonnees c = new Coordonnees();
			int milieuLignes = renjou.getPlateauDeJeu().getLignes() / 2;
			int milieuColonnes = renjou.getPlateauDeJeu().getColonnes() / 2;
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i != 0 || j != 0) {
						c.setLigne(milieuLignes + i);
						c.setColonne(milieuColonnes + j);
						renjou.getPlateauDeJeu().ajouter(c, TypeCase.CaseJouable);
					}
				}
			}
		} else if (renjou.getJoueurCourant() == 1) {
			// faire sauter toutes les cases tabous. Le joueur blanc n'en a pas
		}

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
