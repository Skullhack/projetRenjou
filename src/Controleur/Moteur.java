/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Enum.*;
import Joueur.*;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michauad
 */
public class Moteur implements InterfaceMoteur, java.io.Serializable {
	private Renjou renjou;
	private Log trace;
	public ArrayList<MoteurObserveur> observeurs;

	// Constructeur
	public Moteur(int nbJoueurs) {
		trace = new Log();

		this.observeurs = new ArrayList<>();
		Joueur[] tableauJoueurs = new Joueur[nbJoueurs];

		tableauJoueurs[0] = creerJoueur(TypeJoueur.Humain, TypeCouleur.Noir);
		tableauJoueurs[1] = creerJoueur(TypeJoueur.Humain, TypeCouleur.Blanc);

		this.renjou = new Renjou(tableauJoueurs);

	}

	public Moteur(TypeJoueur typeJoueur1, TypeJoueur typeJoueur2) {
		trace = new Log();
		trace.setNiveau(10);
		this.observeurs = new ArrayList<>();
		Joueur[] tableauJoueurs = new Joueur[2];
		this.renjou = new Renjou(tableauJoueurs);

		tableauJoueurs[0] = creerJoueur(typeJoueur1, TypeCouleur.Noir);
		tableauJoueurs[1] = creerJoueur(typeJoueur2, TypeCouleur.Blanc);

		notifierObserveurs();

	}

	public Joueur creerJoueur(TypeJoueur typeJoueur, TypeCouleur typeCouleur) {

		int nbPionsBase = 60;

		switch (typeJoueur) {
		case Humain:
			return (new Humain(this, TypeJoueur.Humain, nbPionsBase, typeCouleur));
		case IAFacile:
			return (new IAFacile(this, TypeJoueur.IAFacile, nbPionsBase, typeCouleur));
		case IAMoyenne:
			return (new IAMoyenne(this, TypeJoueur.IAMoyenne, nbPionsBase, typeCouleur));
		case IADifficile:
			return (new IADifficile(this, TypeJoueur.IADifficile, nbPionsBase, typeCouleur));
		default:
			return (new Humain(this, TypeJoueur.Humain, nbPionsBase, typeCouleur));
		}

	}

	private void notifierObserveurs() {
		printTrace(80, "Dans notifier: moteur = " + Integer.toHexString(System.identityHashCode(this)));
		printTrace(80, "Dans notifier: " + Integer.toHexString(System.identityHashCode(observeurs)));
		printTrace(80, "Dans notifier: " + observeurs.toString());
		for (MoteurObserveur m : observeurs) {
			printTrace(80, m.getClass().toString());
			m.actualiser();

		}
	}

	public void enregistrerObserveur(MoteurObserveur observer) {
		observeurs.add(observer);
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
		printTrace(80, "DANS SAUVEGARDER");
		ObjectOutputStream oos = null;

		try {
			final FileOutputStream fichier = new FileOutputStream(nomFichier);
			oos = new ObjectOutputStream(fichier);

			// on creee une liste d'observeur temporaire et on vide la liste
			// d'observeur courante.
			// On effectue cette opération car dans la liste d'observeur, on à
			// l'objet IHM.
			// Cependant on ne veut pas serialiser cet objet (erreur)
			ArrayList<MoteurObserveur> observeurTempo = new ArrayList<MoteurObserveur>();
			for (MoteurObserveur m : observeurs) {
				observeurTempo.add(m);
			}
			observeurs.clear();

			oos.writeObject(renjou);
			oos.flush();

			// on reecrit les informations dans la liste des observeurs pour
			// continuer la partie
			for (MoteurObserveur m : observeurTempo) {
				observeurs.add(m);
			}

			printTrace(80, "Dans sauvegarder: observeurTempo = "
					+ Integer.toHexString(System.identityHashCode(observeurTempo)));
			printTrace(80, "Dans sauvegarder: observeur = " + Integer.toHexString(System.identityHashCode(observeurs)));

		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	@Override
	public void charger(String fichierCharger) {
		printTrace(80, "DANS CHARGER");
		ObjectInputStream ois = null;
		try {
			final FileInputStream fichierIn = new FileInputStream(fichierCharger);
			ois = new ObjectInputStream(fichierIn);

			// pour chaque "ancien" joueur, on supprime leur références dans la
			// liste d'observeurs
			observeurs.remove(renjou.getJoueurs()[0]);
			observeurs.remove(renjou.getJoueurs()[1]);

			renjou = (Renjou) ois.readObject();

			// on rajoute les observeurs par rapport aux joueurs du modele
			this.enregistrerObserveur(renjou.getJoueurs()[0]);
			this.enregistrerObserveur(renjou.getJoueurs()[1]);

			// dans l'IA, il y a le moteur en paramètre. Pour que l'ia
			// communique avec le nouveau moteur,
			// il faut setter les joueurs avec le nouveau moteur et supprimer
			// l'ancien
			renjou.getJoueurs()[0].setMoteur(this);
			renjou.getJoueurs()[1].setMoteur(this);

		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}

		notifierObserveurs();
	}

	@Override
	public void operationJouer(Coordonnees c, TypeJoueur j) {

		this.printTrace(1, "Je rentre dans operation jouer");

		this.printTrace(80, "JOUEUR NOIR : " + renjou.getJoueurs()[0].toString() + "JOUEUR BLANC : "
				+ renjou.getJoueurs()[1].toString());

		if (renjou.getEtatPartie() != EtatPartie.EnCours) {
			this.printTrace(1, "La partie n'est plus en cours mais elle est : " + renjou.getEtatPartie());
			return;
		}

		if (j != renjou.getJoueurs()[renjou.getJoueurCourant()].getType()) {
			this.printTrace(1, "Les types ne correspondent pas");
			return;

		}

		if (caseJouable(renjou, c)) {
			this.printTrace(1, "Je rentre dans la fonction jouer avec le point " + c.getLigne() + "," + c.getColonne());
			jouer(renjou, c);
		} else if (caseTabou(renjou, c)) {
			// il n'y a que le joueur noir qui est impliqué par des tabous.
			// Si c'est une case tabou, forcément le joueur blanc gagne
			setPartieFinieJoueurBlanc(renjou);
			this.printTrace(1, "PARTIE GAGNE PAR BLANC AVEC TABOU !!");
		}

		// notify avec etat de la partie
		notifierObserveurs();

	}

	public void jouer(Renjou renjou, Coordonnees c) {

		try {
			TypeCase typeCaseJoueurCourant = getTypeCaseJoueurCourant(renjou);
			renjou.getPlateauDeJeu().ajouter(c, typeCaseJoueurCourant);

			// ajout dans la liste
			ajouterPionJoueDansListeAnnuler(renjou, c, typeCaseJoueurCourant);
			renjou.getListeRefaire().clear();

			decrementerPionsJoueurCourant(renjou);

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
			// ajouterPlateauJeuDansListeAnnuler(renjou);
		}
	}

	private boolean caseJouable(Renjou renjou, Coordonnees c) {
		return (renjou.getPlateauDeJeu().getPlateau()[c.getLigne()][c.getColonne()] == TypeCase.Jouable);
	}

	private boolean caseTabou(Renjou renjou, Coordonnees c) {
		return (renjou.getPlateauDeJeu().getPlateau()[c.getLigne()][c.getColonne()] == TypeCase.Tabou);
	}

	public void decrementerPionsJoueurCourant(Renjou renjou) {
		renjou.getJoueurs()[renjou.getJoueurCourant()]
				.setNbPion(renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPion() - 1);
	}

	public void incrementerPionsJoueurCourant(Renjou renjou) {
		renjou.getJoueurs()[renjou.getJoueurCourant()]
				.setNbPion(renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPion() + 1);
	}

	public void ajouterPionJoueDansListeAnnuler(Renjou renjou, Coordonnees c, TypeCase typeCaseJoueurCourant) {
		PionJoue pionJoue = new PionJoue(c, typeCaseJoueurCourant);
		renjou.getListeAnnuler().add(pionJoue);
	}

	public TypeCase getTypeCaseJoueurCourant(Renjou renjou) throws Exception {
		if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Noir) {
			return TypeCase.PionNoir;
		} else if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Blanc) {
			return TypeCase.PionBlanc;
		} else {
			throw new Exception("Erreur. Il n'y a pas de correspondance de joueur et de couleur de pion");
		}
	}

	@Override
	public void configurerPartie(TypeJoueur typeJoueur1, TypeJoueur typeJoueur2, ArrayList<Tabou> tabouPartie,
			boolean nouvellePartie) {
		if (nouvellePartie) {
			renjou.initRenjou();
			renjou.getJoueurs()[0].setNbPion(renjou.getJoueurs()[0].getNbPionsBase());
			renjou.getJoueurs()[1].setNbPion(renjou.getJoueurs()[1].getNbPionsBase());
		}

		renjou.getJoueurs()[0].setType(typeJoueur1);
		renjou.getJoueurs()[1].setType(typeJoueur2);
		renjou.setTabouJeu(tabouPartie);
		
		notifierObserveurs();
	}

	@Override
	public void recommencerPartie() {

		renjou.initRenjou();
		renjou.getJoueurs()[0].setNbPion(renjou.getJoueurs()[0].getNbPionsBase());
		renjou.getJoueurs()[1].setNbPion(renjou.getJoueurs()[1].getNbPionsBase());

		notifierObserveurs();
	}

	@Override
	public void joueurSuivant() {
		int joueurSuivant = this.renjou.getJoueurCourant() + 1;
		if (joueurSuivant >= this.renjou.getNbJoueurs()) {
			joueurSuivant = 0;
		}
		this.renjou.setJoueurCourant(joueurSuivant);
	}

	public void joueurPrecedent() {
		int joueurPrecedent = this.renjou.getJoueurCourant() - 1;
		if (joueurPrecedent < 0) {
			joueurPrecedent = 1;
		}
		this.renjou.setJoueurCourant(joueurPrecedent);
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
		// pions du joueur noir
		int nbPionsEnCoursJoueurNoir = renjou.getJoueurs()[0].getNbPion();

		// pions du joueur blanc
		int nbPionsEnCoursJoueurBlanc = renjou.getJoueurs()[1].getNbPion();

		return (nbPionsEnCoursJoueurNoir == 0 && nbPionsEnCoursJoueurBlanc == 0);
	}

	public void setPartieFinie(Renjou renjou) {
		if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Noir) {
			renjou.setEtatPartie(EtatPartie.NoirGagne);
		} else if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Blanc) {
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

		if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Noir) {
			// mettre les cases tabous en fonction de la liste
		} else if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Blanc) {
			// faire sauter toutes les cases tabous. Le joueur blanc n'en a pas
			renjou.getPlateauDeJeu().supprimerCasesTabous();
		}

	}

	public void majCasesInjouables(Renjou renjou) {

		int nbPionsBase = renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPionsBase();
		int nbPionsEnCours = renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPion();

		if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Noir
				&& nbPionsEnCours == nbPionsBase - 1) {
			renjou.getPlateauDeJeu().supprimerCasesInjouables();
		} else if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Blanc
				&& nbPionsBase == nbPionsEnCours) {
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

	@Override
	public void annuler() {

		if (this.getRenjou().getJoueurs()[0].getType() == TypeJoueur.Humain
				&& this.getRenjou().getJoueurs()[1].getType() == TypeJoueur.Humain) {
			annulerDemiCoup();
		} else {
			annulerDemiCoup();
			annulerDemiCoup();
		}
		notifierObserveurs();
	}

	@Override
	public void annulerDemiCoup() {
		int dernierElementHistorique = this.getRenjou().getListeAnnuler().size() - 1;
		if (dernierElementHistorique >= 0) {

			joueurPrecedent();

			PionJoue dernierPionJoueHistorique = this.getRenjou().getListeAnnuler().get(dernierElementHistorique);
			this.getRenjou().getPlateauDeJeu().enlever(dernierPionJoueHistorique.c);
			this.getRenjou().getListeRefaire().add(this.getRenjou().getListeAnnuler().get(dernierElementHistorique));
			this.getRenjou().getListeAnnuler().remove(dernierElementHistorique);
			incrementerPionsJoueurCourant(this.getRenjou());
			majCasesTabous(this.getRenjou());

		}
	}

	@Override
	public void refaire() {
		if (this.getRenjou().getJoueurs()[0].getType() == TypeJoueur.Humain
				&& renjou.getJoueurs()[1].getType() == TypeJoueur.Humain) {
			refaireDemiCoup();
		} else {
			refaireDemiCoup();
			refaireDemiCoup();
		}
		notifierObserveurs();
	}

	public void refaireDemiCoup() {
		int dernierElementHistorique = this.getRenjou().getListeRefaire().size() - 1;

		if (dernierElementHistorique >= 0) {
			PionJoue dernierPionJoueHistorique = this.getRenjou().getListeRefaire().get(dernierElementHistorique);
			this.getRenjou().getPlateauDeJeu().ajouter(dernierPionJoueHistorique.c, dernierPionJoueHistorique.typeCase);
			this.getRenjou().getListeAnnuler().add(this.getRenjou().getListeRefaire().get(dernierElementHistorique));
			this.getRenjou().getListeRefaire().remove(dernierElementHistorique);
			decrementerPionsJoueurCourant(this.getRenjou());
			joueurSuivant();
			majCasesTabous(this.getRenjou());
		}

	}

}
