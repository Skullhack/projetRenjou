package IHMFx;

import Controleur.Moteur;
import javafx.scene.image.Image;

public class Images {
	private Moteur m;
	private Image ampoule;
	private Image ampouleDrag;
	private Image sablier;
	private Image recommencer;
	private Image recommencerDrag;
	private Image annuler;
	private Image annulerDisab;
	private Image annulerDrag;
	private Image refaire;
	private Image refaireDisab;
	private Image refaireDrag;
	private Image plateau;
	private Image pionNoir;
	private Image pionBlanc;
	private Image pionNoirJoue;
	private Image pionBlancJoue;
	private Image boutonValider;
	private Image boutonValiderDrag;
	private Image boutonAnnuler;
	private Image boutonAnnulerDrag;
	private Image boutonRetour;
	private Image boutonRetourDrag;
	
	public Images(Moteur m) {
		this.m = m;
		setImage();
	}
	
	public void setImage() {
		String theme = m.getRenjou().getEmplacementThemes();
		ampoule = new Image("./Images/"+theme+"/Ampoule.png");
		ampouleDrag = new Image("./Images/"+theme+"/AmpouleDrag.png");
		sablier = new Image("./Images/"+theme+"/Sablier.png");
		recommencer = new Image("./Images/"+theme+"/Recommencer.png");
		recommencerDrag = new Image("./Images/"+theme+"/RecommencerDrag.png");
		annuler = new Image("./Images/"+theme+"/Annuler.png");
		annulerDisab = new Image("./Images/"+theme+"/AnnulerDisab.png");
		annulerDrag = new Image("./Images/"+theme+"/AnnulerDrag.png");
		refaire = new Image("./Images/"+theme+"/Refaire.png");
		refaireDisab = new Image("./Images/"+theme+"/RefaireDisab.png");
		refaireDrag = new Image("./Images/"+theme+"/RefaireDrag.png");
		plateau = new Image("./Images/"+theme+"/Plateau15x15.png");
		pionNoir = new Image("./Images/"+theme+"/PionNoir.png");
		pionBlanc = new Image("./Images/"+theme+"/PionBlanc.png");
		pionNoirJoue = new Image("./Images/"+theme+"/PionNoirJoue.png");
		pionBlancJoue = new Image("./Images/"+theme+"/PionBlancJoue.png");
		boutonValider = new Image("./Images/"+theme+"/BoutonValider.png");
		boutonValiderDrag = new Image("./Images/"+theme+"/BoutonValiderDrag.png");
		boutonAnnuler = new Image("./Images/"+theme+"/BoutonAnnuler.png");
		boutonAnnulerDrag = new Image("./Images/"+theme+"/BoutonAnnulerDrag.png");
		boutonRetour = new Image("./Images/"+theme+"/BoutonRetour.png");
		boutonRetourDrag = new Image("./Images/"+theme+"/BoutonRetourDrag.png");
	}
	
	public Image getAmpoule() {
		return ampoule;
	}
	
	public Image getAmpouleDrag() {
		return ampouleDrag;
	}
	
	public Image getSablier() {
		return sablier;
	}
	
	public Image getRecommencer() {
		return recommencer;
	}
	
	public Image getRecommencerDrag() {
		return recommencerDrag;
	}
	
	public Image getAnnuler() {
		return annuler;
	}
	
	public Image getAnnulerDisab() {
		return annulerDisab;
	}
	
	public Image getAnnulerDrag() {
		return annulerDrag;
	}
	
	public Image getRefaire() {
		return refaire;
	}
	
	public Image getRefaireDisab() {
		return refaireDisab;
	}
	
	public Image getRefaireDrag() {
		return refaireDrag;
	}
	
	public Image getPlateau() {
		return plateau;
	}
	
	public Image getPionNoir() {
		return pionNoir;
	}
	
	public Image getPionBlanc() {
		return pionBlanc;
	}
	
	public Image getPionNoirJoue() {
		return pionNoirJoue;
	}
	
	public Image getPionBlancJoue() {
		return pionBlancJoue;
	}
	
	public Image getBoutonAnnuler() {
		return boutonAnnuler;
	}
	
	public Image getBoutonAnnulerDrag() {
		return boutonAnnulerDrag;
	}
	
	public Image getBoutonValider() {
		return boutonValider;
	}
	
	public Image getBoutonValiderDrag() {
		return boutonValiderDrag;
	}
	
	public Image getBoutonRetour() {
		return boutonRetour;
	}
	
	public Image getBoutonRetourDrag() {
		return boutonRetourDrag;
	}
}
