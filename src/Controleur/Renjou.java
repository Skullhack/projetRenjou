/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Joueur.Joueur;
import java.util.ArrayList;

/**
 *
 * @author michauad
 */
public class Renjou implements InterfaceRenjou {
	private PlateauDeJeu plateau;
	private Joueur[] tabJoueurs;
	private int joueurCourant;
	private ArrayList<PlateauDeJeu> listeAnnuler;
	private ArrayList<PlateauDeJeu> listeRefaire;
	private boolean partieFinie;
	private ArrayList<Tabous> tabouJeu;
	private Tabous tabous;

	// Constructeur
	public Renjou(Joueur[] tabJoueurs) {
		this.plateau = new PlateauDeJeu();
		this.tabJoueurs = tabJoueurs;
		this.listeAnnuler = new ArrayList<PlateauDeJeu>();
		this.listeRefaire = new ArrayList<PlateauDeJeu>();
		partieFinie = false;

	}

	@Override
	public PlateauDeJeu getPlateauDeJeu() {
		return this.plateau;
	}

	@Override
	public Joueur[] getJoueurs() {
		return this.tabJoueurs;
	}

	@Override
	public int getJoueurCourant() {
		return this.joueurCourant;
	}

	@Override
	public ArrayList<PlateauDeJeu> getListeAnnuler() {
		return this.listeAnnuler;
	}

	@Override
	public ArrayList<PlateauDeJeu> getListeRefaire() {
		return this.listeRefaire;
	}

	@Override
	public boolean getPartieFinie() {
		return this.partieFinie;
	}

	public void setPlateauDeJeu(PlateauDeJeu plateau) {
		this.plateau = plateau;
	}

	public void setJoueurs(Joueur[] tabJoueurs) {
		this.tabJoueurs = tabJoueurs;
	}

	public void setJoueurCourant(int joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	public void setListeAnnuler(ArrayList<PlateauDeJeu> listeAnnuler) {
		this.listeAnnuler = listeAnnuler;
	}

	public void setListeRefaire(ArrayList<PlateauDeJeu> listeRefaire) {
		this.listeRefaire = listeRefaire;
	}

	public void setPartieFinie(boolean partieFinie) {
		this.partieFinie = partieFinie;
	}

	@Override
	public Renjou annuler(Renjou renjou) {
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
	public Renjou annulerDemiCoup(Renjou renjou) {
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
	public Renjou refaire(Renjou renjou) {
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
