package it.polimi.ingsw.GC_24.client.view;

import java.io.Serializable;
import java.util.List;

import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public interface ViewInterface extends Runnable, MyObserver {
	

	//FROM SERVER TO CLIENT
	
	public abstract void show(String message);
	
	public abstract void showToSinglePlayer(Player currentPlayer, String message);
		
	public abstract String chooseAlternativeCost(String request);

	public SetOfValues chooseSale(IncreaseDieValueCard increase); 
	
	public abstract void askForExcommunication();
	
	public abstract String askForCouncilPrivilege(String request);
	
	public abstract String askForExchange(String request);
	
	public abstract String askForServantsForHarvestOrProduction(String request);
	
	public abstract String askForChooseNewCard(String request);
	
	
	
	public abstract void play();

	public abstract void updatePlayerNumber(int playerNumber2, int modelNumber);

	public abstract void communicateActionDone();

	public abstract void updateTurn(List<Player> playerTurn);

	public abstract void getInformationForReceivedModel(Model model);

	public abstract void setMyTurn(Player currentPlayer);

	public abstract String getName();

	public abstract void sendAlternativeCost(String response);
	
	
	
	//FROM CLIENT TO SERVER
	
	public abstract void sendPlayerString(String name) throws InterruptedException;

	public abstract void sendAction(String action);
	
	public abstract void sendAnswerForParameters(String answer);
	
}
