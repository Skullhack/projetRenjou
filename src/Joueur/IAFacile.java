/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import Controleur.*;
import Enum.*;


public class IAFacile extends IA {
	
	public IAFacile(TypeJoueur type, int nbPion, Moteur m){
		super();
		this.m = m;
		this.type = type;
		this.nbPion = nbPion;
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		Point p = jouer();
		Point pInverse = new Point(p.y, p.x);
		//m.operationJouer(pInverse, type);
		
		
	}
	
	public Point jouer(){
		

		
		return null;
	}
	
	public boolean caseJouable(Point p){
		boolean retour = (m.getRenjou().getPlateauDeJeu().getPlateau()[p.x][p.y] == TypeCase.CasePionBlanc);
		retour = retour && (m.getRenjou().getPlateauDeJeu().getPlateau()[p.x][p.y] == TypeCase.CasePionNoir);
		retour = retour && (m.getRenjou().getPlateauDeJeu().getPlateau()[p.x][p.y] == TypeCase.CaseInjouable);
		return retour;
	}
	
	public void modifierHeristique(Point p){
		
	}
    
	public void initHeuristique(){
		
		for(int i=0; i<13; i++){
			for(int j=0; j<13; j++){
				Point p = new Point(i, j);
				if(caseJouable(p)){
					
				}
			}
		}
		
	}
	
}
