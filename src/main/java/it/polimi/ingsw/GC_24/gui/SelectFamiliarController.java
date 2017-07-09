package it.polimi.ingsw.GC_24.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SelectFamiliarController {
	
	
	private static String action;
	private MainClass mainClass;
	private GameBoardController gameBoardController;
	
	@FXML
	private ImageView familiar1= new ImageView();
	@FXML
	private ImageView familiar2= new ImageView();
	@FXML
	private ImageView familiar3= new ImageView();
	@FXML
	private ImageView familiar4= new ImageView();

	@FXML
	private Label familiar1v= new Label();
	@FXML
	private Label familiar2v= new Label();
	@FXML
	private Label familiar3v= new Label();
	@FXML
	private Label familiar4v= new Label();
	
	@FXML
	private TextField servantsTextField;
	@FXML
	private Button plusButton;
	@FXML
	private Button minusButton;

	@FXML
	private Button submitFamiliarButton = new Button();
	@FXML
	private Button cancelFamiliarButton = new Button();
	
	public void chosenFamiliar(MouseEvent clickOnFamiliar) {

		if (clickOnFamiliar.getSource() == familiar1) {
			familiar1.setDisable(true);
			familiar2.setVisible(false);
			familiar3.setVisible(false);
			familiar4.setVisible(false);
			action = "1 " + action;

		}
		if (clickOnFamiliar.getSource() == familiar2) {
			familiar2.setDisable(true);
			familiar1.setVisible(false);
			familiar3.setVisible(false);
			familiar4.setVisible(false);
			action = "2 " + action;
		}
		if (clickOnFamiliar.getSource() == familiar3) {
			familiar3.setDisable(true);
			familiar2.setVisible(false);
			familiar1.setVisible(false);
			familiar4.setVisible(false);
			action = "3 " + action;
		}
		if (clickOnFamiliar.getSource() == familiar4) {
			familiar4.setDisable(true);
			familiar2.setVisible(false);
			familiar3.setVisible(false);
			familiar1.setVisible(false);
			action = "4 " + action;
		}

		submitFamiliarButton.setVisible(true);
	}

	public void submitAction(ActionEvent buttonClick) {
		action = action + " " + servantsTextField.getText();
		System.out.println("Action ready: " + action);
		Stage actualScene = (Stage) submitFamiliarButton.getScene().getWindow();
		actualScene.close();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@"+action);
		mainClass.getView().sendAction(action);
		
		
	}

	public void cancelAction(ActionEvent buttonClick) {
		action = "";
		Stage actualScene = (Stage) cancelFamiliarButton.getScene().getWindow();
		actualScene.close();
	}
	
	public void servantsButtonPressed(ActionEvent buttonClick) {
		if (buttonClick.getSource() == plusButton) {
			servantsTextField.setText(Integer.toString(Integer.parseInt(servantsTextField.getText()) + 1));
		}
		if (buttonClick.getSource() == minusButton) {
			if (!servantsTextField.getText().equals("0")) {
				servantsTextField.setText(Integer.toString(Integer.parseInt(servantsTextField.getText()) - 1));
			}
		}
	}

	public MainClass getMainClass() {
		return mainClass;
	}

	public GameBoardController getGameBoardController() {
		return gameBoardController;
	}

	public void setMainClass(MainClass mainClass) {
		this.mainClass = mainClass;
	}

	public void setGameBoardController(GameBoardController gameBoardController) {
		this.gameBoardController = gameBoardController;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public Label getFamiliar1v() {
		return familiar1v;
	}

	public Label getFamiliar2v() {
		return familiar2v;
	}

	public Label getFamiliar3v() {
		return familiar3v;
	}

	public Label getFamiliar4v() {
		return familiar4v;
	}

	public Button getSubmitFamiliarButton() {
		return submitFamiliarButton;
	}

	public void setSubmitFamiliarButton(Button submitFamiliarButton) {
		this.submitFamiliarButton = submitFamiliarButton;
	}

	public ImageView getFamiliar1() {
		return familiar1;
	}

	public ImageView getFamiliar2() {
		return familiar2;
	}

	public ImageView getFamiliar3() {
		return familiar3;
	}

	public ImageView getFamiliar4() {
		return familiar4;
	}

	public void setFamiliar1(ImageView familiar1) {
		this.familiar1 = familiar1;
	}

	public void setFamiliar2(ImageView familiar2) {
		this.familiar2 = familiar2;
	}

	public void setFamiliar3(ImageView familiar3) {
		this.familiar3 = familiar3;
	}

	public void setFamiliar4(ImageView familiar4) {
		this.familiar4 = familiar4;
	}

	public void setFamiliar1v(Label familiar1v) {
		this.familiar1v = familiar1v;
	}

	public void setFamiliar2v(Label familiar2v) {
		this.familiar2v = familiar2v;
	}

	public void setFamiliar3v(Label familiar3v) {
		this.familiar3v = familiar3v;
	}

	public void setFamiliar4v(Label familiar4v) {
		this.familiar4v = familiar4v;
	}

	
}
