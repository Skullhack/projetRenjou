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
		Log.setPlage(790, 800);
	}
	
	
	@Test
	public void test3continuNoirVide() {
		Log.print(701, "debut test3continuNoirVide");
		pdj = pdj.charger(chemin + "PlateauInfosAlignement3VideNoir");
		Log.print(705, pdj.toString());
		InfosAlignement infos;
		for(TypeDirection d: TypeDirection.values()){
			infos = new InfosAlignement(pdj, new Coordonnees(7,7), d);
			assertTrue(infos.getNbBlanc() == 0);
			assertTrue(infos.getNbNoir() == 3);
			assertTrue(infos.estContinuBlanc());
			assertTrue(infos.estContinuNoir());
			assertFalse(infos.estLibreBlanc());
			assertTrue(infos.estLibreNoir());
			Log.print(705, infos.toString());
		}
		
		Log.print(701, "fin test3continuNoirVide");

	}
	
	@Test
	public void test3continuNoirBloqueBlanc() {
		Log.print(701, "debut test3continuNoirBloqueBlanc");
		pdj = pdj.charger(chemin + "PlateauInfosAlignement3BloqueNoir");
		Log.print(705, pdj.toString());
		InfosAlignement infos;
		for(TypeDirection d: TypeDirection.values()){
			infos = new InfosAlignement(pdj, new Coordonnees(7,7), d);
			Log.print(705, infos.toString());
			
			assertTrue(infos.getNbBlanc() == 0);
			assertTrue(infos.getNbNoir() == 3);
			assertTrue(infos.estContinuBlanc());
			assertTrue(infos.estContinuNoir());
			assertFalse(infos.estLibreBlanc());
			assertFalse(infos.estLibreNoir());
		}
		
		Log.print(701, "fin test3continuNoirBloqueBlanc");

	}

	@Test
	public void test3continuNoirBloqueBordure() {
		Log.print(701, "debut test3continuNoirBloqueBordure");
		pdj = pdj.charger(chemin + "PlateauInfosAlignement3BordureNoir");
		Log.print(705, pdj.toString());
		InfosAlignement[] infos = new InfosAlignement[12];
		Coordonnees c1 = new Coordonnees(3,3);
		Coordonnees c2 = new Coordonnees(3,11);
		Coordonnees c3 = new Coordonnees(11,3);
		Coordonnees c4 = new Coordonnees(11,11);
		
		infos[0] = new InfosAlignement(pdj, c1, TypeDirection.Gauche);
		infos[1] = new InfosAlignement(pdj, c1, TypeDirection.DiagonaleHautGauche);
		infos[2] = new InfosAlignement(pdj, c1, TypeDirection.Haut);
		
		infos[3] = new InfosAlignement(pdj, c2, TypeDirection.Haut);
		infos[4] = new InfosAlignement(pdj, c2, TypeDirection.DiagonaleHautDroite);
		infos[5] = new InfosAlignement(pdj, c2, TypeDirection.Droite);
		
		infos[6] = new InfosAlignement(pdj, c3, TypeDirection.Gauche);
		infos[7] = new InfosAlignement(pdj, c3, TypeDirection.DiagonaleBasGauche);
		infos[8] = new InfosAlignement(pdj, c3, TypeDirection.Bas);
		
		infos[9] = new InfosAlignement(pdj, c4, TypeDirection.Droite);
		infos[10] = new InfosAlignement(pdj, c4, TypeDirection.DiagonaleBasDroite);
		infos[11] = new InfosAlignement(pdj, c4, TypeDirection.Bas);
		
		
		
		for(int i=0;i<infos.length;i++){
			Log.print(705, infos[i].toString());
			
			assertTrue(infos[i].getNbBlanc() == 0);
			assertTrue(infos[i].getNbNoir() == 3);
			assertTrue(infos[i].estContinuBlanc());
			assertTrue(infos[i].estContinuNoir());
			assertFalse(infos[i].estLibreBlanc());
			assertFalse(infos[i].estLibreNoir());
		}
		
		Log.print(701, "fin test3continuNoirBloqueBordure");

	}
	
	@Test
	public void test3NonContinuNoirVide() {
		Log.print(701, "debut test3NonContinuNoirVide");
		pdj = pdj.charger(chemin + "PlateauInfosAlignement3VideNonContinuNoir");
		Log.print(705, pdj.toString());
		InfosAlignement infos;
		for(TypeDirection d: TypeDirection.values()){
			infos = new InfosAlignement(pdj, new Coordonnees(7,7), d);
			assertTrue(infos.getNbBlanc() == 0);
			assertTrue(infos.getNbNoir() == 3);
			assertTrue(infos.estContinuBlanc());
			assertFalse(infos.estContinuNoir());
			assertFalse(infos.estLibreBlanc());
			assertTrue(infos.estLibreNoir());
			Log.print(705, infos.toString());
		}
		
		Log.print(701, "fin test3NonContinuNoirVide");

	}
	
	@Test
	public void test2NonVideContinuNoir() {
		Log.print(701, "debut test2NonVideContinuNoir");
		pdj = pdj.charger(chemin + "PlateauInfosAlignement2NonVideContinuNoir");
		Log.print(705, pdj.toString());
		InfosAlignement infos;
		for(TypeDirection d: TypeDirection.values()){
			infos = new InfosAlignement(pdj, new Coordonnees(7,7), d);
			assertTrue(infos.getNbBlanc() == 0);
			assertTrue(infos.getNbNoir() == 2);
			assertTrue(infos.estContinuBlanc());
			assertTrue(infos.estContinuNoir());
			assertFalse(infos.estLibreBlanc());
			assertFalse(infos.estLibreNoir());
			Log.print(705, infos.toString());
		}
		
		Log.print(701, "fin test2NonVideContinuNoir");

	}
	
	@Test
	public void test1VideContinuNoir() {
		Log.print(701, "debut test1VideContinuNoir");
		pdj = pdj.charger(chemin + "PlateauInfosAlignement1VideContinuNoir");
		Log.print(705, pdj.toString());
		InfosAlignement infos;
		for(TypeDirection d: TypeDirection.values()){
			infos = new InfosAlignement(pdj, new Coordonnees(7,7), d);
			assertTrue(infos.getNbBlanc() == 0);
			assertTrue(infos.getNbNoir() == 1);
			assertTrue(infos.estContinuBlanc());
			assertTrue(infos.estContinuNoir());
			assertFalse(infos.estLibreBlanc());
			assertTrue(infos.estLibreNoir());
			Log.print(705, infos.toString());
		}
		
		Log.print(701, "fin test1VideContinuNoir");

	}
}
