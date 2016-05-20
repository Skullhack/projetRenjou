/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Controleur.Moteur;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author AdrienPort
 */
public class ComposantAffichageRenjou extends JPanel {

    private JButton[] fleches;
    private JLabel[] affichages;
    private Moteur m;
    private int decalage;

    public ComposantAffichageRenjou(Moteur m) {
        fleches = new JButton[2];
        affichages = new JLabel[3];
        String[] nomFleches = {"Precedent", "Suivant"};
        for (int i = 0; i < nomFleches.length; i++) {
            fleches[i] = new JButton();
            fleches[i].setActionCommand(nomFleches[i]);
        }
        for (int i = 0; i < 3; i++) {
            affichages[i] = new JLabel();
        }

        creerLayout();
    }

    public void creerLayout() {
        this.add(fleches[0]);
        for (int i = 0; i < 3; i++) {
            this.add(affichages[i]);
        }
        this.add(fleches[1]);
    }
}
