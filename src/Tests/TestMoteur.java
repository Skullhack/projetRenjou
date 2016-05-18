package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controleur.Coordonnees;
import Controleur.Moteur;

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

		donneesJeu.setNiveauTrace(10);

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

}
