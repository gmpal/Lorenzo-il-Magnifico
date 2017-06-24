package it.polimi.ingsw.GC_24.model;

import it.polimi.ingsw.GC_24.dice.SetOfDice;

public class Family implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8845801385694798977L;
	private FamilyMember member1;
	private FamilyMember member2;
	private FamilyMember member3;
	private FamilyMember member4;
		
	//constructor
	
	//when creating a family, it creates a family of neutral members. Then this family is set from a SetOfDice.
	public Family(PlayerColour playerColour){
		this.member1 = new FamilyMember(playerColour);
		this.member2 = new FamilyMember(playerColour);
		this.member3 = new FamilyMember(playerColour);
		this.member4 = new FamilyMember(playerColour);
	
	}
		
	//sets the family with a SetOfDice after it has been created
	public void setFamily(SetOfDice dice) {
		this.member1.setMember(dice.getDie1());
		this.member2.setMember(dice.getDie2());
		this.member3.setMember(dice.getDie3());
				
	}
	
	//Prints a whole family
	@Override
	public String toString() {
		return  
				"member1 --> " + member1 + 
				"\nmember2 --> " + member2 + 
				"\nmember3 --> " + member3 +
				"\nmember4 --> " + member4 + "\n";
	}
	
	//TODO:test
	public FamilyMember getMemberfromString(String number) {
		if(number.equals("1") ){
			return member1;
		}
		if(number.equals("2") ){
			return member2;
		}
		if(number.equals("3") ){
			return member3;
		}
		if(number.equals("4") ){
			return member4;
		}
		else return null;
	}
	
	//getters and setters
	public FamilyMember getMember1() {
		return member1;
	}
	public void setMember1(FamilyMember member1) {
		this.member1 = member1;
	}
	public FamilyMember getMember2() {
		return member2;
	}
	public void setMember2(FamilyMember member2) {
		this.member2 = member2;
	}
	public FamilyMember getMember3() {
		return member3;
	}
	public void setMember3(FamilyMember member3) {
		this.member3 = member3;
	}
	public FamilyMember getMember4() {
		return member4;
	}
	public void setMember4(FamilyMember member4) {
		this.member4 = member4;
	}
}