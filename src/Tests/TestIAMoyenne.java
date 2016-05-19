package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controleur.Coordonnees;
import Controleur.Moteur;
import Controleur.PlateauDeJeu;
import Enum.TypeCase;
import Enum.TypeCouleur;
import Enum.TypeJoueur;
import Joueur.IA;
import Joueur.IAMoyenne;

public class TestIAMoyenne {

	private static String chemin = "Tests/PlateauDeJeu/";

	
	
	@Test
	public void test() {
		Moteur m = new Moteur(TypeJoueur.IAMoyenne, TypeJoueur.Humain);
		PlateauDeJeu pdj = m.getRenjou().getPlateauDeJeu();
		IAMoyenne ia = (IAMoyenne) m.getRenjou().getJoueurs()[0];
		m.setNiveauTrace(10);

		Coordonnees centre = new Coordonnees(5,5);
		pdj.ajouter(centre, TypeCase.PionNoir);
		
		for(int i=centre.getLigne() -2;i<centre.getLigne()+2;i++){
			for(int j=centre.getColonne() -2;j<centre.getColonne() +2;j++){
				assertTrue(ia.EstJouable(pdj, new Coordonnees(i,j)));
			}
		}
		
		
		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				if(i == centre.getLigne() && j == centre.getColonne()){
					// spec a expliciter
					//assertFalse(ia.caseJouable(pdj, new Coordonnees(i,j), 1));
				}
				else if((i <= centre.getLigne() +2 && i >= centre.getLigne() -2) && (j <= centre.getColonne() +2 && j >= centre.getColonne() -2)){
					assertTrue(ia.EstJouable(pdj, new Coordonnees(i,j)));
				}
				else{
					assertFalse(ia.EstJouable(pdj, new Coordonnees(i,j)));
				}
				
			}
		}


	}

}
