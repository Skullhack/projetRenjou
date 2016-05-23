package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.omg.PortableServer.ServantLocatorPackage.CookieHolder;

import Controleur.Moteur;
import Enum.TypeCouleur;
import Enum.TypeJoueur;
import Joueur.IAFacile;
import Utilitaire.Coordonnees;
import Utilitaire.PlateauDeJeu;

public class TestIAFacile {


	private static String chemin = "Tests/TestsIAFacile/";
	
	
	Moteur m;
	int nbJoueur = 2;
	IAFacile iaFacileNoire;
	
	@Before
	public void init() {
		
		m = new Moteur(TypeJoueur.IAFacile, TypeJoueur.Humain );
		iaFacileNoire = (IAFacile) m.getRenjou().getJoueurs()[0];
		
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
	
//	//a implementer avec fichier ou ya un point et ou il n'y en a pas
//	@Test
//	public void testNoirGagnantHorizon() {
//
//		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirHorizonNaif");
//		Coordonnees c = iaFacileNoire.jouer(plateau);
//		assertTrue(((c.getLigne()==7)&&(c.getColonne()==4))||((c.getLigne()==7)&&(c.getColonne()==9)));
//		
//	}
//	
//	//a implementer avec fichier ou ya un point et ou il n'y en a pas
//	@Test
//	public void testNoirGagnantVert() {
//
//		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirVertNaif");
//		Coordonnees c = iaFacileNoire.jouer(plateau);
//		assertTrue(((c.getLigne()==4)&&(c.getColonne()==5))||((c.getLigne()==9)&&(c.getColonne()==5)));
//		
//	}
//	
//	//a implementer avec fichier ou ya un point et ou il n'y en a pas
//	@Test
//	public void testNoirGagnantDiagoBasDroite() {
//
//		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirDiagoBasDroiteNaif");
//		Coordonnees c = iaFacileNoire.jouer(plateau);
//		assertTrue(((c.getLigne()==3)&&(c.getColonne()==2))||((c.getLigne()==8)&&(c.getColonne()==7)));
//		
//	}
//	
//	//a implementer avec fichier ou ya un point et ou il n'y en a pas
//	@Test
//	public void testNoirGagnantDiagoBasGauche() {
//
//		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "ProchainCoupGagnantNoirDiagoBasGaucheNaif");
//		Coordonnees c = iaFacileNoire.jouer(plateau);
//		assertTrue(((c.getLigne()==8)&&(c.getColonne()==6))||((c.getLigne()==13)&&(c.getColonne()==1)));
//		
//	}
//	
	//a implementer tout comme les tests avant
	@Test
	public void testNoirEmpechercoupHorizonNaif(){
		
		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "EmpecherCoupGagnantNoirHorizonNaif");
		Coordonnees c = iaFacileNoire.jouer(plateau);
		assertTrue(((c.getLigne()==4)&&(c.getColonne()==2))||((c.getLigne()==4)&&(c.getColonne()==6)));
	}
	
	@Test
	public void testNoirEmpechercoupVertNaif(){
		
		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "EmpecherCoupGagnantNoirVertNaif");
		Coordonnees c = iaFacileNoire.jouer(plateau);
		assertTrue(((c.getLigne()==4)&&(c.getColonne()==3))||((c.getLigne()==8)&&(c.getColonne()==3)));
	}
	
	@Test
	public void testNoirEmpechercoupDiagoBasDroiteNaif(){
		
		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "EmpecherCoupGagnantNoirDiagoBasDroiteNaif");
		Coordonnees c = iaFacileNoire.jouer(plateau);
		assertTrue(((c.getLigne()==2)&&(c.getColonne()==1))||((c.getLigne()==6)&&(c.getColonne()==5)));
	}
	
	@Test
	public void testNoirEmpechercoupDiagoBasGaucheNaif(){
		
		PlateauDeJeu plateau= iaFacileNoire.getMoteur().getRenjou().getPlateauDeJeu().charger(chemin + "EmpecherCoupGagnantNoirDiagoBasGaucheNaif");
		Coordonnees c = iaFacileNoire.jouer(plateau);
		assertTrue(((c.getLigne()==2)&&(c.getColonne()==9))||((c.getLigne()==6)&&(c.getColonne()==5)));
	}
	
	
	
	
	//a implementer tout comme les tests avant
	//public void testBlanc ...
	
}
