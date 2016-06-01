/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Enum.*;
import Joueur.*;
import Utilitaire.*;
import javafx.application.Platform;

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
	public Moteur(TypeJoueur typeJoueur1, TypeJoueur typeJoueur2) {
		Log.setNiveau(10);
		this.observeurs = new ArrayList<>();
		Joueur[] tableauJoueurs = new Joueur[2];
		this.renjou = new Renjou(tableauJoueurs);

		tableauJoueurs[0] = creerJoueur(typeJoueur1, TypeCouleur.Noir);
		tableauJoueurs[1] = creerJoueur(typeJoueur2, TypeCouleur.Blanc);
	}
	
	public void commencer(){
		notifierObserveurs();
		faireJouerIA();
	}

	public Joueur creerJoueur(TypeJoueur typeJoueur, TypeCouleur typeCouleur) {

		int nbPionsBase = 60;

		switch (typeJoueur) {
		case Humain:
			return (new Humain(TypeJoueur.Humain, nbPionsBase, typeCouleur));
		case IAFacile:
			return (new IAFacile(TypeJoueur.IAFacile, nbPionsBase, typeCouleur));
		case IAMoyenne:
			return (new IAMoyenne(TypeJoueur.IAMoyenne, nbPionsBase, typeCouleur));
		case IADifficile:
			return (new IADifficile(TypeJoueur.IADifficile, nbPionsBase, typeCouleur));
		case IAExterne:
			return (new IADifficile(TypeJoueur.IAExterne, nbPionsBase, typeCouleur));
		default:
			return (new Humain(TypeJoueur.Humain, nbPionsBase, typeCouleur));
		}

	}

	private void notifierObserveurs() {
		Log.print(80, "Dans notifier: moteur = " + Integer.toHexString(System.identityHashCode(this)));
		Log.print(80, "Dans notifier: " + Integer.toHexString(System.identityHashCode(observeurs)));
		Log.print(80, "Dans notifier: " + observeurs.toString());
		for (MoteurObserveur m : observeurs) {
			Log.print(80, m.getClass().toString());
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

	@Override
	public void sauvegarder(String nomFichier) {
		Log.print(80, "DANS SAUVEGARDER");
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

			Log.print(80, "Dans sauvegarder: observeurTempo = "
					+ Integer.toHexString(System.identityHashCode(observeurTempo)));
			Log.print(80, "Dans sauvegarder: observeur = " + Integer.toHexString(System.identityHashCode(observeurs)));

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
		Log.print(80, "DANS CHARGER");
		ObjectInputStream ois = null;
		try {
			final FileInputStream fichierIn = new FileInputStream(fichierCharger);
			ois = new ObjectInputStream(fichierIn);

			// pour chaque "ancien" joueur, on supprime leur références dans la
			// liste d'observeurs
			observeurs.remove(renjou.getJoueurs()[0]);
			observeurs.remove(renjou.getJoueurs()[1]);

			renjou = (Renjou) ois.readObject();

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
		faireJouerIA();
	}

	private void faireJouerIA() {
		if (renjou.getJoueurs()[renjou.getJoueurCourant()].getType() != TypeJoueur.Humain && renjou.getEtatPartie() == EtatPartie.EnCours) {

			Thread threadIa = new Thread() {
				public void run() {
					TypeJoueur joueurCourant = renjou.getJoueurs()[renjou.getJoueurCourant()].getType();
					Coordonnees c = renjou.getJoueurs()[renjou.getJoueurCourant()].jouer(renjou.getPlateauDeJeu());
					Platform.runLater(
							() -> operationJouer(c, joueurCourant));
				}
			};

			threadIa.start();

		}
	}
	
	public EtatPartie faireJouerIAVsIAPourTest(){
		while(renjou.getEtatPartie() == EtatPartie.EnCours){
			jouer(renjou, renjou.getJoueurs()[renjou.getJoueurCourant()].jouer(renjou.getPlateauDeJeu()));
		}
		return renjou.getEtatPartie();
	}

	@Override
	public void operationJouer(Coordonnees c, TypeJoueur j) {

		Log.print(1, "Je rentre dans operation jouer");

		Log.print(80, "JOUEUR NOIR : " + renjou.getJoueurs()[0].toString() + "JOUEUR BLANC : "
				+ renjou.getJoueurs()[1].toString());

		if (renjou.getEtatPartie() != EtatPartie.EnCours) {
			Log.print(1, "La partie n'est plus en cours mais elle est : " + renjou.getEtatPartie());
			return;
		}

		if (j != renjou.getJoueurs()[renjou.getJoueurCourant()].getType()) {
			Log.print(1, "Les types ne correspondent pas");
			return;

		}

		if (caseJouable(renjou, c) || caseTabou(renjou, c)) {
			Log.print(1, "Je rentre dans la fonction jouer avec le point " + c.getLigne() + "," + c.getColonne());
			jouer(renjou, c);
		}
		// notify avec etat de la partie
		notifierObserveurs();

		// si c'est une IA, on la fait jouer
		faireJouerIA();

	}

	public void jouer(Renjou renjou, Coordonnees c) {

		TypeCase typeCaseJoueurCourant = null;

		try {
			typeCaseJoueurCourant = getTypeCaseJoueurCourant(renjou);
			renjou.getPlateauDeJeu().ajouter(c, typeCaseJoueurCourant);
			renjou.getListeRefaire().clear();
			decrementerPionsJoueurCourant(renjou);
			incrementerDemiTourCourant(renjou);

			int nbPionsRestant = renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPion();
			Log.print(1,
					"Le nombre de pions du joueur courant " + renjou.getJoueurCourant() + " est : " + nbPionsRestant);

		} catch (Exception e) {
			System.out.print(e);
		}

		if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Noir
				&& !renjou.getTabouJeu().estValide(renjou.getPlateauDeJeu(), c)) {
			setPartieFinieJoueurBlancParTabou(renjou);
			Log.print(1, "PARTIE GAGNE PAR BLANC AVEC TABOU !!");
		} else if (partieFinie(renjou, c)) {
			setPartieFinie(renjou);
			Log.print(1, "JEU FINI !!!");
			for (PionJoue p : renjou.getListeAnnuler()) {
				Log.print(1, p.toString());
			}
		} else if (partieNulle(renjou)) {
			setPartieNulle(renjou);
			Log.print(1, "PARTIE NULLE !!");
		} else {
			Log.print(1, "ON PASSE LA MAIN AU JOUEUR SUIVANT");
		}
		joueurSuivant();
		ajouterPionJoueDansListeAnnuler(renjou, c, typeCaseJoueurCourant, renjou.getEtatPartie());
		majCasesInjouables(renjou);
		majCasesTabous(renjou);
		majAffichageListeTours(renjou);

		// Log.print(1, renjou.getPlateauDeJeu().toString());

	}

	public void majAffichageListeTours(Renjou renjou) {
		if (renjou.getNbDemiTourCourant()+1 > 4) {
			renjou.setIndiceFinHistorique(renjou.getNbDemiTourCourant()+1);
			renjou.setIndiceDebutHistorique(renjou.getIndiceFinHistorique() - 4);
		} else {
			renjou.setIndiceFinHistorique(renjou.getIndiceFinHistorique() + 1);
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

	public void decrementerDemiTourCourant(Renjou renjou) {
		renjou.setNbDemiTourCourant(renjou.getNbDemiTourCourant() - 1);
	}

	public void incrementerDemiTourCourant(Renjou renjou) {
		renjou.setNbDemiTourCourant(renjou.getNbDemiTourCourant() + 1);
	}

	public void ajouterPionJoueDansListeAnnuler(Renjou renjou, Coordonnees c, TypeCase typeCaseJoueurCourant,
			EtatPartie etatPartie) {

		//if (!estDeuxJoueursIA(renjou)) {
			PionJoue pionJoue = new PionJoue(c, typeCaseJoueurCourant, etatPartie);
			renjou.getListeAnnuler().add(pionJoue);
		//}
	}

	public boolean estDeuxJoueursIA(Renjou renjou) {
		return (renjou.getJoueurs()[0].getType() != TypeJoueur.Humain
				&& renjou.getJoueurs()[1].getType() != TypeJoueur.Humain);
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
	public void configurerPartie(TypeJoueur typeJoueur1, TypeJoueur typeJoueur2, ArrayList<TypeTabous> tabouPartie,
			boolean nouvellePartie, boolean modeDebutant) {
		if (nouvellePartie) {
			renjou.initRenjou();
			renjou.getJoueurs()[0].setNbPion(renjou.getJoueurs()[0].getNbPionsBase());
			renjou.getJoueurs()[1].setNbPion(renjou.getJoueurs()[1].getNbPionsBase());
		}

		observeurs.remove(renjou.getJoueurs()[0]);
		observeurs.remove(renjou.getJoueurs()[1]);

		Joueur[] tableauJoueurs = new Joueur[2];
		tableauJoueurs[0] = creerJoueur(typeJoueur1, TypeCouleur.Noir);
		tableauJoueurs[1] = creerJoueur(typeJoueur2, TypeCouleur.Blanc);

		tableauJoueurs[0].setNbPion(renjou.getJoueurs()[0].getNbPion());
		tableauJoueurs[1].setNbPion(renjou.getJoueurs()[1].getNbPion());

		renjou.setJoueurs(tableauJoueurs);

		// tabouPartie.add(TypeTabous.TROIS_TROIS);
		// renjou.setModeDebutant(true);

		renjou.getTabouJeu().setListeTabous(tabouPartie);

		renjou.setModeDebutant(modeDebutant);
		majCasesTabous(renjou);

		Log.print(1, "LE PLATEAU DE JEU APRES CONFIGURER PARTIE : " + renjou.getPlateauDeJeu().toString());

		notifierObserveurs();
		faireJouerIA();
	}

	@Override
	public void recommencerPartie() {

		renjou.initRenjou();
		renjou.getJoueurs()[0].setNbPion(renjou.getJoueurs()[0].getNbPionsBase());
		renjou.getJoueurs()[1].setNbPion(renjou.getJoueurs()[1].getNbPionsBase());

		notifierObserveurs();
		faireJouerIA();
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

	public void setPartieFinieJoueurBlancParTabou(Renjou renjou) {
		renjou.setEtatPartie(EtatPartie.BlancGagneParTabou);
	}

	public void majCasesTabous(Renjou renjou) {

		if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Noir) {
			// mettre les cases tabous pour le joueur noir en mode débutant
			if (renjou.estModeDebutant()) {
				ajoutCoupsTabouModeDebutant(renjou);
			}

		} else if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Blanc) {
			// faire sauter toutes les cases tabous. Le joueur blanc n'en a pas
			renjou.getPlateauDeJeu().supprimerCasesTabous();
		}

	}

	private void ajoutCoupsTabouModeDebutant(Renjou renjou) {
		for (int i = 0; i < renjou.getPlateauDeJeu().getLignes(); i++) {
			for (int j = 0; j < renjou.getPlateauDeJeu().getColonnes(); j++) {
				Coordonnees coorParcours = new Coordonnees(i, j);
				if (caseJouable(renjou, coorParcours)) {
					if (!renjou.getTabouJeu().estValide(renjou.getPlateauDeJeu(), coorParcours)) {
						renjou.getPlateauDeJeu().ajouter(coorParcours, TypeCase.Tabou);
					}
				}
				if (caseTabou(renjou, coorParcours)) {
					if (renjou.getTabouJeu().estValide(renjou.getPlateauDeJeu(), coorParcours)) {
						renjou.getPlateauDeJeu().ajouter(coorParcours, TypeCase.Jouable);
					}
				}
			}
		}
	}

	public void majCasesInjouables(Renjou renjou) {

		int nbPionsBase = renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPionsBase();
		int nbPionsEnCours = renjou.getJoueurs()[renjou.getJoueurCourant()].getNbPion();

		// si les deux joueurs ont le nombre de pions de base. On remet le
		// plateau de base
		if (renjou.getJoueurs()[0].getNbPion() == renjou.getJoueurs()[0].getNbPionsBase()
				&& renjou.getJoueurs()[1].getNbPion() == renjou.getJoueurs()[1].getNbPionsBase()) {
			renjou.getPlateauDeJeu().initPlateau();
		}
		// si c'est au joueur noir de jouer et qu'il a déjà joué un coup, on
		// supprime les cases injouables
		else if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Noir
				&& nbPionsEnCours == nbPionsBase - 1) {
			renjou.getPlateauDeJeu().supprimerCasesInjouables();
		}
		// si c'est le premier coup de joueur blanc, on laisse le premier coup
		// de noir et on met toutes les cases injouables sauf les cases
		// adjacentes au premier coup
		else if (renjou.getJoueurs()[renjou.getJoueurCourant()].getCouleur() == TypeCouleur.Blanc
				&& nbPionsBase == nbPionsEnCours) {
			renjou.getPlateauDeJeu().initPlateauDeuxiemeCoup();
		}
	}

	@Override
	public void annuler() {

		if (this.getRenjou().getJoueurs()[0].getType() == TypeJoueur.Humain
				&& this.getRenjou().getJoueurs()[1].getType() == TypeJoueur.Humain) {
			annulerDemiCoup();
//			if (this.getRenjou().getEtatPartie() != EtatPartie.EnCours) {
//				annulerDemiCoup();
//			}
		} else {
			annulerDemiCoup();
			annulerDemiCoup();
		}

		notifierObserveurs();
		faireJouerIA();
	}

	@Override
	public void annulerDemiCoup() {
		int dernierElementHistorique = this.getRenjou().getListeAnnuler().size() - 1;
		if (dernierElementHistorique >= 0) {

			joueurPrecedent();

			PionJoue dernierPionJoueHistorique = this.getRenjou().getListeAnnuler().get(dernierElementHistorique);
			this.getRenjou().getPlateauDeJeu().enlever(dernierPionJoueHistorique.c);			
			this.getRenjou().setEtatPartie(EtatPartie.EnCours);
			this.getRenjou().getListeRefaire().add(this.getRenjou().getListeAnnuler().get(dernierElementHistorique));
			this.getRenjou().getListeAnnuler().remove(dernierElementHistorique);
			incrementerPionsJoueurCourant(this.getRenjou());
			majCasesInjouables(this.getRenjou());
			majCasesTabous(this.getRenjou());
			decrementerDemiTourCourant(this.getRenjou());
		}
	}

	public void annulerNDemiCoup(int n) {
		for (int i = 0; i < n; i++) {
			annulerDemiCoup();
		}
		notifierObserveurs();
		faireJouerIA();
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
		faireJouerIA();
	}

	public void refaireDemiCoup() {
		int dernierElementHistorique = this.getRenjou().getListeRefaire().size() - 1;

		if (dernierElementHistorique >= 0) {
			PionJoue dernierPionJoueHistorique = this.getRenjou().getListeRefaire().get(dernierElementHistorique);
			this.getRenjou().getPlateauDeJeu().ajouter(dernierPionJoueHistorique.c, dernierPionJoueHistorique.typeCase);
			this.getRenjou().setEtatPartie(dernierPionJoueHistorique.etatPartie);
			this.getRenjou().getListeAnnuler().add(this.getRenjou().getListeRefaire().get(dernierElementHistorique));
			this.getRenjou().getListeRefaire().remove(dernierElementHistorique);
			decrementerPionsJoueurCourant(this.getRenjou());
			joueurSuivant();
			majCasesInjouables(this.getRenjou());
			majCasesTabous(this.getRenjou());
			incrementerDemiTourCourant(this.getRenjou());
		}

	}

	public void refaireNDemiCoup(int n) {
		for (int i = 0; i < n; i++) {
			refaireDemiCoup();
		}
		notifierObserveurs();
		faireJouerIA();
	}

	public boolean premierCoup() {
		return ((renjou.getJoueurs()[0].getNbPion() == renjou.getJoueurs()[0].getNbPionsBase())
				&& (renjou.getJoueurs()[1].getNbPion() == renjou.getJoueurs()[0].getNbPionsBase()));
	}

	public boolean deuxiemeCoup() {
		return ((renjou.getJoueurs()[0].getNbPion() == renjou.getJoueurs()[0].getNbPionsBase() - 1)
				&& (renjou.getJoueurs()[1].getNbPion() == renjou.getJoueurs()[0].getNbPionsBase()));
	}

	public Coordonnees aide() {
		Joueur j = new IAFacile(TypeJoueur.IAFacile, 60, TypeCouleur.Blanc);
		return j.jouer(renjou.getPlateauDeJeu());
	}
}
