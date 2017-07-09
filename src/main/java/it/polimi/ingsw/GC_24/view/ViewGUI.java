package it.polimi.ingsw.GC_24.view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	private String excommunicationAnswer = "";
	private String exchangeAnswer = "";
	private String servantsAnswer = "";
	private String chooseNewCardAnswer = "";

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
	public void sendAction(String command) {
		actionDone = false;

		hm = new HashMap<>();
		hm.put("action", command);
		notifyMyObservers(hm);

	}

	@Override
	public void communicateActionDone() {
		setActionDone(true);
	}

	
	@Override
	public String chooseAlternativeCost(String request) {
		System.out.println("GUI choosing alternative cost");

		Platform.runLater(() -> mainClass.chooseAlternativeCost(request));

		System.out.println("Metodo maincLass partito");
		synchronized (waitingForParameters) {
			alternativeCostAnswer = "";
			while (alternativeCostAnswer.equals("")) {
				try {
					System.out.println("In attesa");
					waitingForParameters.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		return alternativeCostAnswer;
	}

	@Override
	public String chooseSale(String increase) {

		Platform.runLater(() -> mainClass.chooseSale(increase));

		synchronized (waitingForParameters) {
			saleAnswer = "";
			while (saleAnswer.equals("")) {
				try {
					waitingForParameters.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		return saleAnswer;
	}

	@Override
	public String askForExcommunication() {
		Platform.runLater(() -> mainClass.chooseExcommunication());

		synchronized (waitingForParameters) {
			excommunicationAnswer = "";
			while (excommunicationAnswer.equals("")) {
				try {
					waitingForParameters.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		return excommunicationAnswer;

	}
	
	@Override
	public String askForCouncilPrivilege(String request) { 

		Platform.runLater(() -> mainClass.askForCouncilPrivilege(request));

		synchronized (waitingForParameters) {
			councilPrivilegeAnswer = "";
			while (councilPrivilegeAnswer.equals("")) {
				try {
					waitingForParameters.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return councilPrivilegeAnswer;

	}

	@Override
	public String askForExchange(String request) {
		Platform.runLater(() -> mainClass.askForExchange(request));

		synchronized (waitingForParameters) {
			exchangeAnswer = "";
			while (exchangeAnswer.equals("")) {
				try {
					waitingForParameters.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		return exchangeAnswer;
	}

	@Override
	public String askForServantsForHarvestOrProduction(String request) {
		Platform.runLater(() -> mainClass.askForServantsForHarvestOrProduction(request));

		synchronized (waitingForParameters) {
			servantsAnswer = "";
			while (servantsAnswer.equals("")) {
				try {
					waitingForParameters.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		return servantsAnswer;
	}

	@Override
	public String askForChooseNewCard(String request) {
		Platform.runLater(() -> mainClass.askForChooseNewCard(request));

		synchronized (waitingForParameters) {
			chooseNewCardAnswer = "";
			while (chooseNewCardAnswer.equals("")) {
				try {
					waitingForParameters.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return chooseNewCardAnswer;

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
		this.values = personalInformation[6];
		this.colour = personalInformation[7];
		this.permanentEffects = personalInformation[8];
		this.oneTimePerTurnEffects = personalInformation[9];

		Platform.runLater(() -> mainClass.parseValuesString(values));
		Platform.runLater(() -> mainClass.perseFamilyString(family));

	}

	@Override
	public void sendLeader(String command) {
		hm = new HashMap<>();
		hm.put("leader", command);
		this.notifyMyObservers(hm);
	}

	@Override
	public void disconnectClient() {
		// TODO Auto-generated method stub

	}

	public Object getWaitingForParameters() {
		return waitingForParameters;
	}

	public void setCouncilPrivilegeAnswer(String chosenPrivilege) {
		this.councilPrivilegeAnswer = chosenPrivilege;
	}

	public String getCouncilPrivilegeAnswer() {
		return councilPrivilegeAnswer;
	}

	public String getAlternativeCostAnswer() {
		return alternativeCostAnswer;
	}

	public String getSaleAnswer() {
		return saleAnswer;
	}

	public String getExcommunicationAnswer() {
		return excommunicationAnswer;
	}

	public String getExchangeAnswer() {
		return exchangeAnswer;
	}

	public String getServantsAnswer() {
		return servantsAnswer;
	}

	public String getChooseNewCardAnswer() {
		return chooseNewCardAnswer;
	}

	public void setAlternativeCostAnswer(String alternativeCostAnswer) {
		this.alternativeCostAnswer = alternativeCostAnswer;
	}

	public void setSaleAnswer(String saleAnswer) {
		this.saleAnswer = saleAnswer;
	}

	public void setExcommunicationAnswer(String excommunicationAnswer) {
		this.excommunicationAnswer = excommunicationAnswer;
	}

	public void setExchangeAnswer(String exchangeAnswer) {
		this.exchangeAnswer = exchangeAnswer;
	}

	public void setServantsAnswer(String servantsAnswer) {
		this.servantsAnswer = servantsAnswer;
	}

	public void setChooseNewCardAnswer(String chooseNewCardAnswer) {
		this.chooseNewCardAnswer = chooseNewCardAnswer;
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
}