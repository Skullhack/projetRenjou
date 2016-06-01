package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Enum.TypeCase;
import Utilitaire.Coordonnees;
import Utilitaire.PlateauDeJeu;

public class TestPlateauDeJeu {

	private static String chemin = "Tests/TestsPlateauDeJeu/";

	@Test
	public void test() {

		// X: caseTaboo
		// O: caseJouable
		// N: casePionNoir
		// B: CasePionBlanc

		PlateauDeJeu pdj = new PlateauDeJeu();
		comparer(pdj, chemin + "PlateauVide");

	}

	public static void comparer(PlateauDeJeu pdj, String test) {
		PlateauDeJeu pdjtest = pdj.charger(test);
		for (int i = 0; i < pdj.getLignes(); i++) {
			for (int j = 0; j < pdj.getColonnes(); j++) {
				assertEquals(pdj.getPlateau()[i][j], pdjtest.getPlateau()[i][j]);
			}

		}
	}

	@Test
	public void simulationPremierTourNoirEtPremierTourBlanc() {

		PlateauDeJeu plateauJeu = new PlateauDeJeu();
		plateauJeu.ajouter(new Coordonnees(7, 7), TypeCase.PionNoir);
		plateauJeu.initPlateauDeuxiemeCoup();
		comparer(plateauJeu, chemin + "PlateauApresPremierCoupNoir");

		plateauJeu.ajouter(new Coordonnees(8, 8), TypeCase.PionBlanc);
		plateauJeu.supprimerCasesInjouables();
		comparer(plateauJeu, chemin + "PlateauApresPremierCoupNoirEtBlanc");
	}

	@Test
	public void retirerPionNoir() {
		PlateauDeJeu plateauJeu = new PlateauDeJeu();
		plateauJeu.ajouter(new Coordonnees(7, 7), TypeCase.PionNoir);
		plateauJeu.initPlateauDeuxiemeCoup();
		comparer(plateauJeu, chemin + "PlateauApresPremierCoupNoir");

		plateauJeu.ajouter(new Coordonnees(8, 8), TypeCase.PionBlanc);
		plateauJeu.supprimerCasesInjouables();
		comparer(plateauJeu, chemin + "PlateauApresPremierCoupNoirEtBlanc");

		plateauJeu.ajouter(new Coordonnees(14, 14), TypeCase.PionNoir);
		plateauJeu.enlever(new Coordonnees(14, 14));
		
		comparer(plateauJeu, chemin + "PlateauApresPremierCoupNoirEtBlanc");
	}
	
	@Test
	public void retirerPionBlanc() {
		PlateauDeJeu plateauJeu = new PlateauDeJeu();
		plateauJeu.ajouter(new Coordonnees(7, 7), TypeCase.PionNoir);
		plateauJeu.initPlateauDeuxiemeCoup();
		comparer(plateauJeu, chemin + "PlateauApresPremierCoupNoir");

		plateauJeu.ajouter(new Coordonnees(8, 8), TypeCase.PionBlanc);
		plateauJeu.supprimerCasesInjouables();
		comparer(plateauJeu, chemin + "PlateauApresPremierCoupNoirEtBlanc");

		plateauJeu.ajouter(new Coordonnees(14, 14), TypeCase.PionNoir);
		plateauJeu.enlever(new Coordonnees(14, 14));
		
		plateauJeu.ajouter(new Coordonnees(4, 4), TypeCase.PionBlanc);
		plateauJeu.enlever(new Coordonnees(4, 4));
		
		comparer(plateauJeu, chemin + "PlateauApresPremierCoupNoirEtBlanc");
	}

}
