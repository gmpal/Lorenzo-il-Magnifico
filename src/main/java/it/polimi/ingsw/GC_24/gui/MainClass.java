package it.polimi.ingsw.GC_24.gui;

import java.io.IOException;

import it.polimi.ingsw.GC_24.network.Client;
import it.polimi.ingsw.GC_24.view.ViewCLI;
import it.polimi.ingsw.GC_24.view.ViewGUI;
import it.polimi.ingsw.GC_24.view.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainClass extends Application {

	private View view;

	private Client client;
	private Stage primaryStage;
	private Pane rootLayout;

	private String networkChosen;
	private String interfaceChosen;
	private String nameChosen;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.client = new Client();

		initRootLayout();
		

	}

	public void createView() {
		if (interfaceChosen.equals("CLI")) {
			this.view = new ViewCLI();
			System.out.println("Creata CLI");
		} else {
			this.view = new ViewGUI(this);
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
		view.sendPlayerString(nameChosen);
	}

	public void initRootLayout() {
		try {
			// carico il root layout dal file fxml
			FXMLLoader loader = new FXMLLoader(MainClass.class.getResource("Intro.fxml"));

			rootLayout = (Pane) loader.load();

			IntroController introController = loader.getController();
			introController.setMainApp(this);

			// Mostra la scena contenente il root layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showGameBoard() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainClass.class.getResource("GameBoard.fxml"));
			AnchorPane gameBoard = (AnchorPane) loader.load();

			GameBoardController gameBoardController = loader.getController();
			gameBoardController.setMainApp(this);

			Scene gameScene = new Scene(gameBoard);
			primaryStage.setScene(gameScene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
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
		return nameChosen;
	}

	public void setNameChosen(String nameChosen) {
		this.nameChosen = nameChosen;
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
}