package it.polimi.ingsw.GC_24.values;

public class SetOfValues {

	private Wood woods;
	private Stone stones;
	private Coin coins;
	private Servant servants;
	private FaithPoint faithPoints;
	private MilitaryPoint militaryPoints;
	private VictoryPoint victoryPoints;
	

	
	//constructor with player number
	public SetOfValues (int numplayer){
		this.woods = new Wood(3);
		this.stones = new Stone(3);
		this.servants = new Servant(3);
		this.coins = new Coin(4+numplayer);
		this.faithPoints = new FaithPoint(0);
		this.militaryPoints = new MilitaryPoint(0);
		this.victoryPoints = new VictoryPoint(0);
	}
	
	
	//constructor with no parameters --> Sets everything to 0
	public SetOfValues() {
		this.woods = new Wood(0);
		this.stones = new Stone(0);
		this.coins = new Coin(0);
		this.servants = new Servant(0);
		this.faithPoints = new FaithPoint(0);
		this.militaryPoints = new MilitaryPoint(0);
		this.victoryPoints = new VictoryPoint(0);
	}


	//useful methods
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

	//toString
	@Override
	public String toString() {
	
			StringBuilder builder = new StringBuilder();
			
			builder.append("{");
			
			if (woods.getQuantity()!=0) builder.append(" woods=" + woods.getQuantity() + " "); 
			if (stones.getQuantity()!=0) builder.append(" stones=" + stones.getQuantity() + " "); 
			if (coins.getQuantity()!=0) builder.append(" coins=" + coins.getQuantity() + " ");
			if (servants.getQuantity()!=0) builder.append(" servants=" + servants.getQuantity() + " ");
			if (faithPoints.getQuantity()!=0) builder.append(" faithPoints=" + faithPoints.getQuantity() + " ");
			if (militaryPoints.getQuantity()!=0) builder.append(" militaryPoints=" + militaryPoints.getQuantity() + " ");
			if (victoryPoints.getQuantity()!=0) builder.append(" victoryPoints=" + victoryPoints.getQuantity());

			builder.append("}");
			return builder.toString();
	}

	//getters and setters with VALUES	
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
	
/*	//getters and setters with INT
	public int getIntWoods() {
		return woods.getQuantity();
	}
	
	
	public void setIntWoods(int woods) {
		this.woods = new Wood(0);
		this.woods.setQuantity(woods);
	}
	
	public int getIntStones() {
		return stones.getQuantity();
	}
	
	public void setIntStones(int stones) {
		this.stones = new Stone(0);
		this.stones.setQuantity(stones);
	}
	
	public int getIntCoins() {
		return coins.getQuantity();
	}
	
	public void setIntCoins(int coins) {
		this.coins = new Coin(0);
		this.coins.setQuantity(coins);
	}
	
	public int getIntServants() {
		return servants.getQuantity();
	}
	
	public void setIntServants(int servants) {
		this.servants = new Servant(0);
		this.servants.setQuantity(servants);
	}
	
	public int getIntFaithPoints() {
		return faithPoints.getQuantity();
	}
	
	public void setIntFaithPoints(int faithPoints) {
		this.faithPoints = new FaithPoint(0);
		this.faithPoints.setQuantity(faithPoints);
	}
	
	public int getIntMilitaryPoints() {
		return militaryPoints.getQuantity();
	}
	
	public void setIntMilitaryPoints(int militaryPoints) {
		this.militaryPoints = new MilitaryPoint(0);
		this.militaryPoints.setQuantity(militaryPoints);
	}
	
	public int getIntVictoryPoints() {
		return victoryPoints.getQuantity();
	}
	
	/*public void setIntVictoryPoints(int victoryPoints) {
		this.victoryPoints = new VictoryPoint(0);
		this.victoryPoints.setQuantity(victoryPoints);
	} */
	
	
	
}
