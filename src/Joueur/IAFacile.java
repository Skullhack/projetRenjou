/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import java.util.GregorianCalendar;
import java.util.Random;

import Enum.TypeJoueur;

/**
 *
 * @author michauad
 */
public class IAFacile extends IA {
	
	Random r;
	
	public IAFacile(TypeJoueur type, int nbPion){
		super();
		this.type = type;
		this.nbPion = nbPion;
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
    
}
