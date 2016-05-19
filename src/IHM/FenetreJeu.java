/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import Controleur.Moteur;
import javafx.scene.layout.FlowPane;
/**
 *
 * @author michauad
 */
public class FenetreJeu extends JFrame {
    private Moteur m;
    private JPanel[] panels;
    private JMenuBar barreMenu;
	private JMenu[] menus;
	private JMenuItem[][] menuItem;
	private JButton[] boutons;
	private JLabel[] nbPieces;
	private JButton[] aide;
	private Plateau p;
	private EcouteurDeSouris eds;
	private JLabel noir;
	private JLabel blanc;
	private String theme;
	
	public FenetreJeu(Moteur m) {
		//Variables
		this.m = m;
		this.theme = m.getRenjou().getEmplacementThemes();
		p = new Plateau(m.getRenjou().getPlateauDeJeu(), theme);
		eds = new EcouteurDeSouris(m,p);
		//0:PanelPrincipal 1:PanelBouton 2:PanelSud 3:PanelNoir 4:PanelBoutonDansBouton 5:PanelBlanc
		panels = new JPanel[6];
		for (int i=0;i<panels.length;i++)
			panels[i] = new JPanel(); 
		String[] nomMenus = {"Partie","Configuration","Aide"};
		String[][] nomSousMenu = {{"Créer partie","Sauvegarder","Charger","Quitter"},{"Thème","Paramètres","Tabous"},{"Règles","Tutoriel","A propos de"}};
		String[] nomBoutons={"Annuler","Refaire"};
		JLabel[] tempsRestant;
		JButton recommencer;
		
		
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
		panels[3].setLayout(new BoxLayout(panels[3],BoxLayout.Y_AXIS));
		noir = new JLabel("Noir",SwingConstants.CENTER);
		nbPieces = new JLabel[2];
		nbPieces[0] = new JLabel("60");
		aide = new JButton[2];
		aide[0] = new JButton("Aide Coup");
		tempsRestant = new JLabel[2];
		tempsRestant[0] = new JLabel("Temps restant : 30");
		panels[3].add(noir);
		panels[3].add(nbPieces[0]);
		panels[3].add(aide[0]);
		panels[3].add(tempsRestant[0]);
		
		//Initialisation Blanc
		panels[5].setLayout(new BoxLayout(panels[5], BoxLayout.PAGE_AXIS));
		blanc = new JLabel("Blanc",SwingConstants.CENTER);
		nbPieces[1] = new JLabel("60");
		aide[1] = new JButton("Aide Coup");
		tempsRestant[1] = new JLabel("Temps restant : 30");
		panels[5].add(blanc,"CENTER");
		panels[5].add(nbPieces[1]);
		panels[5].add(aide[1]);
		panels[5].add(tempsRestant[1]);
		
		//Initialisation Panel Ouest
		panels[1].setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//On positionne la case de départ du composant
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    //La taille et la hauteur en largeur
	    gbc.gridheight = 3;
	    gbc.gridwidth = 1;
		panels[1].add(panels[3],gbc);
		//On positionne la case de départ du composant
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    //La taille et la hauteur en largeur
	    gbc.gridheight = 1;
		panels[1].add(panels[4],gbc);
		//On positionne la case de départ du composant
	    gbc.gridx = 0;
	    gbc.gridy = 4;
	    //La taille et la hauteur en largeur
	    gbc.gridheight = 3;
	    gbc.fill = GridBagConstraints.VERTICAL;
	    panels[1].add(panels[5],gbc);
	    
	    //Panel Sud
	    recommencer = new JButton("Recommencer");
	    panels[2].add(recommencer);
		
		//Fin
		p.setPreferredSize(new Dimension(600,600));
		this.setJMenuBar(barreMenu);
		panels[0].setLayout(new BorderLayout());
        panels[1].setBackground(Color.BLUE);
        panels[0].add(panels[1],BorderLayout.LINE_START);
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
		if (m.getRenjou().getListeAnnuler().isEmpty()) 
			boutons[0].setEnabled(false);
		else
			boutons[0].setEnabled(true);
		if (m.getRenjou().getListeRefaire().isEmpty()) 
			boutons[1].setEnabled(false);
		else
			boutons[1].setEnabled(true);
	}
	
	public void creerLayout() {
				//Layout Panel Noir
				panels[3].setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
				panels[3].setMinimumSize(new Dimension(panels[1].getWidth(),0));
				noir.setAlignmentY(CENTER_ALIGNMENT);
				noir.setFont(new Font("Time New Roman",Font.BOLD,30));
				ImageIcon pionNoir = new ImageIcon(new ImageIcon("./Images/"+theme+"/Pion noir.png").getImage().getScaledInstance(100,100,  java.awt.Image.SCALE_SMOOTH));
				nbPieces[0].setIcon(pionNoir);
				System.out.println(this.getWidth());
				nbPieces[0].setHorizontalTextPosition(JLabel.CENTER);
				nbPieces[0].setFont(new Font("Time New Roman",Font.BOLD,40));
				nbPieces[0].setForeground(Color.WHITE);
				panels[3].setBackground(Color.GRAY);
				
				panels[4].setBackground(Color.WHITE);
				//Boutons annuler et refaire
				ImageIcon annuler = new ImageIcon(new ImageIcon("./Images/"+theme+"/Annuler.png").getImage().getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH));
				ImageIcon annulerEclaire = new ImageIcon(new ImageIcon("./Images/"+theme+"/AnnulerEclaire.png").getImage().getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH));
				setIcone(boutons[0],new Color(0,0,0,0),annuler,annulerEclaire);
				//boutons[0].addActionListener();
				ImageIcon refaire = new ImageIcon(new ImageIcon("./Images/"+theme+"/Refaire.png").getImage().getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH));
				ImageIcon refaireEclaire = new ImageIcon(new ImageIcon("./Images/"+theme+"/RefaireEclaire.png").getImage().getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH));
				setIcone(boutons[1],new Color(0,0,0,0),refaire,refaireEclaire);
				
				//Layout Panel Blanc
				blanc.setFont(new Font("Time New Roman",Font.BOLD,30));
				ImageIcon pionBlanc = new ImageIcon(new ImageIcon("./Images/"+theme+"/Pion blanc.png").getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH));
				nbPieces[1].setIcon(pionBlanc);
				nbPieces[1].setHorizontalTextPosition(JLabel.CENTER);
				nbPieces[1].setFont(new Font("Time New Roman",Font.BOLD,40));
				nbPieces[1].setForeground(Color.BLACK);
				panels[5].setBackground(Color.WHITE);
	}

	private void setIcone(JButton b,Color color, ImageIcon normal, ImageIcon rollover) {
		b.setIcon(normal);
		b.setBackground(new Color(0,0,0,0));
		b.setSize(new Dimension(normal.getIconWidth(),normal.getIconHeight()));
		b.setText(null);
		b.setRolloverEnabled(false);
		b.setRolloverIcon(rollover);
		b.setBorderPainted(false);
		b.setBackground(Color.WHITE);
	}
}