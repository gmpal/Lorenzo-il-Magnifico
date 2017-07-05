package it.polimi.ingsw.GC_24.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class IntroController {

	
	private String viewInterface;
	private MainClass mainClass;

	@FXML
	private ToggleGroup networkGroup;

	@FXML
	private ToggleGroup interfaceGroup;

	@FXML
	private TextField nameTextField;
	@FXML
	private Button sendInfoButton;

	private Stage prevStage;
	@FXML
	private RadioButton interfaceGUI;
	@FXML
	private RadioButton interfaceCLI;
	@FXML
	private RadioButton networkSocket;
	@FXML
	private RadioButton networkRMI;

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	@FXML
	public void handleSendInfoButton(ActionEvent event) {
		if (event.getSource() == sendInfoButton) {
			String name = nameTextField.getText();
			mainClass.setNameChosen(name);
			if (networkGroup.getSelectedToggle() == networkSocket) {
				mainClass.setNetworkChosen("SOC");
				System.out.println("+++++++"+mainClass.getNetworkChosen());
			}
			if (networkGroup.getSelectedToggle() == networkRMI) {
				mainClass.setNetworkChosen("RMI");
				System.out.println("+++++++"+mainClass.getNetworkChosen());
			}
			if (interfaceGroup.getSelectedToggle() == interfaceCLI) {
				mainClass.setInterfaceChosen("CLI");
				System.out.println("+++++++"+mainClass.getInterfaceChosen());
			}
			if (interfaceGroup.getSelectedToggle() == interfaceGUI) {
				mainClass.setInterfaceChosen("GUI");
				System.out.println("+++++++"+mainClass.getInterfaceChosen());
			}

			
			
			
			if (interfaceGroup.getSelectedToggle()==null || networkGroup.getSelectedToggle() == null || name.equals("")) {
				showWarning();
			} else {
				System.out.println("Your name is " + name);
				System.out.println(mainClass.getInterfaceChosen() + " " + mainClass.getNetworkChosen());
				Stage stage = (Stage) sendInfoButton.getScene().getWindow();
				mainClass.createView();
				mainClass.createNetwork();
				mainClass.sendName();
				stage.close();
			
			}
			
			
		}
	}



	public void showWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Please, fill ALL the required fields");

		alert.showAndWait();
		
	}

	public String getViewInterface() {
		return viewInterface;
	}

	public void setMainApp(MainClass mainClass) {
		this.mainClass = mainClass;

	}
}
