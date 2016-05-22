package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import Controleur.Moteur;
import Controleur.Tabou;
import Enum.TypeJoueur;
import javax.swing.JFrame;

public class EcouteurFenetreMenu implements ActionListener {

    JFrame[] frames;
    Moteur m;

    public EcouteurFenetreMenu(IHM ihm) {
        this.m = ihm.m;
        this.frames = ihm.frames;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();;
        if (action.equals("Annuler")) {
            annuler();
        } else if (action.equals("Valider")) {
            valider();
        } else {
            System.out.println("Erreur Mauvais Bouton");
        }
    }

    private void annuler() {
        frames[0].setEnabled(true);
        frames[1].setVisible(false);
        frames[1].setAlwaysOnTop(false);
    }

    private void valider() {
        TypeJoueur[] tab = new TypeJoueur[2];
        FenetreMenu fm = (FenetreMenu) frames[1];
        String action = fm.getCouleur()[0].getSelection().getActionCommand();
        tab[0] = TypeJoueur.valueOf(action);
        action = fm.getCouleur()[1].getSelection().getActionCommand();
        tab[1] = TypeJoueur.valueOf(action);
        if (tab[0] != null && tab[1] != null) {
        	//gerer charger et nouvelle partie : TODO
            m.configurerPartie(tab[0], tab[1], new ArrayList<Tabou>(), fm.getNouvellePartie());
        }
        frames[0].setEnabled(true);
        frames[1].setVisible(false);
        frames[1].setAlwaysOnTop(false);
    }
}
