package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.board.Towers;

public class Board {
	private static final int maxNumPlayerToLock =3; 
	private int gameID;
	private int numPlayer;
	private Towers towers;
	private Harvest harvest;
	private Production production;
	private Market market;
	private CouncilPanel concilPane;
	
	public Board(int gameID, int numPlayer, Towers towers, Harvest harvest, Production production, Market market,
			CouncilPanel concilPane) {
		super();
		this.gameID = gameID;
		this.numPlayer = numPlayer;
		this.towers = towers;
		this.harvest = harvest;
		this.production = production;
		this.market = market;
		this.concilPane = concilPane;
	}
	
	public boolean lockPlaces(int numPlayer){
		if(numPlayer< maxNumPlayerToLock)return true;
		else return false;
	}
	
	public void clear(){
		//dealCardsOnTower
		//setFamOnPlace(null)
	}
}

