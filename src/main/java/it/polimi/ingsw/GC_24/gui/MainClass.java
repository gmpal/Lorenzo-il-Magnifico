package it.polimi.ingsw.GC_24.gui;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.network.Client;
import it.polimi.ingsw.GC_24.view.View;
import it.polimi.ingsw.GC_24.view.ViewCLI;
import it.polimi.ingsw.GC_24.view.ViewGUI;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainClass extends Application {

	private View view;
	private static List<StringProperty> turnList = new ArrayList<StringProperty>();
	private static StringProperty currentPlayer = new SimpleStringProperty();
	private static StringProperty rankings = new SimpleStringProperty();
	private static List<ImageView> imagesToTake = new ArrayList<ImageView>();
	private static List<Button> buttonstoTake = new ArrayList<Button>();
	
	private Client client;
	private Stage primaryStage;
	private Pane rootLayout;

	private String networkChosen;
	private String interfaceChosen;
	private StringProperty nameChosen = new SimpleStringProperty();
	private StringProperty myCoins = new SimpleStringProperty();
	private StringProperty myWoods = new SimpleStringProperty();
	private StringProperty myServants = new SimpleStringProperty();
	private StringProperty myStones = new SimpleStringProperty();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		StringProperty s = new SimpleStringProperty();
		s.set("");
		for (int i = 0; i < 4; i++) {
			turnList.add(s);
		}
		
		for (int i=0; i<47; i++) {
			imagesToTake.add(new ImageView());
}
		for (int i=0; i<41; i++) {
			buttonstoTake.add(new Button());
}

		this.primaryStage = primaryStage;
		this.client = new Client();
		initRootLayout();
		//showGameBoard();

	}

	public void createView() {
		if (interfaceChosen.equals("CLI")) {
			this.view = new ViewCLI(nameChosen.getValueSafe());
			System.out.println("Creata CLI");
		} else {
			this.view = new ViewGUI(nameChosen.getValueSafe(), this);
			System.out.println("Creata GUI");
		}
	}

	public void createNetwork() {
		try {
			if (networkChosen.equals("SOC")) {
				client.startSocketClient(view);
				System.out.println("Creata SOC");
			} else {
				client.startRMIClient(view);
				System.out.println("Creata RMI");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendName() {
		view.sendPlayerString(nameChosen.getValueSafe());
	}

	public void initRootLayout() {
		try {
			// carico il root layout dal file fxml
			FXMLLoader loader = new FXMLLoader(MainClass.class.getResource("Intro.fxml"));

			rootLayout = (Pane) loader.load();

			IntroController introController = loader.getController();
			introController.setMainApp(this);
			introController.getNameLabel().textProperty().bind(introController.getNameTextField().textProperty());

			// Mostra la scena contenente il root layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateBackgroundButtons(List<String> buttonBackUrls) throws MalformedURLException {
		for (int i = 0; i<buttonstoTake.size(); i++) {
			String url = new File(buttonBackUrls.get(i)).toURI()
					.toURL().toString();
			buttonstoTake.get(i).backgroundProperty().setValue(new Background(new BackgroundImage(new Image(url),null,null,null,null)));
		}
	}
	
	public void updateTurnProperties(List<String> playerTurn) {
		turnList.clear();
		for (String player : playerTurn) {
			StringProperty string = new SimpleStringProperty();
			string.setValue(player);
			turnList.add(string);
		}

		System.out.println("Turni aggiornati nella mainClass");
	}


	public void showGameBoard() {
		try {

			primaryStage.close();
			primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainClass.class.getResource("GameBoard.fxml"));
			TabPane gameBoard = (TabPane) loader.load();

			GameBoardController gameBoardController = loader.getController();
			gameBoardController.setMainApp(this);
			setBindings(gameBoardController);

			Scene gameScene = new Scene(gameBoard);
			primaryStage.setScene(gameScene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private  void setBindings(GameBoardController gameBoardController) {

		// Setting the bindings for players labels
		gameBoardController.getPlayer1label().textProperty().bind(turnList.get(0));
		gameBoardController.getPlayer2label().textProperty().bind(turnList.get(1));
		gameBoardController.getPlayer3label().textProperty().bind(turnList.get(2));
		gameBoardController.getPlayer4label().textProperty().bind(turnList.get(3));

		// Setting the bindings for currentPLayer and This Player
		gameBoardController.getCurrentPlayerLabel().textProperty().bind(currentPlayer);
		gameBoardController.getThisPlayerLabel().textProperty().bind(nameChosen);

		// Setting the bindings for urls
		//initialinizg arraylist imagesToTake
			
		//binding the cards to the imagesToTake array
		for (int i = 0; i < imagesToTake.size(); i++) {
				gameBoardController.getAllTheImages().get(i).imageProperty().bind(imagesToTake.get(i).imageProperty());
		}

		gameBoardController.getRankings().textProperty().bind(rankings);
		
		//bindings rankings
		for ( int i = 0 ; i< buttonstoTake.size(); i++) {
			gameBoardController.getAllTheButtons().get(i).backgroundProperty().bind(buttonstoTake.get(i).backgroundProperty());
			
		}
	}

	public void showSelectPlayer() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainClass.class.getResource("SelectFamiliar.fxml"));
			AnchorPane selectPlayerLayout = (AnchorPane) loader.load();

			SelectFamiliarController selectFamiliarController = loader.getController();
			selectFamiliarController.setMainApp(this);

			Scene selectPlayerScene = new Scene(selectPlayerLayout);
			primaryStage.setScene(selectPlayerScene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showMessage(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public String getNetworkChosen() {
		return networkChosen;
	}

	public void setNetworkChosen(String networkChosen) {
		this.networkChosen = networkChosen;
	}

	public String getInterfaceChosen() {
		return interfaceChosen;
	}

	public void setInterfaceChosen(String interfaceChosen) {
		this.interfaceChosen = interfaceChosen;
	}

	public String getNameChosen() {
		return nameChosen.getValueSafe();
	}

	public void setNameChosen(String nameChosen) {
		this.nameChosen.setValue(nameChosen);
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<StringProperty> getTurnList() {
		return turnList;
	}

	public StringProperty getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(StringProperty currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void updateRankings(String rankings) {
		this.rankings.setValue(rankings);
	}

	public  void updateUrlBoard(ArrayList<String> urlBoard) throws MalformedURLException {
		System.out.println(urlBoard);
		for (int i= 0; i<16;i++) {
			String url = new File(urlBoard.get(i)).toURI()
					.toURL().toString();
			System.out.println("#####"+this.imagesToTake.get(i));
			this.imagesToTake.get(i).imageProperty().set(new Image(url));
		}
	}

	public  void updateUrlPersonalBoard(ArrayList<String> urlPersonalBoard) throws MalformedURLException {
		System.out.println(urlPersonalBoard);
		for (int i= 16; i<44;i++) {
			String url = new File(urlPersonalBoard.get(i-16)).toURI()
					.toURL().toString();
			System.out.println("#####"+this.imagesToTake.get(i));
			this.imagesToTake.get(i).imageProperty().set(new Image(url));
		}
	}

	public  void updateUrlExcommunication(ArrayList<String> urlExcommunication) throws MalformedURLException {
		System.out.println(urlExcommunication);
		for (int i=44; i<47;i++) {
			String url = new File(urlExcommunication.get(i-44)).toURI()
					.toURL().toString();
			System.out.println("#####"+this.imagesToTake.get(i));
			this.imagesToTake.get(i).imageProperty().set(new Image(url));
		}
	}


	public StringProperty getMyCoins() {
		return myCoins;
	}

	public void setMyCoins(StringProperty myCoins) {
		this.myCoins = myCoins;
	}

	public StringProperty getMyWoods() {
		return myWoods;
	}

	public void setMyWoods(StringProperty myWoods) {
		this.myWoods = myWoods;
	}

	public StringProperty getMyServants() {
		return myServants;
	}

	public void setMyServants(StringProperty myServants) {
		this.myServants = myServants;
	}

	public StringProperty getMyStones() {
		return myStones;
	}

	public void setMyStones(StringProperty myStones) {
		this.myStones = myStones;
	}
}