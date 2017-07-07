package it.polimi.ingsw.GC_24.view;


import java.util.List;

import it.polimi.ingsw.GC_24.gui.MainClass;
import javafx.application.Platform;

public class ViewGUI extends View {

	private MainClass mainClass;

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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void communicateActionDone() {
		// TODO Auto-generated method stub

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
				System.out.println("*********************");
		});
	}


	@Override
	public void updateTurn(List<String> playerTurn) {
		this.playersTurn = playerTurn;
		Platform.runLater(() -> mainClass.updateTurnProperties(playerTurn));

	}

}
