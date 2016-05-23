/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import java.awt.Point;

import Controleur.*;
import Enum.*;
import Utilitaire.Coordonnees;
import Utilitaire.Log;
import Utilitaire.PlateauDeJeu;

public class Joueur implements MoteurObserveur, java.io.Serializable {
	protected TypeJoueur type;
	protected int nbPion;
	protected Moteur m;
	protected TypeCouleur couleur;
	protected int nbPionsBase;
	protected PlateauDeJeu pdj;
	
    public Joueur(Moteur moteur, TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
    	moteur.enregistrerObserveur(this);
    	this.type = type;
    	this.nbPion = nbPion;
    	nbPionsBase = nbPion;
    	couleur = couleurJoueur;
    	m = moteur;
    	pdj = new PlateauDeJeu();
    }
    

	// Getter et setter
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

	public int getNbPionsBase() {
		return nbPionsBase;
	}

	public void setNbPionsBase(int nbPionsBase) {
		this.nbPionsBase = nbPionsBase;
	}

	public Moteur getMoteur() {
		return m;
	}

	public void setMoteur(Moteur m) {
		this.m = m;
	}

	public TypeCouleur getCouleur() {
		return couleur;
	}

	public void setCouleur(TypeCouleur c) {
		this.couleur = c;
	}

	public Point play(int[][] plateau, int couleurJoueur, boolean tabou3x3, boolean tabou4x4, boolean tabouOverline){
		//0 = Vide
		//1 = Noir
		//2 = Blanc

		return new Point(0,0);
	}
	
	
	public void initPlateauEtCouleur(int[][] plateau, int couleur){
		int pN=nbPionsBase;
		int pB=nbPionsBase;
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				if(plateau[i][j] == 1){
					pdj.ajouter(new Coordonnees(i,j), TypeCase.PionNoir);
					pN--;
				}else if(plateau[i][j] == 0){
					pdj.ajouter(new Coordonnees(i,j), TypeCase.PionBlanc);
					pB--;
				}else{
					pdj.ajouter(new Coordonnees(i,j), TypeCase.Jouable);
				}
			}
		}
		
		if(couleur == 1){
			nbPion = pN;
			this.couleur = TypeCouleur.Noir;
		}else{
			nbPion = pB;
			this.couleur = TypeCouleur.Blanc;
		}
	}
	
	@Override 
	public void actualiser() {
		Log.print(3, "Joueur notifie");
	}
    
}
