package IHM;

import java.awt.Dimension;
import Controleur.Coordonnees;
import Controleur.Moteur;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Controleur.PlateauDeJeu;
import Enum.TypeCase;

public class Plateau extends JPanel {
	private IHM ihm;
	private Image imagePlateau;
	private Image imagePionBlanc;
	private Image imagePionNoir;
	private int lignes;
	private int colonnes;
	private String theme;
	
    public Plateau(IHM ihm) {
    	this.ihm = ihm;
        this.lignes = ihm.m.getRenjou().getPlateauDeJeu().getLignes();
        this.colonnes = ihm.m.getRenjou().getPlateauDeJeu().getColonnes();
        this.theme = ihm.m.getRenjou().getEmplacementThemes();;
        try {
    		imagePlateau = ImageIO.read(new File("./Images/"+theme+"/Plateau 15x15.png"));
    		imagePionBlanc = ImageIO.read(new File("./Images/"+theme+"/Pion blanc.png"));
    		imagePionNoir = ImageIO.read(new File("./Images/"+theme+"/Pion noir.png"));
    	} catch (IOException e) {
    		
    	}
    }

    public void afficherPion(int i, int j, TypeCase c, Graphics g) {
    	Dimension pan = this.getSize();
    	
    	int width = (pan.width)/16;
    	int height = (pan.height)/16;
    	int x = (i*width+4)+(width/2);
        int y = (j*height+4)+(height/2);
        
        if (c == TypeCase.PionBlanc)
        	g.drawImage(imagePionBlanc, x, y, width, height, null);
        else
        	g.drawImage(imagePionNoir, x, y, width, height, null);
    }

    @Override
    public void paintComponent(Graphics g) {
    	//affichage du plateau
        g.drawImage(imagePlateau, 0, 0, this.getWidth(), this.getHeight(), null);
        
        TypeCase[][] tab = ihm.m.getRenjou().getPlateauDeJeu().getPlateau();
        //affichage des cases
        for (int i=0; i<lignes; i++) {
            for (int j=0; j<colonnes; j++) {
            	if (tab[i][j] == TypeCase.PionBlanc || tab[i][j] == TypeCase.PionNoir)
            		afficherPion(j, i, tab[i][j], g);
            }
        }
        super.paintComponents(g);
    }
}