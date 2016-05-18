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
		System.out.println(donneesJeu.getRenjou().getPlateauDeJeu());
		
		Coordonnees c = new Coordonnees(7, 7);
		
		donneesJeu.operationJouer(c, donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		System.out.println(donneesJeu.getRenjou().getPlateauDeJeu());

		c = new Coordonnees(8, 8);

		donneesJeu.operationJouer(c, donneesJeu.getRenjou().getJoueurs()[donneesJeu.getRenjou().getJoueurCourant()].getType());
		System.out.println(donneesJeu.getRenjou().getPlateauDeJeu());
		
	}

}
