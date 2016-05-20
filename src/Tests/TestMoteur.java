package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controleur.Coordonnees;
import Controleur.Moteur;
import Enum.TypeCase;
import Enum.TypeJoueur;

public class TestMoteur {

	@Test
	public void test() {

		int nbJoueurs = 2;
		Moteur donneesJeu = new Moteur(nbJoueurs);

		donneesJeu.setNiveauTrace(0);
		donneesJeu.printTrace(1, "TEST : d'activation/désactivation des cases injouables");
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees c = new Coordonnees(7, 7);
		donneesJeu.operationJouer(c,
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		c = new Coordonnees(8, 8);

		donneesJeu.operationJouer(c,
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		donneesJeu.printTrace(1, "FIN TEST : d'activation/désactivation des cases injouables");

	}

	@Test
	public void testPartieNulle() {

		int nbJoueurs = 2;
		Moteur donneesJeu = new Moteur(nbJoueurs);

		donneesJeu.setNiveauTrace(0);

		donneesJeu.printTrace(1, "TEST d'une partie nulle");
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees c = new Coordonnees(7, 7);
		donneesJeu.operationJouer(c,
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		c = new Coordonnees(8, 8);

		donneesJeu.operationJouer(c,
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {

				if (i == 4) {
					i++;
				}
				donneesJeu.operationJouer(new Coordonnees(i, j),
						donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
				donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
			}

		}

		donneesJeu.printTrace(1, "FIN TEST d'une partie nulle");

	}

	@Test
	public void testPartieFinieJoueurNoirHautEnBas() {

		int nbJoueurs = 2;
		Moteur donneesJeu = new Moteur(nbJoueurs);

		donneesJeu.setNiveauTrace(0);

		donneesJeu.printTrace(1, "TEST d'une partie finie par le joueur noir (haut en bas)");
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

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
			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
			// System.out.println("ACTUELLEMENT LA PARTI EST : " +
			// donneesJeu.getRenjou().getEtatPartie());

		}
		donneesJeu.printTrace(1, "FIN TEST d'une partie finie par le joueur noir (haut en bas)");

	}

	@Test
	public void testPartieFinieJoueurBlancDiagonaleHautGaucheBasDroite() {

		int nbJoueurs = 2;
		Moteur donneesJeu = new Moteur(nbJoueurs);

		donneesJeu.setNiveauTrace(0);

		donneesJeu.printTrace(1, "TEST d'une partie finie par le joueur blanc (DiagonaleHautGaucheBasDroite)");
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

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
			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
			// System.out.println("ACTUELLEMENT LA PARTI EST : " +
			// donneesJeu.getRenjou().getEtatPartie());

		}

		donneesJeu.printTrace(1, "FIN TEST d'une partie finie par le joueur blanc (DiagonaleHautGaucheBasDroite)");
	}

	@Test
	public void testPartieFinieJoueurBlancGaucheADroite() {

		int nbJoueurs = 2;
		Moteur donneesJeu = new Moteur(nbJoueurs);

		donneesJeu.setNiveauTrace(0);
		donneesJeu.printTrace(1, "TEST d'une partie finie par le joueur blanc (GaucheADroite)");
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

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
			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
			// System.out.println("ACTUELLEMENT LA PARTI EST : " +
			// donneesJeu.getRenjou().getEtatPartie());

		}

		donneesJeu.printTrace(1, "FIN TEST d'une partie finie par le joueur blanc (GaucheADroite)");

	}

	@Test
	public void testPartieFinieJoueurNoirDiagonaleHautDroiteBasGauche() {

		int nbJoueurs = 2;
		Moteur donneesJeu = new Moteur(nbJoueurs);

		donneesJeu.setNiveauTrace(0);
		donneesJeu.printTrace(1, "TEST d'une partie finie par le joueur noir (DiagonaleHautDroiteBasGauche)");
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

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
			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
			// System.out.println("ACTUELLEMENT LA PARTI EST : " +
			// donneesJeu.getRenjou().getEtatPartie());

		}

		donneesJeu.printTrace(1, "FIN TEST d'une partie finie par le joueur noir (DiagonaleHautDroiteBasGauche)");

	}

	@Test
	public void majCaseTabouAvecPartiePerduPourNoir() {

		int nbJoueurs = 2;
		Moteur donneesJeu = new Moteur(nbJoueurs);

		donneesJeu.setNiveauTrace(0);
		donneesJeu.printTrace(1, "TEST d'une case tabou pour faire perdre joueur noir");
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir
		tabCoord[1] = new Coordonnees(6, 6); // blanc qui joue
		tabCoord[2] = new Coordonnees(10, 10); // noir va jouer sur une case
												// tabou
		tabCoord[3] = new Coordonnees(13, 13);

		for (int i = 0; i < tabCoord.length; i++) {

			if (i == 2) {
				// on va mettre la case tabou pour pieger le joueur noir
				donneesJeu.getRenjou().getPlateauDeJeu().ajouter(new Coordonnees(10, 10), TypeCase.Tabou);
			}

			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		}

		donneesJeu.printTrace(1, "FIN TEST d'une case tabou pour faire perdre joueur noir");

	}

	@Test
	public void annulerCoupEntreDeuxJoueurs() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);

		donneesJeu.setNiveauTrace(0);
		donneesJeu.printTrace(1, "TEST d'une annulation de coup");
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir
		tabCoord[1] = new Coordonnees(6, 6); // blanc qui joue
		tabCoord[2] = new Coordonnees(10, 10); // noir
		tabCoord[3] = new Coordonnees(13, 13); // blanc

		for (int i = 0; i < tabCoord.length; i++) {

			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		}

		donneesJeu.printTrace(1, "ANNNULATION D'UN COUP ENTRE DEUX JOUEURS");
		donneesJeu.annuler();
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur courant " + donneesJeu.getRenjou().getJoueurCourant() + " est de  : "
						+ donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getNbPion());

		donneesJeu.operationJouer(new Coordonnees(2, 2),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType()); // blanc
																											// qui
																											// joue
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		donneesJeu.operationJouer(new Coordonnees(13, 13),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType()); // noir
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		donneesJeu.printTrace(1, "FIN TEST d'une annulation de coup");

	}

	@Test
	public void annulerCoupEntreJoueurIA() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);

		donneesJeu.setNiveauTrace(0);
		donneesJeu.printTrace(1, "TEST d'une annulation de coup entre un joueur et une IA");
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir coup 1
		tabCoord[1] = new Coordonnees(1, 1); // noir coup 2
		tabCoord[2] = new Coordonnees(10, 10); // noir coup 3
		tabCoord[3] = new Coordonnees(13, 13); // noir coup 4

		for (int i = 0; i < tabCoord.length; i++) {

			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		}

		donneesJeu.printTrace(1, "ANNNULATION D'UN COUP ENTRE JOUEUR ET IA");
		donneesJeu.annuler();
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		donneesJeu.operationJouer(new Coordonnees(2, 2),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());

		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		donneesJeu.printTrace(1, "FIN TEST d'une annulation de coup ENTRE JOUEUR ET IA");

	}

	@Test
	public void annulerRefaireCoupEntreDeuxJoueurs() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);

		donneesJeu.setNiveauTrace(0);
		donneesJeu.printTrace(1, "TEST annuler/refaire coup entre 2 joueurs");
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir
		tabCoord[1] = new Coordonnees(6, 6); // blanc qui joue
		tabCoord[2] = new Coordonnees(10, 10); // noir
		tabCoord[3] = new Coordonnees(13, 13); // blanc

		for (int i = 0; i < tabCoord.length; i++) {

			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		}

		donneesJeu.printTrace(1, "ANNNULATION D'UN COUP ENTRE DEUX JOUEURS");
		donneesJeu.annuler();
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur courant " + donneesJeu.getRenjou().getJoueurCourant() + " est de  : "
						+ donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getNbPion());

		donneesJeu.printTrace(1, "REFAIRE UN COUP ENTRE DEUX JOUEURS");
		donneesJeu.refaire();
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		donneesJeu.operationJouer(new Coordonnees(14, 14),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		donneesJeu.printTrace(1, "FIN TEST d'une ANNULATION/REFAIRE de coup entre DEUX JOUEURS");

	}

	@Test
	public void annulerRefaireEntreJoueurEtIA() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);

		donneesJeu.setNiveauTrace(0);
		donneesJeu.printTrace(1, "TEST d'un annuler/refaire de coup entre un joueur et une IA");
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir coup 1
		tabCoord[1] = new Coordonnees(1, 1); // noir coup 2
		tabCoord[2] = new Coordonnees(10, 10); // noir coup 3
		tabCoord[3] = new Coordonnees(13, 13); // noir coup 4

		for (int i = 0; i < tabCoord.length; i++) {

			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		}

		donneesJeu.printTrace(1, "ANNNULATION D'UN COUP ENTRE JOUEUR ET IA");
		donneesJeu.annuler();
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		donneesJeu.printTrace(1, "REFAIRE UN COUP ENTRE JOUEUR ET IA");
		donneesJeu.refaire();

		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		donneesJeu.operationJouer(new Coordonnees(0, 14),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur noir est de : " + donneesJeu.getRenjou().getJoueurs()[0].getNbPion());

		donneesJeu.printTrace(1,
				"Le nombre de pion du joueur blanc est de : " + donneesJeu.getRenjou().getJoueurs()[1].getNbPion());

		donneesJeu.printTrace(1, "FIN TEST d'un annuler/refaire un coup ENTRE JOUEUR ET IA");

	}

	@Test
	public void testSauvegardePartie() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);

		donneesJeu.setNiveauTrace(10);
		donneesJeu.printTrace(1, "TEST PARTIE AVEC SAUVEGARDE");
		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		Coordonnees[] tabCoord = new Coordonnees[4];
		tabCoord[0] = new Coordonnees(7, 7); // noir coup 1
		tabCoord[1] = new Coordonnees(1, 1); // noir coup 2
		tabCoord[2] = new Coordonnees(10, 10); // noir coup 3
		tabCoord[3] = new Coordonnees(13, 13); // noir coup 4

		for (int i = 0; i < tabCoord.length; i++) {
			donneesJeu.operationJouer(tabCoord[i],
					donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
			donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		}

		donneesJeu.sauvegarder("testSaveRenjou.ser");

		donneesJeu.printTrace(1, "FIN TEST PARTIE AVEC SAUVEGARDE");

	}

	@Test
	public void testChargementPartie() {

		Moteur donneesJeu = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);

		donneesJeu.setNiveauTrace(10);
		donneesJeu.printTrace(1, "TEST CHARGEMENT DE PARTIE");

		donneesJeu.charger("testSaveRenjou.ser");

		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

		donneesJeu.printTrace(1, "LE JOUEUR NOIR EST DE TYPE : " + donneesJeu.getRenjou().getJoueurs()[0].getType());
		donneesJeu.printTrace(1, "LE JOUEUR BLANC EST DE TYPE : " + donneesJeu.getRenjou().getJoueurs()[1].getType());

		donneesJeu.printTrace(1, "LE JOUEUR NOIR HUMAIN JOUE");

		donneesJeu.operationJouer(new Coordonnees(0, 0),
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());

		donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());

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
			donneesJeu.printTrace(1, donneesJeu.getRenjou().getPlateauDeJeu().toString());
		}

		donneesJeu.printTrace(1, "FIN TEST CHARGEMENT DE PARTIE");

	}

}
