package IHMFx;

import Controleur.Moteur;
import Controleur.MoteurObserveur;
import Enum.TypeJoueur;
import javafx.application.Application;

import javafx.stage.Stage;
public class IHM extends Application implements MoteurObserveur{
	public Moteur m;
	public EcouteurFenetreJeu efj;
	public EcouteurFenetreMenu efm;
	public EcouteurFenetreAide efa;
	public EcouteurFenetreRenjou efr;
	public Images i;
	public FenetreJeu fj;
	public FenetreMenu fm;
	public FenetreAide fa;
	public FenetreRenjou fr;
	public Stage aideStage;
	public boolean modeTuto = false;
	public int etatTuto = 1;

    public static void main(String[] args) {
        Application.launch(IHM.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
    	m = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);
    	m.commencer();
    	i = new Images(m);	
		//Premiere Fenetre
	    efj = new EcouteurFenetreJeu(this);
	        
	    //Seconde Fenetre
	    efm = new EcouteurFenetreMenu(this);
	        
	    //Troisieme Fenetre
	    efa = new EcouteurFenetreAide(this);
	    
	    //Quatrieme Fenetre
	    efr = new EcouteurFenetreRenjou(this);
	    fr.montrer();
	    
	    m.enregistrerObserveur(this);
	    actualiser();
    }

	@Override
	public void actualiser() {
		efj.update();
		efm.update();
		efa.update();

	}

}