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
    
    //Methodes
    public Renjou annuler(Renjou renjou);
    public Renjou annulerDemiCoup(Renjou renjou);
    public Renjou refaire(Renjou renjou);
}
