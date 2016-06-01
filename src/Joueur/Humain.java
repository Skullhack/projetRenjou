/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;


import java.awt.Point;
import Enum.*;
import Utilitaire.Coordonnees;
import Utilitaire.PlateauDeJeu;

public class Humain extends Joueur {

    public Humain(TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
        super(type,nbPion,couleurJoueur);
	}

	@Override
	public Point play(int[][] plateau, int couleurJoueur, boolean tabou3x3, boolean tabou4x4, boolean tabouOverline) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coordonnees jouer(PlateauDeJeu pdj) {
		// TODO Auto-generated method stub
		return null;
	}
}
