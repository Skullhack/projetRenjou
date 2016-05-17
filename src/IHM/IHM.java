/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;


import java.awt.*;
import javax.swing.*;

import Controleur.Moteur;

/**
 *
 * @author givaudav
 */
public class IHM implements Runnable {
	JFrame[] frames;
	Moteur m;
 	
	public void run() {		
		// Creation d'une fenetre

		frames = new JFrame[2];
		frames[0] = new FenetreJeu(m);

		int nbJoueurs = 2;
		Moteur donneesJeu = new Moteur(nbJoueurs);
		donneesJeu.afficherPlateauJeu();
		// Un clic sur le bouton de fermeture clos l'application
		frames[0].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// On fixe la taille et on demarre
		frames[0].setSize(1366,768);
		frames[0].setLocationRelativeTo(null);
		frames[0].setVisible(true);
		
		frames[1] = new FenetreMenu(m);

		// Un clic sur le bouton de fermeture clos l'application
		frames[1].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// On fixe la taille et on demarre

		frames[1].setSize(1366,768);
		frames[1].setLocationRelativeTo(null);
		frames[1].setSize(500, 200);
		frames[1].setVisible(false);
}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new IHM());
	}
}