/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import java.util.ArrayList;

import Enum.TypeCouleur;
import Enum.TypeTabous;

public class Tabou implements InterfaceTabou, java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean troisFoistrois;
	private boolean quatreFoisQuatre;
	private boolean sixSept;
	private static Motif motif = new Motif();

	// Constructeur
	public Tabou(ArrayList<TypeTabous> listeTabous) {

		this.troisFoistrois = false;
		this.quatreFoisQuatre = false;
		this.sixSept = false;
		for (TypeTabous typeTabou : listeTabous) {
			switch (typeTabou) {
			case TROIS_TROIS:
				this.troisFoistrois = true;
				break;
			case QUATRE_QUATRE:
				this.quatreFoisQuatre = true;
				break;
			case SIX_SEPT:
				this.sixSept = true;
				break;
			default:
				break;

			}
		}
	}
	public Tabou(boolean troisFoistrois, boolean quatreFoisQuatre, boolean sixSept) {

		this.troisFoistrois = troisFoistrois;
		this.quatreFoisQuatre = quatreFoisQuatre;
		this.sixSept = sixSept;
	}
	
	
	// Methode
	@Override
	// A appliquer que sur noir!!!!
	public boolean estValide(PlateauDeJeu r, Coordonnees c) {
		// initialisation des infos selon les directions
		Log.print(1010, "r = " + r + "c" + c);

		TypeCouleur typeCouleur = TypeCouleur.Noir;
		motif.setMotif(r,c.getLigne(), c.getColonne());
		Log.print(1010, motif.getInfoDiagonaleHautGauche().toString());
		Log.print(1010, motif.getInfoDiagonaleHautDroite().toString());
		Log.print(1010, motif.getInfoDiagonaleBasGauche().toString());
		Log.print(1010, motif.getInfoDiagonaleBasDroite().toString());

		boolean estValide = true;
		if (troisFoistrois) {
			estValide = estValide && !troisFoisTrois(motif, typeCouleur);
		}
		if (quatreFoisQuatre) {
			estValide = estValide && !quatreFoisQuatre(motif, typeCouleur);
		}
		if (sixSept) {
			estValide = estValide && !sixSept(motif, typeCouleur);
		}
		return estValide;
	}
	
	public static  boolean estValide(PlateauDeJeu r, Coordonnees c, boolean troisFoisTroisAtribut, boolean quatreFoisQuatreAtribut, boolean sixSeptAtribut) {
		return estValide(r,c.getLigne(), c.getColonne(), troisFoisTroisAtribut, quatreFoisQuatreAtribut, sixSeptAtribut);
	}
	
	
	public static boolean estValide(PlateauDeJeu r, int l, int c, boolean troisFoisTroisAtribut, boolean quatreFoisQuatreAtribut, boolean sixSeptAtribut) {
		// initialisation des infos selon les directions

		//Log.print(1010, "r = " + r + " l= " + l + " c= " + c);
		
		motif.setMotif(r, l, c);
//		Log.print(1010, motif.getInfoDiagonaleHautGauche().toString());
//		Log.print(1010, motif.getInfoDiagonaleHautDroite().toString());
//		Log.print(1010, motif.getInfoDiagonaleBasGauche().toString());
//		Log.print(1010, motif.getInfoDiagonaleBasDroite().toString());
//		Log.print(1010, motif.getInfoBas().toString());
//		Log.print(1010, motif.getInfoHaut().toString());
//		Log.print(1010, motif.getInfoDroite().toString());
//		Log.print(1010, motif.getInfoGauche().toString());

		boolean estValide = true;
		if (troisFoisTroisAtribut) {
			//Log.print(1010, "troisfoistrois " + troisFoisTrois(motif, typeCouleur));
			estValide = estValide && !troisFoisTrois(motif, TypeCouleur.Noir);
		}
		if (quatreFoisQuatreAtribut) {

			//Log.print(1010, "quatrefoisquatre " + quatreFoisQuatre(motif, TypeCouleur.Noir));
			estValide = estValide && !quatreFoisQuatre(motif, TypeCouleur.Noir);
		}
		if (sixSeptAtribut) {

			//Log.print(1010, "sixSept" + sixSept(motif, TypeCouleur.Noir));
			estValide = estValide && !sixSept(motif, TypeCouleur.Noir);
		}
		return estValide;
	}

	public boolean estDansListeTabous (TypeTabous typeTabou){
		switch(typeTabou){
		case TROIS_TROIS:
			return troisFoistrois;
		case QUATRE_QUATRE:
			return quatreFoisQuatre;
		case SIX_SEPT:
			return sixSept;
		default :
			break;
		}
		return true;
	}
	
	// Getter and Setter
	public ArrayList<TypeTabous> getListeTabous() {
		ArrayList<TypeTabous> listeTabous = new ArrayList<TypeTabous>();
		if (troisFoistrois) {
			listeTabous.add(TypeTabous.TROIS_TROIS);

		}
		if (quatreFoisQuatre) {
			listeTabous.add(TypeTabous.QUATRE_QUATRE);

		}
		if (sixSept) {
			listeTabous.add(TypeTabous.SIX_SEPT);

		}
		return listeTabous;
	}
	public void setListeTabous(ArrayList<TypeTabous> listeTabous) {
		this.troisFoistrois = false;
		this.quatreFoisQuatre = false;
		this.sixSept = false;

		for (TypeTabous typeTabou : listeTabous) {
			switch (typeTabou) {
			case TROIS_TROIS:
				this.troisFoistrois = true;
				break;
			case QUATRE_QUATRE:
				this.quatreFoisQuatre = true;
				break;
			case SIX_SEPT:
				this.sixSept = true;
				break;
			default:
				break;

			}
		}
	}

	
	// Methode private
	private static boolean sixSept(Motif motif, TypeCouleur typeCouleur) {
		return motif.estSixSept(typeCouleur);
	}
	private static boolean troisFoisTrois(Motif motif, TypeCouleur typeCouleur) {

		return motif.estTroisFoisTroisLibreLibre(typeCouleur);
	}
	private static boolean quatreFoisQuatre(Motif motif, TypeCouleur typeCouleur) {

		return motif.estQuatreFoisQuatre(typeCouleur);
	}

}
