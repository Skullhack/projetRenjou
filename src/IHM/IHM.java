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

	public void run() {
		// Creation d'une fenetre
		JFrame frame = new JFrame("Renjou");

		int nbJoueurs = 2;
		Moteur donneesJeu = new Moteur(nbJoueurs);

		donneesJeu.afficherPlateauJeu();

		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fixe la taille et on demarre
		frame.setSize(1366, 768);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new IHM());
	}
}