package Controleur;

import java.awt.Point;

public class Coordonees {

	private int colonne;
	private int ligne;
	
	public Coordonees(){
		colonne = 0;
		ligne = 0;
	}
	
	public Coordonees(int ligne, int colonne){
		this.colonne = colonne;
		this.ligne = ligne;
	}
	
	public Coordonees(Point p) {
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
