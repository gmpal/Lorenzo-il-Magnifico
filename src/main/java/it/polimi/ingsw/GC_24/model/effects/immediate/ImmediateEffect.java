package it.polimi.ingsw.GC_24.model.effects.immediate;

import java.util.HashMap;
import java.util.List;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.effects.Effect;

/**
 * this abstract class represents all the effects that are given to the player
 * when placing a family member somewhere on the board or when taking a card
 */
public abstract class ImmediateEffect extends Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9149662481180668247L;

	/**
	 * this class gives to the player the resource immediate effect and the
	 * immediate special effect constructor needed for subclasses
	 * 
	 * @param name
	 */
	public ImmediateEffect(String name) {
		super(name);
	}

	/**
	 * this method gives the immediate effect of the card to the player when called
	 * 
	 * @param player
	 */
	public abstract void giveImmediateEffect(Player player);

	/**
	 * this method generates the string that must be shown to the player in order to
	 * ask the parameter for the effect
	 */
	public String generateParametersRequest() {
		return null;
	}

	/**
	 * this method creates the hashMap to be sent to the player for the request for
	 * parameters
	 * 
	 * @param string
	 *            answer
	 */
	public HashMap<String, Object> generateHashMapToSend(String answer) {
		return null;
	}

	/**
	 * this method receives the player's answer and gets the right effect
	 * 
	 * @param string
	 *            responseFromClient
	 */
	public void assignParameters(String responseFromClient) {
	}

	public List<ImmediateEffect> addAllNewEffectsToThisSet(List<ImmediateEffect> secondaryInteractiveEffects) {
		return null;
	}
}