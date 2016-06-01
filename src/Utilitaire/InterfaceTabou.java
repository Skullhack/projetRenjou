/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import java.util.ArrayList;
import Enum.TypeTabous;


/*
 * Cette classe permet de gerer les tabous
 */
public interface InterfaceTabou {

	
	// Methode
	
	/*
	 * Renvoi vrai si la coordonnee c donnee en parametre ne fait pas apparaitre un tabou sur le plateau plateau
	 * Cette methode existe en static en lui donnant les tabous actifs en parametre
	 */
	boolean estValide(PlateauDeJeu plateau, Coordonnees c);
	
	/*
	 * Renvoi vrai si le Tabou typeTabou est actif dans la partie
	 */
	public boolean estDansListeTabous (TypeTabous typeTabou);
	
	// Getter and Setter
	public ArrayList<TypeTabous> getListeTabous();
	public void setListeTabous(ArrayList<TypeTabous> listeTabous);
	
}
