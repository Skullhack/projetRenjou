/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Enum.TypeJoueur;
import Joueur.Joueur;

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

	public void nouvellePartie(TypeJoueur[] tabTypeJoueurs);
	
	public void recommencerPartie();

	public void joueurSuivant();

	public boolean partieFinie(Renjou renjou, Coordonnees c);
	
	public void annuler(Renjou renjou);

	public void annulerDemiCoup(Renjou renjou);

	public void refaire(Renjou renjou);
	
	public void refaireDemiCoup(Renjou renjou);
}
