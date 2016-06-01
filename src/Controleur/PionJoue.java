package Controleur;

import Enum.EtatPartie;
import Enum.TypeCase;
import Utilitaire.Coordonnees;

/*
 * Cette classe est utilisee dans les listes annuler et refaire pour ne sauvegarder qu'une coordonnee, un typeCase et un etatPartie
 * Il n'y a que des getters et des setters et une methode toString pour l'affichage
 */

public class PionJoue implements java.io.Serializable {

	Coordonnees c;
	TypeCase typeCase;
	EtatPartie etatPartie;

	public PionJoue(Coordonnees c, TypeCase typeCase, EtatPartie etatPartie) {
		this.c = c;
		this.typeCase = typeCase;
		this.etatPartie = etatPartie;
	}

	public Coordonnees getCoordonnees() {
		return c;
	}

	public TypeCase getTypeCase() {
		return typeCase;
	}

	public void setCoordonnees(Coordonnees c) {
		this.c = c;
	}

	public void setTypeCase(TypeCase typeCase) {
		this.typeCase = typeCase;
	}

	public EtatPartie getEtatPartie() {
		return etatPartie;
	}

	public void setEtatPartie(EtatPartie etatPartie) {
		this.etatPartie = etatPartie;
	}

	public String toString() {
		return "Coordonnees : " + c + " TypeCase : " + typeCase + " etatPartie : " + etatPartie;
	}

}
