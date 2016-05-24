package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Controleur.Moteur;
import Controleur.MoteurObserveur;
import Enum.TypeCase;
import Enum.TypeJoueur;
import Utilitaire.Coordonnees;
import Utilitaire.Log;
import Utilitaire.PlateauDeJeu;
import Utilitaire.Tabou;
import Utilitaire.Tabous;

public class TestMoteur {

	private static String chemin = "Tests/TestsMoteur/";

	public void afficherPlateauJeuCourant(PlateauDeJeu plateauJeuEnCours) {
		Log.print(1, plateauJeuEnCours.toString());
	}

	public void operationJouerJoueurCourant(Moteur donneesJeu, Coordonnees c) {
		donneesJeu.operationJouer(c,
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
	}

	@Test
	public void testPremiersCoupsEntreDeuxJoueurs() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		Log.setNiveau(0);
		Log.print(1, "TEST : d'activation/désactivation des cases injouables");

		afficherPlateauJeuCourant(plateauJeuEnCours);

		Coordonnees c = new Coordonnees(7, 7);
		operationJouerJoueurCourant(donneesJeu, c);
		afficherPlateauJeuCourant(plateauJeuEnCours);

		c = new Coordonnees(8, 8);
		operationJouerJoueurCourant(donneesJeu, c);
		afficherPlateauJeuCourant(plateauJeuEnCours);

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauApresPremierCoupDeuxJoueurs");

		Log.print(1, "FIN TEST : d'activation/désactivation des cases injouables");

	}

	@Test
	public void testPartieNulle() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		Log.setNiveau(0);

		Log.print(1, "TEST d'une partie nulle");
		afficherPlateauJeuCourant(plateauJeuEnCours);

		Coordonnees c = new Coordonnees(7, 7);
		operationJouerJoueurCourant(donneesJeu, c);
		afficherPlateauJeuCourant(plateauJeuEnCours);

		c = new Coordonnees(8, 8);
		operationJouerJoueurCourant(donneesJeu, c);
		afficherPlateauJeuCourant(plateauJeuEnCours);

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {

				if (i == 4) {
					i++;
				}
				c = new Coordonnees(i, j);
				operationJouerJoueurCourant(donneesJeu, c);
				afficherPlateauJeuCourant(plateauJeuEnCours);
			}

		}

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauJeuApresPartieNulle");

		Log.print(1, "FIN TEST d'une partie nulle");

	}

	@Test
	public void testPartieFinieJoueurNoirHautEnBas() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		Log.setNiveau(0);

		Log.print(1, "TEST d'une partie finie par le joueur noir (haut en bas)");
		afficherPlateauJeuCourant(plateauJeuEnCours);

		Coordonnees[] tabCoord = new Coordonnees[12];
		tabCoord[0] = new Coordonnees(7, 7); // noir
		tabCoord[1] = new Coordonnees(14, 14); // blanc qui joue sur case pas
												// valide
		tabCoord[2] = new Coordonnees(8, 8); // blanc qui joue sur une case
												// valide
		tabCoord[3] = new Coordonnees(6, 7); // noir
		tabCoord[4] = new Coordonnees(10, 10); // blanc
		tabCoord[5] = new Coordonnees(5, 7); // noir
		tabCoord[6] = new Coordonnees(9, 9); // blanc
		tabCoord[7] = new Coordonnees(4, 7); // noir
		tabCoord[8] = new Coordonnees(1, 2); // blanc
		tabCoord[9] = new Coordonnees(3, 7); // noir
		tabCoord[10] = new Coordonnees(1, 4); // blanc
		tabCoord[11] = new Coordonnees(12, 12); // blanc

		for (int i = 0; i < tabCoord.length; i++) {
			operationJouerJoueurCourant(donneesJeu, tabCoord[i]);
			afficherPlateauJeuCourant(plateauJeuEnCours);
		}

		TestPlateauDeJeu.comparer(plateauJeuEnCours,
				chemin + "PlateauApresVictoireJoueurNoirEtCoordonneeInvalideBlanc");
		Log.print(1, "FIN TEST d'une partie finie par le joueur noir (haut en bas)");

	}

	@Test
	public void testPartieFinieJoueurBlancDiagonaleHautGaucheBasDroite() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		Log.setNiveau(0);

		Log.print(1, "TEST d'une partie finie par le joueur blanc (DiagonaleHautGaucheBasDroite)");
		afficherPlateauJeuCourant(plateauJeuEnCours);

		Coordonnees[] tabCoord = new Coordonnees[13];
		tabCoord[0] = new Coordonnees(7, 7); // noir
		tabCoord[1] = new Coordonnees(8, 8); // blanc qui joue
		tabCoord[2] = new Coordonnees(10, 10); // noir
		tabCoord[3] = new Coordonnees(2, 2); // blanc
		tabCoord[4] = new Coordonnees(9, 9); // noir
		tabCoord[5] = new Coordonnees(3, 3); // blanc
		tabCoord[6] = new Coordonnees(1, 8); // noir
		tabCoord[7] = new Coordonnees(4, 4); // blanc
		tabCoord[8] = new Coordonnees(3, 7); // noir
		tabCoord[9] = new Coordonnees(5, 5); // blanc
		tabCoord[10] = new Coordonnees(12, 12); // noir
		tabCoord[11] = new Coordonnees(6, 6); // blanc
		tabCoord[12] = new Coordonnees(3, 8); // noir

		for (int i = 0; i < tabCoord.length; i++) {
			operationJouerJoueurCourant(donneesJeu, tabCoord[i]);
			afficherPlateauJeuCourant(plateauJeuEnCours);
		}

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauVictoireJoueurBlancDiagonale");

		Log.print(1, "FIN TEST d'une partie finie par le joueur blanc (DiagonaleHautGaucheBasDroite)");
	}

	@Test
	public void testPartieFinieJoueurBlancGaucheADroite() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		Log.setNiveau(0);
		Log.print(1, "TEST d'une partie finie par le joueur blanc (GaucheADroite)");
		afficherPlateauJeuCourant(plateauJeuEnCours);

		Coordonnees[] tabCoord = new Coordonnees[13];
		tabCoord[0] = new Coordonnees(7, 7); // noir
		tabCoord[1] = new Coordonnees(6, 6); // blanc qui joue
		tabCoord[2] = new Coordonnees(10, 10); // noir
		tabCoord[3] = new Coordonnees(6, 5); // blanc
		tabCoord[4] = new Coordonnees(9, 9); // noir
		tabCoord[5] = new Coordonnees(6, 4); // blanc
		tabCoord[6] = new Coordonnees(1, 8); // noir
		tabCoord[7] = new Coordonnees(6, 3); // blanc
		tabCoord[8] = new Coordonnees(3, 7); // noir
		tabCoord[9] = new Coordonnees(6, 2); // blanc
		tabCoord[10] = new Coordonnees(12, 12); // noir
		tabCoord[11] = new Coordonnees(6, 6); // blanc
		tabCoord[12] = new Coordonnees(3, 8); // noir

		for (int i = 0; i < tabCoord.length; i++) {
			operationJouerJoueurCourant(donneesJeu, tabCoord[i]);
			afficherPlateauJeuCourant(plateauJeuEnCours);
		}

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauVictoireJoueurBlancGaucheADroite");
		Log.print(1, "FIN TEST d'une partie finie par le joueur blanc (GaucheADroite)");

	}

	@Test
	public void testPartieFinieJoueurNoirDiagonaleHautDroiteBasGauche() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		Log.setNiveau(0);
		Log.print(1, "TEST d'une partie finie par le joueur noir (DiagonaleHautDroiteBasGauche)");
		afficherPlateauJeuCourant(plateauJeuEnCours);

		Coordonnees[] tabCoord = new Coordonnees[13];
		tabCoord[0] = new Coordonnees(7, 7); // noir
		tabCoord[1] = new Coordonnees(6, 6); // blanc qui joue
		tabCoord[2] = new Coordonnees(8, 6); // noir
		tabCoord[3] = new Coordonnees(6, 5); // blanc
		tabCoord[4] = new Coordonnees(9, 5); // noir
		tabCoord[5] = new Coordonnees(6, 4); // blanc
		tabCoord[6] = new Coordonnees(10, 4); // noir
		tabCoord[7] = new Coordonnees(6, 3); // blanc
		tabCoord[8] = new Coordonnees(11, 3); // noir
		tabCoord[9] = new Coordonnees(6, 2); // blanc
		tabCoord[10] = new Coordonnees(12, 12); // noir

		for (int i = 0; i < tabCoord.length; i++) {
			operationJouerJoueurCourant(donneesJeu, tabCoord[i]);
			afficherPlateauJeuCourant(plateauJeuEnCours);
		}

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauVictoireJoueurNoirDiagonaleHautDroiteBasGauche");
		Log.print(1, "FIN TEST d'une partie finie par le joueur noir (DiagonaleHautDroiteBasGauche)");

	}

	@Test
	public void majCaseTabouAvecPartiePerduPourNoir() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		Log.setNiveau(0);
		Log.print(1, "TEST d'une case tabou pour faire perdre joueur noir");
		afficherPlateauJeuCourant(plateauJeuEnCours);

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir
		tabCoord[1] = new Coordonnees(6, 6); // blanc qui joue
		tabCoord[2] = new Coordonnees(10, 10); // noir va jouer sur une case
												// tabou
		tabCoord[3] = new Coordonnees(13, 13);

		for (int i = 0; i < tabCoord.length; i++) {

			if (i == 2) {
				// on va mettre la case tabou pour pieger le joueur noir
				plateauJeuEnCours.ajouter(new Coordonnees(10, 10), TypeCase.Tabou);
				TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauCaseTabouForcee");
			}
			operationJouerJoueurCourant(donneesJeu, tabCoord[i]);
			afficherPlateauJeuCourant(plateauJeuEnCours);

		}

		//TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauCaseApresClicTabouJoueurNoir");
		Log.print(1, "FIN TEST d'une case tabou pour faire perdre joueur noir");

	}

	@Test
	public void annulerCoupEntreDeuxJoueurs() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		Log.setNiveau(0);
		Log.print(1, "TEST d'une annulation de coup");
		afficherPlateauJeuCourant(plateauJeuEnCours);

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir
		tabCoord[1] = new Coordonnees(6, 6); // blanc qui joue
		tabCoord[2] = new Coordonnees(10, 10); // noir
		tabCoord[3] = new Coordonnees(13, 13); // blanc

		for (int i = 0; i < tabCoord.length; i++) {

			operationJouerJoueurCourant(donneesJeu, tabCoord[i]);
			afficherPlateauJeuCourant(plateauJeuEnCours);

		}
		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauTestAvantAnnulation2Humains");
		

		Log.print(1, "ANNNULATION D'UN COUP ENTRE DEUX JOUEURS");
		donneesJeu.annuler();
		
		afficherPlateauJeuCourant(plateauJeuEnCours);
		
		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauTestAprèsAnnulation2Humains");
		
		Log.print(1, "Le nombre de pion du joueur courant " + donneesJeu.getRenjou().getJoueurCourant() + " est de  : "
				+ donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getNbPion());

		operationJouerJoueurCourant(donneesJeu, new Coordonnees(2, 2)); //blanc qui joue
		afficherPlateauJeuCourant(plateauJeuEnCours);

		operationJouerJoueurCourant(donneesJeu, new Coordonnees(13, 13)); //noir qui joue
		afficherPlateauJeuCourant(plateauJeuEnCours);
		
		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauTestAprèsAnnulationJeuxEntre2Humains");
		
		Log.print(1, "FIN TEST d'une annulation de coup");
	}

	@Test
	public void annulerCoupEntreJoueurIA() {

		//-------------
		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);

		Log.setNiveau(0);
		Log.print(1, "TEST d'une annulation de coup entre un joueur et une IA");
		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir coup 1
		tabCoord[1] = new Coordonnees(1, 1); // noir coup 2
		tabCoord[2] = new Coordonnees(10, 10); // noir coup 3
		tabCoord[3] = new Coordonnees(13, 13); // noir coup 4

		for (int i = 0; i < tabCoord.length; i++) {

			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		}

		Log.print(1, "ANNNULATION D'UN COUP ENTRE JOUEUR ET IA");
		donneesJeu.annuler();
		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		Log.print(1, "Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		Log.print(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		donneesJeu.operationJouer(new Coordonnees(2, 2),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());

		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Log.print(1, "FIN TEST d'une annulation de coup ENTRE JOUEUR ET IA");

	}

	@Test
	public void annulerRefaireCoupEntreDeuxJoueurs() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);

		Log.setNiveau(0);
		Log.print(1, "TEST annuler/refaire coup entre 2 joueurs");
		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir
		tabCoord[1] = new Coordonnees(6, 6); // blanc qui joue
		tabCoord[2] = new Coordonnees(10, 10); // noir
		tabCoord[3] = new Coordonnees(13, 13); // blanc

		for (int i = 0; i < tabCoord.length; i++) {

			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		}

		Log.print(1, "ANNNULATION D'UN COUP ENTRE DEUX JOUEURS");
		donneesJeu.annuler();
		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		Log.print(1, "Le nombre de pion du joueur courant " + donneesJeu.getRenjou().getJoueurCourant() + " est de  : "
				+ donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getNbPion());

		Log.print(1, "REFAIRE UN COUP ENTRE DEUX JOUEURS");
		donneesJeu.refaire();
		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		Log.print(1, "Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		Log.print(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		donneesJeu.operationJouer(new Coordonnees(14, 14),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Log.print(1, "Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		Log.print(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		Log.print(1, "FIN TEST d'une ANNULATION/REFAIRE de coup entre DEUX JOUEURS");

	}

	@Test
	public void annulerRefaireEntreJoueurEtIA() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);

		Log.setNiveau(0);
		Log.print(1, "TEST d'un annuler/refaire de coup entre un joueur et une IA");
		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir coup 1
		tabCoord[1] = new Coordonnees(1, 1); // noir coup 2
		tabCoord[2] = new Coordonnees(10, 10); // noir coup 3
		tabCoord[3] = new Coordonnees(13, 13); // noir coup 4

		for (int i = 0; i < tabCoord.length; i++) {

			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		}

		Log.print(1, "ANNNULATION D'UN COUP ENTRE JOUEUR ET IA");
		donneesJeu.annuler();
		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		Log.print(1, "Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		Log.print(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		Log.print(1, "REFAIRE UN COUP ENTRE JOUEUR ET IA");
		donneesJeu.refaire();

		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Log.print(1, "Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		Log.print(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		donneesJeu.operationJouer(new Coordonnees(0, 14),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Log.print(1, "Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		Log.print(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		Log.print(1, "FIN TEST d'un annuler/refaire un coup ENTRE JOUEUR ET IA");

	}

	@Test
	public void testSauvegardePartie() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);

		Log.setNiveau(0);
		Log.print(1, "TEST PARTIE AVEC SAUVEGARDE");
		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir coup 1
		tabCoord[1] = new Coordonnees(1, 1); // noir coup 2
		tabCoord[2] = new Coordonnees(10, 10); // noir coup 3
		tabCoord[3] = new Coordonnees(13, 13); // noir coup 4

		for (int i = 0; i < tabCoord.length; i++) {
			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		}

		donneesJeu.sauvegarder("testSaveRenjou.ser");

		Log.print(1, "FIN TEST PARTIE AVEC SAUVEGARDE");

	}

	@Test
	public void testChargementPartie() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		// vu qu'on va charger un new renjou avec des paramètres humain vs
		// IAFacile, la ligne au dessus n'a plus vraiment de sens. C'est
		// seulement pour simuler une nouvelle partie

		Log.setNiveau(0);
		Log.print(1, "TEST CHARGEMENT DE PARTIE");

		donneesJeu.charger("testSaveRenjou.ser");

		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Log.print(1, "LE JOUEUR NOIR EST DE TYPE : " + donneesJeu.getRenjou().getJoueurs()[0].getType());
		Log.print(1, "LE JOUEUR BLANC EST DE TYPE : " + donneesJeu.getRenjou().getJoueurs()[1].getType());

		Log.print(1, "LE JOUEUR NOIR HUMAIN JOUE");

		donneesJeu.operationJouer(new Coordonnees(0, 0),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());

		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees[] tabCoord = new Coordonnees[8];
		tabCoord[0] = new Coordonnees(11, 9); // noir coup 1
		tabCoord[1] = new Coordonnees(13, 9); // noir coup 2
		tabCoord[2] = new Coordonnees(12, 8); // noir coup 3
		tabCoord[3] = new Coordonnees(11, 7); // noir coup 4
		tabCoord[4] = new Coordonnees(10, 6); // noir coup 5
		tabCoord[5] = new Coordonnees(9, 5); // noir coup 6
		tabCoord[6] = new Coordonnees(14, 10); // noir coup 7
		tabCoord[7] = new Coordonnees(3, 3); // noir coup 8 ne sera pas joué car
												// gagné avant

		for (int i = 0; i < tabCoord.length; i++) {
			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		}

		Log.print(1, "FIN TEST CHARGEMENT DE PARTIE");

	}

	@Test
	public void testRecommencerPartie() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		Log.setNiveau(0);
		Log.print(1, "TEST RECOMMENCER UNE PARTIE");

		Coordonnees[] tabCoord = new Coordonnees[11];
		tabCoord[0] = new Coordonnees(7, 7); // noir
		tabCoord[1] = new Coordonnees(6, 6); // blanc qui joue
		tabCoord[2] = new Coordonnees(8, 6); // noir
		tabCoord[3] = new Coordonnees(6, 5); // blanc
		tabCoord[4] = new Coordonnees(9, 5); // noir
		tabCoord[5] = new Coordonnees(6, 4); // blanc
		tabCoord[6] = new Coordonnees(10, 4); // noir
		tabCoord[7] = new Coordonnees(6, 3); // blanc
		tabCoord[8] = new Coordonnees(13, 3); // noir
		tabCoord[9] = new Coordonnees(9, 2); // blanc
		tabCoord[10] = new Coordonnees(12, 12); // noir

		for (int i = 0; i < tabCoord.length; i++) {
			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		}

		donneesJeu.recommencerPartie();

		Log.print(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		ArrayList<Tabou> tabousDuJeu = donneesJeu.getRenjou().getTabouJeu();
		Tabous allTabous = donneesJeu.getRenjou().getTabous();

		for (int i = 0; i < tabousDuJeu.size(); i++) {
			Log.print(1, "" + tabousDuJeu.get(i).getNom());
		}

		for (int i = 0; i < allTabous.getTabous().size(); i++) {
			Log.print(1, "" + allTabous.getTabous().get(i).getNom());
		}

		Log.print(1, "FIN TEST RECOMMENCER UNE PARTIE");
	}

}
