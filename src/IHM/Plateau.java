package IHM;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Controleur.Moteur;

public class Plateau extends JPanel {
	private Moteur p;
	public Image imagePlateau;

    public Plateau(Moteur p) {
        this.p = p;
        try {
    		imagePlateau = ImageIO.read(new File("./Images/Grille 15x15 grande.png"));
    	} catch (IOException e) {
    		
    	}
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(imagePlateau, 0, 0, null);
        super.paintComponents(g);
    }
}
