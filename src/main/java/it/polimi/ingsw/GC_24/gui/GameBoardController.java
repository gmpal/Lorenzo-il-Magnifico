package it.polimi.ingsw.GC_24.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameBoardController implements Initializable {
	public Stage prevStage;
	
	public int chosenFamiliar;

	public ArrayList<ImageView> territories = new ArrayList<>();
	public ArrayList<ImageView> Buildings = new ArrayList<>();
	public ArrayList<ImageView> characters = new ArrayList<>();
	public ArrayList<ImageView> Ventures = new ArrayList<>();

	public ArrayList<ImageView> territoriesButtons = new ArrayList<>();
	public ArrayList<ImageView> BuildingsButtons = new ArrayList<>();
	public ArrayList<ImageView> charactersButtons = new ArrayList<>();
	public ArrayList<ImageView> VenturesButtons = new ArrayList<>();

	public ArrayList<ImageView> personalTerritories = new ArrayList<>();
	public ArrayList<ImageView> personalBuildings = new ArrayList<>();
	public ArrayList<ImageView> personalCharacters = new ArrayList<>();
	public ArrayList<ImageView> personalVentures = new ArrayList<>();

	public ImageView territories01;
	public ImageView territories02;
	public ImageView territories03;
	public ImageView territories04;
	public ImageView buildings01;
	public ImageView buildings02;
	public ImageView buildings03;
	public ImageView buildings04;
	public ImageView characters01;
	public ImageView characters02;
	public ImageView characters03;
	public ImageView characters04;
	public ImageView ventures01;
	public ImageView ventures02;
	public ImageView veentures03;
	public ImageView veentures04;

	public ImageView personalTerritories01;
	public ImageView personalTerritories02;
	public ImageView personalTerritories03;
	public ImageView personalTerritories04;
	public ImageView personalBuildings01;
	public ImageView personalBuildings02;
	public ImageView personalBuildings03;
	public ImageView personalBuildings04;
	public ImageView personalCharacters01;
	public ImageView personalCharacters02;
	public ImageView personalCharacters03;
	public ImageView personalCharacters04;
	public ImageView personalVentures01;
	public ImageView personalVentures02;
	public ImageView personalVentures03;
	public ImageView personalVentures04;

	public ImageView whiteDie;
	public ImageView blackDie;
	public ImageView orangeDie;

	public ImageView bonusTile;

	public Button buttonTerritory01;
	public Button buttonTerritory02;
	public Button buttonTerritory03;
	public Button buttonTerritory04;
	public Button buttonVentures01;
	public Button buttonVentures02;
	public Button buttonVentures03;
	public Button buttonVentures04;
	public Button buttonCharacters01;
	public Button buttonCharacters02;
	public Button buttonCharacters03;
	public Button buttonCharacters04;
	public Button buttonBuildings01;
	public Button buttonBuildings02;
	public Button buttonBuildings03;
	public Button buttonBuildings04;
	public Button buttonMarket01;
	public Button buttonMarket02;
	public Button buttonMarket03;
	public Button buttonMarket04;
	public Button buttonCouncil;
	public Button buttonProduction;
	public Button buttonHarvest;

	private MainClass mainClass;



	public void towerClickHandler(ActionEvent buttonClick) throws IOException {
		System.out.println("Button clicked");
		Stage stage = new Stage();
		stage.setTitle("Choose Familiar");
		stage.initModality(Modality.APPLICATION_MODAL);
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource("SelectFamiliar.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);

		stage.show();

		if (buttonClick.getSource() == buttonTerritory01) {

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setMainApp(MainClass mainClass) {
		this.mainClass = mainClass;
		
	}

}
