package it.polimi.ingsw.GC_24.values;

import java.io.Serializable;

public class SetOfValues implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6018353213379508963L;
	private Wood woods;
	private Stone stones;
	private Coin coins;
	private Servant servants;
	private FaithPoint faithPoints;
	private MilitaryPoint militaryPoints;
	private VictoryPoint victoryPoints;

	// constructor with no parameters --> Sets everything to 0
	public SetOfValues() {
		this.woods = new Wood(0);
		this.stones = new Stone(0);
		this.servants = new Servant(0);
		this.coins = new Coin(0);
		this.faithPoints = new FaithPoint(0);
		this.militaryPoints = new MilitaryPoint(0);
		this.victoryPoints = new VictoryPoint(0);
	}

	// useful methods

	/** method to set a SetOfValue from a numPlayer*/
	public void setInitialValues(int playerNumber) {
		this.woods = new Wood(3);
		this.stones = new Stone(3);
		this.servants = new Servant(3);
		this.coins = new Coin(4 + playerNumber);
		this.faithPoints = new FaithPoint(0);
		this.militaryPoints = new MilitaryPoint(0);
		this.victoryPoints = new VictoryPoint(0);
	}

	/** method to sum two sets of values*/
	public SetOfValues addTwoSetsOfValues(SetOfValues v) {
		v.setCoins(new Coin(this.coins.getQuantity() + v.getCoins().getQuantity()));
		v.setWoods(new Wood(this.woods.getQuantity() + v.getWoods().getQuantity()));
		v.setStones(new Stone(this.stones.getQuantity() + v.getStones().getQuantity()));
		v.setServants(new Servant(this.servants.getQuantity() + v.getServants().getQuantity()));
		v.setFaithPoints(new FaithPoint(this.faithPoints.getQuantity() + v.getFaithPoints().getQuantity()));
		v.setMilitaryPoints(new MilitaryPoint(this.militaryPoints.getQuantity() + v.getMilitaryPoints().getQuantity()));
		v.setVictoryPoints(new VictoryPoint(this.victoryPoints.getQuantity() + v.getVictoryPoints().getQuantity()));
		return v;
	}

	/** method to subtract two sets of values [v- this]*/
	public SetOfValues subTwoSetsOfValues(SetOfValues v) {
		v.setCoins(new Coin(v.getCoins().getQuantity() - this.coins.getQuantity()));
		v.setWoods(new Wood(v.getWoods().getQuantity() - this.woods.getQuantity()));
		v.setStones(new Stone(v.getStones().getQuantity() - this.stones.getQuantity()));
		v.setServants(new Servant(v.getServants().getQuantity() - this.servants.getQuantity()));
		v.setFaithPoints(new FaithPoint(v.getFaithPoints().getQuantity() - this.faithPoints.getQuantity()));
		v.setMilitaryPoints(new MilitaryPoint(v.getMilitaryPoints().getQuantity() - this.militaryPoints.getQuantity()));
		v.setVictoryPoints(new VictoryPoint(v.getVictoryPoints().getQuantity() - this.victoryPoints.getQuantity()));
		return v;
	}

	/** This method returns true if you have...enough of that Value */
	public boolean doIHaveEnoughOfThis(Value value) {
		return value.amIPresentInThisSet(this);
	}

	/** Returns true if my SetOfValues contains the cost Set */
	public boolean doIHaveThisSet(SetOfValues cost) {
		return cost.subTwoSetsOfValues(this).isAcceptable();
		// this-cost >=0;
	}

	/*public Value valueNotNullFromSet() {
		if (this.coins.quantity != 0) {
			return coins;
		} else if (this.faithPoints.quantity != 0) {
			return faithPoints;
		} else if (this.militaryPoints.quantity != 0) {
			return militaryPoints;
		} else if (this.stones.quantity != 0) {
			return stones;
		} else if (this.victoryPoints.quantity != 0) {
			return victoryPoints;
		} else if (this.woods.quantity != 0) {
			return woods;
		} else
			return null;
	}*/

	/** Returns true if the Set does not contains negative quantity */
	public boolean isAcceptable() {

		return (this.woods.getQuantity() >= 0 && this.stones.getQuantity() >= 0 && this.servants.getQuantity() >= 0
				&& this.coins.getQuantity() >= 0 && this.faithPoints.getQuantity() >= 0
				&& this.militaryPoints.getQuantity() >= 0 && this.victoryPoints.getQuantity() >= 0);
	}

	public VictoryPoint convertSetToVictoryPoints() {
		return new VictoryPoint((int)((this.coins.quantity+this.servants.quantity+this.stones.quantity+this.woods.quantity)/5));
	}

	public boolean isEmpty() {
		return (woods.getQuantity()==0 && stones.getQuantity()==0 && coins.getQuantity()==0 &&
				servants.getQuantity()==0 && faithPoints.getQuantity()==0 &&
				militaryPoints.getQuantity()==0 && victoryPoints.getQuantity()==0);
	}
	// hashCode() redefined
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coins == null) ? 0 : coins.hashCode());
		result = prime * result + ((faithPoints == null) ? 0 : faithPoints.hashCode());
		result = prime * result + ((militaryPoints == null) ? 0 : militaryPoints.hashCode());
		result = prime * result + ((servants == null) ? 0 : servants.hashCode());
		result = prime * result + ((stones == null) ? 0 : stones.hashCode());
		result = prime * result + ((victoryPoints == null) ? 0 : victoryPoints.hashCode());
		result = prime * result + ((woods == null) ? 0 : woods.hashCode());
		return result;
	}

	// equals() redefined
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetOfValues other = (SetOfValues) obj;
		if (coins == null) {
			if (other.coins != null)
				return false;
		} else if (!coins.equals(other.coins))
			return false;
		if (faithPoints == null) {
			if (other.faithPoints != null)
				return false;
		} else if (!faithPoints.equals(other.faithPoints))
			return false;
		if (militaryPoints == null) {
			if (other.militaryPoints != null)
				return false;
		} else if (!militaryPoints.equals(other.militaryPoints))
			return false;
		if (servants == null) {
			if (other.servants != null)
				return false;
		} else if (!servants.equals(other.servants))
			return false;
		if (stones == null) {
			if (other.stones != null)
				return false;
		} else if (!stones.equals(other.stones))
			return false;
		if (victoryPoints == null) {
			if (other.victoryPoints != null)
				return false;
		} else if (!victoryPoints.equals(other.victoryPoints))
			return false;
		if (woods == null) {
			if (other.woods != null)
				return false;
		} else if (!woods.equals(other.woods))
			return false;
		return true;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append("[");

		if (woods.getQuantity() != 0)
			builder.append(" "+woods);
		if (stones.getQuantity() != 0)
			builder.append(" "+stones);
		if (coins.getQuantity() != 0)
			builder.append(" "+coins);
		if (servants.getQuantity() != 0)
			builder.append(" "+servants);
		if (faithPoints.getQuantity() != 0)
			builder.append(" "+faithPoints);
		if (militaryPoints.getQuantity() != 0)
			builder.append(" "+militaryPoints);
		if (victoryPoints.getQuantity() != 0)
			builder.append(" "+victoryPoints);
		builder.append(" ]");
		return builder.toString();
	}

	// getters and setters with VALUES
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
