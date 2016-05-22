package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Controleur.*;
import Enum.*;
import Joueur.IAMoyenne;

public class TestIAMoyenne {

	private static String chemin = "Tests/TestsIAMoyenne/";
	Moteur m;
	PlateauDeJeu pdj;
	IAMoyenne ia;
	
	@Before
	public void init() {
		m = new Moteur(TypeJoueur.IAMoyenne, TypeJoueur.Humain);
		pdj = m.getRenjou().getPlateauDeJeu();
		ia = (IAMoyenne) m.getRenjou().getJoueurs()[0];
		
		// plage de test de IAMoyenne entre 600 et 700
		// plage detestEstBlancOuNoir 601 610
		// plage detestPartieFinie 611 620
		// plage detestEstJouableSimple 621 630
		// plage detestEstJouable 631 640
		m.setPlageTrace(650,700);

	}
	
	@Test
	public void testEstBlancOuNoir() {
		m.printTrace(601, "debut testEstBlancOuNoir");
		
		m.printTrace(605, pdj.toString()); 
		assertFalse(ia.EstBlancOuNoir(pdj,new Coordonnees(0,0)));
		assertFalse(ia.EstBlancOuNoir(pdj,new Coordonnees(7,7)));
		pdj.ajouter(new Coordonnees(0,0), TypeCase.PionBlanc);
		pdj.ajouter(new Coordonnees(7,7), TypeCase.PionNoir);
		assertTrue(ia.EstBlancOuNoir(pdj,new Coordonnees(0,0)));
		assertTrue(ia.EstBlancOuNoir(pdj,new Coordonnees(7,7)));	
		m.printTrace(601, "fin testEstBlancOuNoir");
		
	}
	
	
	@Test
	public void testPartieFinie() {
		m.printTrace(611, "debut testPartieFinie");

		
		pdj = pdj.charger(chemin + "PlateauGagnantNominal");
		m.printTrace(615, pdj.toString()); 
		//cas nominal, pas de test en bordure, pas d'alignement de plus de 5, pas d'alignement de moins de 5
		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				Coordonnees c = new Coordonnees(i,j);
				if(ia.EstBlancOuNoir(pdj,c)){
					assertTrue(ia.PartieFinie(pdj,c));
				}
			}
		}
		
		
		
		//Bordures
		pdj = pdj.charger(chemin + "PlateauGagnantBordures");
		m.printTrace(615, pdj.toString()); 

		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				Coordonnees c = new Coordonnees(i,j);
				if(ia.EstBlancOuNoir(pdj,c)){
					m.printTrace(615, "test Partie Finie en " + c);
					assertTrue(ia.PartieFinie(pdj,c));
				}
			}
		}
		
		//Alignements de moins de 5
		pdj = pdj.charger(chemin + "PlateauPerdantBordures");
		m.printTrace(615, pdj.toString()); 

		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				Coordonnees c = new Coordonnees(i,j);
				if(ia.EstBlancOuNoir(pdj,c)){
					m.printTrace(615, "test Partie Finie en " + c);
					assertFalse(ia.PartieFinie(pdj,c));
				}
			}
		}
		
		//Alignements de plus de 5 considéré comme gagnant (sujet a changement)
		pdj = pdj.charger(chemin + "PlateauGagnant5Plus");
		m.printTrace(615, pdj.toString()); 

		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				Coordonnees c = new Coordonnees(i,j);
				if(ia.EstBlancOuNoir(pdj,c)){
					m.printTrace(615, "test Partie Finie en " + c);
					assertTrue(ia.PartieFinie(pdj,c));
				}
			}
		}
		
		m.printTrace(611, "fin testPartieFinie");

		
	}


	
	@Test
	public void testEstJouableSimple() {
		m.printTrace(621, "debut testEstJouableSimple");

		Coordonnees centre = new Coordonnees(5,5);
		pdj.ajouter(centre, TypeCase.PionNoir);
		m.printTrace(625, pdj.toString());
		
		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				if(i == centre.getLigne() && j == centre.getColonne()){
					assertFalse(ia.EstJouable(pdj, new Coordonnees(i,j)));
				}
				else if((i <= centre.getLigne() +2 && i >= centre.getLigne() -2) && (j <= centre.getColonne() +2 && j >= centre.getColonne() -2)){
					assertTrue(ia.EstJouable(pdj, new Coordonnees(i,j)));
				}
				else{
					assertFalse(ia.EstJouable(pdj, new Coordonnees(i,j)));
				}
				
			}
		}
	
		m.printTrace(621, "fin testEstJouableSimple");

	}
	
	
	
	@Test
	public void testEstJouable() {		
		m.printTrace(631, "debut testEstJouable");

		pdj.ajouter(new Coordonnees(7,7), TypeCase.Injouable);
		Coordonnees centre = new Coordonnees(0,0);
		pdj.ajouter(centre, TypeCase.PionBlanc);
		m.printTrace(635, pdj.toString());
		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				if(Esta2cases(new Coordonnees(i,j), centre)){
					m.printTrace(635, "true? i= "+i+" j= "+j);
					assertTrue(ia.EstJouable(pdj, new Coordonnees(i,j)));
					pdj.enlever(new Coordonnees(i,j));
				}
				else{
					m.printTrace(635, "false? i= "+i+" j= "+j);

					assertFalse(ia.EstJouable(pdj, new Coordonnees(i,j)));
				}
				
			}
		}
		m.printTrace(635, pdj.toString());

		pdj = pdj.charger(chemin + "PlateauVide");
		m.printTrace(635, pdj.toString());
		
		ArrayList<Coordonnees> l = new ArrayList<>();
		l.add(new Coordonnees(0,0));
		l.add(new Coordonnees(0,14));
		l.add(new Coordonnees(14,0));
		l.add(new Coordonnees(14,14));
		l.add(new Coordonnees(7,7));
		
		for(Coordonnees c : l)
			pdj.ajouter(c, TypeCase.PionBlanc);

		m.printTrace(635, pdj.toString());

		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				if(Esta2cases(l,new Coordonnees(i,j))){
					m.printTrace(635, "true? i= "+i+" j= "+j);
					assertTrue(ia.EstJouable(pdj, new Coordonnees(i,j)));
					pdj.enlever(new Coordonnees(i,j));
				}
				else{
					m.printTrace(635, "false? i= "+i+" j= "+j);

					assertFalse(ia.EstJouable(pdj, new Coordonnees(i,j)));
				}
				
			}
		}
		m.printTrace(635, pdj.toString());

		pdj = pdj.charger(chemin + "PlateauEstJouable");
		m.printTrace(639, pdj.toString());

		l.clear();
		//on rempli la liste avec les pions sur le plateau
		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				if(ia.EstBlancOuNoir(pdj, new Coordonnees(i,j)))
					l.add(new Coordonnees(i,j));
			}
		}
		m.printTrace(639, l.toString());

		//on test
		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				if(Esta2cases(l,new Coordonnees(i,j)) && !ia.EstBlancOuNoir(pdj, new Coordonnees(i,j))){
					m.printTrace(639, "true? i= "+i+" j= "+j);
					assertTrue(ia.EstJouable(pdj, new Coordonnees(i,j)));
					pdj.enlever(new Coordonnees(i,j));
				}
				else{
					m.printTrace(639, "false? i= "+i+" j= "+j);

					assertFalse(ia.EstJouable(pdj, new Coordonnees(i,j)));
				}
				
			}
		}
		m.printTrace(639, pdj.toString());
		//on retest
		TestPlateauDeJeu.comparer(pdj, chemin +  "PlateauEstJouableCorrection");
		
		m.printTrace(631, "fin testEstJouable");

	}
	
	public static boolean Esta2cases(ArrayList<Coordonnees> c1, Coordonnees c2){
		
		for(Coordonnees c : c1)
			if(Esta2cases(c,c2))
				return true;
		
		
		return false;
	}

	
	public static boolean Esta2cases(Coordonnees c1, Coordonnees c2){
	return 	(c1.getLigne() <= c2.getLigne() +2 && c1.getLigne() >= c2.getLigne() -2) && 
			(c1.getColonne() <= c2.getColonne() +2 && c1.getColonne() >= c2.getColonne() -2) &&
			(c1.getLigne() != c2.getLigne() || c1.getColonne() != c2.getColonne());
			
	}
	
	
	@Test
	public void testJouerProfondeur0(){
		m.printTrace(641, "debut testJouerProfondeur0");

		m.printTrace(645, pdj.toString());

		pdj = pdj.charger(chemin + "Plateau4NoirGagnant");
		m.printTrace(645, pdj.toString());
		m.setPlageTrace(640, 650);
		Coordonnees c = ia.Jouer(pdj);
		assertTrue(c.getLigne() == 5 && c.getColonne() == 1);
		
		pdj.ajouter(c, TypeCase.PionNoir);

		m.printTrace(641, "c= " + c);
		m.printTrace(645, pdj.toString());

		
		
		
		m.printTrace(641, "fin testJouerProfondeur0");
	}
	
	@Test 
	public void testJouerProfondeur2(){
		m.printTrace(651, "debut testJouerProfondeur2");

		m.printTrace(655, pdj.toString());

		pdj = pdj.charger(chemin + "Plateau3VideNoirGagnant");
		m.printTrace(655, pdj.toString());
		Coordonnees c = ia.Jouer(pdj);
		//assertTrue(c.getLigne() == 5 && c.getColonne() == 1);
		
		pdj.ajouter(c, TypeCase.PionNoir);

		m.printTrace(651, "c= " + c);
		m.printTrace(655, pdj.toString());

		
		
		
		m.printTrace(651, "fin testJouerProfondeur2");
	}

}
