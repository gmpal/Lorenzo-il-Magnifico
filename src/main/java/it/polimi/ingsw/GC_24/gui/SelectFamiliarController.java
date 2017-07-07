package it.polimi.ingsw.GC_24.gui;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SelectFamiliarController {

	public ImageView blackFamiliar;
	public ImageView whiteFamiliar;
	public ImageView orangeFamiliar;
	public ImageView neutralFamiliar;
	
	public Label blackFamiliarValue;
	public Label whiteFamiliarValue;
	public Label orangeFamiliarValue;
	public Label neutralFamiliarValue;

	public TextField servantsTextField;
		
	public Button submitFamiliarButton;
	public Button cancelFamiliarButton;
	private MainClass mainClass;
	
	public void chooseFamiliar(MouseEvent clickOnFamiliar) {
		if (clickOnFamiliar.getSource()==blackFamiliar) {
			whiteFamiliar.setVisible(false);
			orangeFamiliar.setVisible(false);
			neutralFamiliar.setVisible(false);
			
		}
		if (clickOnFamiliar.getSource()==whiteFamiliar) {
			blackFamiliar.setVisible(false);
			orangeFamiliar.setVisible(false);
			neutralFamiliar.setVisible(false);
		}
		if (clickOnFamiliar.getSource()==orangeFamiliar) {
			whiteFamiliar.setVisible(false);
			blackFamiliar.setVisible(false);
			neutralFamiliar.setVisible(false);
		}
		if (clickOnFamiliar.getSource()==neutralFamiliar) {
			whiteFamiliar.setVisible(false);
			orangeFamiliar.setVisible(false);
			blackFamiliar.setVisible(false);
		}
		
	}
	
	
	public void setMainApp(MainClass mainClass) {
		this.mainClass = mainClass;
		
	}
}
