/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.awt.Point;

/**
 *
 * @author michauad
 */
public class Tabou implements InterfaceTabou, java.io.Serializable {
	private String nom;
	private boolean[][] configuration;

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

	private void setNom(String nom) {
		this.nom = nom;
	}

	private void setConfiguration(boolean[][] configuration) {
		this.configuration = configuration;
	}

	@Override
	public boolean estValide(Renjou r, Coordonnees c) {
		return true;
	}

}
