package Controleur;

import java.awt.Point;

public class Coordonee {

	private int colonne;
	private int ligne;
	
	public Coordonee(){
		colonne = 0;
		ligne = 0;
	}
	
	public Coordonee(int ligne, int colonne){
		this.colonne = colonne;
		this.ligne = ligne;
	}
	
	public Coordonee(Point p) {
		this.ligne = p.y;
		this.colonne = p.x;
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
