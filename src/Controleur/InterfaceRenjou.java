/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Joueur.Joueur;
import java.awt.List;
import java.util.ArrayList;

import Enum.EtatPartie;

/**
 *
 * @author michauad
 */
public interface InterfaceRenjou { 
    //Getter
    public PlateauDeJeu getPlateauDeJeu();
    public Joueur[] getJoueurs();
    public int getJoueurCourant();
    public ArrayList<PlateauDeJeu> getListeAnnuler();
    public ArrayList<PlateauDeJeu> getListeRefaire();
    public EtatPartie getEtatPartie();
    public ArrayList<Tabou> getTabouJeu();
	public Tabous getTabous();
    
    //Setter
    public void setPlateauDeJeu(PlateauDeJeu plateau);
    public void setJoueurs(Joueur[] tabJoueurs);
    public void setJoueurCourant(int joueurCourant);
    public void setListeAnnuler(ArrayList<PlateauDeJeu> listeAnnuler);
    public void setListeRefaire(ArrayList<PlateauDeJeu> listeRefaire);
    public void setEtatPartie(EtatPartie etatPartie);
	public void setTabouJeu(ArrayList<Tabou> tabouJeu);
	public void setTabous(Tabous tabous);
    
    //Methodes
    public Renjou annuler(Renjou renjou);
    public Renjou annulerDemiCoup(Renjou renjou);
    public Renjou refaire(Renjou renjou);
    
    public Renjou clone(Renjou renjou);
    
}
