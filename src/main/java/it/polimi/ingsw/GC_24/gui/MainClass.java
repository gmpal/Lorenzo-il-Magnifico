package it.polimi.ingsw.GC_24.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainClass extends Application {

	private View view;
	private static List<StringProperty> turnList = new ArrayList<StringProperty>();
	private static StringProperty currentPlayer = new SimpleStringProperty();
	private static List<ImageView> imagesToTake = new ArrayList<ImageView>();
	private Client client;
	private Stage primaryStage;
	private Pane rootLayout;

	private String networkChosen;
	private String interfaceChosen;
	private StringProperty nameChosen = new SimpleStringProperty();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

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

	public void updateTurnProperties(List<String> playerTurn) {
		turnList.clear();
		for (String player : playerTurn) {
			StringProperty string = new SimpleStringProperty();
			string.setValue(player);
			turnList.add(string);
		}

		System.out.println("Turni aggiornati nella mainClass");
	}

	public void updateImageViewUrls(String[] urls) {
		for (int i = 0; i < urls.length; i++) {
			imagesToTake.add(new ImageView(urls[i]));
		}
	}

	public void showGameBoard() {
		try {

			primaryStage.close();
			primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainClass.class.getResource("GameBoard.fxml"));
			AnchorPane gameBoard = (AnchorPane) loader.load();

			GameBoardController gameBoardController = loader.getController();
			gameBoardController.setMainApp(this);
			initializeTurnListAndSetBindings(gameBoardController);
/*
			String url = new File("src/main/java/it/polimi/ingsw/GC_24/img/cards/devcards_f_en_c_1.png").toURI()
					.toURL().toString();

			Image image = new Image(url);
			
				Timer t1 = new Timer();
				t1.schedule(new TimerTask() {

					@Override
					public void run() {
						for (int i=0; i<30; i++) {
							imagesToTake.get(i).imageProperty().set(image);
						}
						
					}
					
				}, 5000);
				
				
			
*/
			Scene gameScene = new Scene(gameBoard);
			primaryStage.setScene(gameScene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeTurnListAndSetBindings(GameBoardController gameBoardController) {

		// Initializing the turnList
		StringProperty s = new SimpleStringProperty();
		s.set("");
		for (int i = 0; i < 4; i++) {
			turnList.add(s);
		}

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
		for (int i=0; i<45; i++) {
			imagesToTake.add(new ImageView());
		}
		
		//binding
		for (int i = 0; i < imagesToTake.size(); i++) {
				gameBoardController.getAllTheImages().get(i).imageProperty().bind(imagesToTake.get(i).imageProperty());
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
}