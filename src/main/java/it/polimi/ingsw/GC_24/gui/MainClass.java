package it.polimi.ingsw.GC_24.gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
	private StringProperty myColour = new SimpleStringProperty();
	private String chosenPrivilege;
	private boolean family4Available = true;
	private boolean family3Available= true;
	private boolean family2Available= true;
	private boolean family1Available= true;

	private  StringProperty family1Value = new SimpleStringProperty();
	private  StringProperty family2Value = new SimpleStringProperty();
	private  StringProperty family3Value = new SimpleStringProperty();
	private  StringProperty family4Value = new SimpleStringProperty();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		StringProperty s = new SimpleStringProperty();
		s.set("fake");
		for (int i = 0; i < 4; i++) {
			turnList.add(s);
		}

		for (int i = 0; i < 50; i++) {
			imagesToTake.add(new ImageView());
		}
		for (int i = 0; i < 44; i++) {
			buttonstoTake.add(new Button());
		}

		this.primaryStage = primaryStage;
		this.client = new Client();
		initRootLayout();
		// showGameBoard();

	}

	public void createView() {
		if (interfaceChosen.equals("CLI")) {
			this.view = new ViewCLI(nameChosen.getValueSafe());
			
		} else {
			this.view = new ViewGUI(nameChosen.getValueSafe(), this);
			
		}
	}

	public void createNetwork() {
		try {
			if (networkChosen.equals("SOC")) {
				client.startSocketClient(view);
				
			} else {
				client.startRMIClient(view);
				
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
		for (int i = 0; i < buttonBackUrls.size() - 3; i++) {
			String url = new File(buttonBackUrls.get(i)).toURI().toURL().toString();
			buttonstoTake.get(i).backgroundProperty().setValue(new Background(new BackgroundImage(new Image(url),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null)));
		}
		for (int i = buttonBackUrls.size() - 3; i < buttonBackUrls.size(); i++) {
			String url = new File(buttonBackUrls.get(i)).toURI().toURL().toString();
			imagesToTake.get(i + 6).imageProperty().set(new Image(url));
		}
	}

	public void parseValuesString(String values) {
		StringTokenizer tokenizer = new StringTokenizer(values);
		String token = "";
		if (values.contains("woods")) {
			while (!token.equals("woods")) {
				
				token = tokenizer.nextToken();
			}
			tokenizer.nextToken();
			this.myWoods.setValue(tokenizer.nextToken());

		}
		if (values.contains("stones")) {
			while (!token.equals("stones")) {
				token = tokenizer.nextToken();
			}
			tokenizer.nextToken();
			this.myStones.setValue(tokenizer.nextToken());

		}
		if (values.contains("coins")) {
			while (!token.equals("coins")) {
				token = tokenizer.nextToken();
			}
			tokenizer.nextToken();
			this.myCoins.setValue(tokenizer.nextToken());

		}
		if (values.contains("servants")) {
			while (!token.equals("servants")) {
				token = tokenizer.nextToken();
			}
			tokenizer.nextToken();
			this.myServants.setValue(tokenizer.nextToken());

		}
	}

	public void perseFamilyString(String family) {
		family1Available = false;
		family2Available = false;
		family3Available = false;
		family4Available = false;
		StringTokenizer tokenizer = new StringTokenizer(family);
		String token = "";
		if (family.contains("Member 1")) {
			while (!token.equals("Value")) {
				token = tokenizer.nextToken();
			}
			tokenizer.nextToken();
			String valore = tokenizer.nextToken();
			this.family1Value.setValue(valore);
			this.family1Available = true;
		}
		token = "";
		if (family.contains("Member 2")) {
			while (!token.equals("Value")) {
				token = tokenizer.nextToken();
			}
			tokenizer.nextToken();
			String valore = tokenizer.nextToken();
			this.family2Value.setValue(valore);
			this.family2Available = true;

		}
		token = "";
		if (family.contains("Member 3")) {
			while (!token.equals("Value")) {
				token = tokenizer.nextToken();
			}
			tokenizer.nextToken();
			String valore = tokenizer.nextToken();
			this.family3Value.setValue(valore);
			this.family3Available = true;

		}
		token = "";
		if (family.contains("Member 4")) {
			while (!token.equals("Value")) {
				token = tokenizer.nextToken();
			}
			tokenizer.nextToken();
			String valore = tokenizer.nextToken();
			this.family4Value.setValue(valore);
			this.family4Available = true;

		}
		System.out.println(
				family1Value.getValue() + family2Value.getValue() + family3Value.getValue() + family4Value.getValue());
	}

	public void updateTurnProperties(List<String> playerTurn) {
		turnList.clear();
		for (String player : playerTurn) {
			StringProperty string = new SimpleStringProperty();
			string.setValue(player);
			turnList.add(string);
		}
		for (int i = playerTurn.size(); i < 4; i++) {
			StringProperty string = new SimpleStringProperty();
			string.setValue("");
			turnList.add(string);
		}


	}

	public void showGameBoard() {
		try {

			primaryStage.close();
			primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainClass.class.getResource("GameBoard.fxml"));
			ScrollPane gameBoard = (ScrollPane) loader.load();

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

	private void setBindings(GameBoardController gameBoardController) {
		System.out.println(turnList);
		// Setting the bindings for players labels
		gameBoardController.getPlayer1label().textProperty().bind(turnList.get(0));
		gameBoardController.getPlayer2label().textProperty().bind(turnList.get(1));
		gameBoardController.getPlayer3label().textProperty().bind(turnList.get(2));
		gameBoardController.getPlayer4label().textProperty().bind(turnList.get(3));

		// Setting the bindings for currentPLayer and This Player
		gameBoardController.getCurrentPlayerLabel().textProperty().bind(currentPlayer);
		gameBoardController.getThisPlayerLabel().textProperty().bind(nameChosen);

		// Setting the bindings for urls
		// initialinizg arraylist imagesToTake

		// binding the cards to the imagesToTake array
		for (int i = 0; i < imagesToTake.size(); i++) {
			gameBoardController.getAllTheImages().get(i).imageProperty().bind(imagesToTake.get(i).imageProperty());
			System.out.println(imagesToTake.get(i));
			System.out.println(gameBoardController.getAllTheImages().get(i));
		}

		gameBoardController.getRankings().textProperty().bind(rankings);

		// bindings rankings
		for (int i = 0; i < buttonstoTake.size() - 3; i++) {
			gameBoardController.getAllTheButtons().get(i).backgroundProperty()
					.bind(buttonstoTake.get(i).backgroundProperty());

		}

		// binding values
		gameBoardController.getCoinsLabel().textProperty().bind(this.getMyCoins());
		gameBoardController.getServantsLabel().textProperty().bind(this.getMyServants());
		gameBoardController.getWoodsLabel().textProperty().bind(this.getMyWoods());
		gameBoardController.getStonesLabel().textProperty().bind(this.getMyStones());

		System.out.println("Tutti i binding fatti!!");

	}

	public void showMessage(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}



	public void updateUrlBoard(ArrayList<String> urlBoard) throws MalformedURLException {
		System.out.println(urlBoard);
		for (int i = 0; i < 16; i++) {
			String url = new File(urlBoard.get(i)).toURI().toURL().toString();
		
			imagesToTake.get(i).imageProperty().set(new Image(url));
		}
	}

	public void updateUrlPersonalBoard(ArrayList<String> urlPersonalBoard) throws MalformedURLException {
		System.out.println(urlPersonalBoard);
		for (int i = 16; i < 44; i++) {
			String url = new File(urlPersonalBoard.get(i - 16)).toURI().toURL().toString();
			
			imagesToTake.get(i).imageProperty().set(new Image(url));
		}
	}

	public void updateUrlExcommunication(ArrayList<String> urlExcommunication) throws MalformedURLException {
		System.out.println(urlExcommunication);
		for (int i = 44; i < 47; i++) {
			String url = new File(urlExcommunication.get(i - 44)).toURI().toURL().toString();
			
			imagesToTake.get(i).imageProperty().set(new Image(url));
		}
	}

	
	
	
	public void askForCouncilPrivilege(String request){
		
		StringTokenizer tokenizer = new StringTokenizer(request);
		String numberOfCouncils = tokenizer.nextToken();
		String answer = "";
		
		if (numberOfCouncils.equals("1")) {
			answer = showCouncilPrivilegeRequest(request);
		}
		if (numberOfCouncils.equals("2")) {
			answer = showCouncilPrivilegeRequest(request);
			String answer2 = showCouncilPrivilegeRequest(request);
			while(answer.contains(answer2)) {
				showMessage("Choose a DIFFERENT councilPrivilege");
				answer2 = showCouncilPrivilegeRequest(request);
			}
			answer = answer + " " + answer2;
		}
		
		if (numberOfCouncils.equals("3")) {
			answer = showCouncilPrivilegeRequest(request);
			String answer2 = showCouncilPrivilegeRequest(request);
			while(answer.contains(answer2)) {
				showMessage("Choose a DIFFERENT councilPrivilege");
				answer2 = showCouncilPrivilegeRequest(request);
			}
			answer = answer + " " + answer2;
			String answer3 = showCouncilPrivilegeRequest(request);
			while(answer.contains(answer3)) {
				showMessage("Choose a DIFFERENT councilPrivilege");
				answer3 = showCouncilPrivilegeRequest(request);
			}
			answer = answer + " " + answer2;
		}
		
		synchronized (((ViewGUI) view).getWaitingForParameters()){
			((ViewGUI) view).setCouncilPrivilegeAnswer(answer);
			((ViewGUI) view).getWaitingForParameters().notify();
			}
		
	}
	
	
	private String showCouncilPrivilegeRequest (String request) {
		String answer = "";
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CouncilPrivilegeRequest");
		alert.setHeaderText(request);
		alert.initModality(Modality.APPLICATION_MODAL);
		ButtonType buttonTypeOne = new ButtonType("One");
		ButtonType buttonTypeTwo = new ButtonType("Two");
		ButtonType buttonTypeThree = new ButtonType("Three");
		ButtonType buttonTypeFour = new ButtonType("Four");
		ButtonType buttonTypeFive = new ButtonType("Five");
		//ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == buttonTypeOne){
		    answer = "1";
		} else if (result.get() == buttonTypeTwo) {
		    answer = "2";
		} else if (result.get() == buttonTypeThree) {
		    answer = "3";
		} else if (result.get() == buttonTypeFour) {
		    answer = "4";
		} else if (result.get() == buttonTypeFive) {
		    answer = "5";
		} else {
		   //nothing
		}
		
		return answer;
		
	}

	public void askForServantsForHarvestOrProduction(String request) {
		String answer = "";
		List<String> choices = new ArrayList<>();
		choices.add("0");
		choices.add("1");
		choices.add("2");
		choices.add("3");
		choices.add("4");
		choices.add("5");
		choices.add("6");
		choices.add("7");
		choices.add("8");
		choices.add("9");
		choices.add("10");

		ChoiceDialog<String> dialog = new ChoiceDialog<>("0", choices);
		dialog.setTitle("Number of servants");
		dialog.setHeaderText(request);
		dialog.setContentText("Choose your number:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		   answer =  result.get();
		}
		
		synchronized (((ViewGUI) view).getWaitingForParameters()){
			((ViewGUI) view).setServantsAnswer(answer);
			((ViewGUI) view).getWaitingForParameters().notify();
			}
		
		
	}
	
	public void askForChooseNewCard(String request) {
		String answer = "";
		if (request.equals("everyTower")) {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Choose new card tower request");
			alert.setHeaderText("You can pick another card, choose the tower you want to take it from:");
			alert.setContentText(request);
			alert.initModality(Modality.APPLICATION_MODAL);
			ButtonType buttonTypeOne = new ButtonType("Territories");
			ButtonType buttonTypeTwo = new ButtonType("Characters");
			ButtonType buttonTypeThree = new ButtonType("Buildings");
			ButtonType buttonTypeFour = new ButtonType("Ventures");
			//ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo,buttonTypeThree,buttonTypeFour);
			
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == buttonTypeOne){
			    answer = "territories";
			} else if (result.get() == buttonTypeTwo) {
			    answer = "characters";
			} else if (result.get() == buttonTypeThree) {
			    answer = "buildings";
			} else if (result.get() == buttonTypeFour) {
			    answer = "ventures";
			} else {
			   //nothing
			}
			
		}
			
		answer = chooseNewCardFloor(answer);
		System.out.println("*********************************"+answer);
		synchronized (((ViewGUI) view).getWaitingForParameters()){
			((ViewGUI) view).setChooseNewCardAnswer(answer);
			((ViewGUI) view).getWaitingForParameters().notify();
			}
	}

	private String chooseNewCardFloor(String answer) {
		String floor = "";
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Choose new card floor request");
		alert.setHeaderText("Choose the floor of "+answer+" you want to take your card from.");
		alert.initModality(Modality.APPLICATION_MODAL);
		ButtonType buttonTypeOne = new ButtonType("1");
		ButtonType buttonTypeTwo = new ButtonType("2");
		ButtonType buttonTypeThree = new ButtonType("3");
		ButtonType buttonTypeFour = new ButtonType("4");
		//ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo,buttonTypeThree,buttonTypeFour);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == buttonTypeOne){
			floor = "1";
		} else if (result.get() == buttonTypeTwo) {
			floor = "2";
		} else if (result.get() == buttonTypeThree) {
			floor = "3";
		} else if (result.get() == buttonTypeFour) {
			floor = "4";
		} else {
		   //nothing
		}
		
		answer = answer + " " + floor;
		return answer;
	}

	public void  askForExchange(String request) {
		String answer = "";
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exchange Request");
		alert.setHeaderText("The card you have chosen can give you two different values in exchange.");
		alert.setContentText(request);
		alert.initModality(Modality.APPLICATION_MODAL);
		ButtonType buttonTypeOne = new ButtonType("One");
		ButtonType buttonTypeTwo = new ButtonType("Two");
		//ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == buttonTypeOne){
		    answer = "1";
		} else if (result.get() == buttonTypeTwo) {
		    answer = "2";
		} else {
		   //nothing
		}
		
		synchronized (((ViewGUI) view).getWaitingForParameters()){
			((ViewGUI) view).setExchangeAnswer(answer);
			((ViewGUI) view).getWaitingForParameters().notify();
			}
	}

	public void chooseSale(String increase) {
		String answer = "";
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Double sale choice");
		alert.setHeaderText("The card you have chosen can give you two different sales on the next activities.");
		alert.setContentText(increase);
		alert.initModality(Modality.APPLICATION_MODAL);
		ButtonType buttonTypeOne = new ButtonType("One");
		ButtonType buttonTypeTwo = new ButtonType("Two");
		//ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == buttonTypeOne){
		    answer = "1";
		} else if (result.get() == buttonTypeTwo) {
		    answer = "2";
		} else {
		   //nothing
		}
		
		synchronized (((ViewGUI) view).getWaitingForParameters()){
			((ViewGUI) view).setSaleAnswer(answer);
			((ViewGUI) view).getWaitingForParameters().notify();
			}
	}

	public void chooseAlternativeCost(String request) {
		String answer = "";
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Double card cost");
		alert.setHeaderText("The card you picked has two different costs: make your choice");
		alert.setContentText(request);
		alert.initModality(Modality.APPLICATION_MODAL);
		ButtonType buttonTypeOne = new ButtonType("One");
		ButtonType buttonTypeTwo = new ButtonType("Two");
		//ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		System.out.println("Tutto pronto...");
		Optional<ButtonType> result = alert.showAndWait();
		System.out.println("Chiesto! ");
		if (result.get() == buttonTypeOne){
		    answer = "1";
		} else if (result.get() == buttonTypeTwo) {
		    answer = "2";
		} else {
		   //nothing
		}
		
		synchronized (((ViewGUI) view).getWaitingForParameters()){
			((ViewGUI) view).setAlternativeCostAnswer(answer);
			((ViewGUI) view).getWaitingForParameters().notify();
			}
		
	}

	public void chooseExcommunication() {
		String answer = "";
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Vatican Support");
		alert.setHeaderText("Do you want to support the Vatican?");
		alert.initModality(Modality.APPLICATION_MODAL);
		ButtonType buttonTypeOne = new ButtonType("Yes");
		ButtonType buttonTypeTwo = new ButtonType("No");
		//ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == buttonTypeOne){
		    answer = "y";
		} else if (result.get() == buttonTypeTwo) {
		    answer = "n";
		} else {
		   //nothing
		}
		
		synchronized (((ViewGUI) view).getWaitingForParameters()){
			((ViewGUI) view).setExcommunicationAnswer(answer);
			((ViewGUI) view).getWaitingForParameters().notify();
			}
		
	}

	

	public StringProperty getFamily1Value() {
		return family1Value;
	}

	public StringProperty getFamily2Value() {
		return family2Value;
	}

	public StringProperty getFamily3Value() {
		return family3Value;
	}

	public StringProperty getFamily4Value() {
		return family4Value;
	}

	public boolean isFamily4Available() {
		return family4Available;
	}

	public boolean isFamily3Available() {
		return family3Available;
	}

	public boolean isFamily2Available() {
		return family2Available;
	}

	public boolean isFamily1Available() {
		return family1Available;
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

	public void setCurrentPlayer(StringProperty currentPlayerProperty) {
		currentPlayer = currentPlayerProperty;
	}

	public void updateRankings(String ranking) {
		rankings.setValue(ranking);
	}
}