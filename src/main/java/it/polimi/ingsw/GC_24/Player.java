package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Value;

public class Player {
	
	private String myName;
	private int myNumber;
	private Family myFamily;
	private PersonalBoard myBoard; 
	private SetOfValues myValues;
	private PlayerColour myColour;

	
	//AUTO-GENERATED contructor, NEEDS TO BE CORRECTED
	public Player(String myName, int myNumber, Family myFamily, PersonalBoard myBoard, SetOfValues myValues,
			PlayerColour myColour) {
		this.myName = myName;
		this.myNumber = myNumber;
		this.myFamily = myFamily;
		this.myBoard = myBoard;
		this.myValues = new SetOfValues(myNumber);
		this.myColour = myColour;
	}
	
	//useful methods
	
	public SetOfValues getMyValuesFromColour(PlayerColour playerColour) {
		if(this.myColour.equals(playerColour));
		return myValues;
	}
	
	public void takeValuesFromPlace(Place place){
		Value value = place.getValues();
		this.myValues=value.addValueToSet(this.myValues);
	}
	
	//Prints name and number of a Player
	@Override
	public String toString() {
		return myName + ", player "+ myNumber;
	}
		
	

	//getters and setters
	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public int getMyNumber() {
		return myNumber;
	}

	public void setMyNumber(int myNumber) {
		this.myNumber = myNumber;
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

	public PlayerColour getMycolour() {
		return myColour;
	}

	public void setMycolour(PlayerColour mycolour) {
		this.myColour = mycolour;
	}
	
}
	
	
	
	
	
	
	
	
