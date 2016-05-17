/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Controleur.Moteur;

/**
 *
 * @author michauad
 */
public class FenetreMenu extends JFrame {
	private Moteur m;
	private JTabbedPane tabbedPane;
	private JPanel[] panels;
	
    public FenetreMenu(Moteur m) {
    	this.m=m;
    	//0:PanelTout 1:PanelJoueur 2:PanelTabou 3:PanelBoutons
    	panels = new JPanel[4];
    	for (int i=0; i<panels.length;i++)
    		panels[i]=new JPanel();
    	//Initialisation Label
    	JLabel nom = new JLabel("Configuration");
    	
    	//Tabs
    	tabbedPane = new JTabbedPane();
    	tabbedPane.addTab("Joueurs", null, panels[1],"Configuration de la partie");
    	tabbedPane.addTab("Tabous", null, panels[2],"Configuration des tabous");
    	
    	
    	//Panel0
    	panels[0].setLayout(new BorderLayout());
    	panels[0].add(nom, BorderLayout.PAGE_START);
    	panels[0].add(tabbedPane, BorderLayout.CENTER);
    	this.add(panels[0]);
    	
    	
    }
}
