package it.polimi.ingsw.GC_24.model.effects.immediate;

import java.util.HashMap;
import java.util.List;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.effects.Effect;

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
	
	
	public String generateParametersRequest() {
		return null;
	}
	
	public HashMap<String,Object> generateHashMapToSend(String response){
	return null;
	}
	
	public void assignParameters(String responseFromClient) {
		
	}

	public List<ImmediateEffect> addAllNewEffectsToThisSet(List<ImmediateEffect> secondaryInteractiveEffects){
		return secondaryInteractiveEffects;
	}

}
