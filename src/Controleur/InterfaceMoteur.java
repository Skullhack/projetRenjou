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

	public void charger(String nomFichier);

	public boolean coupValide(Renjou renjou, Coordonees c);

	public void operationJouer(Coordonees c, Joueur j);

	public void nouvellePartie(TypeJoueur[] tabTypeJoueurs);

	public void joueurSuivant();

	public boolean partieFinie(Renjou renjou);
}
