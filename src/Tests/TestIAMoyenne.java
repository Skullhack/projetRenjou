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
		m.setNiveauTrace(0);

	}
	
	@Test
	public void testEstBlancOuNoir() {
		
		m.printTrace(5, pdj.toString()); 
		assertFalse(ia.EstBlancOuNoir(pdj,new Coordonnees(0,0)));
		assertFalse(ia.EstBlancOuNoir(pdj,new Coordonnees(7,7)));
		pdj.ajouter(new Coordonnees(0,0), TypeCase.PionBlanc);
		pdj.ajouter(new Coordonnees(7,7), TypeCase.PionNoir);
		assertTrue(ia.EstBlancOuNoir(pdj,new Coordonnees(0,0)));
		assertTrue(ia.EstBlancOuNoir(pdj,new Coordonnees(7,7)));	
		
	}
	
	
	@Test
	public void testPartieFinie() {
		pdj = pdj.charger(chemin + "PlateauGagnantNominal");
		m.printTrace(5, pdj.toString()); 
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
		m.printTrace(5, pdj.toString()); 

		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				Coordonnees c = new Coordonnees(i,j);
				if(ia.EstBlancOuNoir(pdj,c)){
					m.printTrace(66, "test Partie Finie en " + c);
					assertTrue(ia.PartieFinie(pdj,c));
				}
			}
		}
		
		//Alignements de moins de 5
		pdj = pdj.charger(chemin + "PlateauPerdantBordures");
		m.printTrace(66, pdj.toString()); 

		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				Coordonnees c = new Coordonnees(i,j);
				if(ia.EstBlancOuNoir(pdj,c)){
					m.printTrace(66, "test Partie Finie en " + c);
					assertFalse(ia.PartieFinie(pdj,c));
				}
			}
		}
		
		//Alignements de plus de 5 considéré comme gagnant (sujet a changement)
		pdj = pdj.charger(chemin + "PlateauGagnant5Plus");
		m.printTrace(66, pdj.toString()); 

		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				Coordonnees c = new Coordonnees(i,j);
				if(ia.EstBlancOuNoir(pdj,c)){
					m.printTrace(66, "test Partie Finie en " + c);
					assertTrue(ia.PartieFinie(pdj,c));
				}
			}
		}
		
		
	}


	
	@Test
	public void testEstJouableSimple() {
		
		Coordonnees centre = new Coordonnees(5,5);
		pdj.ajouter(centre, TypeCase.PionNoir);
		m.printTrace(66, pdj.toString());
		
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
	
	
	}
	
	
	
	@Test
	public void testEstJouable() {		
		
		pdj.ajouter(new Coordonnees(7,7), TypeCase.Injouable);
		Coordonnees centre = new Coordonnees(0,0);
		pdj.ajouter(centre, TypeCase.PionBlanc);
		m.printTrace(66, pdj.toString());
		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				if(Esta2cases(new Coordonnees(i,j), centre)){
					m.printTrace(66, "true? i= "+i+" j= "+j);
					assertTrue(ia.EstJouable(pdj, new Coordonnees(i,j)));
					pdj.enlever(new Coordonnees(i,j));
				}
				else{
					m.printTrace(66, "false? i= "+i+" j= "+j);

					assertFalse(ia.EstJouable(pdj, new Coordonnees(i,j)));
				}
				
			}
		}
		m.printTrace(66, pdj.toString());

		pdj = pdj.charger(chemin + "PlateauVide");
		m.printTrace(66, pdj.toString());
		
		ArrayList<Coordonnees> l = new ArrayList<>();
		l.add(new Coordonnees(0,0));
		l.add(new Coordonnees(0,14));
		l.add(new Coordonnees(14,0));
		l.add(new Coordonnees(14,14));
		l.add(new Coordonnees(7,7));
		
		for(Coordonnees c : l)
			pdj.ajouter(c, TypeCase.PionBlanc);

		m.printTrace(66, pdj.toString());

		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				if(Esta2cases(l,new Coordonnees(i,j))){
					m.printTrace(66, "true? i= "+i+" j= "+j);
					assertTrue(ia.EstJouable(pdj, new Coordonnees(i,j)));
					pdj.enlever(new Coordonnees(i,j));
				}
				else{
					m.printTrace(66, "false? i= "+i+" j= "+j);

					assertFalse(ia.EstJouable(pdj, new Coordonnees(i,j)));
				}
				
			}
		}
		m.printTrace(66, pdj.toString());

		pdj = pdj.charger(chemin + "PlateauEstJouable");
		m.printTrace(66, pdj.toString());

		l.clear();
		//on rempli la liste avec les pions sur le plateau
		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				if(ia.EstBlancOuNoir(pdj, new Coordonnees(i,j)))
					l.add(new Coordonnees(i,j));
			}
		}
		m.printTrace(66, l.toString());

		//on test
		for(int i=0;i<pdj.getLignes();i++){
			for(int j=0;j<pdj.getColonnes();j++){
				if(Esta2cases(l,new Coordonnees(i,j)) && !ia.EstBlancOuNoir(pdj, new Coordonnees(i,j))){
					m.printTrace(66, "true? i= "+i+" j= "+j);
					assertTrue(ia.EstJouable(pdj, new Coordonnees(i,j)));
					pdj.enlever(new Coordonnees(i,j));
				}
				else{
					m.printTrace(66, "false? i= "+i+" j= "+j);

					assertFalse(ia.EstJouable(pdj, new Coordonnees(i,j)));
				}
				
			}
		}
		m.printTrace(66, pdj.toString());
		//on retest
		TestPlateauDeJeu.comparer(pdj, chemin +  "PlateauEstJouableCorrection");
		

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

}
