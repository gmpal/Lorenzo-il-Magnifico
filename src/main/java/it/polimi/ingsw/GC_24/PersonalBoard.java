package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.cards.*;

public class PersonalBoard {
	
	private PlayerColour player; 
	private Territories territoriesCards[]; 
	private Characters charactersCards[];
	private Buildings buildingsCards[];
	private Ventures venturesCards[];
	
	//getter e setter
	public PlayerColour getPlayer() {
		return player;
	}
	public void setPlayer(PlayerColour player) {
		this.player = player;
	}
	public Territories[] getTerritoriesCards() {
		return territoriesCards;
	}
	public void setTerritoriesCards(Territories[] territoriesCards) {
		this.territoriesCards = territoriesCards;
	}
	public Characters[] getCharactersCards() {
		return charactersCards;
	}
	public void setCharactersCards(Characters[] charactersCards) {
		this.charactersCards = charactersCards;
	}
	public Buildings[] getBuildingsCards() {
		return buildingsCards;
	}
	public void setBuildingsCards(Buildings[] buildingsCards) {
		this.buildingsCards = buildingsCards;
	}
	public Ventures[] getVenturesCards() {
		return venturesCards;
	}
	public void setVenturesCards(Ventures[] venturesCards) {
		this.venturesCards = venturesCards;
	}
	
	public void setCard(Development card){
		
	}
	
	

}
