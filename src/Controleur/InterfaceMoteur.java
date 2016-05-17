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
public interface InterfaceMoteur {
	// Getter
	public InterfaceRenjou getRenjou();

	// Setter
	public void setRenjou(Renjou renjou);

	// Methodes
	public void sauvegarder(String nomfichier);

	public void charger(String nomfichier);

	public boolean coupValide(Renjou renjou, Case c);

	public ArrayList<Case> listeCoupValide(Renjou renjou);

	public void operationJouer(Case c, Joueur j);

	public void nouvellePartie(TypeJoueur[] tjoueurs);

	public void joueurSuivant();

	public boolean partieFinie(Renjou renjou);
}
