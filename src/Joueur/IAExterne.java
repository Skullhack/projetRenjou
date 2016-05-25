package Joueur;

import Controleur.Moteur;
import Enum.TypeCase;
import Enum.TypeCouleur;
import Enum.TypeJoueur;
import Utilitaire.Coordonnees;
import Utilitaire.PlateauDeJeu;

public class IAExterne extends IA {
	
	IAFacile ia;
	
	public IAExterne(TypeJoueur type, int nbPion, TypeCouleur couleurJoueur) {
		super(type, nbPion, couleurJoueur);
		nbLigne = m.getRenjou().getPlateauDeJeu().getLignes();
		nbColonne = m.getRenjou().getPlateauDeJeu().getColonnes();
		ia = new IAFacile(type, nbPion, couleurJoueur); // a remplacer par la classe correcpondante a l'ia a implementer
	}
	
	public Coordonnees Jouer(PlateauDeJeu p){
		int[][] plateau = new int[15][15];
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				if(p.getPlateau()[i][j] == TypeCase.PionBlanc){
					plateau[i][j] = 2;
				}else if(p.getPlateau()[i][j] == TypeCase.PionNoir){
					plateau[i][j] = 1;
				}else{
					plateau[i][j] = 0;
				}
			}
		}
		int couleur = 1;
		if(getCouleur() == TypeCouleur.Blanc){
			couleur =2;
		}
		
		return new Coordonnees(ia.play(plateau, couleur, true,true,true));
	}
}
