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

public class TestIAFacile {


	private static String chemin = "TestsPlateauDeJeu/TestsIA/";
	
	
	Moteur m;
	int nbJoueur = 2;
	IAFacile iaFacileNoire;
	
	@Before
	public void init() {
		
		m = new Moteur(nbJoueur);
		iaFacileNoire = new IAFacile(m,TypeJoueur.IAFacile, 60, TypeCouleur.Noir);
		
	}
	
	
	@Test
	public void testNoirGagnantHorizonNaif() {

		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirHorizonNaif");
		Coordonnees c = iaFacileNoire.jouer(plateau);
		assertTrue(((c.getLigne()==7)&&(c.getColonne()==4))||((c.getLigne()==7)&&(c.getColonne()==9)));
		
	}
	
	@Test
	public void testNoirGagnantVertNaif() {

		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirVertNaif");
		Coordonnees c = iaFacileNoire.jouer(plateau);
		assertTrue(((c.getLigne()==4)&&(c.getColonne()==5))||((c.getLigne()==9)&&(c.getColonne()==5)));
		
	}
	
	@Test
	public void testNoirGagnantDiagoBasDroiteNaif() {

		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirDiagoBasDroiteNaif");
		Coordonnees c = iaFacileNoire.jouer(plateau);
		assertTrue(((c.getLigne()==3)&&(c.getColonne()==2))||((c.getLigne()==8)&&(c.getColonne()==7)));
		
	}
	
	@Test
	public void testNoirGagnantDiagoBasGaucheNaif() {

		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirDiagoBasGaucheNaif");
		Coordonnees c = iaFacileNoire.jouer(plateau);
		assertTrue(((c.getLigne()==8)&&(c.getColonne()==6))||((c.getLigne()==13)&&(c.getColonne()==1)));
		
	}
	
	//a implementer avec fichier ou ya un point et ou il n'y en a pas
	@Test
	public void testNoirGagnantHorizon() {

		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirHorizonNaif");
		Coordonnees c = iaFacileNoire.jouer(plateau);
		assertTrue(((c.getLigne()==7)&&(c.getColonne()==4))||((c.getLigne()==7)&&(c.getColonne()==9)));
		
	}
	
	//a implementer avec fichier ou ya un point et ou il n'y en a pas
	@Test
	public void testNoirGagnantVert() {

		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirVertNaif");
		Coordonnees c = iaFacileNoire.jouer(plateau);
		assertTrue(((c.getLigne()==4)&&(c.getColonne()==5))||((c.getLigne()==9)&&(c.getColonne()==5)));
		
	}
	
	//a implementer avec fichier ou ya un point et ou il n'y en a pas
	@Test
	public void testNoirGagnantDiagoBasDroite() {

		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirDiagoBasDroiteNaif");
		Coordonnees c = iaFacileNoire.jouer(plateau);
		assertTrue(((c.getLigne()==3)&&(c.getColonne()==2))||((c.getLigne()==8)&&(c.getColonne()==7)));
		
	}
	
	//a implementer avec fichier ou ya un point et ou il n'y en a pas
	@Test
	public void testNoirGagnantDiagoBasGauche() {

		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirDiagoBasGaucheNaif");
		Coordonnees c = iaFacileNoire.jouer(plateau);
		assertTrue(((c.getLigne()==8)&&(c.getColonne()==6))||((c.getLigne()==13)&&(c.getColonne()==1)));
		
	}
	
	//a implementer tout comme les tests avant
	//public void testNoirEmpechercoup ...
	
	//a implementer tout comme les tests avant
	//public void testBlanc ...
	
}
