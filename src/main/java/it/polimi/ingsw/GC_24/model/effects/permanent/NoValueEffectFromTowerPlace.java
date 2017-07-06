package it.polimi.ingsw.GC_24.model.effects.permanent;

public class NoValueEffectFromTowerPlace extends PermanentEffect{

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
