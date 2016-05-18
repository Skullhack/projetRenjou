package IHM;

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
	
    public Plateau(PlateauDeJeu p) {
        this.p = p;
        this.lignes = p.getLignes();
        this.colonnes = p.getColonnes();
        this.tab = p.getPlateau();
        try {
    		imagePlateau = ImageIO.read(new File("./Images/Plateau 15x15.png"));
    		imagePionBlanc = ImageIO.read(new File("./Images/Pion blanc.png"));
    		imagePionNoir = ImageIO.read(new File("./Images/Pion noir.png"));
    	} catch (IOException e) {
    		
    	}
    }
    
    public void traiterClic(Point p) {
    	
    }
    
    public void afficherPion(int i, int j, TypeCase c, Graphics g) {
    	
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