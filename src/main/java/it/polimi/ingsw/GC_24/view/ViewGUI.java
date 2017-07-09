package it.polimi.ingsw.GC_24.view;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import it.polimi.ingsw.GC_24.gui.MainClass;
import javafx.application.Platform;

public class ViewGUI extends View {

	private MainClass mainClass;
	private List<String> urlExcommunication = new ArrayList<>();
	private List<String> urlPersonalBoard = new ArrayList<>();
	private List<String> urlBoard = new ArrayList<>();
	private ArrayList<String> urlColour;

	
	private String councilPrivilegeAnswer = "";
	private String alternativeCostAnswer = "";
	private String saleAnswer = "";
	private String ExcommunicationAnswer = "";
	private String ExchangeAnswer = "";
	private String Servants = "";
	
	private Object waitingForParameters = new Object();
	
	
	public ViewGUI(String name, MainClass mainClass) {
		super(name);
		this.mainClass = mainClass;
	}

	@Override
	public void play() {
		Platform.runLater(() -> mainClass.showGameBoard());
	}

	@Override
	public <C> void update(C change) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show(String message) {
		Platform.runLater(() -> mainClass.showMessage(message));

	}

	@Override
	public String chooseAlternativeCost(String request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String chooseSale(String increase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void askForExcommunication() {
		// TODO Auto-generated method stub

	}

	@Override
	public String askForCouncilPrivilege(String request) {
		Platform.runLater(() -> mainClass.askForCouncil());
		
		
	}

	@Override
	public String askForExchange(String request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String askForServantsForHarvestOrProduction(String request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String askForChooseNewCard(String request) {
		
	}

	@Override
	public void communicateActionDone() {
	

	}

	@Override
	public void setMyTurn(String currentPlayer) {
		if (currentPlayer.equals(name)) {
			myTurn = true;

		} else {
			myTurn = false;
		}

		Platform.runLater(() -> mainClass.getCurrentPlayer().set(currentPlayer));

		if (myTurn) {
			Platform.runLater(() -> mainClass.showMessage("It's your turn!"));
		} else {
			Platform.runLater(() -> mainClass.showMessage("Not your turn!"));
		}

		Platform.runLater(() -> {
			mainClass.getCurrentPlayer().set(currentPlayer);
		});
	}

	@Override
	public void updateTurn(List<String> playerTurn) {
		this.playersTurn = playerTurn;
		Platform.runLater(() -> mainClass.updateTurnProperties(playerTurn));

	}

	@Override
	public void setRankings(String rankings) {
		this.rankings = rankings;
		Platform.runLater(() -> mainClass.updateRankings(rankings));

	}

	public List<String> getUrlExcommunication() {
		return urlExcommunication;
	}

	public List<String> getUrlPersonalBoard() {
		return urlPersonalBoard;
	}

	public List<String> getUrlBoard() {
		return urlBoard;

	}

	@Override
	public void setUrlPersonalBoard(ArrayList<String> urlPersonalBoard) {
		this.urlPersonalBoard = urlPersonalBoard;
		Platform.runLater(() -> {
			try {
				mainClass.updateUrlPersonalBoard(urlPersonalBoard);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public void setUrlColour(ArrayList<String> urlColour) {
		this.urlColour = urlColour;
		Platform.runLater(() -> {
			try {
				mainClass.updateBackgroundButtons(urlColour);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	@Override
	public void setUrlExcommunication(ArrayList<String> urlExcommunication) {
		this.urlExcommunication = urlExcommunication;
		Platform.runLater(() -> {
			try {
				mainClass.updateUrlExcommunication(urlExcommunication);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public void setUrlBoard(ArrayList<String> urlBoard) {
		this.urlBoard = urlBoard;
		Platform.runLater(() -> {
			try {
				mainClass.updateUrlBoard(urlBoard);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
	
	@Override
	public void parsePersonalInformations(String[] personalInformation) {
		this.personalTerritories = personalInformation[0];
		this.personalBuildings = personalInformation[1];
		this.personalCharacters = personalInformation[2];
		this.personalVentures = personalInformation[3];
		this.personalLeaders = personalInformation[4];
		this.family = personalInformation[5];
		this.values =	personalInformation[6];	
		this.colour = personalInformation[7];
		this.permanentEffects =  personalInformation[8];
		this.oneTimePerTurnEffects =  personalInformation[9];
		
		Platform.runLater(() -> mainClass.parseValuesString(values));
		Platform.runLater(() -> mainClass.perseFamilyString(family));
		
	}
	

}
