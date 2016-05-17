/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Enum.TypeCase;

/**
 *
 * @author michauad
 */
public class PlateauDeJeu implements InterfacePlateauDeJeu {
	TypeCase[][] plateau;
	int lignes = 15;
	int colonnes = 15;

	// Constructeur
	public PlateauDeJeu() {
		this.plateau = new TypeCase[lignes][colonnes];

		// si on evolue le terrain, le terrain
		// doit systematiquement avoir une
		// valeur impaire (pour avoir un point
		// central sur le plateau)
		int milieuLignes = lignes / 2;
		int milieuColonnes = colonnes / 2;

		for (int i = 0; i < lignes; i++) {
			for (int j = 0; j < colonnes; j++) {

				if (i == milieuLignes && j == milieuColonnes) {
					// on se trouve au milieu de la grille;
					this.plateau[i][j] = TypeCase.CaseJouable;
				} else {
					this.plateau[i][j] = TypeCase.CaseInjouable;
				}
			}
		}

	}

	@Override
	public TypeCase[][] getPlateau() {
		return this.plateau;
	}

	public void setPlateau(TypeCase[][] plateau) {
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
