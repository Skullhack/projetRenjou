/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import java.awt.Point;
import java.io.*;

import Enum.TypeCase;

/**
 *
 * @author michauad
 */
public class PlateauDeJeu implements InterfacePlateauDeJeu, java.io.Serializable {
	private TypeCase[][] plateau;
	private final int lignes = 15;
	private final int colonnes = 15;

	// Constructeur
	public PlateauDeJeu() {
		this.plateau = new TypeCase[lignes][colonnes];

		// si on evolue le terrain, le terrain
		// doit systematiquement avoir une
		// valeur impaire (pour avoir un point
		// central sur le plateau)
		initPlateau();

	}

	public void initPlateau() {

		int milieuLignes = lignes / 2;
		int milieuColonnes = colonnes / 2;

		for (int i = 0; i < lignes; i++) {
			for (int j = 0; j < colonnes; j++) {

				if (i == milieuLignes && j == milieuColonnes) {
					// on se trouve au milieu de la grille;
					this.plateau[i][j] = TypeCase.Jouable;
				} else {
					this.plateau[i][j] = TypeCase.Injouable;
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

	public int getColonnes() {
		return colonnes;
	}

	public int getLignes() {
		return lignes;
	}

	@Override
	public void ajouter(Coordonnees c, TypeCase typeCase) {
		this.plateau[c.getLigne()][c.getColonne()] = typeCase;
	}

	@Override
	public void enlever(Coordonnees c) {
		this.plateau[c.getLigne()][c.getColonne()] = TypeCase.Jouable;
	}

	public void supprimerCasesTabous() {
		for (int i = 0; i < this.lignes; i++) {
			for (int j = 0; j < this.colonnes; j++) {
				if (this.plateau[i][j] == TypeCase.Tabou) {
					this.plateau[i][j] = TypeCase.Jouable;
				}
			}
		}
	}

	public void supprimerCasesInjouables() {
		for (int i = 0; i < this.lignes; i++) {
			for (int j = 0; j < this.colonnes; j++) {
				if (this.plateau[i][j] == TypeCase.Injouable) {
					this.plateau[i][j] = TypeCase.Jouable;
				}
			}
		}
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

	public TypeCase getTypeCaseTableau(Coordonnees c) {
		return this.plateau[c.getLigne()][c.getColonne()];
	}

	public TypeCase getTypeCaseTableauParLigneColonne(int lignes, int colonnes) {
		return this.plateau[lignes][colonnes];
	}

	public static TypeCase charToTypeCase(char c) {
		switch (c) {
		case 'N':
			return TypeCase.PionNoir;
		case 'B':
			return TypeCase.PionBlanc;
		case 'X':
			return TypeCase.Tabou;
		case '.':
			return TypeCase.Jouable;
		case 'I':
			return TypeCase.Injouable;
		default:
			return TypeCase.Tabou;
		}
	}

	public static String TypeCasetoString(TypeCase tc) {
		switch (tc) {
		case PionNoir:
			return "N";
		case PionBlanc:
			return "B";
		case Tabou:
			return "X";
		case Jouable:
			return ".";
		case Injouable:
			return "I";
		default:
			return "X";
		}
	}

	public PlateauDeJeu charger(String nomFichier) {
		PlateauDeJeu pdj = new PlateauDeJeu();

		try {
			InputStream flux = new FileInputStream(nomFichier);
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			String ligne;
			int i = 0;
			while ((ligne = buff.readLine()) != null) {
				for (int j = 0; j < ligne.length(); j++)
					pdj.plateau[i][j] = charToTypeCase(ligne.charAt(j));
				i++;
			}
			buff.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return pdj;

	}

	public String toString() {

		String buff = new String();
		for (int i = 0; i < this.getLignes(); i++) {
			for (int j = 0; j < this.getColonnes(); j++) {
				buff += TypeCasetoString(this.getPlateau()[i][j]);
			}

			buff += "\n";
		}
		return buff;
	}
}
