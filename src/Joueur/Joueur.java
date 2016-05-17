/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import java.util.Observable;
import java.util.Observer;

import Controleur.*;
import Enum.*;


public class Joueur implements Observer {
    public TypeJoueur type;
    public int nbPion;
    public Moteur m;
     
    public Joueur(){
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Joueur(TypeJoueur type, int nbPion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		 throw new UnsupportedOperationException("Not supported yet.");
		
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
	
	public Moteur getMoteur() {
		return m;
	}
	
	public void setMoteur(Moteur m) {
		this.m = m;
	}

    
	
}
