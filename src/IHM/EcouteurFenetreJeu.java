package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import Controleur.Moteur;

public class EcouteurFenetreJeu implements ActionListener  {
	Moteur m;
	
	public EcouteurFenetreJeu(Moteur m) {
		this.m = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();;
		if (action.equals("Annuler"))
			 m.annuler();
		else if (action.equals("Refaire"))
			m.refaire();
		else if (action.equals("Charger"))
			charger();
		else if (action.equals("Sauvegarder"))
			sauvegarder();
		else if (action.equals("Quitter"))
			System.exit(0);
		else if (action.equals("Recommencer"))
			m.recommencerPartie();
		else
			System.out.println("Erreur Mauvais Bouton");
	}
	
	private void sauvegarder() {
		String fichierSauvegarder = null;
		JFileChooser ouvertureFenetre = new JFileChooser();
		int checkEtatFenetre = ouvertureFenetre.showOpenDialog(null);
		if (checkEtatFenetre == ouvertureFenetre.APPROVE_OPTION) {
			fichierSauvegarder = ouvertureFenetre.getSelectedFile().getAbsolutePath();
			m.sauvegarder(fichierSauvegarder);
		}

	}

	private void charger() {
		String fichierCharger = null;
		JFileChooser ouvertureFenetre = new JFileChooser();
		int checkEtatFenetre = ouvertureFenetre.showOpenDialog(null);
		if (checkEtatFenetre == ouvertureFenetre.APPROVE_OPTION) {
			fichierCharger = ouvertureFenetre.getSelectedFile().getAbsolutePath();
			m.charger(fichierCharger);
		}

	}
}
