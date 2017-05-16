package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Player {
	
	private String myName;
	private int myNumber;
	private FamilyMember[] myFamily;
	private PersonalBoard myBoard; 
	private SetOfValues myValues;
	private PlayerColour mycolour;

	
	
	
	
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
		return mycolour;
	}

	public void setMycolour(PlayerColour mycolour) {
		this.mycolour = mycolour;
	}
}
	
	
	
	
	
	
	
	
