package it.polimi.ingsw.GC_24.view;


import it.polimi.ingsw.GC_24.gui.MainClass;


public class ViewGUI extends View {

	private MainClass mainClass;

	public ViewGUI(String name, MainClass mainClass) {
		super (name);
	this.mainClass = mainClass;
	}

	@Override
	public void play() {
		mainClass.showGameBoard();
		
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
		// TODO Auto-generated method stub
		
	}




	



}
