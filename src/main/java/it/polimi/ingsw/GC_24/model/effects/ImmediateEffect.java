package it.polimi.ingsw.GC_24.model.effects;

import java.util.HashMap;
import java.util.List;

import it.polimi.ingsw.GC_24.model.Player;

public abstract class ImmediateEffect extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9149662481180668247L;

	/** this class gives to the player the resource immediate effect and the
	* immediate special effect
	* constructor needed for subclasses
	*/
	public ImmediateEffect(String name) {
		super(name);
	}

	/**this method gives the immediate effect of the card to 
	 * the player when called
	 */
	public abstract void giveImmediateEffect(Player player);
	
	public abstract String generateParametersRequest();
	
	public abstract HashMap<String,Object> generateHashMapToSend(String response);
	
	public abstract void assignParameters(String responseFromClient);

	public abstract List<ImmediateEffect> addAllNewEffectsToThisSet(List<ImmediateEffect> secondaryInteractiveEffects);

}
