package it.polimi.ingsw.GC_24.model.effects.permanent;

/**
 * with this effect the player will have their family members increased by a
 * certain fixed quantity or otherwise will have a certain starting die value when
 * performing harvest or production exception --> there is a leader card that
 * sets the values of the family members instead of only increasing it
 */
public class IncreaseDieValueActivity extends PermanentEffect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9090358683522256263L;
	private int increaseDieValue;

	// constructor
	public IncreaseDieValueActivity(String name, int increaseDieValue) {
		super(name);
		this.increaseDieValue = increaseDieValue;
	}

	@Override
	public String toString() {
		if (getName().equalsIgnoreCase("setDiceValue")) {
			return "Your family members' value will be set at " + increaseDieValue;
		}
		return getName() + ": " + " from now on you will have a starting die value of " + increaseDieValue
				+ " when performing this activity";
	}

	// getters and setters
	public int getIncreaseDieValue() {
		return increaseDieValue;
	}

	public void setIncreaseDieValue(int increaseDieValue) {
		this.increaseDieValue = increaseDieValue;
	}

}
