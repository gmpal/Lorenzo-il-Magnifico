package it.polimi.ingsw.GC_24.places;

public abstract class ActivityPlace extends Place{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2421131792594876986L;
	private int additionalCostDice;
	
	//constructor
	public ActivityPlace(int costDice, int additionalCostDice) {
		super(costDice);			
		this.additionalCostDice = additionalCostDice;
	}
	
	//getter and setter
	public int getAdditionalCostDice() {
		return additionalCostDice;
	}

	public void setAdditionalCostDice(int additionalCostDice) {
		this.additionalCostDice = additionalCostDice;
	}	
	
/*	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "\n" );
		if (isAvailable()){
			builder.append("Extra die's cost: " + getAdditionalCostDice());
		}else{
			builder.append("Place occupied by the " + getFamMemberOnPlace().getPlayerColour() + " player");
		}
		return builder.toString();
	}
		*/
}
