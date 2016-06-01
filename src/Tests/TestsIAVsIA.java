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
		int nbNoirGagne = 0;
		int nbBlancGagne = 0;
		int nbBlancGagneTabous = 0;
		int nbNull = 0;
		EtatPartie etatPartie;
		
		for(int i = 0; i < nbPartie ; i++){
			if(i % 100 == 0){
				System.out.println("nbPartie : " + i);
			}
			Moteur m = new Moteur(TypeJoueur.IAFacile, TypeJoueur.IAFacile);
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
		assertTrue((nbNull > nbBlancGagne) && (nbNull > nbNoirGagne));
		
	}
	
	

}
