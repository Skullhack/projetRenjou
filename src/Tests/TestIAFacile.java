package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.omg.PortableServer.ServantLocatorPackage.CookieHolder;

import Controleur.Coordonnees;
import Controleur.Moteur;
import Controleur.PlateauDeJeu;
import Enum.TypeCouleur;
import Enum.TypeJoueur;
import Joueur.IAFacile;
import Joueur.Joueur;

public class TestIAFacile {


	private static String chemin = "TestsPlateauDeJeu/TestsIA/";
	
	@Before
	public void init() {
	}
	
	
	@Test
	public void test() {
		
		Moteur m;
		int nbJoueur = 2;
		IAFacile iaFacile;

		m = new Moteur(nbJoueur);
		iaFacile = new IAFacile(m,TypeJoueur.IAFacile, 60, TypeCouleur.Noir);
		
		PlateauDeJeu plateau= iaFacile.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirHorizonNaif");
		System.out.println(plateau);
		Coordonnees c = iaFacile.jouer(plateau);
		System.out.println(c);
		assertTrue(((c.getLigne()==7)&&(c.getColonne()==4))||((c.getLigne()==7)&&(c.getColonne()==9)));
		
	}

}
