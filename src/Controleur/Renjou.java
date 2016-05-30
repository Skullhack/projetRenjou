/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Joueur.Joueur;
import Utilitaire.PlateauDeJeu;
import Utilitaire.Tabou;

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
	private Tabou tabousJeu;
	private String emplacementThemes;
	private boolean modeDebutant;
	private int nbDemiTourCourant;
	private int indiceDebutHistorique;
	private int indiceFinHistorique;

	// Constructeur
	public Renjou(Joueur[] tabJoueurs) {

		this.plateau = new PlateauDeJeu();
		this.listeAnnuler = new ArrayList<PionJoue>();
		this.listeRefaire = new ArrayList<PionJoue>();
		this.etatPartie = EtatPartie.EnCours;
		this.joueurCourant = 0;
		this.tabJoueurs = tabJoueurs;
		this.setIndiceDebutHistorique(0);
		this.setIndiceFinHistorique(0);
		
		ArrayList<TypeTabous> listeTabou = new ArrayList<TypeTabous>();
		listeTabou.add(TypeTabous.TROIS_TROIS);
		listeTabou.add(TypeTabous.QUATRE_QUATRE);
		listeTabou.add(TypeTabous.SIX_SEPT);
		this.tabousJeu = new Tabou(listeTabou);
		// ajout des tabous en dur
		// ArrayList<TypeTabous> listeTabous = new ArrayList<TypeTabous>();
		// listeTabous.add(TypeTabous.TROIS_TROIS);
		// fin ajout des tabous en dur

		this.emplacementThemes = "Traditionnel";
		this.modeDebutant = false;

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

	public Tabou getTabouJeu() {
		return tabousJeu;
	}

	public String getEmplacementThemes() {
		return emplacementThemes;
	}
	
	public int getNbDemiTourCourant(){
		return nbDemiTourCourant ;
	}
	
	public boolean estModeDebutant() {
		return modeDebutant;
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

	public void setTabouJeu(Tabou tabousJeu) {
		this.tabousJeu = tabousJeu;
	}

	public void setEmplacementThemes(String emplacementThemes) {
		this.emplacementThemes = emplacementThemes;
	}
	
	public void setModeDebutant(boolean modeDebutant) {
		this.modeDebutant = modeDebutant;
	}

	public void setNbDemiTourCourant(int n){
		nbDemiTourCourant = n;
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

	public void setNouveauTheme(String theme) {
		this.emplacementThemes = theme;
	}

	public int getIndiceDebutHistorique() {
		return indiceDebutHistorique;
	}

	public void setIndiceDebutHistorique(int indiceDebutHistorique) {
		this.indiceDebutHistorique = indiceDebutHistorique;
	}

	public int getIndiceFinHistorique() {
		return indiceFinHistorique;
	}

	public void setIndiceFinHistorique(int indiceFinHistorique) {
		this.indiceFinHistorique = indiceFinHistorique;
	}

}
