package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import Controleur.Moteur;
import javax.swing.JFrame;

public class EcouteurFenetreJeu implements ActionListener {
    JFrame[] frames;
    Moteur m;

    public EcouteurFenetreJeu(IHM ihm) {
        this.m = ihm.m;
        this.frames = ihm.frames;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FenetreMenu fm = (FenetreMenu) frames[1];
        String action = e.getActionCommand();
        if (action.equals("Annuler")) {
            m.annuler();
        } else if (action.equals("Refaire")) {
            m.refaire();
        } else if (action.equals("Charger")) {
            charger();
        } else if (action.equals("Sauvegarder")) {
            sauvegarder();
        } else if (action.equals("Quitter")) {
            System.exit(0);
        } else if (action.equals("Recommencer")) {
            m.recommencerPartie();
        } else if (action.equals("AideCoup")) {
            System.out.println("AideCoup");
        } else if (action.equals("Joueur")) {
            joueur();
            fm.setNouvellePartie(false);
        } else if (action.equals("Creer partie")){
            joueur();
            fm.setNouvellePartie(true);
        } else if (action.equals("Tabous")){
            tabous();
            fm.setNouvellePartie(false);
        } else if (action.equals("Theme")){
            themes();
            fm.setNouvellePartie(false);
        } else if (action.equals("Regles")){
        	regles();
        } else if (action.equals("Tutoriel")){
        	tutoriel();
        } else if (action.equals("A propos de")){
        	aProposDe();
        } else {
            System.out.println("Erreur Mauvais Bouton");
        }
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

    private void joueur() {
        frames[1].setVisible(true);
        frames[1].setAlwaysOnTop(true);
        frames[0].setEnabled(false);
        FenetreMenu fm = (FenetreMenu) frames[1];
        fm.getTabbedPane().setSelectedIndex(0);
    }
    
    private void tabous() {
        frames[1].setVisible(true);
        frames[1].setAlwaysOnTop(true);
        frames[0].setEnabled(false);
        FenetreMenu fm = (FenetreMenu) frames[1];
        fm.getTabbedPane().setSelectedIndex(1);
    }
    
    private void themes() {
        frames[1].setVisible(true);
        frames[1].setAlwaysOnTop(true);
        frames[0].setEnabled(false);
        FenetreMenu fm = (FenetreMenu) frames[1];
        fm.getTabbedPane().setSelectedIndex(2);
    }
    
    private void regles() {
        frames[2].setVisible(true);
        frames[2].setAlwaysOnTop(true);
        frames[0].setEnabled(false);
        FenetreAide fm = (FenetreAide) frames[2];
        fm.getTabbedPane().setSelectedIndex(0);
    }
    
    private void tutoriel() {
        frames[2].setVisible(true);
        frames[2].setAlwaysOnTop(true);
        frames[0].setEnabled(false);
        FenetreAide fm = (FenetreAide) frames[2];
        fm.getTabbedPane().setSelectedIndex(1);
    }
    
    private void aProposDe() {
        frames[2].setVisible(true);
        frames[2].setAlwaysOnTop(true);
        frames[0].setEnabled(false);
        FenetreAide fm = (FenetreAide) frames[2];
        fm.getTabbedPane().setSelectedIndex(2);
    }
}
