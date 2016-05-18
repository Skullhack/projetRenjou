package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controleur.Moteur;
import Enum.TypeJoueur;
import Joueur.IAFacile;
import Joueur.Joueur;

public class TestIAFacile {

	Moteur m;
	int nbJoueur;
	
	@Before
	public void init() {
		m = new Moteur(nbJoueur);
		Joueur iaFacile = new IAFacile(TypeJoueur.IAFacile, 60, m);
	}
	
	
	@Test
	public void test() {
		
		
		
		fail("Not yet implemented");
	}

}
