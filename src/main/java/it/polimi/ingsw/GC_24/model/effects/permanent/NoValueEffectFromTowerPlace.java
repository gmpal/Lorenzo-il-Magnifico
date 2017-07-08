package it.polimi.ingsw.GC_24.model.effects.permanent;

/**
 * this effect is a malus that can be activated after the Vatican
 * excommunication. The player won't receive the place's immediate effect when
 * placing his family member on a tower any longer
 */
public class NoValueEffectFromTowerPlace extends PermanentEffect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4724435070656130936L;

	public NoValueEffectFromTowerPlace(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "No Value Effect From Tower Place: when you place a family member on a tower place you won't get the values anymore ";
	}
}
