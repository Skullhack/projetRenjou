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
import java.awt.Dimension;
import java.awt.Font;

/**
 *
 * @author michauad
 */
public class FenetreMenu extends JFrame {

    private Moteur m;
    private JTabbedPane tabbedPane;
    private JPanel[] panels;
    private ButtonGroup[] couleur;
    private JRadioButton[][] boutonsCouleurs;
    private JButton[] boutons;
    private JLabel labelNoir;
    private JLabel labelBlanc;
    private JLabel nom;
    private EcouteurFenetreMenu efm;

    public FenetreMenu(IHM ihm) {
        this.m = ihm.m;
        efm = new EcouteurFenetreMenu(ihm);
        this.setTitle("Configuration");
        String[] nomBoutons = {"Valider", "Annuler"};
        //0:PanelTout 1:PanelJoueur 2:PanelTabou 3:PanelBoutons
        panels = new JPanel[4];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
        }

        //Tabs
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Joueurs", null, panels[1], "Configuration de la partie");
        tabbedPane.addTab("Tabous", null, panels[2], "Configuration des tabous");

        //Tab Joueur
        couleur = new ButtonGroup[2];
        couleur[0] = new ButtonGroup();
        couleur[1] = new ButtonGroup();
        boutonsCouleurs = new JRadioButton[2][];
        boutonsCouleurs[0] = new JRadioButton[TypeJoueur.values().length];
        boutonsCouleurs[1] = new JRadioButton[TypeJoueur.values().length];
        for (int i = 0; i < TypeJoueur.values().length; i++) {
            boutonsCouleurs[0][i] = new JRadioButton();
            boutonsCouleurs[0][i].setActionCommand(TypeJoueur.values()[i].toString());
            couleur[0].add(boutonsCouleurs[0][i]);
            boutonsCouleurs[1][i] = new JRadioButton();
            boutonsCouleurs[1][i].setActionCommand(TypeJoueur.values()[i].toString());
            couleur[1].add(boutonsCouleurs[1][i]);
        }
        labelNoir = new JLabel("Noir");
        labelNoir.setFont(new Font("Time New Roman",Font.BOLD,50));
        labelBlanc = new JLabel("Blanc");
        labelBlanc.setFont(new Font("Time New Roman",Font.BOLD,50));
        
        //Boutons
        boutons = new JButton[nomBoutons.length];
        for (int i = 0; i < nomBoutons.length; i++) {
            boutons[i] = new JButton(nomBoutons[i]);
            panels[3].add(boutons[i]);
            boutons[i].setActionCommand(nomBoutons[i]);
            boutons[i].addActionListener(efm);
        }

        creerLayout();
    }

    public void creerLayout() {
        //Layout
        panels[1].setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; //Positionnement départ composant X
        gbc.gridy = 0; //Positionnement départ composant Y
        gbc.gridheight = 1; //Taille Composant X
        gbc.gridwidth = 3; //Taille Composant Y
        panels[1].add(labelNoir, gbc);
        gbc.gridx = 3; //Positionnement départ composant X
        gbc.gridwidth = 1; //Taille Composant Y
        panels[1].add(labelBlanc, gbc);
        for (int i = 0; i < TypeJoueur.values().length; i++) {
            gbc.gridx = 0;
            gbc.gridy = 1 + i;
            panels[1].add(boutonsCouleurs[0][i], gbc); //Ajout Bouton Noir
            boutonsCouleurs[0][i].setSize(new Dimension(100, 100));
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            JLabel label = new JLabel(TypeJoueur.values()[i].toString());
            label.setFont(new Font("Time New Roman",Font.CENTER_BASELINE,30));
            panels[1].add(label, gbc);  //Ajout texte choix
            gbc.gridx = 3;
            gbc.gridwidth = 1;
            panels[1].add(boutonsCouleurs[1][i], gbc); //Ajout Bouton Blanc
        }

        //Panel0
        panels[0].setLayout(new BorderLayout());
        panels[0].add(tabbedPane, BorderLayout.CENTER);
        panels[0].add(panels[3], BorderLayout.PAGE_END);
        this.add(panels[0]);
    }
    
    public JRadioButton[][] getBoutonsCouleur() {
        return boutonsCouleurs;
    }
    public ButtonGroup[] getCouleur() {
        return couleur;
    }
}
