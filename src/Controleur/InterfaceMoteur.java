/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Enum.TypeJoueur;
import Joueur.Joueur;
import Utilitaire.Coordonnees;
import Utilitaire.Tabou;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author michauad
 */
public interface InterfaceMoteur {
	// Getter
	public InterfaceRenjou getRenjou();

	// Setter
	public void setRenjou(Renjou renjou);

	// Methodes
	public void sauvegarder(String nomFichier);

	public void charger(String fichierCharger);

	public void operationJouer(Coordonnees c, TypeJoueur j);

	public void configurerPartie(TypeJoueur typeJoueur1, TypeJoueur typeJoueur2, ArrayList<Tabou> tabouPartie,
			boolean nouvellePartie);

	public void recommencerPartie();

	public void joueurSuivant();

	public boolean partieFinie(Renjou renjou, Coordonnees c);

	public void annuler();

	public void annulerDemiCoup();

	public void refaire();

	public void refaireDemiCoup();
}
