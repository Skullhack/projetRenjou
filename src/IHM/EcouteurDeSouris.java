package IHM;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Controleur.Moteur;
import Enum.TypeJoueur;
import Utilitaire.Coordonnees;

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
    	
    	double widthMax = p.getWidth() - width/1.4;
    	double heightMax = p.getHeight() - height/1.4;
    	
    	if (e.getX()<widthMax && e.getY()<heightMax) {
    		int ligne = (e.getY() - height/2) /height %15;
    		int colonne = (e.getX() - width/2) /width %15;
    		Coordonnees c = new Coordonnees(ligne,colonne);
    		m.operationJouer(c, TypeJoueur.Humain);
    	}
    }
    
    public void mouseReleased(MouseEvent e) {}
}