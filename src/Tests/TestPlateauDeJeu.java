package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controleur.PlateauDeJeu;

public class TestPlateauDeJeu {

	private static String chemin = "Tests/TestsPlateauDeJeu/";
	@Test
	public void test() {
		
		// X: caseTaboo
		// O: caseJouable
		// N: casePionNoir
		// B: CasePionBlanc
		
		
		PlateauDeJeu pdj = new PlateauDeJeu();
		comparer(pdj, chemin + "PlateauVide" );
		
			
	}
	
	public static void comparer(PlateauDeJeu pdj, String test ){
		PlateauDeJeu pdjtest = pdj.charger(test);
		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
					assertEquals(pdj.getPlateau()[i][j], pdjtest.getPlateau()[i][j]); 
			}
		
		}
	}
	

}
