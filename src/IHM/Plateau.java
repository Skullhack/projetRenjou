package IHM;

import java.awt.Dimension;

import Controleur.PionJoue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Enum.TypeCase;

public class Plateau extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IHM ihm;
	private Image imagePlateau;
	private Image imagePionBlanc;
	private Image imagePionNoir;
	private Image imagePionBlancJoue;
	private Image imagePionNoirJoue;
	private int lignes;
	private int colonnes;
	private String theme;
	private ArrayList<PionJoue> historique;
	
    public Plateau(IHM ihm) {
    	this.ihm = ihm;
        this.lignes = ihm.m.getRenjou().getPlateauDeJeu().getLignes();
        this.colonnes = ihm.m.getRenjou().getPlateauDeJeu().getColonnes();
        this.theme = ihm.m.getRenjou().getEmplacementThemes();
        try {
    		imagePlateau = ImageIO.read(new File("src/Images/"+theme+"/Plateau15x15.png"));
    		imagePionBlanc = ImageIO.read(new File("src/Images/"+theme+"/PionBlanc.png"));
    		imagePionNoir = ImageIO.read(new File("src/Images/"+theme+"/PionNoir.png"));
    		imagePionNoirJoue = ImageIO.read(new File("src/Images/"+theme+"/PionNoirJoue.png"));
    		imagePionBlancJoue = ImageIO.read(new File("src/Images/"+theme+"/PionBlancJoue.png"));
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
    
    public void afficherDernier(Graphics g) {
    	this.historique = ihm.m.getRenjou().getListeAnnuler();
    	if (historique.size() > 0) {
    		TypeCase c = historique.get(historique.size()-1).getTypeCase();
    		int colonneDernier = historique.get(historique.size()-1).getCoordonnees().getColonne();
    		int ligneDernier = historique.get(historique.size()-1).getCoordonnees().getLigne();
        
    		Dimension pan = this.getSize();
    	
    		int width = (pan.width)/16;
    		int height = (pan.height)/16;
    		int x = (colonneDernier*width+4)+(width/2);
    		int y = (ligneDernier*height+4)+(height/2);
        
    		if (c == TypeCase.PionNoir)
    			g.drawImage(imagePionNoirJoue, x, y, width, height, null);
    		else
    			g.drawImage(imagePionBlancJoue, x, y, width, height, null);
		}
    }
    
    /*@Override
    public void setBounds(int x, int y, int width, int height) {
    	   int currentWidth = getWidth();
    	   int currentHeight = getHeight();
    	   if (currentWidth!=width || currentHeight!=height) {
    	      // find out which one has changed
    	      if (currentWidth!=width && currentHeight!=height) {  
    	         // both changed, set size to max
    	         width = height = Math.max(width, height);
    	      }
    	      else if (currentWidth==width) {
    	          // height changed, make width the same
    	          width = height;
    	      }
    	      else // currentHeight==height
    	          height = width;
    	   }
    	   super.setBounds(x, y, width, height);
    	}*/

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
        
        afficherDernier(g);
        super.paintComponents(g);
    }
}