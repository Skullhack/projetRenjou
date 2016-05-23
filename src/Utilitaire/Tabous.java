/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaire;

import java.util.ArrayList;

/**
 *
 * @author michauad
 */
public class Tabous implements InterfaceTabous, java.io.Serializable {
	private ArrayList<Tabou> listeTabous;

	// Constructeur
	public Tabous() {
		listeTabous = new ArrayList<Tabou>();
	}

	@Override
	public ArrayList<Tabou> getTabous() {

		return this.listeTabous;
	}

	private void setTabous(ArrayList<Tabou> tabous) {
		this.setTabous(tabous);
	}

	public ArrayList<String> getTabouToString() {
		ArrayList<String> tabousToString = new ArrayList<String>();
		for (int i=0; i<listeTabous.size();i++) {
			tabousToString.add(listeTabous.get(i).getNom());
		}
		return tabousToString;
	}
	
	@Override
	public void lireTabous() {
		// throw new UnsupportedOperationException("Not supported yet."); //To
		// change body of generated methods, choose Tools | Templates.
	}
}
