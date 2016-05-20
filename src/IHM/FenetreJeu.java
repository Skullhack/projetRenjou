/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Controleur.Moteur;

/**
 *
 * @author michauad
 */
public class FenetreJeu extends JFrame {
    private ComposantAffichageRenjou affichageRenjou;
    private Moteur m;
    private JPanel[] panels;
    private JMenuBar barreMenu;
    private JMenu[] menus;
    private JMenuItem[][] menuItem;
    private JButton[] boutons;
    private JLabel[] nbPieces;
    private JButton[] aides;
    private JLabel[] sabliers;
    private Plateau p;
    private EcouteurDeSouris eds;
    private EcouteurFenetreJeu efj;
    private JLabel noir;
    private JLabel blanc;
    private String theme;
    private JButton recommencer;
    private JLabel[] tempsRestant;

    public FenetreJeu(IHM ihm) {
        //Variables
        this.m = ihm.m;
        efj = new EcouteurFenetreJeu(ihm);
        this.theme = m.getRenjou().getEmplacementThemes();
        p = new Plateau(ihm);
        eds = new EcouteurDeSouris(m, p);
        //0:PanelPrincipal 1:PanelBouton 2:PanelSud 3:PanelBoutonDansBouton
        panels = new JPanel[6];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
        }
        String[] nomMenus = {"Partie", "Configuration", "Aide"};
        String[][] nomSousMenu = {{"Creer partie", "Sauvegarder", "Charger", "Quitter"}, {"Theme", "Parametres", "Tabous"}, {"Regles", "Tutoriel", "A propos de"}};
        String[] nomBoutons = {"Annuler", "Refaire"};

        //Initialisation menus
        barreMenu = new JMenuBar();
        menus = new JMenu[nomMenus.length];
        menuItem = new JMenuItem[nomMenus.length][];
        for (int i = 0; i < menuItem.length; i++) {
            menuItem[i] = new JMenuItem[nomSousMenu[i].length];
        }
        for (int i = 0; i < nomMenus.length; i++) {
            menus[i] = new JMenu(nomMenus[i]);
            barreMenu.add(menus[i]);
            for (int j = 0; j < menuItem[i].length; j++) {
                menuItem[i][j] = new JMenuItem(nomSousMenu[i][j]);
                menuItem[i][j].setActionCommand(nomSousMenu[i][j]);
                menus[i].add(menuItem[i][j]);
                menuItem[i][j].addActionListener(efj);
            }
        }

        //Initialisation Bouton Dans Bouton
        boutons = new JButton[nomBoutons.length];
        for (int i = 0; i < boutons.length; i++) {
            boutons[i] = new JButton();
            boutons[i].setActionCommand(nomBoutons[i]);
            panels[3].add(boutons[i]);
            boutons[i].addActionListener(efj);
        }

        //Initialisation Panel Ouest
        //Initialisation Noir
        noir = new JLabel("Noir", SwingConstants.CENTER); //Creation label NOIR
        nbPieces = new JLabel[2]; //Premiere icone nombre de piece (noir)
        nbPieces[0] = new JLabel("60"); //Premier bouton d'aide(noir)
        aides = new JButton[2];
        aides[0] = new JButton(); //Bouton d'aide noir
        aides[0].setActionCommand("AideCoup");
        sabliers = new JLabel[2];
        sabliers[0] = new JLabel(); //Sablier noir
        tempsRestant = new JLabel[2];
        tempsRestant[0] = new JLabel("Temps restant : 30");//Temps restant noir
        //Initialisation Blanc
        blanc = new JLabel("Blanc", SwingConstants.CENTER); //Creation label BLANC
        nbPieces[1] = new JLabel("60"); //Deuxieme icone nombre de piece (blanc)
        aides[1] = new JButton(); //Bouton d'aide du joueur blanc
        aides[1].setActionCommand("AideCoup");
        sabliers[1] = new JLabel(); //Sablier du joueur blanc
        tempsRestant[1] = new JLabel("Temps restant : 30"); //Temps restant joueur blanc

        //Panel Sud
        recommencer = new JButton("Recommencer");
        panels[2].add(recommencer);
        recommencer.addActionListener(efj);
        affichageRenjou = new ComposantAffichageRenjou(m);
        panels[2].add(affichageRenjou);

        //Fin
        p.setPreferredSize(new Dimension(600, 600));
        this.setJMenuBar(barreMenu);
        panels[0].setLayout(new BorderLayout());
        panels[0].add(panels[1], BorderLayout.LINE_START);
        panels[0].add(p, BorderLayout.CENTER);
        panels[0].add(panels[2], BorderLayout.PAGE_END);
        this.add(panels[0]);
        this.creerLayout();
        this.repaint();
    }

    public void repaint() {
        p.repaint();
        nbPieces[0].setText(Integer.toString(m.getRenjou().getJoueurs()[0].getNbPion()));
        nbPieces[1].setText(Integer.toString(m.getRenjou().getJoueurs()[1].getNbPion()));
        if (m.getRenjou().getListeAnnuler().isEmpty()) {
            boutons[0].setEnabled(false);
        } else {
            boutons[0].setEnabled(true);
        }
        if (m.getRenjou().getListeRefaire().isEmpty()) {
            boutons[1].setEnabled(false);
        } else {
            boutons[1].setEnabled(true);
        }
    }

    public void creerLayout() {
        //Layout Panel Ouest
        panels[1].setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0; //X de depart du composant
        gbc.gridy = 0; //Y de de depart du composant
        gbc.gridheight = 1; //Hauteur du composant
        gbc.gridwidth = 1; //Largeur du composant
        panels[1].add(noir, gbc); //Premier label Noir
        gbc.gridy = 1;
        panels[1].add(nbPieces[0], gbc); //Premier nbPiece noir
        gbc.gridy = 2;
        panels[1].add(aides[0], gbc); //Premier bouton d'aide (noir)
        gbc.gridy = 3;
        panels[1].add(sabliers[0], gbc); //Premier sablier
        gbc.gridy = 4;
        panels[1].add(tempsRestant[0], gbc); //Temps restant (noir)
        gbc.gridy = 5;
        panels[1].add(panels[3], gbc); //Les deux boutons annuler et refaire
        gbc.gridy = 6;
        panels[1].add(blanc, gbc); //Deuxieme label blanc
        gbc.gridy = 7;
        panels[1].add(nbPieces[1], gbc); //Icone Pieces Blanches
        gbc.gridy = 8;
        panels[1].add(aides[1], gbc); //Second bouton d'aide (blanc)
        gbc.gridy = 9;
        panels[1].add(sabliers[1], gbc); //Second sablier
        gbc.gridy = 10;
        panels[1].add(tempsRestant[1], gbc); //Temps restant (noir)

        //Layout Panel Noir
        noir.setAlignmentY(CENTER_ALIGNMENT);
        noir.setFont(new Font("Time New Roman", Font.BOLD, 30));
        ImageIcon pionNoir = new ImageIcon(new ImageIcon("./Images/" + theme + "/Pion noir.png").getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
        nbPieces[0].setIcon(pionNoir);
        nbPieces[0].setHorizontalTextPosition(JLabel.CENTER);
        nbPieces[0].setFont(new Font("Time New Roman", Font.BOLD, 30));
        nbPieces[0].setForeground(Color.WHITE);

        //Boutons annuler et refaire
        ImageIcon annuler = new ImageIcon(new ImageIcon("./Images/" + theme + "/Annuler.png").getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH));
        ImageIcon annulerEclaire = new ImageIcon(new ImageIcon("./Images/" + theme + "/AnnulerEclaire.png").getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH));
        setIcone(boutons[0], new Color(0, 0, 0, 0), annuler, annulerEclaire);
        //boutons[0].addActionListener();
        ImageIcon refaire = new ImageIcon(new ImageIcon("./Images/" + theme + "/Refaire.png").getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH));
        ImageIcon refaireEclaire = new ImageIcon(new ImageIcon("./Images/" + theme + "/RefaireEclaire.png").getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH));
        setIcone(boutons[1], new Color(0, 0, 0, 0), refaire, refaireEclaire);

        //Boutons aide
        ImageIcon aide = new ImageIcon(new ImageIcon("./Images/" + theme + "/AmpouleGrisee.png").getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
        ImageIcon aideEclaire = new ImageIcon(new ImageIcon("./Images/" + theme + "/AmpouleAlumee.png").getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
        setIcone(aides[1], new Color(0, 0, 0, 0), aide, aideEclaire);
        setIcone(aides[0], new Color(0, 0, 0, 0), aide, aideEclaire);

        //Bouton sablier
        ImageIcon sablier = new ImageIcon(new ImageIcon("./Images/" + theme + "/Sablier.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        sabliers[0].setIcon(sablier);
        sabliers[1].setIcon(sablier);

        //Layout Panel Blanc
        blanc.setFont(new Font("Time New Roman", Font.BOLD, 30));
        ImageIcon pionBlanc = new ImageIcon(new ImageIcon("./Images/" + theme + "/Pion blanc.png").getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH));
        nbPieces[1].setIcon(pionBlanc);
        nbPieces[1].setHorizontalTextPosition(JLabel.CENTER);
        nbPieces[1].setFont(new Font("Time New Roman", Font.BOLD, 30));
        nbPieces[1].setForeground(Color.BLACK);

        //Background
        panels[2].setBackground(Color.WHITE);
        panels[1].setBackground(Color.WHITE);
        panels[3].setBackground(Color.WHITE);
    }

    private void setIcone(JButton b, Color color, ImageIcon normal, ImageIcon rollover) {
        b.setBorderPainted(false);
        b.setIcon(normal);
        b.setBackground(new Color(0, 0, 0, 0));
        b.setSize(new Dimension(normal.getIconWidth(), normal.getIconHeight()));
        b.setRolloverEnabled(false);
        b.setRolloverIcon(rollover);
        b.setBackground(Color.WHITE);
    }
}
