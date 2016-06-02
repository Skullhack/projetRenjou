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
import Utilitaire.Motif;
import Utilitaire.PlateauDeJeu;

public class TestInfosAlignement {
	
	Moteur m;
	PlateauDeJeu pdj;
	private static String chemin = "Tests/TestsInfosAlignement/";

	@Before
	public void init() {
		m = new Moteur(TypeJoueur.Humain, TypeJoueur.Humain);
		pdj = m.getRenjou().getPlateauDeJeu();
		//plage de trace 700 � 800
		Log.setPlage(720, 730);
	}
	
	
	@Test
	public void test3continuNoirVide() {
		Log.print(701, "debut test3continuNoirVide");
		pdj = pdj.charger(chemin + "troisVideNoir");
		Log.print(705, pdj.toString());
		InfosAlignement infos;
		for(TypeDirection d: TypeDirection.values()){
			infos = new InfosAlignement(pdj, new Coordonnees(7,7), d);
			assertTrue(infos.getNbPion(TypeCouleur.Blanc) == 0);
			assertTrue(infos.getNbPion(TypeCouleur.Noir) == 3);
			assertTrue(infos.getNbPionNonContinu(TypeCouleur.Blanc) == 0);
			assertTrue(infos.getNbPionNonContinu(TypeCouleur.Noir) == 0);

			assertFalse(infos.estLibre(TypeCouleur.Blanc));
			assertTrue(infos.estLibre(TypeCouleur.Noir));
			assertFalse(infos.estLibre2Cases(TypeCouleur.Blanc));
			assertTrue(infos.estLibre2Cases(TypeCouleur.Noir));
			
			Log.print(705, infos.toString());
		}
		
		Log.print(701, "fin test3continuNoirVide");

	}
	
	@Test
	public void test3continuNoirBloqueBlanc() {
		Log.print(701, "debut test3continuNoirBloqueBlanc");
		pdj = pdj.charger(chemin + "troisBloqueNoir");
		Log.print(705, pdj.toString());
		InfosAlignement infos;
		for(TypeDirection d: TypeDirection.values()){
			infos = new InfosAlignement(pdj, new Coordonnees(7,7), d);
			Log.print(705, infos.toString());
			
			assertTrue(infos.getNbPion(TypeCouleur.Blanc) == 0);
			assertTrue(infos.getNbPion(TypeCouleur.Noir) == 3);
			assertTrue(infos.getNbPionNonContinu(TypeCouleur.Blanc) == 0);
			assertTrue(infos.getNbPionNonContinu(TypeCouleur.Noir) == 0);
			assertFalse(infos.estLibre(TypeCouleur.Blanc));
			assertFalse(infos.estLibre(TypeCouleur.Noir));
			assertFalse(infos.estLibre2Cases(TypeCouleur.Blanc));
			assertFalse(infos.estLibre2Cases(TypeCouleur.Noir));
		}
		
		Log.print(701, "fin test3continuNoirBloqueBlanc");

	}

	@Test
	public void test3continuNoirBloqueBordure() {
		Log.print(701, "debut test3continuNoirBloqueBordure");
		pdj = pdj.charger(chemin + "troisBordureNoir");
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
			Log.print(705,infos[i].toString());
			
			assertTrue(infos[i].getNbPion(TypeCouleur.Blanc) == 0);
			assertTrue(infos[i].getNbPion(TypeCouleur.Noir) == 3);
			assertTrue(infos[i].getNbPionNonContinu(TypeCouleur.Blanc) == 0);
			assertTrue(infos[i].getNbPionNonContinu(TypeCouleur.Noir) == 0);
			assertFalse(infos[i].estLibre(TypeCouleur.Blanc));
			assertFalse(infos[i].estLibre(TypeCouleur.Noir));
			assertFalse(infos[i].estLibre2Cases(TypeCouleur.Blanc));
			assertFalse(infos[i].estLibre2Cases(TypeCouleur.Noir));
		}
		
		Log.print(701, "fin test3continuNoirBloqueBordure");

	}
	
	@Test
	public void test3NonContinuNoirVide() {
		
		Log.print(701, "debut test3NonContinuNoirVide");
		pdj = pdj.charger(chemin + "troisVideNonContinuNoir");
		Log.print(705, pdj.toString());
		InfosAlignement infos;
		for(TypeDirection d: TypeDirection.values()){
			infos = new InfosAlignement(pdj, new Coordonnees(7,7), d);
			Log.print(795, infos.toString());
			assertTrue(infos.getNbPion(TypeCouleur.Blanc) == 0);
			assertTrue(infos.getNbPion(TypeCouleur.Noir) == 2);
			assertTrue(infos.getNbPionNonContinu(TypeCouleur.Blanc) == 0);
			assertTrue(infos.getNbPionNonContinu(TypeCouleur.Noir) == 1);
			assertFalse(infos.estLibre(TypeCouleur.Blanc));
			assertTrue(infos.estLibre(TypeCouleur.Noir));
			assertFalse(infos.estLibre2Cases(TypeCouleur.Blanc));
			assertTrue(infos.estLibre2Cases(TypeCouleur.Noir));
		}
		
		Log.print(701, "fin test3NonContinuNoirVide");

	}
	
	@Test
	public void test2NonVideContinuNoir() {
		Log.print(701, "debut test2NonVideContinuNoir");
		pdj = pdj.charger(chemin + "deuxNonVideContinuNoir");
		Log.print(705, pdj.toString());
		InfosAlignement infos;
		for(TypeDirection d: TypeDirection.values()){
			infos = new InfosAlignement(pdj, new Coordonnees(7,7), d);
			assertTrue(infos.getNbPion(TypeCouleur.Blanc) == 0);
			assertTrue(infos.getNbPion(TypeCouleur.Noir) == 2);
			assertTrue(infos.getNbPionNonContinu(TypeCouleur.Blanc) == 0);
			assertTrue(infos.getNbPionNonContinu(TypeCouleur.Noir) == 0);
			assertFalse(infos.estLibre(TypeCouleur.Blanc));
			assertFalse(infos.estLibre(TypeCouleur.Noir));
			Log.print(705, infos.toString());
		}
		
		Log.print(701, "fin test2NonVideContinuNoir");

	}
	
	@Test
	public void test1VideContinuNoir() {
		Log.print(701, "debut test1VideContinuNoir");
		pdj = pdj.charger(chemin + "unVideContinuNoir");
		Log.print(705, pdj.toString());
		InfosAlignement infos;
		for(TypeDirection d: TypeDirection.values()){
			infos = new InfosAlignement(pdj, new Coordonnees(7,7), d);
			assertTrue(infos.getNbPion(TypeCouleur.Blanc) == 0);
			assertTrue(infos.getNbPion(TypeCouleur.Noir) == 1);
			assertTrue(infos.getNbPionNonContinu(TypeCouleur.Blanc) == 0);
			assertTrue(infos.getNbPionNonContinu(TypeCouleur.Noir) == 0);
			assertFalse(infos.estLibre(TypeCouleur.Blanc));
			assertTrue(infos.estLibre(TypeCouleur.Noir));
			Log.print(705, infos.toString());
		}
		
		Log.print(701, "fin test1VideContinuNoir");

	}
	
	@Test
	public void testtrois1CaseBordureNoir() {
		
		Log.print(701, "debut testtrois1CaseBordureNoir");
		pdj = pdj.charger(chemin + "trois1CaseBordureNoir");
		Log.print(705, pdj.toString());
		InfosAlignement[] infos = new InfosAlignement[12];
		Coordonnees c1 = new Coordonnees(4,4);
		Coordonnees c2 = new Coordonnees(4,10);
		Coordonnees c3 = new Coordonnees(10,4);
		Coordonnees c4 = new Coordonnees(10,10);
		
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
			Log.print(705, "i= " + i + infos[i].toString());
			
			assertTrue(infos[i].getNbPion(TypeCouleur.Blanc) == 0);
			assertTrue(infos[i].getNbPion(TypeCouleur.Noir) == 3);
			assertTrue(infos[i].getNbPionNonContinu(TypeCouleur.Blanc) == 0);
			assertTrue(infos[i].getNbPionNonContinu(TypeCouleur.Noir) == 0);
			assertFalse(infos[i].estLibre(TypeCouleur.Blanc));
			assertTrue(infos[i].estLibre(TypeCouleur.Noir));
			assertFalse(infos[i].estLibre2Cases(TypeCouleur.Blanc));
			assertFalse(infos[i].estLibre2Cases(TypeCouleur.Noir));
		}
		
		Log.print(701, "fin test1VideContinuNoir");

	}
	
	
	
	@Test
	public void main() {
		Log.print(721, "debut main");
		pdj = pdj.charger(chemin + "TestFauxTroisTrois2");
		Log.print(725, pdj.toString());
		InfosAlignement infos = new InfosAlignement(pdj, new Coordonnees(9,5), TypeDirection.Haut);
		Motif m = new Motif(pdj, new Coordonnees(9,5));
		Log.print(725, m.toString());
		
		Log.print(725, ""+m.estTroisFoisTroisLibreLibre(TypeCouleur.Noir) );
		
		Log.print(721, "fin main");

	}
	
}
