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
import Utilitaire.PlateauDeJeu;

public abstract class Joueur implements InterfaceJoueur, java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected TypeJoueur type;
	protected int nbPion;
	protected Moteur m;
	protected TypeCouleur couleur;
	protected int nbPionsBase;
	protected PlateauDeJeu pdj;
	
    public Joueur(TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
    	
    	this.type = type;
    	this.nbPion = nbPion;
    	nbPionsBase = nbPion;
    	couleur = couleurJoueur;
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

	
	//0 = Vide
	//1 = Noir
	//2 = Blanc
	@Override
	public abstract Point play(int[][] plateau, int couleurJoueur, boolean tabou3x3, boolean tabou4x4, boolean tabouOverline);
	

	
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
	public abstract Coordonnees jouer(PlateauDeJeu pdj) ;
    
}
