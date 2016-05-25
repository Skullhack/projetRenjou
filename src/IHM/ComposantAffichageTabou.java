/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Images.*;
import Controleur.Moteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author AdrienPort
 */
public class ComposantAffichageTabou extends JPanel implements ActionListener{

    private JButton[] fleches;
    private JLabel[] affichages;
    private Moteur m;
    private int decalage;

    public ComposantAffichageTabou(Moteur m) {
    	this.m = m;
//    	this.decalage = 0;
//        fleches = new JButton[2];
//        affichages = new JLabel[3];
//        String[] nomFleches = {"Precedent", "Suivant"};
//        for (int i = 0; i < nomFleches.length; i++) {
//            fleches[i] = new JButton();
//            fleches[i].setActionCommand(nomFleches[i]);
//            fleches[i].addActionListener(this);
//        }
//        for (int i = 0; i < 3; i++) {
//            affichages[i] = new JLabel();
//        }
//
//        creerLayout();
//		//this.repaint();
    }

//    public void creerLayout() {
//        this.add(fleches[0]);
//        for (int i = 0; i < 3; i++) {
//            this.add(affichages[i]);
//        }
//        this.add(fleches[1]);
//        fleches[0].setEnabled(false);
//        if (m.getRenjou().getTabouJeu().size() < 4) {
//            fleches[1].setEnabled(false);
//        }
//        this.repeindre();
//    }
//    
//	@Override
	public void actionPerformed(ActionEvent e) {
//		String action = e.getActionCommand();;
//        if (action.equals("Precedent")) {
//            if (decalage > 0) {
//            	decalage--;
//        		this.repeindre();
//            }	
//        } else if (action.equals("Suivant")) {
//        	if (3+decalage < (m.getRenjou().getTabouJeu().size())) {
//        		decalage++;
//        		this.repeindre();
//        	}
//        }
//        
//        if (decalage==0) {
//            fleches[0].setEnabled(false);
//        } else {
//            fleches[0].setEnabled(true);
//        }
//        if (3+decalage >= (m.getRenjou().getTabouJeu().size())) {
//            fleches[1].setEnabled(false);
//        } else {
//            fleches[1].setEnabled(true);
//        }
	}
//	
//	public JButton getRenjouPrecedent() {
//		return fleches[0];
//	}
//	
//	public JButton getRenjouSuivant() {
//		return fleches[1];
//	}
//	
//	public void repeindre() {
//		if ((m.getRenjou().getTabouJeu().get(0+decalage)) != null)  {
//			ImageIcon img1 = new ImageIcon(new ImageIcon("src/Images/Tabous/"+m.getRenjou().getTabouJeu().get(0+decalage).getNom()+"Image.png").getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH));
//			if  (img1 != null)
//				affichages[0].setIcon(img1);
//		}
//		if ((m.getRenjou().getTabouJeu().get(1+decalage)) != null)  {
//			ImageIcon img2 = new ImageIcon(new ImageIcon("src/Images/Tabous/"+m.getRenjou().getTabouJeu().get(1+decalage).getNom()+"Image.png").getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH));
//			if  (img2 != null)
//				affichages[1].setIcon(img2);
//		}
//		if ((m.getRenjou().getTabouJeu().get(1+decalage)) != null)  {
//			ImageIcon img3 = new ImageIcon(new ImageIcon("src/Images/Tabous/"+m.getRenjou().getTabouJeu().get(2+decalage).getNom()+"Image.png").getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH));getInsets();
//			if  (img3 != null)
//				affichages[2].setIcon(img3);
//		}
//	}
}
