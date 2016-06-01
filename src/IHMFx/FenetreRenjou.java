package IHMFx;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FenetreRenjou extends Stage{	
	private EcouteurFenetreRenjou efr;
	
	public FenetreRenjou(EcouteurFenetreRenjou efr) throws IOException {
        this.efr = efr;
		FXMLLoader fx = new FXMLLoader(getClass().getResource("LayoutFenetreRenjou.fxml"));
		fx.setController(efr);
		Parent root = fx.load();
    	this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Renjou");
        this.setScene(new Scene(root, 600, 400));
        this.centerOnScreen();
        EventHandler<WindowEvent> eh = new EventHandler<WindowEvent>() {
        public void handle(WindowEvent we) {
                we.consume();
        		int confirm = JOptionPane.showOptionDialog(
       	             null, "Etes vous sur de vouloir quitter ?", 
       	             "Quitter le jeu", JOptionPane.YES_NO_OPTION, 
       	             JOptionPane.QUESTION_MESSAGE, null, null, null);
       	        if (confirm == 0) {
       	           System.exit(0);
       	        }
            }
        };
        this.setOnCloseRequest(eh);
	}
	
	public void montrer() {
		this.show();
	}
	
	public void cacher() {
		this.hide();
	}
}
