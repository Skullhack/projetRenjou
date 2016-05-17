/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Enum.TypeJoueur;
import Joueur.Joueur;

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
	public boolean coupValide(Renjou renjou, Case c) {
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
	public ArrayList<Case> listeCoupValide(Renjou renjou) {
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
	public void operationJouer(Case c, Joueur j) {
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
}
