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
	public boolean coupValide(Renjou renjou, Coordonees c) {

		// se baser sur la methode estValide qui existe dans la classe Renjou.
		// On part du principe que ça fonctionne

		// if(renjou.getPlateauDeJeu().getPlateau()[i][j] == ) {
		//
		// }
		
		
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
	public void operationJouer(Coordonees c, Joueur j) {
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
				if (renjou.getPlateauDeJeu().getPlateau()[i][j] == TypeCase.CaseInjouable) {
					System.out.print("X");
				} else if (renjou.getPlateauDeJeu().getPlateau()[i][j] == TypeCase.CaseJouable) {
					System.out.print("O");
				} else if (renjou.getPlateauDeJeu().getPlateau()[i][j] == TypeCase.CasePionBlanc) {
					System.out.print("2");
				} else if (renjou.getPlateauDeJeu().getPlateau()[i][j] == TypeCase.CasePionNoir) {
					System.out.print("1");
				}
			}
			System.out.println("");
		}
	}

}
