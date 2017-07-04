package it.polimi.ingsw.GC_24.view;

import java.util.List;

import it.polimi.ingsw.GC_24.gui.MainClass;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;
import it.polimi.ingsw.GC_24.observers.MyObservable;

public class ViewGUI extends View {

	private MainClass mainClass;

	public ViewGUI(MainClass mainClass) {
	this.mainClass = mainClass;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <C> void update(C change) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showToSinglePlayer(Player currentPlayer, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String chooseAlternativeCost(String request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SetOfValues chooseSale(IncreaseDieValueCard increase) {
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
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlayerNumber(int playerNumber2, int modelNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void communicateActionDone() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMyTurn(Player currentPlayer) {
		// TODO Auto-generated method stub
		
	}



}
