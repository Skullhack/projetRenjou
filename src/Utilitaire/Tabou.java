/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import java.util.ArrayList;

import com.sun.glass.ui.CommonDialogs.Type;

import Controleur.MoteurObserveur;
import Controleur.Renjou;
import Enum.TypeCouleur;
import Enum.TypeDirection;
import Enum.TypeTabous;

/**
 *
 * @author michauad
 */
public class Tabou implements InterfaceTabou, java.io.Serializable {
	private boolean troisFoistrois;
	private boolean quatreFoisQuatre;
	private boolean sixSept;

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
	
	// A appliquer que sur noir!!!!
	@Override
	// A appliquer que sur noir!!!!
	public boolean estValide(PlateauDeJeu r, Coordonnees c) {
		// initialisation des infos selon les directions
		Log.print(1010, "r = " + r + "c" + c);

		TypeCouleur typeCouleur = TypeCouleur.Noir;
		Motif motif = new Motif(r,c);
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

	public boolean estValide(PlateauDeJeu r, Coordonnees c, boolean troisFoisTroisAtribut, boolean quatreFoisQuatreAtribut, boolean sixSeptAtribut, TypeCouleur typeCouleur) {
		// initialisation des infos selon les directions
		Log.print(1010, "r = " + r + "c" + c);
		Motif motif = new Motif(r,c);

		Log.print(1010, motif.getInfoDiagonaleHautGauche().toString());
		Log.print(1010, motif.getInfoDiagonaleHautDroite().toString());
		Log.print(1010, motif.getInfoDiagonaleBasGauche().toString());
		Log.print(1010, motif.getInfoDiagonaleBasDroite().toString());

		boolean estValide = true;
		if (troisFoisTroisAtribut) {
			estValide = estValide && !troisFoisTrois(motif, typeCouleur);
		}
		if (quatreFoisQuatreAtribut) {
			estValide = estValide && !quatreFoisQuatre(motif, typeCouleur);
		}
		if (sixSeptAtribut) {
			estValide = estValide && !sixSept(motif, typeCouleur);
		}
		return estValide;
	}

	
	public boolean sixSept(Motif motif, TypeCouleur typeCouleur) {
		return motif.estSixSept(typeCouleur);
	}

	public boolean troisFoisTrois(Motif motif, TypeCouleur typeCouleur) {
		return motif.estTroisFoisTroisLibreLibre(typeCouleur);
	}

	public boolean quatreFoisQuatre(Motif motif, TypeCouleur typeCouleur) {
		return motif.estQuatreFoisQuatre(typeCouleur);
	}

}
