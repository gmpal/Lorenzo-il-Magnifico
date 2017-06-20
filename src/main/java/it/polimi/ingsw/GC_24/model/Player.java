package it.polimi.ingsw.GC_24.model;

import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.values.*;


public class Player implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6848723894380632778L;
	
	private String myName;
	private Family myFamily;
	private PersonalBoard myBoard; 
	private SetOfValues myValues;
	private PlayerColour myColour;
	
	//Constructor 
	public Player(String myName, PlayerColour myColour) {
		this.myColour = myColour;
		this.myName = myName;
		this.myFamily =  new Family(myColour);
		this.myBoard = new PersonalBoard();
		this.myValues = new SetOfValues();
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
		SetOfValues value = place.getValue().getEffectValues();
		this.myValues = value.addTwoSetsOfValues(this.myValues);
	}
	
	//returns false if the increment is a negative number(not allowed) or if it is grater 
	//than the number of servants of the player, hence it is not possible to raise the die's
	//value of the required increment
	public boolean isPossibleIncreaseDieValue(int increment){
		int myservants = this.getMyValues().getServants().getQuantity();
		if (increment >= 0)
			return myservants >= increment;
		else
			return false;
	}
	
	//Prints name of a Player
	@Override
	public String toString() {
		return "Player [myName=" + myName + ", myFamily=" + myFamily + ", myBoard=" + myBoard + ", myValues=" + myValues
				+ ", myColour=" + myColour + "]";
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


