package Controleur;

import java.awt.Point;

import Enum.TypeCase;

public class PionJoue implements java.io.Serializable {

	Coordonnees c;
	TypeCase typeCase;

	public PionJoue(Coordonnees c, TypeCase typeCase) {
		this.c = c;
		this.typeCase = typeCase;
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

}
