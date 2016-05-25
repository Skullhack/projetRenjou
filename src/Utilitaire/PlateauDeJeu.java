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
	private int nbPionBlanc;
	private int nbPionNoir;

	// Constructeur
	public PlateauDeJeu() {
		this.plateau = new TypeCase[lignes][colonnes];
		nbPionBlanc = 0;
		nbPionNoir = 0;
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

	public void initPlateauDeuxiemeCoup() {
		// on met toutes les cases injouables sauf le premier coup du joueur
		// noir qui n'est pas modifié
		int milieuLignes = lignes / 2;
		int milieuColonnes = colonnes / 2;

		for (int i = 0; i < lignes; i++) {
			for (int j = 0; j < colonnes; j++) {

				if (i == milieuLignes && j == milieuColonnes) {
					// on ne modifie pas le coup déjà joué par le joueur noir
				} else {
					this.plateau[i][j] = TypeCase.Injouable;
				}
			}
		}

		// le tabou du joueur Blanc : les 8 cases adjacentes du premier coup
		Coordonnees c = new Coordonnees();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i != 0 || j != 0) {
					c.setLigne(milieuLignes + i);
					c.setColonne(milieuColonnes + j);
					this.ajouter(c, TypeCase.Jouable);
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

	public int getNbPionBlanc(){
		return nbPionBlanc;
	}
	
	public int getNbPionNoir(){
		return nbPionNoir;
	}	
	@Override
	public void ajouter(Coordonnees c, TypeCase typeCase) {
		this.plateau[c.getLigne()][c.getColonne()] = typeCase;
		if(typeCase == TypeCase.PionBlanc){
			nbPionBlanc++;
		}
		if(typeCase == TypeCase.PionNoir){
			nbPionNoir++;
		}		
	}

	@Override
	public void enlever(Coordonnees c) {
		if(this.plateau[c.getLigne()][c.getColonne()] == TypeCase.PionBlanc){
			nbPionBlanc--;
		}
		if(this.plateau[c.getLigne()][c.getColonne()] == TypeCase.PionNoir){
			nbPionNoir--;
		}	
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
				plateauDeJeuClone.ajouter(new Coordonnees(i,j), this.plateau[i][j]);
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
					pdj.ajouter(new Coordonnees(i,j), charToTypeCase(ligne.charAt(j)));
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
