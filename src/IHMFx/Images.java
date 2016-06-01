package IHMFx;

import java.util.ArrayList;

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
	private Image pionNoirTransparent;
	private Image pionBlanc;
	private Image pionBlancTransparent;
	private Image pionNoirJoue;
	private Image pionBlancJoue;
	private Image boutonValider;
	private Image boutonValiderDrag;
	private Image boutonAnnuler;
	private Image boutonAnnulerDrag;
	private Image boutonRetour;
	private Image boutonRetourDrag;
	private Image fond;
	private Image quatreQuatreImage;
	private Image quatreQuatreExplication;
	private Image quatreQuatreDisab;
	private Image troisTroisImage;
	private Image troisTroisExplication;
	private Image troisTroisDisab;
	private Image sixSeptImage;
	private Image sixSeptExplication;
	private Image sixSeptDisab;
	private Image croixRouge;
	private Image cercleVert;
	private Image cercleVertPlein;
	private Image imageVide;
	private Image flecheHaut;
	private Image flecheHautDisab;
	private Image flecheHautDrag;
	private Image flecheBas;
	private Image flecheBasDisab;
	private Image flecheBasDrag;
	private Image partieRapide;
	private Image partieRapideDrag;
	private Image partiePersonnalisee;
	private Image partiePersonnaliseeDrag;
	private Image chargerPartie;
	private Image chargerPartieDrag;
	private Image lancerTutoriel;
	private Image lancerTutorielDrag;
	private Image quitter;
	private Image quitterDrag;
	private ArrayList<Image> imagesTuto;
	
	
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
		pionNoirTransparent = new Image("./Images/"+theme+"/PionNoirTransparent.png");
		pionBlanc = new Image("./Images/"+theme+"/PionBlanc.png");
		pionBlancTransparent = new Image("./Images/"+theme+"/PionBlancTransparent.png");
		pionNoirJoue = new Image("./Images/"+theme+"/PionNoirJoue.png");
		pionBlancJoue = new Image("./Images/"+theme+"/PionBlancJoue.png");
		boutonValider = new Image("./Images/"+theme+"/BoutonValider.png");
		boutonValiderDrag = new Image("./Images/"+theme+"/BoutonValiderDrag.png");
		boutonAnnuler = new Image("./Images/"+theme+"/BoutonAnnuler.png");
		boutonAnnulerDrag = new Image("./Images/"+theme+"/BoutonAnnulerDrag.png");
		boutonRetour = new Image("./Images/"+theme+"/BoutonRetour.png");
		boutonRetourDrag = new Image("./Images/"+theme+"/BoutonRetourDrag.png");
		fond = new Image("./Images/"+theme+"/Fond.png");
		flecheHaut = new Image("./Images/"+theme+"/FlecheHaut.png");
		flecheHautDisab = new Image("./Images/"+theme+"/FlecheHautDisab.png");
		flecheHautDrag = new Image("./Images/"+theme+"/FlecheHautDrag.png");
		flecheBas = new Image("./Images/"+theme+"/FlecheBas.png");
		flecheBasDisab = new Image("./Images/"+theme+"/FlecheBasDisab.png");
		flecheBasDrag = new Image("./Images/"+theme+"/FlecheBasDrag.png");
		quatreQuatreImage = new Image("./Images/PourToutLesThemes/QUATRE-QUATREImage.png");
		quatreQuatreExplication = new Image("./Images/PourToutLesThemes/QUATRE-QUATREExplication.png");
		quatreQuatreDisab = new Image("./Images/PourToutLesThemes/QUATRE-QUATREDisab.png");
		troisTroisImage = new Image("./Images/PourToutLesThemes/TROIS-TROISImage.png");
		troisTroisExplication = new Image("./Images/PourToutLesThemes/TROIS-TROISExplication.png");
		troisTroisDisab = new Image("./Images/PourToutLesThemes/TROIS-TROISDisab.png");
		sixSeptImage = new Image("./Images/PourToutLesThemes/SIX-SEPTImage.png");
		sixSeptExplication = new Image("./Images/PourToutLesThemes/SIX-SEPTExplication.png");
		sixSeptDisab = new Image("./Images/PourToutLesThemes/SIX-SEPTDisab.png");
		croixRouge = new Image("./Images/PourToutLesThemes/CroixRouge.png");
		cercleVert = new Image("./Images/PourToutLesThemes/CercleVert.png");
		cercleVertPlein = new Image("./Images/PourToutLesThemes/CercleVertPlein.png");
		imageVide = new Image("./Images/PourToutLesThemes/ImageVide.png");
		partieRapide = new Image("./Images/PourToutLesThemes/PartieRapide.png");
		partieRapideDrag = new Image("./Images/PourToutLesThemes/PartieRapideDrag.png");
		partiePersonnalisee = new Image("./Images/PourToutLesThemes/PartiePersonnalisee.png");
		partiePersonnaliseeDrag = new Image("./Images/PourToutLesThemes/PartiePersonnaliseeDrag.png");
		chargerPartie = new Image("./Images/PourToutLesThemes/ChargerPartie.png");
		chargerPartieDrag = new Image("./Images/PourToutLesThemes/ChargerPartieDrag.png");
		lancerTutoriel = new Image("./Images/PourToutLesThemes/LancerTutoriel.png");
		lancerTutorielDrag = new Image("./Images/PourToutLesThemes/LancerTutorielDrag.png");
		quitter = new Image("./Images/PourToutLesThemes/Quitter.png");
		quitterDrag = new Image("./Images/PourToutLesThemes/QuitterDrag.png");
		imagesTuto = new ArrayList<>();
		for (int i=1;i<13;i++) {
			imagesTuto.add(new Image("./Images/Tutoriel/Tutoriel"+i+".png"));
		}
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
	
	public Image getPionNoirTransparent() {
		return pionNoirTransparent;
	}
	
	public Image getPionBlanc() {
		return pionBlanc;
	}
	
	public Image getPionBlancTransparent() {
		return pionBlancTransparent;
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
	
	public Image getFond() {
		return fond;
	}
	
	public Image getQuatreQuatreImage() {
		return quatreQuatreImage;
	}
	public Image getQuatreQuatreExplication() {
		return quatreQuatreExplication;
	}
	
	public Image getQuatreQuatreDisab() {
		return quatreQuatreDisab;
	}
	
	public Image getTroisTroisImage() {
		return troisTroisImage;
	}
	
	public Image getTroisTroisExplication() {
		return troisTroisExplication;
	}
	
	public Image getTroisTroisDisab() {
		return troisTroisDisab;
	}
	
	public Image getSixSeptImage() {
		return sixSeptImage;
	}
	
	public Image getSixSeptExplication() {
		return sixSeptExplication;
	}
	
	public Image getSixSeptDisab() {
		return sixSeptDisab;
	}
	
	public Image getCroixRouge() {
		return croixRouge;
	}
	
	public Image getCercleVert() {
		return cercleVert;
	}
	
	public Image getCercleVertPlein() {
		return cercleVertPlein;
	}
	
	public Image getImageVide() {
		return imageVide;
	}
	
	public Image getFlecheHaut() {
		return flecheHaut;
	}
	
	public Image getFlecheHautDisab() {
		return flecheHautDisab;
	}
	
	public Image getFlecheHautDrag() {
		return flecheHautDrag;
	}
	
	public Image getFlecheBas() {
		return flecheBas;
	}
	
	public Image getFlecheBasDisab() {
		return flecheBasDisab;
	}
	
	public Image getFlecheBasDrag() {
		return flecheBasDrag;
	}
	
	public Image getPartieRapide() {
		return partieRapide;
	}
	
	public Image getPartieRapideDrag() {
		return partieRapideDrag;
	}
	
	public Image getPartiePersonnalisee() {
		return partiePersonnalisee;
	}
	
	public Image getPartiePersonnaliseeDrag() {
		return partiePersonnaliseeDrag;
	}
	
	public Image getChargerPartie() {
		return chargerPartie;
	}
	
	public Image getChargerPartieDrag() {
		return chargerPartieDrag;
	}
	
	public Image getLancerTutoriel() {
		return lancerTutoriel;
	}
	
	public Image getLancerTutorielDrag() {
		return lancerTutorielDrag;
	}
	
	public Image getQuitter() {
		return quitter;
	}
	
	public Image getQuitterDrag() {
		return quitterDrag;
	}
	public ArrayList<Image> getImagesTuto() {
		return imagesTuto;
	}
}
