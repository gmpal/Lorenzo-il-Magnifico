package it.polimi.ingsw.GC_24;

public class Family {
	
	private Player player;
	private SetOfDice dice;
	private FamilyMember member1;
	private FamilyMember member2;
	private FamilyMember member3;
	private FamilyMember member4;
	
	//constructor
	
	public Family(Player player, SetOfDice dice){
		this.player = player;
		this.dice = dice;
		this.member1 = new FamilyMember(player, dice.getDie1());
		this.member2 = new FamilyMember(player, dice.getDie2());
		this.member3 = new FamilyMember(player, dice.getDie3());
		this.member4 = new FamilyMember(player);
	}
	
	//Prints a whole family
	@Override
	public String toString() {
		return "Family of " + player + 
				":\n member1 --> " + member1.isAvailableString() + 
				",\n member2 --> " + member2.isAvailableString() + 
				",\n member3 --> " + member3.isAvailableString() +
				",\n member4 --> " + member4.isAvailableString() + ".\n";
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
	public SetOfDice getDice() {
		return dice;
	}

	public void setDice(SetOfDice dice) {
		this.dice = dice;
	}
	
	
}
