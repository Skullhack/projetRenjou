/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Joueur.Joueur;

import java.awt.Point;
import java.util.ArrayList;
import Enum.*;

/**
 *
 * @author michauad
 */
public class Renjou implements InterfaceRenjou {
	private PlateauDeJeu plateau;
	private Joueur[] tabJoueurs;
	private int joueurCourant;
	private ArrayList<PionJoue> listeAnnuler;
	private ArrayList<PionJoue> listeRefaire;
	private EtatPartie etatPartie;
	private ArrayList<Tabou> tabouJeu;
	private Tabous tabous;

	// Constructeur
	public Renjou(Joueur[] tabJoueurs) {
		this.plateau = new PlateauDeJeu();
		this.tabJoueurs = tabJoueurs;
		this.listeAnnuler = new ArrayList<PionJoue>();
		this.listeRefaire = new ArrayList<PionJoue>();
		etatPartie = EtatPartie.EnCours;

		this.tabouJeu = new ArrayList<Tabou>(); // ligne qui sera à modifier.
												// pour le moment, mis en dur
		this.joueurCourant = 0;

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
	public ArrayList<PionJoue> getListeAnnuler() {
		return this.listeAnnuler;
	}

	@Override
	public ArrayList<PionJoue> getListeRefaire() {
		return this.listeRefaire;
	}

	public EtatPartie getEtatPartie() {
		return etatPartie;
	}

	public ArrayList<Tabou> getTabouJeu() {
		return tabouJeu;
	}

	public Tabous getTabous() {
		return tabous;
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

	public void setListeAnnuler(ArrayList<PionJoue> listeAnnuler) {
		this.listeAnnuler = listeAnnuler;
	}

	public void setListeRefaire(ArrayList<PionJoue> listeRefaire) {
		this.listeRefaire = listeRefaire;
	}

	public void setEtatPartie(EtatPartie etatPartie) {
		this.etatPartie = etatPartie;
	}

	public void setTabouJeu(ArrayList<Tabou> tabouJeu) {
		this.tabouJeu = tabouJeu;
	}

	public void setTabous(Tabous tabous) {
		this.tabous = tabous;
	}

	public int getNbJoueurs() {
		return tabJoueurs.length;
	}

	public Renjou clone(Renjou renjou) {
		try {
			Object renjouClone = this.clone();
			return (Renjou) renjouClone;
		} catch (CloneNotSupportedException e) {
			System.out.println("le clone n'a pas pu être effectué. Trace : " + e);
		}
		return null;
	}

}
