/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Joueur.Joueur;
import Utilitaire.PlateauDeJeu;
import Utilitaire.Tabou;
import Utilitaire.Tabous;

import java.awt.Point;
import java.util.ArrayList;
import Enum.*;

/**
 *
 * @author michauad
 */
public class Renjou implements InterfaceRenjou, java.io.Serializable {
	private PlateauDeJeu plateau;
	private Joueur[] tabJoueurs;
	private int joueurCourant;
	private ArrayList<PionJoue> listeAnnuler;
	private ArrayList<PionJoue> listeRefaire;
	private EtatPartie etatPartie;
	private ArrayList<Tabou> tabouJeu;
	private Tabous tabous;
	private String emplacementThemes;

	// Constructeur
	public Renjou(Joueur[] tabJoueurs) {
		
		this.plateau = new PlateauDeJeu();
		this.listeAnnuler = new ArrayList<PionJoue>();
		this.listeRefaire = new ArrayList<PionJoue>();
		this.etatPartie = EtatPartie.EnCours;
		this.joueurCourant = 0;
		this.tabJoueurs = tabJoueurs;
		
		// bloc de tabous en dur pour le moment
		this.tabouJeu = new ArrayList<Tabou>();
		this.tabous = new Tabous();

		boolean[][] configInterdite = new boolean[15][15];
		Tabou tabou1 = new Tabou(configInterdite, "TROIS-TROIS");
		Tabou tabou2 = new Tabou(configInterdite, "FAUX-TROIS");
		Tabou tabou3 = new Tabou(configInterdite, "QUATRE");
		Tabou tabou4 = new Tabou(configInterdite, "SIX");
		this.tabouJeu = new ArrayList<Tabou>();

		tabous.getTabous().add(tabou1);
		tabous.getTabous().add(tabou2);
		tabous.getTabous().add(tabou3);
		tabous.getTabous().add(tabou4);

		tabouJeu.add(tabou1);
		tabouJeu.add(tabou2);
		tabouJeu.add(tabou3);
		tabouJeu.add(tabou4);
		// fin du bloc tabous

		this.emplacementThemes = "Traditionnel";

	}

	public void initRenjou() {
		this.plateau.initPlateau();
		this.listeAnnuler.clear();
		this.listeRefaire.clear();
		setEtatPartie(EtatPartie.EnCours);
		setJoueurCourant(0);
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

	public String getEmplacementThemes() {
		return emplacementThemes;
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

	public void setEmplacementThemes(String emplacementThemes) {
		this.emplacementThemes = emplacementThemes;
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
