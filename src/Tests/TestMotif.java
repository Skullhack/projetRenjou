package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Enum.TypeCouleur;
import Utilitaire.Coordonnees;
import Utilitaire.Log;
import Utilitaire.Motif;
import Utilitaire.PlateauDeJeu;

public class TestMotif {

	private static String chemin = "Tests/TestsMotif/";
	PlateauDeJeu pdj;
	
	@Before
	public void init() {
		pdj = new PlateauDeJeu();
	}
	
	@Test
	public void TroisHorizontal() {
		Log.setPlage(500, 502);
		pdj = pdj.charger(chemin + "TroisHorizontal");
		Log.print(501, pdj.toString());
		Motif m;
		m = new Motif(pdj, new Coordonnees(0,2));
		assertTrue(m.estTroisHorizontaleLibreLibre(TypeCouleur.Noir));
		m = new Motif(pdj, new Coordonnees(1,2));
		assertTrue(m.estTroisHorizontaleLibreLibre(TypeCouleur.Noir));
		m = new Motif(pdj, new Coordonnees(2,2));
		assertTrue(m.estTroisHorizontaleLibre(TypeCouleur.Noir));
		m = new Motif(pdj, new Coordonnees(3,2));
		assertTrue(m.estTroisHorizontaleLibre(TypeCouleur.Noir));
		m = new Motif(pdj, new Coordonnees(4,2));
		assertTrue(m.estTroisHorizontaleLibreLibre(TypeCouleur.Noir));
		m = new Motif(pdj, new Coordonnees(5,2));
		assertTrue(m.estTroisHorizontaleLibre(TypeCouleur.Noir));
		m = new Motif(pdj, new Coordonnees(6,2));
		assertTrue(m.estTroisHorizontale(TypeCouleur.Noir));
		m = new Motif(pdj, new Coordonnees(7,2));
		assertTrue(m.estTroisHorizontaleLibre(TypeCouleur.Noir));
		
		
	}

}
