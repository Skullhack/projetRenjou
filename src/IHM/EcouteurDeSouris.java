package IHM;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class EcouteurDeSouris implements MouseListener {
    Plateau p;

    // Ecouteur de souris a besoin de connaitre l'aire de dessin car elle doit
    // changer le message que celle ci affiche
    public EcouteurDeSouris(Plateau p) {
        this.p = p;
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    public void mouseClicked(MouseEvent e) {
        p.traiterClic(e.getPoint());
        p.repaint();
    }
    
    public void mouseReleased(MouseEvent e) {}
}