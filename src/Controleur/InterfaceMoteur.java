/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Enum.EtatPartie;
import Enum.TypeJoueur;
import Enum.TypeTabous;
import Utilitaire.Coordonnees;
import java.util.ArrayList;

public interface InterfaceMoteur {

	// Methodes
	
	/*
	 * Permet d'enregistrer des observeurs (tels que l'IA et l'IHM)
	 */
	public void enregistrerObserveur(MoteurObserveur observer);
	
	/*
	 * Lance une partie en notifiant les observeurs
	 */
	public void commencer();
	
	/*
	 * Donnees : une coordonnee c et un typeJoueur j
	 * verifie si c'est au tour du joueur j de jouer, verif si la coordonnee c est valide 
	 * Effet de bord : modifie le Renjou si la coordonne et le joueur sont corrects
	 */
	public void operationJouer(Coordonnees c, TypeJoueur j);
	
	/*
	 * Renvoi vrai si la partie passer en paramètre (le Renjou) possède une configuration gagnante avec la coordonnee c passer en paramètre
	 */
	public boolean partieFinie(Renjou renjou, Coordonnees c);
	
	/*
	 * Donnees : tout les elements necessaire pour une configuration de partie:
	 * 			typeJoueur 1, TypeJoueur 2, liste des tabous actifs, si c'est une nouvelle ou juste une configuration de partie, si le mode debutant est active.
	 * Effet de bord : modifie tout les paramètre du jeu (Renjou)
	 */
	public void configurerPartie(TypeJoueur typeJoueur1, TypeJoueur typeJoueur2, ArrayList<TypeTabous> tabouPartie,
			boolean nouvellePartie, boolean modeDebutant);
	
	/*
	 * Redemarre une partie à 0 en gardant les configurations existantes
	 */
	public void recommencerPartie();
	
	/*
	 * Permet de sauvegarder l'état de la partie dans un fichier externe
	 */
	public void sauvegarder(String nomFichier);
	
	/*
	 * Permet de charger une partie a partir d'un fichier externe
	 */
	public void charger(String fichierCharger);
	
	/*
	 * Permet de faire jouer deux ia entre elles et renvoi l'état de la partie termine
	 */
	public EtatPartie faireJouerIAVsIAPourTest();
	
	/*
	 * Permet d'annuler un demi coup
	 * Si on a joueur contre IA alors on annule 2 demi-coups
	 * Si non on annule juste un coup
	 * Effet de bord : modifie l'état du jeu
	 */
	public void annuler();
	
	/*
	 * Permet d'annuler un demi tour, c'est à dire de revenir à l'état précédent un demi tour
	 * Effet de bord : modifie l'état du jeu
	 */
	public void annulerDemiCoup();
	
	/*
	 * Permet d'annuler n demi tour
	 * Effet de bord : modifie l'état du jeu
	 */
	public void annulerNDemiCoup(int n);
	
	/*
	 * Permet de refaire un demi coup
	 * Si on a joueur contre IA alors on refait 2 demi-coups
	 * Si non on refait juste un coup
	 * Effet de bord : modifie l'état du jeu
	 */
	public void refaire();
	
	/*
	 * Permet de refaire un demi tour, c'est à dire de revenir à l'état suivant un demi tour
	 * Effet de bord : modifie l'état du jeu
	 */
	public void refaireDemiCoup();
	
	/*
	 * Permet de refaire n demi tour
	 * Effet de bord : modifie l'état du jeu
	 */
	public void refaireNDemiCoup(int n);
	
	/*
	 * Renvoi vrai si on est au premier tour de jeu
	 */
	public boolean premierCoup();
	
	/*
	 * Renvoi vrai si on est au deuxième tour de jeu
	 */
	public boolean deuxiemeCoup();
	
	/*
	 * Renvoi une coordonnée pouvant être joué en appelant l'IA facile
	 */
	public Coordonnees aide();
	
	// Getter and Setter
	public InterfaceRenjou getRenjou();
	public void setRenjou(Renjou renjou);

}
