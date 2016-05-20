package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controleur.Tabou;

public class ComposantSelectionTabou extends JPanel implements ActionListener{
	private IHM ihm;
	private JList listeTabouDisponible;
	private JList listeTabouSelectionnes;
	private JButton[] boutonSelection;
	private ArrayList<String> tabousTotaux;
	private ArrayList<String> tabousSelectionnes;

	
	public ComposantSelectionTabou(IHM ihm) {
		this.ihm = ihm;
		//Liste Tabou Disponibles
		tabousTotaux = ihm.m.getRenjou().getTabous().getTabouToString();
		
		//Boutons selection
		String [] nomBoutons={"Ajouter","Supprimer"};
		boutonSelection = new JButton[nomBoutons.length];
		for (int i=0; i<nomBoutons.length;i++) {
			boutonSelection[i]=new JButton();
			boutonSelection[i].setActionCommand(nomBoutons[i]);
		}
		
		//Liste Tabou Selectionné
		tabousSelectionnes = getTabouJeuToString();
		listeTabouSelectionnes = new JList(tabousSelectionnes.toArray());
		JScrollPane listScrollerSelectionnes = new JScrollPane(listeTabouSelectionnes);
		
		//On retire les elements en double de tabousTotaux
		ListIterator<String> iterator = tabousTotaux.listIterator();
		while(iterator.hasNext()) {
			String elem = iterator.next();
			for (int i=0;i<tabousSelectionnes.size();i++) {
				if (tabousSelectionnes.get(i).equals(elem)) {
					iterator.remove();
				}
			}
		}
		listeTabouDisponible = new JList(tabousTotaux.toArray());
		JScrollPane listScrollerDispo = new JScrollPane(listeTabouDisponible);

		
		setLayout();
	}
	
	public ArrayList<String> getTabouJeuToString() {
		ArrayList<String> tabousToString = new ArrayList<String>();
		for (int i=0; i<ihm.m.getRenjou().getTabouJeu().size();i++) {
			tabousToString.add(ihm.m.getRenjou().getTabouJeu().get(i).getNom());
		}
		return tabousToString;
	}
	
	public void setLayout() {
		this.add(listeTabouDisponible);
		this.add(boutonSelection[0]);
		this.add(boutonSelection[1]);
		this.add(listeTabouSelectionnes);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
