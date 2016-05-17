/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MenuBar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private Moteur m;
    private JPanel[] panels;
    private JMenuBar barreMenu;
	private JMenu[] menus;
	private JMenuItem[][] menuItem;
	private JButton[] boutons;
	private JButton[] nbPieces;
	private JButton aide;
	private Plateau p;
	
	public FenetreJeu(Moteur m) {
		//Variables
		this.m = m;
		p = new Plateau(m);
		//0:PanelPrincipal 1:PanelBouton 2:PanelSud 3:PanelNoir 4:PanelBoutonDansBouton 5:PanelBlanc
		panels = new JPanel[6];
		for (int i=0;i<panels.length;i++)
			panels[i] = new JPanel(); 
		String[] nomMenus = {"Partie","Configuration","Aide"};
		String[][] nomSousMenu = {{"Créer partie","Sauvegarder","Charger","Quitter"},{"Thème","Paramètres","Tabous"},{"Règles","Tutoriel","A propos de"}};
		String[] nomBoutons={"Annuler","Refaire"};
		JLabel tempsRestant;
		
		
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
		for (int i=0; i<boutons.length;i++) {
			boutons[i] = new JButton(nomBoutons[i]);
			panels[4].add(boutons[i]);
		}
		
		//Initialisation Noir
		panels[3].setLayout(new BoxLayout(panels[3], BoxLayout.PAGE_AXIS));
		JLabel noir = new JLabel("Noir");
		nbPieces = new JButton[2];
		nbPieces[0] = new JButton("60");
		aide = new JButton("Aide Coup");
		tempsRestant = new JLabel("Temps restant : 30");
		panels[3].add(noir);
		panels[3].add(nbPieces[0]);
		panels[3].add(aide);
		panels[3].add(tempsRestant);
		panels[3].setBackground(Color.BLACK);
		
		//Initialisation Blanc
		panels[5].setLayout(new BoxLayout(panels[5], BoxLayout.PAGE_AXIS));
		JLabel blanc = new JLabel("Blanc");
		nbPieces = new JButton[2];
		nbPieces[1] = new JButton("60");
		panels[5].add(blanc);
		panels[5].add(nbPieces[1]);
		panels[5].add(aide);
		panels[5].add(tempsRestant);
		panels[5].setBackground(Color.WHITE);
		
		//Initialisation Panel Ouest
		panels[1].setLayout(new BoxLayout(panels[1], BoxLayout.PAGE_AXIS));
		panels[1].add(panels[3]);
		panels[1].add(panels[4]);
		panels[1].add(panels[5]);

		
		//Fin
		p.setPreferredSize(new Dimension(600,600));
		this.setJMenuBar(barreMenu);
		panels[0].setLayout(new BorderLayout());
        panels[1].setBackground(Color.BLUE);
        panels[0].add(panels[1],BorderLayout.LINE_START);
        panels[0].add(p, BorderLayout.AFTER_LINE_ENDS);
		this.add(panels[0]);
		
	}
}