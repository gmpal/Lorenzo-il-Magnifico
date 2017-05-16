package it.polimi.ingsw.GC_24.values;

public class SetOfValues {

	private Wood woods;
	private Stone stones;
	private Coin coins;
	private Servant servants;
	private FaithPoint faithPoints;
	private MilitaryPoint militaryPoints;
	private VictoryPoint victoryPoints;
	
	
	//costruttore
	public SetOfValues (int numplayer){
		this.woods.setValue(3);
		this.stones.setValue(3);
		this.servants.setValue(3);
		this.coins.setValue(4+numplayer);
		this.faithPoints.setValue(0);
		this.militaryPoints.setValue(0);
		this.victoryPoints.setValue(0);
	}
	
	//getters and setters
	public Wood getWoods() {
		return woods;
	}
	public void setWoods(Wood woods) {
		this.woods = woods;
	}
	public Stone getStones() {
		return stones;
	}
	public void setStones(Stone stones) {
		this.stones = stones;
	}
	public Coin getCoins() {
		return coins;
	}
	public void setCoins(Coin coins) {
		this.coins = coins;
	}
	public Servant getServants() {
		return servants;
	}
	public void setServants(Servant servants) {
		this.servants = servants;
	}
	public FaithPoint getFaithPoints() {
		return faithPoints;
	}
	public void setFaithPoints(FaithPoint faithPoints) {
		this.faithPoints = faithPoints;
	}
	public MilitaryPoint getMilitaryPoints() {
		return militaryPoints;
	}
	public void setMilitaryPoints(MilitaryPoint militaryPoints) {
		this.militaryPoints = militaryPoints;
	}
	public VictoryPoint getVictoryPoints() {
		return victoryPoints;
	}
	public void setVictoryPoints(VictoryPoint victoryPoints) {
		this.victoryPoints = victoryPoints;
	} 
	
	
	
}