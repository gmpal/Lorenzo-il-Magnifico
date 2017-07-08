package it.polimi.ingsw.GC_24.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameBoardController implements Initializable {
	public Stage prevStage;
	private MainClass mainClass;
	private static String action;

	private ArrayList<ImageView> allTheImages = new ArrayList<>();


	@FXML
	private ImageView territories01 = new ImageView();
	@FXML
	private ImageView territories02 = new ImageView();
	@FXML
	private ImageView territories03 = new ImageView();
	@FXML
	private ImageView territories04 = new ImageView();
	@FXML
	private ImageView buildings01 = new ImageView();
	@FXML
	private ImageView buildings02 = new ImageView();
	@FXML
	private ImageView buildings03 = new ImageView();
	@FXML
	private ImageView buildings04 = new ImageView();
	@FXML
	private ImageView characters01 = new ImageView();
	@FXML
	private ImageView characters02 = new ImageView();
	@FXML
	private ImageView characters03 = new ImageView();
	@FXML
	private ImageView characters04 = new ImageView();
	@FXML
	private ImageView ventures01 = new ImageView();
	@FXML
	private ImageView ventures02 = new ImageView();
	@FXML
	private ImageView ventures03 = new ImageView();
	@FXML
	private ImageView ventures04 = new ImageView();

	@FXML
	private ImageView personalVentures01 = new ImageView();
	@FXML
	private ImageView personalVentures02 = new ImageView();
	@FXML
	private ImageView personalVentures03 = new ImageView();
	@FXML
	private ImageView personalVentures04 = new ImageView();
	@FXML
	private ImageView personalVentures05 = new ImageView();
	@FXML
	private ImageView personalVentures06 = new ImageView();
	@FXML
	private ImageView personalCharacters01 = new ImageView();
	@FXML
	private ImageView personalCharacters02 = new ImageView();
	@FXML
	private ImageView personalCharacters03 = new ImageView();
	@FXML
	private ImageView personalCharacters04 = new ImageView();
	@FXML
	private ImageView personalCharacters05 = new ImageView();
	@FXML
	private ImageView personalCharacters06 = new ImageView();
	@FXML
	private ImageView personalTerritories01 = new ImageView();
	@FXML
	private ImageView personalTerritories02 = new ImageView();
	@FXML
	private ImageView personalTerritories03 = new ImageView();
	@FXML
	private ImageView personalTerritories04 = new ImageView();
	@FXML
	private ImageView personalTerritories05 = new ImageView();
	@FXML
	private ImageView personalTerritories06 = new ImageView();
	@FXML
	private ImageView personalBuildings01 = new ImageView();
	@FXML
	private ImageView personalBuildings02 = new ImageView();
	@FXML
	private ImageView personalBuildings03 = new ImageView();
	@FXML
	private ImageView personalBuildings04 = new ImageView();
	@FXML
	private ImageView personalBuildings05 = new ImageView();
	@FXML
	private ImageView personalBuildings06 = new ImageView();
	@FXML
	private ImageView leader01 = new ImageView();
	@FXML
	private ImageView leader02 = new ImageView();
	@FXML
	private ImageView leader03 = new ImageView();
	@FXML
	private ImageView leader04 = new ImageView();
	@FXML
	private ImageView personalTile = new ImageView();
	@FXML
	private ImageView excommunication01 = new ImageView();
	@FXML
	private ImageView excommunication02 = new ImageView();
	@FXML
	private ImageView excommunication03 = new ImageView();


	@FXML
	private Label rankings = new Label();



	@FXML
	private Label player1label = new Label();
	@FXML
	private Label player2label = new Label();
	@FXML
	private Label player3label = new Label();
	@FXML
	private Label player4label = new Label();
	@FXML
	private Label thisPlayerLabel = new Label();
	@FXML
	private Label currentPlayerLabel = new Label();


	
	@FXML
	private Button buttonTerritory01;
	@FXML
	private Button buttonTerritory02;
	@FXML
	private Button buttonTerritory03;
	@FXML
	private Button buttonTerritory04;
	@FXML
	private Button buttonVentures01;
	@FXML
	private Button buttonVentures02;
	@FXML
	private Button buttonVentures03;
	@FXML
	private Button buttonVentures04;
	@FXML
	private Button buttonCharacters01;
	@FXML
	private Button buttonCharacters02;
	@FXML
	private Button buttonCharacters03;
	@FXML
	private Button buttonCharacters04;
	@FXML
	private Button buttonBuildings01;
	@FXML
	private Button buttonBuildings02;
	@FXML
	private Button buttonBuildings03;
	@FXML
	private Button buttonBuildings04;
	@FXML
	private Button buttonMarket01;
	@FXML
	private Button buttonMarket02;
	@FXML
	private Button buttonMarket03;
	@FXML
	private Button buttonMarket04;
	@FXML
	private Button buttonCouncil;
	@FXML
	private Button buttonProduction;
	@FXML
	private Button buttonHarvest;

	@FXML
	private ImageView familiar1;
	@FXML
	private ImageView familiar2;
	@FXML
	private ImageView familiar3;
	@FXML
	private ImageView familiar4;


	@FXML
	private Label familiar1Value;
	@FXML
	private Label familiar2Value;
	@FXML
	private Label familiar3Value;
	@FXML
	private Label familiar4Value;

	@FXML
	private TextField servantsTextField;
	@FXML
	private Button plusButton;
	@FXML
	private Button minusButton;

	@FXML
	private Button submitFamiliarButton;
	@FXML
	private Button cancelFamiliarButton;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		allTheImages.add(territories01);
		allTheImages.add(territories02);
		allTheImages.add(territories03);
		allTheImages.add(territories04);

		allTheImages.add(characters01);
		allTheImages.add(characters02);
		allTheImages.add(characters03);
		allTheImages.add(characters04);

		allTheImages.add(buildings01);
		allTheImages.add(buildings02);
		allTheImages.add(buildings03);
		allTheImages.add(buildings04);
		
		allTheImages.add(ventures01);
		allTheImages.add(ventures02);
		allTheImages.add(ventures03);
		allTheImages.add(ventures04);


		allTheImages.add(personalTerritories01);
		allTheImages.add(personalTerritories02);
		allTheImages.add(personalTerritories03);
		allTheImages.add(personalTerritories04);
		allTheImages.add(personalTerritories05);
		allTheImages.add(personalTerritories06);

		allTheImages.add(personalCharacters01);
		allTheImages.add(personalCharacters02);
		allTheImages.add(personalCharacters03);
		allTheImages.add(personalCharacters04);
		allTheImages.add(personalCharacters05);
		allTheImages.add(personalCharacters06);

		allTheImages.add(personalBuildings01);
		allTheImages.add(personalBuildings02);
		allTheImages.add(personalBuildings03);
		allTheImages.add(personalBuildings04);
		allTheImages.add(personalBuildings05);
		allTheImages.add(personalBuildings06);

		allTheImages.add(personalVentures01);
		allTheImages.add(personalVentures02);
		allTheImages.add(personalVentures03);
		allTheImages.add(personalVentures04);
		allTheImages.add(personalVentures05);
		allTheImages.add(personalVentures06);
		
		allTheImages.add(leader01);
		allTheImages.add(leader02);
		allTheImages.add(leader03);
		allTheImages.add(leader04);
		
		allTheImages.add(excommunication01);
		allTheImages.add(excommunication02);
		allTheImages.add(excommunication03);
		
	
	}

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

	}

	public void submitAction(ActionEvent buttonClick) {
		action = action + " " + servantsTextField.getText();
		System.out.println("Action ready: " + action);
		Stage actualScene = (Stage) submitFamiliarButton.getScene().getWindow();
		this.mainClass.getView().sendAction(action);
		actualScene.close();
		
	}

	public void cancelAction(ActionEvent buttonClick) {
		action = "";
		Stage actualScene = (Stage) cancelFamiliarButton.getScene().getWindow();
		actualScene.close();
	}

	public void towerClickHandler(ActionEvent buttonClick) throws IOException {
		if (mainClass.getView().isMyTurn()) {
			Stage stage = new Stage();
			stage.setTitle("Choose Familiar");
			stage.initModality(Modality.APPLICATION_MODAL);
			Pane myPane = null;
			myPane = FXMLLoader.load(getClass().getResource("SelectFamiliar.fxml"));
			Scene scene = new Scene(myPane);
			stage.setScene(scene);

			stage.show();

			if (buttonClick.getSource() == buttonTerritory01) {
				action = "territories 1";
			}
			if (buttonClick.getSource() == buttonTerritory02) {
				action = "territories 2";
			}
			if (buttonClick.getSource() == buttonTerritory03) {
				action = "territories 3";
			}
			if (buttonClick.getSource() == buttonTerritory04) {
				action = "territories 4";
			}

			// buildings buttons
			if (buttonClick.getSource() == buttonBuildings01) {
				action = "buildings 1";
			}
			if (buttonClick.getSource() == buttonBuildings02) {
				action = "buildings 2";
			}
			if (buttonClick.getSource() == buttonBuildings03) {
				action = "buildings 3";
			}
			if (buttonClick.getSource() == buttonBuildings04) {
				action = "buildings 4";
			}

			// ventures buttons
			if (buttonClick.getSource() == buttonVentures01) {
				action = "ventures 1";
			}
			if (buttonClick.getSource() == buttonVentures02) {
				action = "ventures 2";
			}
			if (buttonClick.getSource() == buttonVentures03) {
				action = "ventures 3";
			}
			if (buttonClick.getSource() == buttonVentures04) {
				action = "ventures 4";
			}

			// characters buttons
			if (buttonClick.getSource() == buttonCharacters01) {
				action = "characters 1";
			}
			if (buttonClick.getSource() == buttonCharacters02) {
				action = "characters 2";
			}
			if (buttonClick.getSource() == buttonCharacters03) {
				action = "characters 3";
			}
			if (buttonClick.getSource() == buttonCharacters04) {
				action = "characters 4";
			}

			// market action
			if (buttonClick.getSource() == buttonMarket01) {
				action = "market 1";
			}
			if (buttonClick.getSource() == buttonMarket02) {
				action = "market 2";
			}
			if (buttonClick.getSource() == buttonMarket03) {
				action = "market 3";
			}
			if (buttonClick.getSource() == buttonMarket04) {
				action = "market 4";
			}

			if (buttonClick.getSource() == buttonHarvest) {
				action = "harvest 0";
			}

			if (buttonClick.getSource() == buttonProduction) {
				action = "production 0";
			}

			if (buttonClick.getSource() == buttonCouncil) {
				action = "council 0";
			}
		} else {
			// not my turn
			mainClass.showMessage("Not your turn, you can't do this action");
		}
	}

	public void cardZooming(MouseEvent mouseClick) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Here's your card");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource("CardZooming.fxml"));

		ImageView cardImage = (ImageView) myPane.getChildren().get(0);
		ImageView cardToZoom = (ImageView) mouseClick.getSource();
		cardImage.setImage(cardToZoom.getImage());
		Scene scene = new Scene(myPane);
		stage.setScene(scene);

		stage.show();
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

	public void setMainApp(MainClass mainClass) {
		this.mainClass = mainClass;

	}

	public Label getPlayer1label() {
		return player1label;
	}

	public Label getPlayer2label() {
		return player2label;
	}

	public Label getPlayer3label() {
		return player3label;
	}

	public Label getPlayer4label() {
		return player4label;
	}

	public Label getThisPlayerLabel() {
		return thisPlayerLabel;
	}

	public void setThisPlayerLabel(Label thisPlayerLabel) {
		this.thisPlayerLabel = thisPlayerLabel;
	}

	public Label getCurrentPlayerLabel() {
		return currentPlayerLabel;
	}

	public void setCurrentPlayerLabel(Label currentPlayerLabel) {
		this.currentPlayerLabel = currentPlayerLabel;
	}

	public ArrayList<ImageView> getAllTheImages() {
		return allTheImages;
	}

	public void setAllTheImages(ArrayList<ImageView> allTheImages) {
		this.allTheImages = allTheImages;
	}

	public Label getRankings() {
		return rankings;
	}

	public void setRankings(Label rankings) {
		this.rankings = rankings;
	}

}