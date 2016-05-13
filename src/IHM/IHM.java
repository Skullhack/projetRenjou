/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;


import java.awt.*;
import javax.swing.*;

/**
 *
 * @author givaudav
 */
public class IHM implements Runnable {

	public void run() {
		// Creation d'une fenetre
		JFrame frame = new JFrame("Projet Renjou");

		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fixe la taille et on demarre
		frame.setSize(500, 200);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new IHM());
	}
}