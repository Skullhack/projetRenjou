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
    	Coordonnees c = new Coordonnees(e.getPoint());
    	m.operationJouer(c, TypeJoueur.Humain);
        p.repaint();
    }
    
    public void mouseReleased(MouseEvent e) {}
}