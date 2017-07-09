package it.polimi.ingsw.GC_24.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrivilegeController {

	private static MainClass mainClass;

	@FXML
	private ImageView privilege = new ImageView();
	@FXML
	private RadioButton privilegeRadioButton1 = new RadioButton();
	@FXML
	private RadioButton privilegeRadioButton2 = new RadioButton();
	@FXML
	private RadioButton privilegeRadioButton3 = new RadioButton();
	@FXML
	private RadioButton privilegeRadioButton4 = new RadioButton();
	@FXML
	private RadioButton privilegeRadioButton5 = new RadioButton();
	@FXML
	private Button submitPrivilegeButton = new Button();
	@FXML
	private ToggleGroup privilegeRadioButtons = new ToggleGroup();

	public void chooseCouncilPrivilege(ActionEvent eventPrivilege) throws IOException {
		if (eventPrivilege.getSource() == submitPrivilegeButton) {
			if (privilegeRadioButtons.getSelectedToggle() == privilegeRadioButton1) {
				mainClass.setChosenPrivilege("1");
			}
			if (privilegeRadioButtons.getSelectedToggle() == privilegeRadioButton2) {
				mainClass.setChosenPrivilege("2");
			}
			if (privilegeRadioButtons.getSelectedToggle() == privilegeRadioButton3) {
				mainClass.setChosenPrivilege("3");
			}
			if (privilegeRadioButtons.getSelectedToggle() == privilegeRadioButton4) {
				mainClass.setChosenPrivilege("4");
			}
			if (privilegeRadioButtons.getSelectedToggle() == privilegeRadioButton5) {
				mainClass.setChosenPrivilege("5");
			}
			
		}	
		
		Stage actualStage = (Stage) submitPrivilegeButton.getScene().getWindow();
		actualStage.close();
	}
	
}
