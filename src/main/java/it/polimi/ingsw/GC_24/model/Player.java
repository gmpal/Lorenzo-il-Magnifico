package it.polimi.ingsw.GC_24.model;

import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.values.*;


public class Player {
	
	private String myName;
	private Family myFamily;
	private PersonalBoard myBoard; 
	private SetOfValues myValues;
	private PlayerColour myColour;
	
	//Constructor number1 --> NEED to create a setOfValues
	public Player(String myName, PlayerColour myColour) {
		this.myName = myName;
		this.myFamily =  new Family(this);
		this.myBoard = new PersonalBoard(this);
		this.myValues = new SetOfValues();
		this.myColour = myColour;
	}
	
	//useful methods
	
	//useful to find the value of the player if you only know his colour
	public SetOfValues getMyValuesFromColour(PlayerColour playerColour) {
		if(this.myColour.equals(playerColour))
			return myValues;
		else
			return null;
	}
	
	public void takeValuesFromPlace(Place place){
		Value value = place.getValue();
		this.myValues = value.addValueToSet(this.myValues);
	}
	
	public boolean isPossibleIncreaseDieValue(int increment){
		int myservants = this.getMyValues().getServants().getQuantity();
		return myservants >= increment;
	}
	
	//Prints name and number of a Player
	@Override
	public String toString() {
		return myName;

	}
		
	//getters and setters
	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public Family getMyFamily() {
		return myFamily;
	}

	public void setMyFamily(Family myFamily) {
		this.myFamily = myFamily;
	}

	public PlayerColour getMyColour() {
		return myColour;
	}

	public void setMyColour(PlayerColour myColour) {
		this.myColour = myColour;
	}

	public PersonalBoard getMyBoard() {
		return myBoard;
	}

	public void setMyBoard(PersonalBoard myBoard) {
		this.myBoard = myBoard;
	}

	public SetOfValues getMyValues() {
		return myValues;
	}

	public void setMyValues(SetOfValues myValues) {
		this.myValues = myValues;
	}
	
}


