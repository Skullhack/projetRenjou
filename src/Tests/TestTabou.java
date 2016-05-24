package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controleur.Moteur;
import Enum.TypeDirection;
import Enum.TypeJoueur;
import Utilitaire.Coordonnees;
import Utilitaire.InfosAlignement;
import Utilitaire.Log;
import Utilitaire.PlateauDeJeu;
import Utilitaire.Tabou;

public class TestTabou {

	
	Moteur m;
	PlateauDeJeu pdj;
	Tabou test;
	private static String chemin = "Tests/TestsTabou/";

	@Before
	public void init() {
		m = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		pdj = m.getRenjou().getPlateauDeJeu();
		test = new Tabou(null, "");
		//plage de trace 1000 � 1100
		Log.setPlage(1000, 1100);
	}
	
	@Test
	public void testTroisTroisBasique() {
		pdj = pdj.charger(chemin + "TestTroisTroisBasique1");
		assertFalse(test.estValide(pdj, new Coordonnees(3,3), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(4,7), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(7,11), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(11,11), true, false, false));
		
		pdj = pdj.charger(chemin + "TestTroisTroisBasique2");		
		assertFalse(test.estValide(pdj, new Coordonnees(3,3), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(3,12), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(7,5), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(7,9), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(7,13), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(10,1), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(11,6), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(12,12), true, false, false));

		
		pdj = pdj.charger(chemin + "TestTroisTroisBasique3");
		assertFalse(test.estValide(pdj, new Coordonnees(3,3), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(3,5), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(3,10), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(8,1), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(9,5), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(9,10), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(11,6), true, false, false));
		
		pdj = pdj.charger(chemin + "TestTroisTroisBasique4");		
		assertFalse(test.estValide(pdj, new Coordonnees(1,1), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(1,11), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(5,10), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(6,3), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(8,10), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(10,5), true, false, false));
	}
	
	@Test
	public void testTroisTroisTrouSimple(){
		pdj = pdj.charger(chemin + "TestTroisTroisTrouSimple");
		assertFalse(test.estValide(pdj, new Coordonnees(1,2), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(3,10), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(3,7), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(6,1), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(7,9), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(10,3), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(10,6), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(10,11), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(12,5), true, false, false));
		
		pdj.charger(chemin + "TestTroisTroisTrouSimple2");
		assertFalse(test.estValide(pdj, new Coordonnees(2,2), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(3,7), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(3,12), true, false, false));
		
	}
	
	@Test
	public void testFauxTroisTrois(){
		pdj = pdj.charger(chemin + "TestFauxTroisTrois");
		assertTrue(test.estValide(pdj, new Coordonnees(4,5), true, false, false));
		assertTrue(test.estValide(pdj, new Coordonnees(9,2), true, false, false));
		assertTrue(test.estValide(pdj, new Coordonnees(12,8), true, false, false));
		assertTrue(test.estValide(pdj, new Coordonnees(7,11), true, false, false));
	}
	
	@Test
	public void testQuatreQuatre(){
		pdj = pdj.charger(chemin + "TestQuatreQuatreBasique1");
		assertFalse(test.estValide(pdj, new Coordonnees(4,3), false, true, false));
		assertFalse(test.estValide(pdj, new Coordonnees(5,10), false, true, false));
		assertFalse(test.estValide(pdj, new Coordonnees(10,3), false, true, false));
		assertFalse(test.estValide(pdj, new Coordonnees(11,11), false, true, false));
		
		pdj = pdj.charger(chemin + "TestQuatreQuatreBasique2");
		assertFalse(test.estValide(pdj, new Coordonnees(7,1), false, true, false));
		assertFalse(test.estValide(pdj, new Coordonnees(8,3), false, true, false));
		assertFalse(test.estValide(pdj, new Coordonnees(6,6), false, true, false));
		assertFalse(test.estValide(pdj, new Coordonnees(7,9), false, true, false));
	}
	
	@Test
	public void testSixSept(){
		pdj = pdj.charger(chemin + "TestSixSept");
		assertFalse(test.estValide(pdj, new Coordonnees(9,2), false, false, true));
		assertFalse(test.estValide(pdj, new Coordonnees(4,10), false, false, true));
		assertFalse(test.estValide(pdj, new Coordonnees(12,7), false, false, true));
		assertFalse(test.estValide(pdj, new Coordonnees(12,12), false, false, true));
	}
	
	@Test
	public void testQuatreTrois(){
		pdj = pdj.charger(chemin + "TestQuatreTrois");
		assertTrue(test.estValide(pdj, new Coordonnees(2,3), true, true, true));
		assertTrue(test.estValide(pdj, new Coordonnees(7,10), true, true, true));
		assertTrue(test.estValide(pdj, new Coordonnees(11,10), true, true, true));
		assertTrue(test.estValide(pdj, new Coordonnees(10,4), true, true, true));
	}
	
}
