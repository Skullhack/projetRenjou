package IHM;

import java.awt.BorderLayout;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Controleur.Moteur;

public class FenetreAide extends JFrame implements WindowListener{
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Moteur m;
	    private JTabbedPane tabbedPane;
	    private JPanel[] panels;
	    private JButton[] boutons;
	    private JLabel labelRegle;
	    private EcouteurFenetreAide efa;

	    public FenetreAide(IHM ihm) {
	        this.m = ihm.m;
	        efa = new EcouteurFenetreAide(ihm);
	        this.addWindowListener(this);
	        this.setTitle("Aide");
	        String[] nomBoutons = {"Retour"};
	        //0:PanelTout 1:PanelRegle 2:PanelTuto 3:PanelAPropos 4:PanelBoutons
	        panels = new JPanel[5];
	        for (int i = 0; i < panels.length; i++) {
	            panels[i] = new JPanel();
	        }

	        //Tabs
	        tabbedPane = new JTabbedPane();
	        tabbedPane.addTab("Regles", null, panels[1], "Aide sur r�gles du jeu");
	        tabbedPane.addTab("Tutoriel", null, panels[2], "Tutoriel du jeu");
	        tabbedPane.addTab("A propos de", null, panels[3], "A propos de ce jeu");

	        //Bouton
	        boutons = new JButton[nomBoutons.length];
	        for (int i=0;i<nomBoutons.length;i++) {
	        	boutons[i] = new JButton(nomBoutons[i]);
	        	boutons[i].setActionCommand(nomBoutons[i]);
	        	boutons[i].addActionListener(efa);
	        	panels[4].add(boutons[i]);
	        }
	        
	        //Panel Aide Regle Jeu
	        labelRegle = new JLabel();
	        panels[1].add(labelRegle);
	        
	        //Panel Tutoriel
	        //TODO
	        
	        //Panel A Propos De
	        
	        //PanelTout
	        panels[0].setLayout(new BorderLayout());
	        panels[0].add(tabbedPane, BorderLayout.CENTER);
	        panels[0].add(panels[4],BorderLayout.SOUTH);
	        this.add(panels[0]);
	        
	        creerLayout();
	    }

	    public void creerLayout() {
	        //Panel Aide Regle Jeu
	        ImageIcon imageRegle = new ImageIcon(new ImageIcon("src/Images/"+m.getRenjou().getEmplacementThemes()+"/Regles.png").getImage().getScaledInstance(600, 500, java.awt.Image.SCALE_SMOOTH));
	        labelRegle.setIcon(imageRegle);
	    }
	    
	    public JTabbedPane getTabbedPane() {
	    	return tabbedPane;
	    }

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowClosing(WindowEvent e) {
			efa.retour();
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
}
