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
	public boolean estValide(PlateauDeJeu r, Coordonnees c, boolean troisFoistrois, boolean quatreFoisQuatre, boolean overline ) {
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
			Log.print(1010, "troisFoisTrois est Valide");
			estValide = estValide && !troisFoisTrois();
		}
		if(quatreFoisQuatre){
			estValide = estValide && !quatreFoisQuatre();
		}
		if(overline){
			estValide = estValide && !overline();
		}
		
		return estValide;
	}
	
	public boolean overline() {
		if(nbPionDiagonaleDroite() > 5){
			return true;
		}
		if(nbPionDiagonaleGauche() > 5){
			return true;
		}
		if(nbPionHorizontale() > 5){
			return true;
		}
		if(nbPionVerticale() > 5){
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
		return (infoDiagonaleHautGauche.estLibreNoir() || infoDiagonaleBasDroite.estLibreNoir() || (nbPionDiagonaleDroite() == 3));
	}
	
	//     -
	//   -
	// -
	private boolean estTroisDiagonaleGauche(){	
		return (infoDiagonaleHautDroite.estLibreNoir() || infoDiagonaleBasGauche.estLibreNoir() || ( nbPionDiagonaleGauche() == 3));
	}
	
	private boolean estTroisVerticale(){
		
		return (infoHaut.estLibreNoir() || infoBas.estLibreNoir() || (nbPionVerticale() == 3));
	}
	
	private boolean estTroisHorizontale(){
		
		return (infoDroite.estLibreNoir() || infoGauche.estLibreNoir() || ( nbPionHorizontale()== 3));
	}
	
	
	// -
	//   -
	//     -
	//       -
	private boolean estQuatreDiagonaleDroite(){
		return (infoDiagonaleHautGauche.estLibreNoir() || infoDiagonaleBasDroite.estLibreNoir() || (nbPionDiagonaleDroite() == 4));
	}
	
	//       -
	//     -
	//   -
	// -
	private boolean estQuatreDiagonaleGauche(){	
		return (infoDiagonaleHautDroite.estLibreNoir() || infoDiagonaleBasGauche.estLibreNoir() || (nbPionDiagonaleGauche() == 4));
	}
	
	private boolean estQuatreVerticale(){
		return (infoHaut.estLibreNoir() || infoBas.estLibreNoir() || (nbPionVerticale() == 4));
	}
	
	private boolean estQuatreHorizontale(){
		return (infoDroite.estLibreNoir() || infoGauche.estLibreNoir() || (nbPionHorizontale() == 4));
	}
	
	private int nbPionDiagonaleDroite(){
		return (infoDiagonaleHautGauche.getNbNoir() + infoDiagonaleBasDroite.getNbNoir() + 1);
	}
	
	private int nbPionDiagonaleGauche(){
		return (infoDiagonaleHautDroite.getNbNoir() + infoDiagonaleBasGauche.getNbNoir() + 1);
	}
	
	private int nbPionVerticale(){
		return (infoHaut.getNbNoir() + infoBas.getNbNoir() + 1);
	}
	
	private int nbPionHorizontale(){
		return (infoDroite.getNbNoir() + infoGauche.getNbNoir() + 1);
	}
	
}
