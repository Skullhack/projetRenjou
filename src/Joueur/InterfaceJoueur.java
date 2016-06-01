package Joueur;

import java.awt.Point;

import Utilitaire.Coordonnees;
import Utilitaire.PlateauDeJeu;

public interface InterfaceJoueur {

	
	/*
	 * Donnees : un plateau de jeu
	 * Resultat : une coordonnées correspond à un coup joué par l'IA
	 */
	public Coordonnees jouer(PlateauDeJeu pdj);
	
	/*
	 * Méthode utilisée pour une integration avec une IA externe
	 * Donnees : un tableau d'int correspondant à un plateau de jeu, un int correspondant à la couleur du joueur, trois boolean représantant les tabous
	 * Resultat : un point correspond à un coup joué par l'IA
	 */
	public Point play(int[][] plateau, int couleurJoueur, boolean tabou3x3, boolean tabou4x4, boolean tabouOverline);
	
}
