/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Joueur.Joueur;
import Utilitaire.PlateauDeJeu;
import Utilitaire.Tabou;
import java.util.ArrayList;
import Enum.EtatPartie;

/*
 * Cette classe nous sert de modele, elle contient tout les elements necessaires pour faire une partie
 */
public interface InterfaceRenjou {
	
	// Methodes
	
	/*
	 * Initiliase le renjou avec les valeurs par defaut
	 */
	public void initRenjou();
	
	/*
	 * Renvoi une copie du Renjou
	 */
	public Renjou clone(Renjou renjou);
	
	// Getter and Setter
	public PlateauDeJeu getPlateauDeJeu();
	public Joueur[] getJoueurs();
	public int getJoueurCourant();
	public ArrayList<PionJoue> getListeAnnuler();
	public ArrayList<PionJoue> getListeRefaire();
	public EtatPartie getEtatPartie();
	public Tabou getTabouJeu();
	public void setPlateauDeJeu(PlateauDeJeu plateau);
	public void setJoueurs(Joueur[] tabJoueurs);
	public void setJoueurCourant(int joueurCourant);
	public void setListeAnnuler(ArrayList<PionJoue> listeAnnuler);
	public void setListeRefaire(ArrayList<PionJoue> listeRefaire);
	public void setEtatPartie(EtatPartie etatPartie);
	public void setTabouJeu(Tabou tabouJeu);
	public boolean estModeDebutant();
	public String getEmplacementThemes();
	public int getNbDemiTourCourant();
	public void setEmplacementThemes(String emplacementThemes);
	public void setModeDebutant(boolean modeDebutant);
	public void setNbDemiTourCourant(int n);
	public int getNbJoueurs();
	public void setNouveauTheme(String theme);
	public int getIndiceDebutHistorique();
	public void setIndiceDebutHistorique(int indiceDebutHistorique);
	public int getIndiceFinHistorique();
	public void setIndiceFinHistorique(int indiceFinHistorique);

}
