package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controleur.*;
import Enum.*;
import Joueur.*;
import Utilitaire.Coordonnees;
import Utilitaire.InfosAlignement;
import Utilitaire.Log;
import Utilitaire.PlateauDeJeu;

public class TestInfosAlignement {
	
	Moteur m;
	PlateauDeJeu pdj;
	private static String chemin = "Tests/TestsInfosAlignement/";

	@Before
	public void init() {
		m = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		pdj = m.getRenjou().getPlateauDeJeu();
		//plage de trace 700 à 800
		Log.setPlage(700, 800);
	}
	
	
	@Test
	public void test3VideNoirVide() {
		Log.print(701, "debut test3VideNoirVide");
		pdj = pdj.charger(chemin + "PlateauInfosTabou3VideNoir");
		Log.print(705, pdj.toString());

		InfosAlignement gauche = new InfosAlignement(pdj, new Coordonnees(7,7), TypeDirection.Gauche);
		Log.print(705, gauche.toString());
		Log.print(701, "fin test3VideNoirVide");

	}

}
