/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;
import java.awt.MenuBar;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Controleur.Moteur;

/**
 *
 * @author michauad
 */
public class FenetreJeu extends JFrame{
    Moteur m;
    JPanel[] panels;
    JMenuBar barreMenu;
	JMenu[] menus;
	JMenuItem[][] menuItem;
	
	public FenetreJeu(Moteur m) {
		//Variables
		this.m = m;
		//0:PanelPrincipal 1:PanelMenu 2:PanelBouton 3:Grille 4:PanelJ1 5:PanelBoutonBouton 6:PanelJ2
		panels = new JPanel[7];
		for (int i=0;i<panels.length;i++)
			panels[i] = new JPanel(); 
		String[] nomMenus = {"Partie","Aide"};
		String[][] nomSousMenu = {{"Recommencer","Nouveau","Sauvegarder","Charger","Quitter"},{"Règles","A propos de"}};
		
		//Initialisation menus
		barreMenu = new JMenuBar();
		menus = new JMenu[nomMenus.length];
		menuItem = new JMenuItem[nomMenus.length][];
		for (int i=0; i<menuItem.length;i++)
			menuItem[i] = new JMenuItem[nomSousMenu[i].length];
		for (int i=0; i<nomMenus.length;i++) {
			menus[i] = new JMenu(nomMenus[i]);
			barreMenu.add(menus[i]);
			for (int j=0; j<menuItem[i].length;j++) {
				menuItem[i][j]=new JMenuItem(nomSousMenu[i][j]);
				menus[i].add(menuItem[i][j]);
			}
		}
		panels[1].add(barreMenu, BorderLayout.WEST);
		
		//Initialisation Bouton
		
		
		//Fin
		panels[0].add(panels[1], BorderLayout.NORTH);
		this.add(panels[0]);
	}
}