/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Joueur.Joueur;
import java.awt.List;
import java.util.ArrayList;

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
    public boolean getPartieFinie();
    
    //Setter
    public void setPlateauDeJeu(PlateauDeJeu plateau);
    public void setJoueurs(Joueur[] tabJoueurs);
    public void setJoueurCourant(int joueurCourant);
    public void setListeAnnuler(ArrayList<PlateauDeJeu> listeAnnuler);
    public void setListeRefaire(ArrayList<PlateauDeJeu> listeRefaire);
    public void setPartieFinie(boolean partieFinie);
    
    
    //Methodes
    public Renjou annuler(Renjou renjou);
    public Renjou annulerDemiCoup(Renjou renjou);
    public Renjou refaire(Renjou renjou);
}
