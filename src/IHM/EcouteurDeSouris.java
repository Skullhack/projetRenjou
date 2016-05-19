package IHM;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Controleur.Coordonnees;
import Controleur.Moteur;
import Enum.TypeJoueur;

class EcouteurDeSouris implements MouseListener {
    Moteur m;
    Plateau p;

    // Ecouteur de souris a besoin de connaitre l'aire de dessin car elle doit
    // changer le message que celle ci affiche
    public EcouteurDeSouris(Moteur m, Plateau p) {
        this.m = m;
        this.p = p;
        p.addMouseListener(this);
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    public void mouseClicked(MouseEvent e) {
    	int width = (p.getWidth())/16;
    	int height = (p.getHeight())/16;
    	int ligne = (e.getY() - height/2) /height %15; 
    	int colonne = (e.getX() - width/2) /width %15; 
    	Coordonnees c = new Coordonnees(colonne,ligne);
    	if (e.getX() < (p.getHeight()-(height/2)) && e.getY() < (p.getWidth()-(width/2)))
    		m.operationJouer(c, TypeJoueur.Humain);
    }
    
    public void mouseReleased(MouseEvent e) {}
}