/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;


import java.awt.*;
import javax.swing.*;

import Controleur.Moteur;
import Controleur.MoteurObserveur;
import Enum.TypeJoueur;

/**
 *
 * @author givaudav
 */
public class IHM implements Runnable, MoteurObserveur {
	JFrame[] frames;
	Moteur m;
 	
	public void run() {
		// Creation d'une fenetre
		frames = new JFrame[2];
		this.m = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);
		this.m.enregistrerObserveur(this);
		frames[0] = new FenetreJeu(this);

		// Un clic sur le bouton de fermeture clos l'application
		frames[0].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// On fixe la taille et on demarre
		frames[0].setSize(1000,700);
        frames[0].setResizable(false);
		frames[0].setLocationRelativeTo(null);
		frames[0].setVisible(true);
		
		frames[1] = new FenetreMenu(this);

		// Un clic sur le bouton de fermeture clos l'application
		frames[1].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// On fixe la taille et on demarre

		frames[1].setSize(1366,768);
		frames[1].setSize(500, 200);
		frames[1].setVisible(false);
		frames[1].setSize(500,350);
                frames[1].setResizable(false);
		frames[1].setLocationRelativeTo(null);
}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new IHM());
	}

	@Override
	public void actualiser() {
		// TODO Auto-generated method stub
		//doit lancer repaint
		m.printTrace(3, "IHM notifiee");
		frames[0].repaint();
	}
}