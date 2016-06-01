package Utilitaire;

import java.awt.Point;

/*
 * Cette classe permet davoir des indice lignes et colonnes pour notre plateau de jeu
 */

public class Coordonnees implements java.io.Serializable{

	private int colonne;
	private int ligne;
	
	public Coordonnees(){
		colonne = 0;
		ligne = 0;
	}
	
	public Coordonnees(int ligne, int colonne){
		this.colonne = colonne;
		this.ligne = ligne;
	}
	
	public Coordonnees(Point p) {
		this.ligne = p.y;
		this.colonne = p.x;
	}

	public String toString(){
		return("ligne : " + ligne + " colonne " + colonne);
	}
	
	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
	
	
	
}
