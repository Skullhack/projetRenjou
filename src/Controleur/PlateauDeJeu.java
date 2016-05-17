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
public class PlateauDeJeu implements InterfacePlateauDeJeu {
	Case[][] plateau;
	int lignes = 15;
	int colonnes = 15;

	// Constructeur
	public PlateauDeJeu() {
		this.plateau = new Case[lignes][colonnes];
	}

	@Override
	public Case[][] getPlateau() {
		return this.plateau;
	}

	public void setPlateau(Case[][] plateau) {
		this.plateau = plateau;
	}

	@Override
	public void ajouter(Case c) {

		throw new UnsupportedOperationException("Not supported yet."); // To
		// change
		// body
		// of
		// generated
		// methods,
		// choose
		// Tools
		// |
		// Templates.

	}

	@Override
	public void enlever(Case c) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public PlateauDeJeu clone() {
		PlateauDeJeu plateauDeJeuClone = new PlateauDeJeu();
		for (int i = 0; i < this.lignes; i++) {
			for (int j = 0; j < this.colonnes; j++) {
				plateauDeJeuClone.plateau[i][j] = this.plateau[i][j];
			}
		}
		return plateauDeJeuClone;

	}

}
