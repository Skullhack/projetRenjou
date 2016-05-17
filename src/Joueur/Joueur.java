/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import Controleur.*;
import Enum.*;


public class Joueur implements InterfaceJoueur {
    TypeJoueur type;
    int nbPion;
    Moteur m;
     
    public Joueur(){
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Joueur(TypeJoueur type, int nbPion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public TypeJoueur getType() {
		return type;
	}

	public void setType(TypeJoueur type) {
		this.type = type;
	}

	public int getNbPion() {
		return nbPion;
	}

	public void setNbPion(int nbPion) {
		this.nbPion = nbPion;
	}
    
	
}
