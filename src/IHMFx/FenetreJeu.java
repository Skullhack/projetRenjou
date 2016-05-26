package IHMFx;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FenetreJeu extends Stage {
	public FenetreJeu(EcouteurFenetreJeu efj) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("LayoutFenetreJeu.fxml"));
		fx.setController(efj);
		Parent root = fx.load();
    	this.setScene(new Scene(root, 950, 650));
    	this.setTitle("Renjou");
    	this.setResizable(false);
    	this.centerOnScreen();
    	Platform.setImplicitExit(false);
    	EventHandler<WindowEvent> eh = new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
        		int confirm = JOptionPane.showOptionDialog(
       	             null, "Etes vous sur de vouloir quitter ?", 
       	             "Quitter la partie", JOptionPane.YES_NO_OPTION, 
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
}
