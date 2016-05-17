/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.awt.Point;

import Enum.*;

/**
 *
 * @author michauad
 */
public interface InterfacePlateauDeJeu {
	// Getter
	public TypeCase[][] getPlateau();

	// Setter
	public void setPlateau(TypeCase[][] plateau);

	// Methodes
	public void ajouter(Point p);

	public void enlever(Point p);

	public PlateauDeJeu clone();
}
