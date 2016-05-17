/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;
import java.awt.MenuBar;

import javax.swing.JButton;
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
	JButton[] boutons;
	
	public FenetreJeu(Moteur m) {
		//Variables
		this.m = m;
		//0:PanelPrincipal 1:PanelBouton 2:Grille 3:PanelSud 4:PanelJ1 5:PanelBoutonDansBouton 6:PanelJ2
		panels = new JPanel[7];
		for (int i=0;i<panels.length;i++)
			panels[i] = new JPanel(); 
		String[] nomMenus = {"Partie","Configuration","Aide"};
		String[][] nomSousMenu = {{"Créer partie","Sauvegarder","Charger","Quitter"},{"Thème","Paramètres","Tabous"},{"Règles","Tutoriel","A propos de"}};
		String[] nomBoutons={"Annuler","Refaire"};
		
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
		
		//Initialisation Bouton Dans Bouton
		boutons = new JButton[nomBoutons.length];
		
		//Fin
		this.setJMenuBar(barreMenu);
		this.add(panels[0]);
	}
}