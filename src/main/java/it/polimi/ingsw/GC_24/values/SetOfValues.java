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
		this.woods = new Wood(3);
		this.stones = new Stone(3);
		this.servants = new Servant(3);
		this.coins = new Coin(4+numplayer);
		this.faithPoints = new FaithPoint(0);
		this.militaryPoints = new MilitaryPoint(0);
		this.victoryPoints = new VictoryPoint(0);
	}
	

	//toString
	@Override
	public String toString() {
		return "[woods=" + woods.getQuantity() + 
				", stones=" + stones.getQuantity() + 
				", coins=" + coins.getQuantity() + 
				", servants=" + servants.getQuantity()+ 
				", faithPoints=" + faithPoints.getQuantity() + 
				", militaryPoints=" + militaryPoints.getQuantity() + 
				", victoryPoints=" + victoryPoints.getQuantity() + "]\n\n";
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
	
	//method to sum two sets of values
	public SetOfValues addTwoSetsOfValues(SetOfValues v){
		v.setCoins(new Coin(this.coins.getQuantity() + v.getCoins().getQuantity()));
		v.setWoods(new Wood(this.woods.getQuantity() + v.getWoods().getQuantity()));
		v.setStones(new Stone(this.stones.getQuantity() + v.getStones().getQuantity()));
		v.setServants(new Servant(this.servants.getQuantity() + v.getServants().getQuantity()));
		v.setFaithPoints(new FaithPoint(this.faithPoints.getQuantity() + v.getFaithPoints().getQuantity()));
		v.setMilitaryPoints(new MilitaryPoint(this.militaryPoints.getQuantity() + v.getMilitaryPoints().getQuantity()));
		v.setVictoryPoints(new VictoryPoint(this.victoryPoints.getQuantity() + v.getVictoryPoints().getQuantity()));
		return v;	
	}
	
}
