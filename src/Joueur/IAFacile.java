/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Joueur;

import java.util.ArrayList;
import java.util.Observable;

import Controleur.*;
import Enum.*;

public class IAFacile extends IA {


	public IAFacile(TypeJoueur type, int nbPion, Moteur m, TypeCouleur c) {
		super();
		this.m = m;
		this.type = type;
		this.nbPion = nbPion;
		this.couleur = c;
		nbLigne = m.getRenjou().getPlateauDeJeu().getLignes();
		nbColonne = m.getRenjou().getPlateauDeJeu().getColonnes();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(m.getRenjou().getJoueurs()[m.getRenjou().getJoueurCourant()] == this){
			Coordonnees p = jouer(m.getRenjou().getPlateauDeJeu());
			m.operationJouer(p, type);
	
		}

	}

	public Coordonnees jouer(PlateauDeJeu plateau) {
		
		Coordonnees c = estCoupGagnant(plateau);
		if(c.getLigne() == -1){
			c = empecherCoupGagnant(plateau);
			if(c.getLigne() == -1){
				c = pointRandom();
			}
		}
		
		return c;
	}

	private Coordonnees estCoupGagnant(PlateauDeJeu plateau){
		
		TypeCase typeCase =  TypeCase.PionBlanc;
		switch(couleur){
			case Blanc :
				typeCase = TypeCase.PionBlanc;
				break;
			case Noir :
				typeCase = TypeCase.PionNoir;
				break;
			default :
				break;
		}
		
		Coordonnees c = new Coordonnees(-1,-1);
		// parcour du tableau 
		for(int i=0; i<nbLigne; i++){
			for(int j=0; j<nbColonne; j++){
				c = quatresAlignes(typeCase, new Coordonnees(i,j), plateau);
				if(c.getLigne() != -1){
					break;
				}
			}
			
		}
		
		return c;
	}
	
	private Coordonnees empecherCoupGagnant(PlateauDeJeu plateau){
		
		TypeCase typeCase =  TypeCase.PionBlanc;
		switch(couleur){
			case Blanc :
				typeCase = TypeCase.PionNoir;
				break;
			case Noir :
				typeCase = TypeCase.PionBlanc;
				break;
			default :
				break;
		}
		Coordonnees c = new Coordonnees(-1,-1);
		// parcour du tableau 
		for(int i=0; i<nbLigne; i++){
			for(int j=0; j<nbColonne; j++){
				c = troisAlignes(typeCase, new Coordonnees(i,j), plateau);
				if(c.getLigne() != -1){
					break;
				}
			}
			
		}

		return c;		
	}
	
	//a reprendre
	private Coordonnees quatresAlignes(TypeCase typeCase, Coordonnees p, PlateauDeJeu plateau){
		
		Coordonnees c = new Coordonnees(-1,-1);
		
		if(troisAlignesDiagonalBasDroite(typeCase, p, plateau)){
			Coordonnees pPrime = new Coordonnees(p.getLigne()+1, p.getColonne()+1);
			if(troisAlignesDiagonalBasDroite(typeCase, pPrime, plateau)){
				c = coordonneesDunAlignementATroisPoints(p, TypeDirection.DiagonaleBasDroite);
				if(c.getColonne() == -1){
					c = coordonneesDunAlignementATroisPoints(p, TypeDirection.DiagonaleBasDroite);
					if(c.getLigne() != -1){
						return c;
					}
				}
			}
		}

		
		
		if(troisAlignesDiagonalBasGauche(typeCase, p, plateau)){
			Coordonnees pPrime = new Coordonnees(p.getLigne()+1, p.getColonne()-1);
			if(troisAlignesDiagonalBasDroite(typeCase, pPrime, plateau)){
				
				c = coordonneesDunAlignementATroisPoints(p, TypeDirection.DiagonaleBasGauche);
				if(c.getColonne() == -1){
					c = coordonneesDunAlignementATroisPoints(p, TypeDirection.DiagonaleBasGauche);
					if(c.getLigne() != -1){
						return c;
					}
				}
			}
		}
		
		
		
		if(troisAlignesHorizontal(typeCase, p, plateau)){
			Coordonnees pPrime = new Coordonnees(p.getLigne(), p.getColonne()+1);
			if(troisAlignesDiagonalBasDroite(typeCase, pPrime, plateau)){
				c = coordonneesDunAlignementATroisPoints(p, TypeDirection.Droite);
				if(c.getColonne() == -1){
					c = coordonneesDunAlignementATroisPoints(p, TypeDirection.Droite);
					if(c.getLigne() != -1){
						return c;
					}
				}
			}
		}
		
		if(troisAlignesVertical(typeCase, p, plateau)){
			Coordonnees pPrime = new Coordonnees(p.getLigne()+1, p.getColonne());
			if(troisAlignesDiagonalBasDroite(typeCase, pPrime, plateau)){
				c = coordonneesDunAlignementATroisPoints(p, TypeDirection.Bas);
				if(c.getColonne() == -1){
					c = coordonneesDunAlignementATroisPoints(p, TypeDirection.Bas);
					if(c.getLigne() != -1){
						return c;
					}
				}
			}
		}
		
		
		return c;
	}
	
	private Coordonnees troisAlignes(TypeCase typeCase, Coordonnees p, PlateauDeJeu plateau){
		
		Coordonnees c = new Coordonnees(-1,-1);
				
		if(troisAlignesDiagonalBasDroite(typeCase, p, plateau)){
			c = coordonneesDunAlignementATroisPoints(p, TypeDirection.DiagonaleBasDroite);
			if(c.getLigne() != -1){
				return c;
			}
		}
		
		if(troisAlignesDiagonalBasGauche(typeCase, p, plateau)){
			c = coordonneesDunAlignementATroisPoints(p, TypeDirection.DiagonaleBasGauche);
			if(c.getLigne() != -1){
				return c;
			}
		}
		
		if(troisAlignesHorizontal(typeCase, p, plateau)){
			c = coordonneesDunAlignementATroisPoints(p, TypeDirection.Droite);
			if(c.getLigne() != -1){
				return c;
			}
		}
		
		if(troisAlignesVertical(typeCase, p, plateau)){
			c = coordonneesDunAlignementATroisPoints(p, TypeDirection.Bas);	
			if(c.getLigne() != -1){
				return c;
			}
		}
		return c;
	}
	
	
	//     -
	//   -
	// -
	private boolean troisAlignesDiagonalBasGauche(TypeCase typeCase, Coordonnees p, PlateauDeJeu plateau ) {
		// TODO Auto-generated method stub
		
		int i = p.getLigne();
		int j = p.getColonne();
		
		i++;
		j--;
		if((i<nbLigne)&&(j>0)){

			if(plateau.getPlateau()[i][j] == typeCase){

				i++;
				j--;
				if((i<nbLigne)&&(j>0)){
					
					if(plateau.getPlateau()[i][j] == typeCase){
						return true;
					}
				}
				
			}
		}
		
		return false;
	}
	
	// -
	//   -
	//     -
	private boolean troisAlignesDiagonalBasDroite(TypeCase typeCase, Coordonnees p, PlateauDeJeu plateau) {
		// TODO Auto-generated method stub
		
		int i = p.getLigne();
		int j = p.getColonne();
		
		i++;
		j++;
		if((i<nbLigne)&&(j<nbColonne)){
			if(plateau.getPlateau()[i][j] == typeCase){
				i++;
				j++;
				if((i<nbLigne)&&(j<nbColonne)){
					if(plateau.getPlateau()[i][j] == typeCase){
						return true;
					}
				}
				
			}
		}
		
		return false;
	}

	// - - -
	private boolean troisAlignesHorizontal(TypeCase typeCase, Coordonnees p, PlateauDeJeu plateau){
		
		int i = p.getLigne();
		int j = p.getColonne();

		j++;
		if(j<nbColonne){
			if(plateau.getPlateau()[i][j] == typeCase){
				j++;
				if(j<nbColonne){
					if(plateau.getPlateau()[i][j] == typeCase){
						return true;
					}
				}
				
			}
		}
		
		return false;
	}

	// -
	// -
	// -
	private boolean troisAlignesVertical(TypeCase typeCase, Coordonnees p, PlateauDeJeu plateau){
		
		int i = p.getLigne();
		int j = p.getColonne();

		i++;
		if(i<nbLigne){
			if(plateau.getPlateau()[i][j] == typeCase){
				i++;
				if(j<nbLigne){
					if(plateau.getPlateau()[i][j] == typeCase){
						return true;
					}
				}
				
			}
		}
		
		return false;
	}
	
	private Coordonnees coordonneesDunAlignementATroisPoints(Coordonnees p, TypeDirection typeDirection){
		Coordonnees c = new Coordonnees(-1,-1);
		
		switch(typeDirection){
			case Bas:
				
				//on test le point en haut
				if((p.getLigne() - 1 > 0) && caseJouable(new Coordonnees(p.getLigne()-1, p.getColonne()))){
					return (new Coordonnees(p.getLigne()-1,p.getColonne()));
				}
				//on test le point en bas
				else if(( p.getLigne() + 3 < nbLigne) &&(caseJouable(new Coordonnees(p.getLigne()+3, p.getColonne())))){
					return (new Coordonnees(p.getLigne()+3, p.getColonne()));
				}
				
				break;
				
			case Droite:
				
				//on test le point a gauche
				if((p.getColonne() - 1 > 0) && (caseJouable(new Coordonnees(p.getLigne(), p.getColonne()-1)))){
					return (new Coordonnees(p.getLigne(),p.getColonne() - 1));
				}
				//on test le point a droite
				else if(( p.getColonne() + 3 < nbColonne) &&(caseJouable(new Coordonnees(p.getLigne(), p.getColonne()+3)))){
					return (new Coordonnees(p.getLigne(), p.getColonne() + 3));
				}
				
				break;
				
			case DiagonaleBasDroite:
				
				//on test le point en haut a gauche
				if((p.getLigne() - 1 > 0) && (p.getColonne() -1 > 0) && (caseJouable(new Coordonnees(p.getLigne()-1, p.getColonne()-1)))){
					return (new Coordonnees(p.getLigne() - 1,p.getColonne() - 1));
				}
				//on test le point en bas a droite
				else if(( p.getLigne() + 3 < nbLigne) && ( p.getColonne() + 3 < nbColonne) &&(caseJouable(new Coordonnees(p.getLigne()+3, p.getColonne()+3)))){
					return (new Coordonnees(p.getLigne() + 3,p.getColonne() + 3));
				}
				
				break;
				
			case DiagonaleBasGauche :
				
				//on test le point en haut a droite
				if((p.getLigne() - 1 > 0) && (p.getColonne() + 1 < nbColonne) && (caseJouable(new Coordonnees(p.getLigne()-1, p.getColonne()+1)))){
					return (new Coordonnees(p.getLigne() - 1,p.getColonne() + 1));
				}
				//on test le point en bas a gauche
				else if(( p.getLigne() + 3 < nbLigne) && ( p.getColonne() - 3 > 0) &&(caseJouable(new Coordonnees(p.getLigne()+3, p.getColonne()-3)))){
					return (new Coordonnees(p.getLigne() + 3,p.getColonne() - 3));
				}
				
				break;
				
			default :
				break;
				
		}
			
		
		return null;
	}
	
	private Coordonnees pointRandom(){
		initHeuristique();
		ArrayList<Coordonnees> listePoint = new ArrayList<Coordonnees>();
		for(int i=0; i<nbLigne; i++){
			for(int j=0; j<nbColonne; j++){
				int nbHeristique = tabHeuristique[i][j];
				for(int k=0; k <nbHeristique; k++){
					listePoint.add(new Coordonnees(i,j));
				}
			}
		}
		
		int indiceRandom = r.nextInt(listePoint.size());
		return listePoint.get(indiceRandom);
	}
}
