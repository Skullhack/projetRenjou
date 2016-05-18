package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controleur.Coordonnees;
import Controleur.Moteur;

public class TestMoteur {

	@Test
	public void test() {

		System.out.println("TEST : d'activation/désactivation des cases injouables BLOB");
		int nbJoueurs = 2;
		Moteur donneesJeu = new Moteur(nbJoueurs);
		System.out.println(donneesJeu.getRenjou().getPlateauDeJeu());

		Coordonnees c = new Coordonnees(7, 7);
		donneesJeu.operationJouer(c,
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		System.out.println(donneesJeu.getRenjou().getPlateauDeJeu());

		c = new Coordonnees(8, 8);

		donneesJeu.operationJouer(c,
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		System.out.println(donneesJeu.getRenjou().getPlateauDeJeu());

	}

	@Test
	public void testPartieFinie() {

		System.out.println("TEST d'une partie finie");
		int nbJoueurs = 2;
		Moteur donneesJeu = new Moteur(nbJoueurs);
		System.out.println(donneesJeu.getRenjou().getPlateauDeJeu());

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
			System.out.println(donneesJeu.getRenjou().getPlateauDeJeu());
			System.out.println("ACTUELLEMENT LA PARTI EST : " + donneesJeu.getRenjou().getEtatPartie());

		}

	}

	@Test
	public void testPartieNulle() {

		System.out.println("TEST d'une partie nulle");
		int nbJoueurs = 2;
		Moteur donneesJeu = new Moteur(nbJoueurs);
		System.out.println(donneesJeu.getRenjou().getPlateauDeJeu());

		Coordonnees c = new Coordonnees(7, 7);
		donneesJeu.operationJouer(c,
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		System.out.println(donneesJeu.getRenjou().getPlateauDeJeu());

		c = new Coordonnees(8, 8);

		donneesJeu.operationJouer(c,
				donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		System.out.println(donneesJeu.getRenjou().getPlateauDeJeu());

		for (int i = 1; i < 14; i++) {
			for (int j = 1; j < 14; j++) {
				donneesJeu.operationJouer(new Coordonnees(i, j),
						donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
				System.out.println(donneesJeu.getRenjou().getPlateauDeJeu());
			}

		}
	}

}
