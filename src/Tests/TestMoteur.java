package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Controleur.Moteur;
import Controleur.MoteurObserveur;
import Enum.TypeCase;
import Enum.TypeJoueur;
import Enum.TypeTabous;
import Joueur.IAFacile;
import Utilitaire.Coordonnees;
import Utilitaire.Log;
import Utilitaire.PlateauDeJeu;
import Utilitaire.Tabou;

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
		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getNbPion(), 0);
		assertEquals(donneesJeu.getRenjou().getJoueurs()[1].getNbPion(), 0);

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

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauCaseApresClicTabouJoueurNoir");
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
		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[0].getNbPionsBase() - 2);
		assertEquals(donneesJeu.getRenjou().getJoueurs()[1].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[1].getNbPionsBase() - 2);

		Log.print(1, "ANNNULATION D'UN COUP ENTRE DEUX JOUEURS");
		donneesJeu.annuler();

		afficherPlateauJeuCourant(plateauJeuEnCours);

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauTestApresAnnulation2Humains");

		Log.print(1, "Le nombre de pion du joueur courant " + donneesJeu.getRenjou().getJoueurCourant() + " est de  : "
				+ donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getNbPion());

		assertEquals(donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getNbPionsBase() - 1);

		operationJouerJoueurCourant(donneesJeu, new Coordonnees(2, 2)); // blanc
																		// qui
																		// joue
		afficherPlateauJeuCourant(plateauJeuEnCours);

		operationJouerJoueurCourant(donneesJeu, new Coordonnees(13, 13)); // noir
																			// qui
																			// joue
		afficherPlateauJeuCourant(plateauJeuEnCours);

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauTestApresAnnulationJeuxEntre2Humains");

		Log.print(1, "FIN TEST d'une annulation de coup");
	}

	@Test
	public void annulerCoupEntreJoueurIA() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		Log.setNiveau(0);
		Log.print(1, "TEST d'une annulation de coup entre un joueur et une IA");
		afficherPlateauJeuCourant(plateauJeuEnCours);

		((IAFacile) donneesJeu.getRenjou().getJoueurs()[1]).setSeed(100);

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir coup 1
		tabCoord[1] = new Coordonnees(1, 1); // noir coup 2
		tabCoord[2] = new Coordonnees(10, 10); // noir coup 3
		tabCoord[3] = new Coordonnees(13, 13); // noir coup 4

		for (int i = 0; i < tabCoord.length; i++) {

			operationJouerJoueurCourant(donneesJeu, tabCoord[i]);
			afficherPlateauJeuCourant(plateauJeuEnCours);

		}

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauTestAvantAnnulationHumainIA");

		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[0].getNbPionsBase() - 4);
		assertEquals(donneesJeu.getRenjou().getJoueurs()[1].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[1].getNbPionsBase() - 4);

		Log.print(1, "ANNNULATION D'UN COUP ENTRE JOUEUR ET IA");
		donneesJeu.annuler();
		afficherPlateauJeuCourant(plateauJeuEnCours);

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauTestApresAnnulationHumainIA");

		Log.print(1, "Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		Log.print(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[0].getNbPionsBase() - 3);
		assertEquals(donneesJeu.getRenjou().getJoueurs()[1].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[1].getNbPionsBase() - 3);

		operationJouerJoueurCourant(donneesJeu, new Coordonnees(2, 2));

		afficherPlateauJeuCourant(plateauJeuEnCours);
		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauTestApresAnnulationEtUnCoupHumainIA");

		Log.print(1, "FIN TEST d'une annulation de coup ENTRE JOUEUR ET IA");

	}

	@Test
	public void annulerRefaireCoupEntreDeuxJoueurs() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		Log.setNiveau(0);
		Log.print(1, "TEST annuler/refaire coup entre 2 joueurs");
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

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauAvantAnnulerRefaire2Humains");
		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[0].getNbPionsBase() - 2);
		assertEquals(donneesJeu.getRenjou().getJoueurs()[1].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[1].getNbPionsBase() - 2);

		Log.print(1, "ANNNULATION D'UN COUP ENTRE DEUX JOUEURS");
		donneesJeu.annuler();
		afficherPlateauJeuCourant(plateauJeuEnCours);
		Log.print(1, "Le nombre de pion du joueur courant " + donneesJeu.getRenjou().getJoueurCourant() + " est de  : "
				+ donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getNbPion());
		assertEquals(donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getNbPionsBase() - 1);

		Log.print(1, "REFAIRE UN COUP ENTRE DEUX JOUEURS");
		donneesJeu.refaire();
		afficherPlateauJeuCourant(plateauJeuEnCours);
		assertEquals(donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getNbPionsBase() - 2);
		Log.print(1, "Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		Log.print(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauApresAnnulerRefaire2Humains");

		operationJouerJoueurCourant(donneesJeu, new Coordonnees(14, 14));
		afficherPlateauJeuCourant(plateauJeuEnCours);

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauApresAnnulerRefaireEtUnCoup2Humains");
		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[0].getNbPionsBase() - 3);
		assertEquals(donneesJeu.getRenjou().getJoueurs()[1].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[1].getNbPionsBase() - 2);

		Log.print(1, "Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		Log.print(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		Log.print(1, "FIN TEST d'une ANNULATION/REFAIRE de coup entre DEUX JOUEURS");

	}

	@Test
	public void annulerRefaireEntreJoueurEtIA() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		Log.setNiveau(0);
		Log.print(1, "TEST d'un annuler/refaire de coup entre un joueur et une IA");
		afficherPlateauJeuCourant(plateauJeuEnCours);

		((IAFacile) donneesJeu.getRenjou().getJoueurs()[1]).setSeed(100);

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir coup 1
		tabCoord[1] = new Coordonnees(1, 1); // noir coup 2
		tabCoord[2] = new Coordonnees(10, 10); // noir coup 3
		tabCoord[3] = new Coordonnees(13, 13); // noir coup 4

		for (int i = 0; i < tabCoord.length; i++) {

			operationJouerJoueurCourant(donneesJeu, tabCoord[i]);
			afficherPlateauJeuCourant(plateauJeuEnCours);

		}

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauAvantAnnulerRefaireHumainIA");
		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[0].getNbPionsBase() - 4);
		assertEquals(donneesJeu.getRenjou().getJoueurs()[1].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[1].getNbPionsBase() - 4);

		Log.print(1, "ANNNULATION D'UN COUP ENTRE JOUEUR ET IA");
		donneesJeu.annuler();
		afficherPlateauJeuCourant(plateauJeuEnCours);
		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[0].getNbPionsBase() - 3);
		Log.print(1, "Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		Log.print(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		Log.print(1, "REFAIRE UN COUP ENTRE JOUEUR ET IA");
		donneesJeu.refaire();

		afficherPlateauJeuCourant(plateauJeuEnCours);
		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauApresAnnulerRefaireHumainIA");
		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[0].getNbPionsBase() - 4);

		Log.print(1, "Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		Log.print(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		operationJouerJoueurCourant(donneesJeu, new Coordonnees(0, 14));
		afficherPlateauJeuCourant(plateauJeuEnCours);

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauApresAnnulerRefaireEtUnCoupHumainIA");
		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[0].getNbPionsBase() - 5);
		assertEquals(donneesJeu.getRenjou().getJoueurs()[1].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[1].getNbPionsBase() - 5);

		Log.print(1, "Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		Log.print(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		Log.print(1, "FIN TEST d'un annuler/refaire un coup ENTRE JOUEUR ET IA");
	}

	@Test
	public void testSauvegardePartie() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		((IAFacile) donneesJeu.getRenjou().getJoueurs()[1]).setSeed(100);

		Log.setNiveau(0);
		Log.print(1, "TEST PARTIE AVEC SAUVEGARDE");
		afficherPlateauJeuCourant(plateauJeuEnCours);

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir coup 1
		tabCoord[1] = new Coordonnees(1, 1); // noir coup 2
		tabCoord[2] = new Coordonnees(10, 10); // noir coup 3
		tabCoord[3] = new Coordonnees(13, 13); // noir coup 4

		for (int i = 0; i < tabCoord.length; i++) {
			operationJouerJoueurCourant(donneesJeu, tabCoord[i]);
			afficherPlateauJeuCourant(plateauJeuEnCours);
		}

		donneesJeu.sauvegarder("testSaveRenjou.ser");

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauJeuSauvegardeHumainIA");

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
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();

		afficherPlateauJeuCourant(plateauJeuEnCours);

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauJeuSauvegardeHumainIA");

		Log.print(1, "LE JOUEUR NOIR EST DE TYPE : " + donneesJeu.getRenjou().getJoueurs()[0].getType());
		Log.print(1, "LE JOUEUR BLANC EST DE TYPE : " + donneesJeu.getRenjou().getJoueurs()[1].getType());

		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getType(), TypeJoueur.Humain);
		assertEquals(donneesJeu.getRenjou().getJoueurs()[1].getType(), TypeJoueur.IAFacile);

		Log.print(1, "LE JOUEUR NOIR HUMAIN JOUE");

		operationJouerJoueurCourant(donneesJeu, new Coordonnees(0, 0));
		afficherPlateauJeuCourant(plateauJeuEnCours);

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
			operationJouerJoueurCourant(donneesJeu, tabCoord[i]);
			afficherPlateauJeuCourant(plateauJeuEnCours);
		}

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauJeuSauvegardeVictoireNoireHumainIA");

		Log.print(1, "FIN TEST CHARGEMENT DE PARTIE");

	}

	@Test
	public void testRecommencerPartie() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();
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
			operationJouerJoueurCourant(donneesJeu, tabCoord[i]);
			afficherPlateauJeuCourant(plateauJeuEnCours);
		}

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauJeuAvantRecommencerPartie");
		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[0].getNbPionsBase() - 6);
		assertEquals(donneesJeu.getRenjou().getJoueurs()[1].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[1].getNbPionsBase() - 5);

		donneesJeu.recommencerPartie();

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauJeuApresRecommencerPartie");
		assertEquals(donneesJeu.getRenjou().getJoueurs()[0].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[0].getNbPionsBase());
		assertEquals(donneesJeu.getRenjou().getJoueurs()[1].getNbPion(),
				donneesJeu.getRenjou().getJoueurs()[1].getNbPionsBase());

		afficherPlateauJeuCourant(plateauJeuEnCours);

		Log.print(1, "FIN TEST RECOMMENCER UNE PARTIE");
	}
	
	@Test
	public void testRecommencerPartieAvecTabous() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		PlateauDeJeu plateauJeuEnCours = donneesJeu.getRenjou().getPlateauDeJeu();
		Log.setNiveau(10);
		Log.print(1, "TEST RECOMMENCER UNE PARTIE");
		
		ArrayList<TypeTabous> tabousJeu = new ArrayList<TypeTabous>();
		tabousJeu.add(TypeTabous.TROIS_TROIS);
		tabousJeu.add(TypeTabous.SIX_SEPT);
		donneesJeu.configurerPartie(TypeJoueur.Humain, TypeJoueur.Humain, tabousJeu, false);

		Log.print(1, donneesJeu.getRenjou().getTabouJeu().getListeTabous().toString());
		
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
			operationJouerJoueurCourant(donneesJeu, tabCoord[i]);
			afficherPlateauJeuCourant(plateauJeuEnCours);
		}

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauJeuAvantRecommencerPartie");
		

		donneesJeu.recommencerPartie();
		Log.print(1, donneesJeu.getRenjou().getTabouJeu().getListeTabous().toString());

		TestPlateauDeJeu.comparer(plateauJeuEnCours, chemin + "PlateauJeuApresRecommencerPartie");
		
		afficherPlateauJeuCourant(plateauJeuEnCours);

		Log.print(1, "FIN TEST RECOMMENCER UNE PARTIE");
	}
	

}
