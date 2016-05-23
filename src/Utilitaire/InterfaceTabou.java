/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import java.awt.Point;

import Controleur.Coordonnees;
import Controleur.Renjou;

/**
 *
 * @author michauad
 */
public interface InterfaceTabou {
	// Getter
	public String getNom();

	public boolean[][] getConfiguration();

	// Methodes
	public boolean estValide(Renjou r, Coordonnees c);
}
