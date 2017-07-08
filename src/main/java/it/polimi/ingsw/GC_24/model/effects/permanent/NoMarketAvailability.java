package it.polimi.ingsw.GC_24.model.effects.permanent;

/**
 * this effect is a malus that can be activated after the Vatican
 * excommunication. The player won't be able to place their family members in
 * the market places any longer
 */
public class NoMarketAvailability extends PermanentEffect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8619207851164601798L;

	// constructor
	public NoMarketAvailability(String name) {
		super(name);
	}
}
