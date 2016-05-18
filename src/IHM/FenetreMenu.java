/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import Controleur.Moteur;
import Enum.TypeJoueur;

/**
 *
 * @author michauad
 */
public class FenetreMenu extends JFrame {
	private Moteur m;
	private JTabbedPane tabbedPane;
	private JPanel[] panels;
	ButtonGroup [] couleur;
	JRadioButton[][] boutonsCouleurs;
	JButton[] boutons;
	
	
    public FenetreMenu(Moteur m) {
    	this.m=m;
    	String[] nomBoutons = {"Valider","Quitter"};
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
    	
    	
    	//Tab Joueur
    	couleur = new ButtonGroup[2];
    	couleur[0] = new ButtonGroup();
    	couleur[1] = new ButtonGroup();
    	boutonsCouleurs = new JRadioButton[2][];
    	boutonsCouleurs[0] = new JRadioButton[TypeJoueur.values().length];
    	boutonsCouleurs[1] = new JRadioButton[TypeJoueur.values().length];
    	for (int i=0;i<TypeJoueur.values().length;i++) {
    		boutonsCouleurs[0][i] = new JRadioButton(TypeJoueur.values()[i].toString());
    		couleur[0].add(boutonsCouleurs[0][i]);
    		boutonsCouleurs[1][i] = new JRadioButton(TypeJoueur.values()[i].toString());
    		couleur[1].add(boutonsCouleurs[1][i]);
    	}
    	//Layout
    	panels[1].setLayout(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
		//On positionne la case de départ du composant
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    //La taille et la hauteur en largeur
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    JLabel labelNoir = new JLabel("Noir");
		panels[1].add(labelNoir,gbc);
		//On positionne la case de départ du composant
	    gbc.gridx = 2;
	    gbc.gridy = 0;
	    //La taille et la hauteur en largeur
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    JLabel labelBlanc = new JLabel("Blanc");
		panels[1].add(labelBlanc,gbc);
		for (int i=0;i<TypeJoueur.values().length;i++) {
			//On positionne la case de départ du composant
		    gbc.gridx = 2+i;
		    gbc.gridy = 0;
			panels[1].add(boutonsCouleurs[0][i]);
			//On positionne la case de départ du composant
		    gbc.gridy = 1;
		    //La taille et la hauteur en largeur
		    gbc.gridwidth = 2;
			panels[1].add(new JLabel(TypeJoueur.values()[i].toString()));
		    gbc.gridy = 3;
		    //La taille et la hauteur en largeur
		    gbc.gridwidth = 1;
			panels[1].add(boutonsCouleurs[1][i]);
		}
		
		
    	//Boutons
    	boutons = new JButton[nomBoutons.length];
    	for (int i=0;i<nomBoutons.length;i++) {
    		boutons[i] = new JButton(nomBoutons[i]);
    		panels[3].add(boutons[i]);
    	}
    		
    	
    	//Panel0
    	panels[0].setLayout(new BorderLayout());
    	panels[0].add(nom, BorderLayout.PAGE_START);
    	panels[0].add(tabbedPane, BorderLayout.CENTER);
    	panels[0].add(panels[3], BorderLayout.PAGE_END);
    	this.add(panels[0]); 			
    }
}
