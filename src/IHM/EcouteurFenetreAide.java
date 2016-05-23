package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import Controleur.Moteur;
import Enum.TypeJoueur;
import Utilitaire.Tabou;

import javax.swing.JFrame;

public class EcouteurFenetreAide implements ActionListener {

    JFrame[] frames;
    Moteur m;

    public EcouteurFenetreAide(IHM ihm) {
        this.m = ihm.m;
        this.frames = ihm.frames;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();;
        if (action.equals("Retour")) {
            retour();
        } else {
            System.out.println("Erreur Mauvais Bouton");
        }
    }

    public void retour() {
        frames[0].setEnabled(true);
        frames[2].setVisible(false);
        frames[2].setAlwaysOnTop(false);
    }
}
