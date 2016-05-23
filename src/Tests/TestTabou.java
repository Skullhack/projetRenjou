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
	private static String chemin = "Tests/TestsTabous/";

	@Before
	public void init() {
		m = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		pdj = m.getRenjou().getPlateauDeJeu();
		//plage de trace 700 � 800
		//Log.setPlage(700, 800);
	}
	
	@Test
	public void testTroisTroisGauche() {
		pdj = pdj.charger(chemin + "TestTroisTroisGauche");
		Tabou test = new Tabou(null, "");
		
//		InfosAlignement infoGauche = new InfosAlignement(r, c, TypeDirection.Gauche);
//		InfosAlignement infoDroite = new InfosAlignement(r, c, TypeDirection.Droite);
//		InfosAlignement infoHaut = new InfosAlignement(r, c, TypeDirection.Haut);
//		InfosAlignement infoBas = new InfosAlignement(r, c, TypeDirection.Bas);
//		InfosAlignement infoDiagonaleHautGauche = new InfosAlignement(r, c, TypeDirection.DiagonaleHautGauche);
//		InfosAlignement infoDiagonaleHautDroite = new InfosAlignement(r, c, TypeDirection.DiagonaleHautDroite);
//		InfosAlignement infoDiagonaleBasGauche = new InfosAlignement(r, c, TypeDirection.DiagonaleBasGauche);
//		InfosAlignement infoDiagonaleBasDroite = new InfosAlignement(r, c, TypeDirection.DiagonaleBasDroite);
		
		
		assertFalse(test.estValide(pdj, new Coordonnees(3,11), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(11,11), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(3,3), true, false, false));
		assertFalse(test.estValide(pdj, new Coordonnees(11,3), true, false, false));
		
	}

}
