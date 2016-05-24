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
	private Image annulerDrag;
	private Image refaire;
	private Image refaireDrag;
	private Image plateau;
	private Image pionNoir;
	private Image pionBlanc;
	private Image pionNoirJoue;
	private Image pionBlancJoue;
	
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
		annulerDrag = new Image("./Images/"+theme+"/AnnulerDrag.png");
		refaire = new Image("./Images/"+theme+"/Refaire.png");
		refaireDrag = new Image("./Images/"+theme+"/RefaireDrag.png");
		plateau = new Image("./Images/"+theme+"/Plateau 15x15.png");
		pionNoir = new Image("./Images/"+theme+"/Pion noir.png");
		pionBlanc = new Image("./Images/"+theme+"/Pion blanc.png");
		pionNoirJoue = new Image("./Images/"+theme+"/Pion noir joue.png");
		pionBlancJoue = new Image("./Images/"+theme+"/Pion blanc joue.png");
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
	
	public Image getAnnulerDrag() {
		return annulerDrag;
	}
	
	public Image getRefaire() {
		return refaire;
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
}
