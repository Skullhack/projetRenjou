/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;


import Enum.*;

/*
 * Cette classe permet de sauvegarder l'etat du plateau de jeu
 */
public interface InterfacePlateauDeJeu {

	// Methodes
	
	/*
	 * Permet de mettre le plateau au tour 0 ( seul le centre est jouable)
	 */
	public void initPlateau();
	
	/*
	 * Permet de mettre le plateau au tour 1 ( seuls les cases a cote du centre sont jouables)
	 */
	public void initPlateauDeuxiemeCoup();
	
	/*
	 * Transforme la case de coordonne c par le TypeCase typeCase et met a jour le nombre de pion des joueurs
	 */
	public void ajouter(Coordonnees c, TypeCase typeCase);
	
	/*
	 * Transforme la case de coordonne c par jouable et met a jour le nombre de pion des joueurs
	 */
	public void enlever(Coordonnees c);
	
	/*
	 * Transforme les cases tabous par des cases jouables
	 */
	public void supprimerCasesTabous();
	
	/*
	 * Transforme les cases injouables par des cases jouables
	 */
	public void supprimerCasesInjouables();
	
	/*
	 * Renvoi le typeCase de la coordonnee c
	 */
	public TypeCase getTypeCaseTableau(Coordonnees c);
	
	/*
	 * Renvoi le typeCase de la ligne et de la colonne donnees en parametre
	 */
	public TypeCase getTypeCaseTableau(int lignes, int colonnes);
	
	/*
	 * Charge un plateau de jeu a partir d'un fichier externe
	 */
	public PlateauDeJeu charger(String nomFichier);
	
	/*
	 * Renvoi une copie du plateau de jeu
	 */
	public PlateauDeJeu clone();
	
	/*
	 * Renvoi un string correspond a l'affichage du plateau de jeu
	 */
	public String toString();
	
	// Getter and Setter
	public TypeCase[][] getPlateau();
	public void setPlateau(TypeCase[][] plateau);
	public int getColonnes();
	public int getLignes();
	public int getNbPionBlanc();
	public int getNbPionNoir();
	

}
