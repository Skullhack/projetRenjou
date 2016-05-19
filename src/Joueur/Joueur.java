/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import java.util.Observable;
import java.util.Observer;

import Controleur.*;
import Enum.*;

public class Joueur implements MoteurObserveur, java.io.Serializable {
	protected TypeJoueur type;
	protected int nbPion;
	protected Moteur m;
	protected TypeCouleur couleur;
	protected int nbPionsBase;

	public Joueur(Moteur moteur, TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
		this.type = type;
		this.nbPion = nbPion;
		nbPionsBase = nbPion;
		couleur = couleurJoueur;
		m = moteur;
		moteur.enregistrerObserveur(this);
	}

	// Getter et setter
	public TypeJoueur getType() {
		return type;
	}

	public void setType(TypeJoueur type) {
		this.type = type;
	}

	public int getNbPion() {
		return nbPion;
	}

	public void setNbPion(int nbPion) {
		this.nbPion = nbPion;
	}

	public int getNbPionsBase() {
		return nbPionsBase;
	}

	public void setNbPionsBase(int nbPionsBase) {
		this.nbPionsBase = nbPionsBase;
	}

	public Moteur getMoteur() {
		return m;
	}

	public void setMoteur(Moteur m) {
		this.m = m;
	}

	public TypeCouleur getCouleur() {
		return couleur;
	}

	public void setCouleur(TypeCouleur c) {
		this.couleur = c;
	}

	@Override
	public void actualiser() {
		// TODO Auto-generated method stub
		// doit lancer jouer()
		m.printTrace(3, "IA notifiee");

	}

}
