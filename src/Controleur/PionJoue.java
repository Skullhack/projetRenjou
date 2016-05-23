package Controleur;

import java.awt.Point;

import Enum.EtatPartie;
import Enum.TypeCase;
import Utilitaire.Coordonnees;

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
