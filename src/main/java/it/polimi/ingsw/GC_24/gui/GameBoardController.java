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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameBoardController implements Initializable {
	public Stage prevStage;
	private static MainClass mainClass;
	private static String action;

	private ArrayList<ImageView> allTheImages = new ArrayList<>();
	private ArrayList<Button> allTheButtons = new ArrayList<>();

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
	private Label coinsLabel = new Label();
	@FXML
	private Label woodsLabel = new Label();
	@FXML
	private Label servantsLabel = new Label();
	@FXML
	private Label stonesLabel = new Label();
	
	@FXML
	private Button buttonTerritory01 = new Button();
	@FXML
	private Button buttonTerritory02 = new Button();
	@FXML
	private Button buttonTerritory03 = new Button();
	@FXML
	private Button buttonTerritory04 = new Button();
	@FXML
	private Button buttonVentures01 = new Button();
	@FXML
	private Button buttonVentures02 = new Button();
	@FXML
	private Button buttonVentures03 = new Button();
	@FXML
	private Button buttonVentures04 = new Button();
	@FXML
	private Button buttonCharacters01 = new Button();
	@FXML
	private Button buttonCharacters02 = new Button();
	@FXML
	private Button buttonCharacters03 = new Button();
	@FXML
	private Button buttonCharacters04 = new Button();
	@FXML
	private Button buttonBuildings01 = new Button();
	@FXML
	private Button buttonBuildings02 = new Button();
	@FXML
	private Button buttonBuildings03 = new Button();
	@FXML
	private Button buttonBuildings04 = new Button();
	@FXML
	private Button buttonMarket01 = new Button();
	@FXML
	private Button buttonMarket02 = new Button();
	@FXML
	private Button buttonMarket03 = new Button();
	@FXML
	private Button buttonMarket04 = new Button();
	@FXML
	private Button buttonCouncil01 = new Button();
	@FXML
	private Button buttonCouncil02 = new Button();
	@FXML
	private Button buttonCouncil03 = new Button();
	@FXML
	private Button buttonCouncil04 = new Button();
	@FXML
	private Button buttonCouncil05 = new Button();
	@FXML
	private Button buttonCouncil06 = new Button();
	@FXML
	private Button buttonCouncil07 = new Button();

	@FXML
	private Button buttonProduction01 = new Button();
	@FXML
	private Button buttonProduction02 = new Button();
	@FXML
	private Button buttonProduction03 = new Button();
	@FXML
	private Button buttonProduction04 = new Button();
	@FXML
	private Button buttonProduction05 = new Button();
	@FXML
	private Button buttonProduction06 = new Button();
	@FXML
	private Button buttonProduction07 = new Button();

	@FXML
	private Button buttonHarvest01 = new Button();
	@FXML
	private Button buttonHarvest02 = new Button();
	@FXML
	private Button buttonHarvest03 = new Button();
	@FXML
	private Button buttonHarvest04 = new Button();
	@FXML
	private Button buttonHarvest05 = new Button();
	@FXML
	private Button buttonHarvest06 = new Button();
	@FXML
	private Button buttonHarvest07 = new Button();

	@FXML
	private GridPane councilGridPane;
	@FXML
	private GridPane harvestGridPane;
	@FXML
	private GridPane produtionGridPane;


	@FXML
	private ImageView blackDie = new ImageView();
	@FXML
	private ImageView whiteDie= new ImageView();
	@FXML
	private ImageView orangeDie= new ImageView();




	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initializeAllTheImages();
		initializeAllTheButtons();

	}

	private void initializeAllTheButtons() {
		allTheButtons.add(buttonTerritory01);
		allTheButtons.add(buttonTerritory02);
		allTheButtons.add(buttonTerritory03);
		allTheButtons.add(buttonTerritory04);

		allTheButtons.add(buttonCharacters01);
		allTheButtons.add(buttonCharacters02);
		allTheButtons.add(buttonCharacters03);
		allTheButtons.add(buttonCharacters04);

		allTheButtons.add(buttonBuildings01);
		allTheButtons.add(buttonBuildings02);
		allTheButtons.add(buttonBuildings03);
		allTheButtons.add(buttonBuildings04);

		allTheButtons.add(buttonVentures01);
		allTheButtons.add(buttonVentures02);
		allTheButtons.add(buttonVentures03);
		allTheButtons.add(buttonVentures04);

		allTheButtons.add(buttonCouncil01);
		allTheButtons.add(buttonCouncil02);
		allTheButtons.add(buttonCouncil03);
		allTheButtons.add(buttonCouncil04);
		allTheButtons.add(buttonCouncil05);
		allTheButtons.add(buttonCouncil06);
		allTheButtons.add(buttonCouncil07);

		allTheButtons.add(buttonHarvest01);
		allTheButtons.add(buttonHarvest02);
		allTheButtons.add(buttonHarvest03);
		allTheButtons.add(buttonHarvest04);
		allTheButtons.add(buttonHarvest05);
		allTheButtons.add(buttonHarvest06);
		allTheButtons.add(buttonHarvest07);

		allTheButtons.add(buttonProduction01);
		allTheButtons.add(buttonProduction02);
		allTheButtons.add(buttonProduction03);
		allTheButtons.add(buttonProduction04);
		allTheButtons.add(buttonProduction05);
		allTheButtons.add(buttonProduction06);
		allTheButtons.add(buttonProduction07);

		allTheButtons.add(buttonMarket01);
		allTheButtons.add(buttonMarket02);
		allTheButtons.add(buttonMarket03);
		allTheButtons.add(buttonMarket04);

	}

	private void initializeAllTheImages() {
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
		
		allTheImages.add(blackDie);
		allTheImages.add(orangeDie);
		allTheImages.add(whiteDie);

	}



	public void towerClickHandler(ActionEvent buttonClick) throws IOException {
		if (mainClass.getView().isMyTurn()) {
			Stage stage = new Stage();
			stage.setTitle("Choose Familiar");
			stage.initModality(Modality.APPLICATION_MODAL);
			Pane myPane = null;
			FXMLLoader loader = new FXMLLoader(MainClass.class.getResource("SelectFamiliar.fxml"));
			
			myPane = loader.load();
		
			SelectFamiliarController controller = loader.getController();
			
			controller.setGameBoardController(this);
			controller.setMainClass(this.mainClass);
			
			
			
			controller.getSubmitFamiliarButton().setVisible(false);
			
			
			controller.getFamiliar1v().setText(mainClass.getFamily1Value().getValue());
			controller.getFamiliar2v().setText(mainClass.getFamily2Value().getValue());
			controller.getFamiliar3v().setText(mainClass.getFamily3Value().getValue());
			controller.getFamiliar4v().setText(mainClass.getFamily4Value().getValue());
			controller.getFamiliar1().setVisible(mainClass.isFamily1Available());
			controller.getFamiliar2().setVisible(mainClass.isFamily2Available());
			controller.getFamiliar3().setVisible(mainClass.isFamily3Available());
			controller.getFamiliar4().setVisible(mainClass.isFamily4Available());
			
			
	

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

			if (buttonClick.getSource() == buttonHarvest01 || buttonClick.getSource() == buttonHarvest02
					|| buttonClick.getSource() == buttonHarvest03 || buttonClick.getSource() == buttonHarvest04
					|| buttonClick.getSource() == buttonHarvest05 || buttonClick.getSource() == buttonHarvest06
					|| buttonClick.getSource() == buttonHarvest07) {
				action = "harvest 0";
			}

			if (buttonClick.getSource() == buttonProduction01 || buttonClick.getSource() == buttonProduction02
					|| buttonClick.getSource() == buttonProduction03 || buttonClick.getSource() == buttonProduction04
					|| buttonClick.getSource() == buttonProduction05 || buttonClick.getSource() == buttonProduction06
					|| buttonClick.getSource() == buttonProduction07) {
				action = "production 0";
			}

			if (buttonClick.getSource() == buttonCouncil01 || buttonClick.getSource() == buttonCouncil02
					|| buttonClick.getSource() == buttonCouncil03 || buttonClick.getSource() == buttonCouncil04
					|| buttonClick.getSource() == buttonCouncil05 || buttonClick.getSource() == buttonCouncil06
					|| buttonClick.getSource() == buttonCouncil07) {
				action = "council 0";
			}
			controller.setAction(action);
			Scene scene = new Scene(myPane);
			stage.setScene(scene);

			stage.show();
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

	
	
	public void setMainApp(MainClass mainClass) {
		this.mainClass = mainClass;
		System.out.println("------------------> MAIN CLASS SETTED:" +mainClass );
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

	public ArrayList<Button> getAllTheButtons() {
		return allTheButtons;
	}

	public Label getCoinsLabel() {
		return coinsLabel;
	}

	public Label getWoodsLabel() {
		return woodsLabel;
	}

	public Label getServantsLabel() {
		return servantsLabel;
	}

	public Label getStonesLabel() {
		return stonesLabel;
	}
	
	

}