package it.polimi.ingsw.GC_24;

public class Player {
	
	private String myName;
	private FamilyMember[] myFamily;
	private PersonalBoard myBoard; 
	//per il momento è un array di Values
	private Value[] myValues;
	private PlayerColour myColour;
	
	
	
	
	//getters and setters
	
	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
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

	public value[] getMyValues() {
		return myValues;
	}

	public void setMyValues(value[] myValues) {
		this.myValues = myValues;
	}

	public PlayerColour getMyColour() {
		return myColour;
	}

	public void setMyColour(PlayerColour myColour) {
		this.myColour = myColour;
	}
}
	
	
	
	
	
	
	
