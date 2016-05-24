/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;


import Controleur.Renjou;
import Enum.TypeDirection;

/**
 *
 * @author michauad
 */
public class Tabou implements InterfaceTabou, java.io.Serializable {
	private String nom;
	private boolean[][] configuration;
	private InfosAlignement infoGauche;
	private InfosAlignement infoDroite;
	private InfosAlignement infoHaut;
	private InfosAlignement infoBas;
	private InfosAlignement infoDiagonaleHautGauche;
	private InfosAlignement infoDiagonaleHautDroite;
	private InfosAlignement infoDiagonaleBasGauche;
	private InfosAlignement infoDiagonaleBasDroite;
	

	// Constructeur
	public Tabou(boolean[][] conf, String nom) {
		this.nom=nom;
		this.configuration=conf;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public boolean[][] getConfiguration() {
		return this.configuration;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setConfiguration(boolean[][] configuration) {
		this.configuration = configuration;
	}

	//A appliquer que sur noir!!!!
	@Override
	//A appliquer que sur noir!!!!
	public boolean estValide(PlateauDeJeu r, Coordonnees c, boolean troisFoistrois, boolean quatreFoisQuatre, boolean sixSept ) {
		//initialisation des infos selon les directions
		infoGauche = new InfosAlignement(r, c, TypeDirection.Gauche);
		infoDroite = new InfosAlignement(r, c, TypeDirection.Droite);
		infoHaut = new InfosAlignement(r, c, TypeDirection.Haut);
		infoBas = new InfosAlignement(r, c, TypeDirection.Bas);
		infoDiagonaleHautGauche = new InfosAlignement(r, c, TypeDirection.DiagonaleHautGauche);
		infoDiagonaleHautDroite = new InfosAlignement(r, c, TypeDirection.DiagonaleHautDroite);
		infoDiagonaleBasGauche = new InfosAlignement(r, c, TypeDirection.DiagonaleBasGauche);
		infoDiagonaleBasDroite = new InfosAlignement(r, c, TypeDirection.DiagonaleBasDroite);
		
		boolean estValide = true;
		if(troisFoistrois){
			estValide = estValide && !troisFoisTrois();
		}
		if(quatreFoisQuatre){
			estValide = estValide && !quatreFoisQuatre();
		}
		if(sixSept){
			estValide = estValide && !sixSept();
		}
		
		return estValide;
	}
	
	public boolean sixSept() {
		if((nbPionNoirDiagonaleDroite() > 5) && (infoDiagonaleBasGauche.nbNoirNonContinu > 0)){
			return true;
		}
		if(nbPionNoirDiagonaleGauche() > 5){
			return true;
		}
		if(nbPionNoirHorizontale() > 5){
			return true;
		}
		if(nbPionNoirVerticale() > 5){
			return true;
		}
		
		return false;
	}

	public boolean troisFoisTrois(){
		if(estTroisDiagonaleDroite()){
			if(estTroisDiagonaleGauche()){
				return true;
			}else if(estTroisHorizontale()){
					return true;
			}else if(estTroisVerticale()){
				
				return true;
			}
			
		}else if(estTroisDiagonaleGauche()){
			if(estTroisHorizontale()){
				return true;
			}else if(estTroisVerticale()){
				return true;
			}
		}else if(estTroisHorizontale()){
			if(estTroisVerticale()){
				return true;
			}
		}
		
		
		return false;
	}
	
	public boolean quatreFoisQuatre(){
		
		if(estQuatreDiagonaleDroite()){
			if(estQuatreDiagonaleGauche()){
				return true;
			}else if(estQuatreHorizontale()){
					return true;
			}else if(estQuatreVerticale()){
				return true;
			}
			
		}else if(estQuatreDiagonaleGauche()){
			if(estQuatreHorizontale()){
				return true;
			}else if(estQuatreVerticale()){
				return true;
			}
		}else if(estQuatreHorizontale()){
			if(estQuatreVerticale()){
				return true;
			}
		}
		
		return false;
	}
	
	//A appliquer que sur noir!!!!
	
	// -
	//   -
	//     -
	private boolean estTroisDiagonaleDroite(){
		return (infoDiagonaleHautGauche.estLibreNoir() && infoDiagonaleBasDroite.estLibreNoir() && (nbPionNoirDiagonaleDroite() == 3));
	}
	
	//     -
	//   -
	// -
	private boolean estTroisDiagonaleGauche(){	
		return (infoDiagonaleHautDroite.estLibreNoir() && infoDiagonaleBasGauche.estLibreNoir() && ( nbPionNoirDiagonaleGauche() == 3));
	}
	
	private boolean estTroisVerticale(){
		
		return (infoHaut.estLibreNoir() && infoBas.estLibreNoir() && (nbPionNoirVerticale() == 3));
	}
	
	private boolean estTroisHorizontale(){
		
		return (infoDroite.estLibreNoir() && infoGauche.estLibreNoir() && ( nbPionNoirHorizontale()== 3));
	}
	
	
	// -
	//   -
	//     -
	//       -
	private boolean estQuatreDiagonaleDroite(){
		return ((infoDiagonaleHautGauche.estLibreNoir() || infoDiagonaleBasDroite.estLibreNoir()) && (nbPionNoirDiagonaleDroite() == 4));
	}
	
	//       -
	//     -
	//   -
	// -
	private boolean estQuatreDiagonaleGauche(){	
		return ((infoDiagonaleHautDroite.estLibreNoir() || infoDiagonaleBasGauche.estLibreNoir()) && (nbPionNoirDiagonaleGauche() == 4));
	}
	
	private boolean estQuatreVerticale(){
		return ((infoHaut.estLibreNoir() || infoBas.estLibreNoir()) && (nbPionNoirVerticale() == 4));
	}
	
	private boolean estQuatreHorizontale(){
		return ((infoDroite.estLibreNoir() || infoGauche.estLibreNoir()) && (nbPionNoirHorizontale() == 4));
	}
	
	private int nbPionNoirDiagonaleDroite(){
		return (infoDiagonaleHautGauche.getNbNoir() + infoDiagonaleBasDroite.getNbNoir() + 1);
	}
	
	private int nbPionNoirDiagonaleGauche(){
		return (infoDiagonaleHautDroite.getNbNoir() + infoDiagonaleBasGauche.getNbNoir() + 1);
	}
	
	private int nbPionNoirVerticale(){
		return (infoHaut.getNbNoir() + infoBas.getNbNoir() + 1);
	}
	
	private int nbPionNoirHorizontale(){
		return (infoDroite.getNbNoir() + infoGauche.getNbNoir() + 1);
	}
	
	private int nbPionBlancVerticale(){
		return (infoHaut.getNbBlanc() + infoBas.getNbBlanc() + 1);
	}
	
	private int nbPionBlancDiagonaleDroite(){
		return (infoDiagonaleHautGauche.getNbBlanc() + infoDiagonaleBasDroite.getNbBlanc());
	}
	
	private int nbPionBlancHorizontale(){
		return (infoDroite.getNbBlanc() + infoGauche.getNbBlanc());
	}
	
	private int nbPionBlancDiagonaleGauche(){
		return (infoDiagonaleHautDroite.getNbBlanc() + infoDiagonaleBasGauche.getNbBlanc());
	}
}
