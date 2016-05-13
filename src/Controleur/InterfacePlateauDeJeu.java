/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

/**
 *
 * @author michauad
 */
public interface InterfacePlateauDeJeu {   
    //Getter
    public Case[][] getPlateau();
    
    //Methodes
    public void ajouter(Case c);
    public void enlever(Case c);
    public PlateauDeJeu clone();
}
