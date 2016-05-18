/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Enum.EtatPartie;
import Enum.TypeCase;
import Enum.TypeCouleur;
import Enum.TypeDecalage;
import Enum.TypeJoueur;
import Joueur.Humain;
import Joueur.Joueur;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author michauad
 */
public class Moteur implements InterfaceMoteur {
	private Renjou renjou;
	private Log trace;

	// Constructeur
	public Moteur(int nbJoueurs) {
		trace = new Log();
		Joueur[] tableauJoueurs = new Joueur[nbJoueurs];
		
		tableauJoueurs[0] = new Humain(this, TypeJoueur.Humain, 60, TypeCouleur.Noir);
		tableauJoueurs[1] = new Humain(this, TypeJoueur.Humain, 60, TypeCouleur.Blanc);
		// tabJ[0] = new Humain(this, 1000);
		// tabJ[1] = new IAMoyen(this,1000);
		// System.out.println("this: " + this);
		this.renjou = new Renjou(tableauJoueurs);

	}

	
	@Override
	public Renjou getRenjou() {
		return this.renjou;
	}

	public void setRenjou(Renjou renjou) {
		this.renjou = renjou;
	}

	public void setNiveauTrace(int niveau) {
		trace.setNiveau(niveau);
	}

	public void printTrace(int niveau, String msg) {
		trace.print(niveau, msg);
	}

	@Override
	public void sauvegarder(String nomFichier) {
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
	public void charger(String nomFichier) {
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
	public void operationJouer(Coordonnees c, TypeJoueur j) {

		this.printTrace(1, "Je rentre dans operation jouer");

		if (j != renjou.getJoueurs()[renjou.getJoueurCourant()].getType()) {
			this.printTrace(1, "Les types ne correspondent pas");
			return;

		}

		if (renjou.getEtatPartie() != EtatPartie.EnCours) {
			this.printTrace(1, "La partie n'est plus en cours mais elle est : " + renjou.getEtatPartie());
			return;
		}

		if (caseJouable(renjou, c)) {
			this.printTrace(1, "Je rentre dans la fonction jouer avec le point " + c.getLigne() + "," + c.getColonne());
			jouer(renjou, c);
		} else if (caseTabou(renjou, c)) {
			// il n'y a que le joueur noir qui est impliqué par des tabous.
			// Si c'est une case tabou, forcément le joueur blanc gagne
			setPartieFinieJoueurBlanc(renjou);
		}

		// notify avec etat de la partie

	}

	public void jouer(Renjou renjou, Coordonnees c) {

		try {
			TypeCase typeCaseJoueurCourant = getTypeCaseJoueurCourant(renjou);
			renjou.getPlateauDeJeu().ajouter(c, typeCaseJoueurCourant);
			decrementerPionsJoueurCourant();

			int nbPionsRestant = renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPion();
			this.printTrace(1,
					"Le nombre de pions du joueur courant " + renjou.getJoueurCourant() + " est : " + nbPionsRestant);

		} catch (Exception e) {
			System.out.print(e);
		}

		if (partieFinie(renjou, c)) {
			setPartieFinie(renjou);
			this.printTrace(1, "JEU FINI !!!");
		} else if (partieNulle(renjou)) {
			setPartieNulle(renjou);
			this.printTrace(1, "PARTIE NULLE !!");
		} else {

			this.printTrace(1, "ON PASSE LA MAIN AU JOUEUR SUIVANT");

			joueurSuivant();
			majCasesInjouables(renjou);
			majCasesTabous(renjou);
			renjou.getListeAnnuler().add(renjou.getPlateauDeJeu());
		}
	}

	private boolean caseJouable(Renjou renjou, Coordonnees c) {
		return (renjou.getPlateauDeJeu().getPlateau()[c.getLigne()][c.getColonne()] == TypeCase.Jouable);
	}

	private boolean caseTabou(Renjou renjou, Coordonnees c) {
		return (renjou.getPlateauDeJeu().getPlateau()[c.getLigne()][c.getColonne()] == TypeCase.Tabou);
	}

	private void decrementerPionsJoueurCourant() {
		renjou.getJoueurs()[renjou.getJoueurCourant()]
				.setNbPion(renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPion() - 1);
	}

	public TypeCase getTypeCaseJoueurCourant(Renjou renjou) throws Exception {
		if (renjou.getJoueurCourant() == 0) {
			return TypeCase.PionNoir;
		} else if (renjou.getJoueurCourant() == 1) {
			return TypeCase.PionBlanc;
		} else {
			throw new Exception("Erreur. Il n'y a pas de correspondance de joueur et de couleur de pion");
		}
	}

	@Override
	public void nouvellePartie(TypeJoueur[] tabTypeJoueurs) {
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
	public void joueurSuivant() {
		int joueurSuivant = this.renjou.getJoueurCourant() + 1;
		this.renjou.setJoueurCourant(joueurSuivant);
		if (this.renjou.getJoueurCourant() >= this.renjou.getNbJoueurs()) {
			this.renjou.setJoueurCourant(0);
		}
	}

	@Override
	public boolean partieFinie(Renjou renjou, Coordonnees c) {

		// si 5 pions sont alignes
		Coordonnees cDecale = coordonneesDecalage(renjou, c, TypeDecalage.Gauche);
		if (cinqPointsAlignes(renjou, cDecale, TypeDecalage.Gauche)) {
			return true;
		}

		cDecale = coordonneesDecalage(renjou, c, TypeDecalage.Haut);
		if (cinqPointsAlignes(renjou, cDecale, TypeDecalage.Haut)) {
			return true;
		}

		cDecale = coordonneesDecalage(renjou, c, TypeDecalage.DiagonaleHautDroite);
		if (cinqPointsAlignes(renjou, cDecale, TypeDecalage.DiagonaleHautDroite)) {
			return true;
		}

		cDecale = coordonneesDecalage(renjou, c, TypeDecalage.DiagonaleHautGauche);
		if (cinqPointsAlignes(renjou, cDecale, TypeDecalage.DiagonaleHautGauche)) {
			return true;
		}
		return false;

	}

	public Coordonnees coordonneesDecalage(Renjou renjou, Coordonnees c, TypeDecalage typeDecalage) {

		int ligneCourante = c.getLigne();
		int colonneCourante = c.getColonne();
		TypeCase typeCaseJoueurCourant = null;

		int nbLignesPlateau = renjou.getPlateauDeJeu().getLignes();
		int nbColonnesPlateau = renjou.getPlateauDeJeu().getColonnes();

		try {
			typeCaseJoueurCourant = getTypeCaseJoueurCourant(renjou);

		} catch (Exception e) {
			System.out.println(e);
		}

		switch (typeDecalage) {

		case Gauche:
			// parcours colonne -1
			while (colonneCourante >= 0 && (typeCaseJoueurCourant == renjou.getPlateauDeJeu()
					.getTypeCaseTableauParLigneColonne(ligneCourante, colonneCourante))) {
				colonneCourante--;
			}
			colonneCourante++;
			break;
		case DiagonaleHautGauche:
			// parcours diagonale ligne -1 colonne -1
			while (ligneCourante >= 0 && colonneCourante >= 0 && (typeCaseJoueurCourant == renjou.getPlateauDeJeu()
					.getTypeCaseTableauParLigneColonne(ligneCourante, colonneCourante))) {
				ligneCourante--;
				colonneCourante--;
			}
			ligneCourante++;
			colonneCourante++;
			break;

		case DiagonaleHautDroite:
			// parcours diagonale ligne -1 colonne + 1
			while (ligneCourante >= 0 && colonneCourante < nbColonnesPlateau && (typeCaseJoueurCourant == renjou
					.getPlateauDeJeu().getTypeCaseTableauParLigneColonne(ligneCourante, colonneCourante))) {
				ligneCourante--;
				colonneCourante++;
			}
			ligneCourante++;
			colonneCourante--;
			break;

		case Haut:
			// parcours ligne -1
			while (ligneCourante >= 0 && (typeCaseJoueurCourant == renjou.getPlateauDeJeu()
					.getTypeCaseTableauParLigneColonne(ligneCourante, colonneCourante))) {
				ligneCourante--;
			}
			ligneCourante++;
			break;
		default:
			break;

		}
		return new Coordonnees(ligneCourante, colonneCourante);
	}

	public boolean cinqPointsAlignes(Renjou renjou, Coordonnees c, TypeDecalage typeDecalage) {

		int nbPionsAlignes = 0;
		int colonneCourante = c.getColonne();
		int ligneCourante = c.getLigne();
		TypeCase typeCaseJoueurCourant = null;

		int nbLignesPlateau = renjou.getPlateauDeJeu().getLignes();
		int nbColonnesPlateau = renjou.getPlateauDeJeu().getColonnes();

		try {
			typeCaseJoueurCourant = getTypeCaseJoueurCourant(renjou);

		} catch (Exception e) {
			System.out.println(e);
		}

		switch (typeDecalage) {
		case Gauche:
			// parcours colonne +1

			while (colonneCourante < nbColonnesPlateau && (typeCaseJoueurCourant == renjou.getPlateauDeJeu()
					.getTypeCaseTableauParLigneColonne(ligneCourante, colonneCourante))) {
				colonneCourante++;
				nbPionsAlignes++;
			}
			break;

		case DiagonaleHautGauche:
			// parcours ligne +1 colonne +1
			while (ligneCourante < nbLignesPlateau && colonneCourante < nbColonnesPlateau
					&& (typeCaseJoueurCourant == renjou.getPlateauDeJeu()
							.getTypeCaseTableauParLigneColonne(ligneCourante, colonneCourante))) {
				ligneCourante++;
				colonneCourante++;
				nbPionsAlignes++;
			}
			break;

		case DiagonaleHautDroite:
			// parcours diagonale ligne +1 colonne -1
			while (ligneCourante < nbLignesPlateau && colonneCourante >= 0 && (typeCaseJoueurCourant == renjou
					.getPlateauDeJeu().getTypeCaseTableauParLigneColonne(ligneCourante, colonneCourante))) {
				ligneCourante++;
				colonneCourante--;
				nbPionsAlignes++;
			}

			break;

		case Haut:
			// parcours ligne +1
			while (ligneCourante < nbLignesPlateau && (typeCaseJoueurCourant == renjou.getPlateauDeJeu()
					.getTypeCaseTableauParLigneColonne(ligneCourante, colonneCourante))) {
				ligneCourante++;
				nbPionsAlignes++;
			}
			break;

		default:
			break;
		}

		return (nbPionsAlignes >= 5);
	}

	public boolean partieNulle(Renjou renjou) {

		// si les 2 joueurs ont un nombre de pions vide
		int nbPionsEnCoursJoueurNoir = renjou.getJoueurs()[0].getNbPion();
		int nbPionsEnCoursJoueurBlanc = renjou.getJoueurs()[1].getNbPion();

		return (nbPionsEnCoursJoueurNoir == 0 && nbPionsEnCoursJoueurBlanc == 0);
	}

	public void setPartieFinie(Renjou renjou) {
		if (renjou.getJoueurCourant() == 0) {
			renjou.setEtatPartie(EtatPartie.NoirGagne);
		} else if (renjou.getJoueurCourant() == 1) {
			renjou.setEtatPartie(EtatPartie.BlancGagne);
		}
	}

	public void setPartieNulle(Renjou renjou) {
		renjou.setEtatPartie(EtatPartie.PartieNulle);
	}

	public void setPartieFinieJoueurBlanc(Renjou renjou) {
		renjou.setEtatPartie(EtatPartie.BlancGagne);
	}

	public void majCasesTabous(Renjou renjou) {

		// int nbPionsBase =
		// renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPionsBase();
		// int nbPionsEnCours =
		// renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPion();

		if (renjou.getJoueurCourant() == 0) {
			// mettre les cases tabous en fonction de la liste
		} else if (renjou.getJoueurCourant() == 1) {
			// faire sauter toutes les cases tabous. Le joueur blanc n'en a pas
			renjou.getPlateauDeJeu().supprimerCasesTabous();
		}

	}

	public void majCasesInjouables(Renjou renjou) {

		int nbPionsBase = renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPionsBase();
		int nbPionsEnCours = renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPion();

		if (renjou.getJoueurCourant() == 0 && nbPionsEnCours == nbPionsBase - 1) {
			renjou.getPlateauDeJeu().supprimerCasesInjouables();
		} else if (renjou.getJoueurCourant() == 1 && nbPionsBase == nbPionsEnCours) {
			// le tabou du joueur Blanc : les 8 cases adjacentes du premier coup
			Coordonnees c = new Coordonnees();
			int milieuLignes = renjou.getPlateauDeJeu().getLignes() / 2;
			int milieuColonnes = renjou.getPlateauDeJeu().getColonnes() / 2;
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i != 0 || j != 0) {
						c.setLigne(milieuLignes + i);
						c.setColonne(milieuColonnes + j);
						renjou.getPlateauDeJeu().ajouter(c, TypeCase.Jouable);
					}
				}
			}
		}
	}

}
