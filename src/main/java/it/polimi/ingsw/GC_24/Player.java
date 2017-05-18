package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Value;

public class Player {
	
	private String myName;
	private int myNumber;
	private FamilyMember[] myFamily;
	private PersonalBoard myBoard; 
	private SetOfValues myValues;
	private PlayerColour myColour;

	
	
	
	//useful methods
/*	
	public Player givePlayerfromColour(PlayerColour colour){
		if (this.getMycolour()==colour){
			return this.Player;
		}
	}
*/
	
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

	public FamilyMember[] getMyFamily() {
		return myFamily;
	}

	public void setMyFamily(FamilyMember[] myFamily) {
		this.myFamily = myFamily;
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
	
	public SetOfValues getMyValuesFromColour(PlayerColour playerColour) {
		if(this.myColour.equals(playerColour));
		return myValues;
	}
	
	public void takeValuesFromPlace(Place place){
		Value value = place.getValues();
		this.myValues=value.addValueToSet(this.myValues);
	}
}
	
	
	
	
	
	
	
	
