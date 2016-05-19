package IHM;

import java.awt.Dimension;
import Controleur.Coordonnees;
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
	private PlateauDeJeu p;
	private Image imagePlateau;
	private Image imagePionBlanc;
	private Image imagePionNoir;
	private int lignes;
	private int colonnes;
	private TypeCase[][] tab;
	private String theme;
	
    public Plateau(PlateauDeJeu p, String theme) {
        this.p = p;
        this.lignes = p.getLignes();
        this.colonnes = p.getColonnes();
        this.tab = p.getPlateau();
        this.theme = theme;
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
        
        //affichage des cases
        for (int i=0; i<lignes; i++) {
            for (int j=0; j<colonnes; j++) {
            	if (tab[i][j] == TypeCase.PionBlanc || tab[i][j] == TypeCase.PionNoir)
            		afficherPion(i, j, tab[i][j], g);
            }
        }
        
        
        super.paintComponents(g);
    }
}