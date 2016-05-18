package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controleur.Moteur;
import Enum.TypeCouleur;
import Enum.TypeJoueur;
import Joueur.IA;
import Joueur.IAMoyenne;

public class TestIAMoyenne {

	private static String chemin = "src/Tests/PlateauDeJeu/";

	@Test
	public void test() {
		Moteur m = new Moteur(2);
		//IA ia1 = new IA(m, TypeJoueur.IAMoyenne, 60, TypeCouleur.Blanc);
		//IA ia2 = new IA(m, TypeJoueur.IAMoyenne, 60, TypeCouleur.Blanc);
		
		//ia1.afficherTabHeuristique();

		//ia.afficherTabHeuristique();
	}

}
