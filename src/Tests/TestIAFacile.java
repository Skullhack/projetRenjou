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
	
	
	Moteur m;
	int nbJoueur = 2;
	IAFacile iaFacile;
	
	@Before
	public void init() {
		
		m = new Moteur(nbJoueur);
		iaFacile = new IAFacile(m,TypeJoueur.IAFacile, 60, TypeCouleur.Noir);
		
	}
	
	
	@Test
	public void testNoirHorizonNaif() {

		PlateauDeJeu plateau= iaFacile.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirHorizonNaif");
		Coordonnees c = iaFacile.jouer(plateau);
		assertTrue(((c.getLigne()==7)&&(c.getColonne()==4))||((c.getLigne()==7)&&(c.getColonne()==9)));
		
	}
	
	@Test
	public void testNoirVertNaif() {

		PlateauDeJeu plateau= iaFacile.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirVertNaif");
		Coordonnees c = iaFacile.jouer(plateau);
		assertTrue(((c.getLigne()==4)&&(c.getColonne()==5))||((c.getLigne()==9)&&(c.getColonne()==5)));
		
	}
	
	
	@Test
	public void testNoirDiagoBasDroiteNaif() {

		PlateauDeJeu plateau= iaFacile.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirDiagoBasDroiteNaif");
		Coordonnees c = iaFacile.jouer(plateau);
		assertTrue(((c.getLigne()==3)&&(c.getColonne()==2))||((c.getLigne()==8)&&(c.getColonne()==7)));
		
	}
	
	
	@Test
	public void testNoirDiagoBasGaucheNaif() {

		PlateauDeJeu plateau= iaFacile.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirDiagoBasGaucheNaif");
		Coordonnees c = iaFacile.jouer(plateau);
		assertTrue(((c.getLigne()==8)&&(c.getColonne()==6))||((c.getLigne()==13)&&(c.getColonne()==1)));
		
	}

}
