package it.polimi.ingsw.GC_24.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
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
	@FXML
	private Label nameLabel;
	@FXML
	private Label waitingLabel;

	

	@FXML
	public void handleSendInfoButton(ActionEvent event) {
		if (event.getSource() == sendInfoButton) {
			String name = nameTextField.getText();
			mainClass.setNameChosen(name);
			if (networkGroup.getSelectedToggle() == networkSocket) {
				mainClass.setNetworkChosen("SOC");
				}
			if (networkGroup.getSelectedToggle() == networkRMI) {
				mainClass.setNetworkChosen("RMI");
				}
			if (interfaceGroup.getSelectedToggle() == interfaceCLI) {
				mainClass.setInterfaceChosen("CLI");
				}
			if (interfaceGroup.getSelectedToggle() == interfaceGUI) {
				mainClass.setInterfaceChosen("GUI");
				}

			
			
			
			if (interfaceGroup.getSelectedToggle()==null || networkGroup.getSelectedToggle() == null || name.equals("")) {
				showWarning();
			} else {
				mainClass.createView();
				mainClass.createNetwork();
				mainClass.sendName();
				waitingLabel.setText("Waiting for other players...");
				sendInfoButton.setDisable(true);
				if (mainClass.getInterfaceChosen().equals("CLI")) {
					mainClass.getPrimaryStage().close();
					System.out.println("Waiting for other players...");
					
				}
			}
			
			
		}
	}



	public void showWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Field missing");
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
	
	public TextField getNameTextField() {
		return nameTextField;
	}

	public void setNameTextField(TextField nameTextField) {
		this.nameTextField = nameTextField;
	}

	public Label getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(Label nameLabel) {
		this.nameLabel = nameLabel;
	}

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}
}
