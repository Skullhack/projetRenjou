package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controleur.Moteur;
import Enum.EtatPartie;
import Enum.TypeJoueur;

public class TestsIAVsIA {

	int nbPartie = 1000;
	
	@Test
	public void FacileVsFacile() {
		System.out.println("test IA Facile contre Facile");
		jouerIAs(TypeJoueur.IAFacile, TypeJoueur.IAFacile );
	}
	
	
	@Test
	public void FacileVsMoyenne() {
		System.out.println("test IA Facile contre Moyenne");
		jouerIAs(TypeJoueur.IAFacile, TypeJoueur.IAMoyenne );
	}
	
	@Test
	public void MoyenneVsFacile() {
		System.out.println("test Moyenne contre Facile");
		jouerIAs(TypeJoueur.IAMoyenne, TypeJoueur.IAFacile);
	}
	
	
	@Test
	public void MoyenneVsMoyenne() {
		System.out.println("test Moyenne contre Facile");
		jouerIAs(TypeJoueur.IAMoyenne, TypeJoueur.IAMoyenne);
	}
	
	public void jouerIAs(TypeJoueur t1, TypeJoueur t2){
		int nbNoirGagne = 0;
		int nbBlancGagne = 0;
		int nbBlancGagneTabous = 0;
		int nbNull = 0;
		EtatPartie etatPartie;
		
		for(int i = 0; i < nbPartie ; i++){
			if(i % 100 == 0){
				System.out.println("nbPartie : " + i);
			}
			Moteur m = new Moteur(t1, t2);
			etatPartie = m.faireJouerIAVsIAPourTest();
			switch (etatPartie) {
			case BlancGagne: 
				nbBlancGagne ++;
				break;
			case BlancGagneParTabou:
				nbBlancGagneTabous ++;
				break;
			case NoirGagne:
				nbNoirGagne ++;
				break;
			case PartieNulle:
				nbNull ++;
				break;
			default : 
				System.out.println("erreur une partie est en cours ou rien");
				break;
			}
			
		}
		int nbBlancGagneTotal = nbBlancGagne + nbBlancGagneTabous;
		System.out.println("il y a eu "+ nbPartie + " parties :");
		System.out.println("Partie nulle : "+ nbNull);
		System.out.println("Blanc a gagné : "+ nbBlancGagneTotal + " dont : " + nbBlancGagneTabous + " par tabous du Noir");
		System.out.println("Noir a gagné : "+ nbNoirGagne);
		
	}
	

}
