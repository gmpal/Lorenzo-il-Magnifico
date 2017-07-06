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
	public abstract String generateParametersRequest();

	/**
	 * this method creates the hashMap to be sent to the player for the request for
	 * parameters
	 * 
	 * @param string
	 *            answer
	 */
	public abstract HashMap<String, Object> generateHashMapToSend(String answer);

	/**
	 * this method receives the player's answer and gets the right effect
	 * 
	 * @param string
	 *            responseFromClient
	 */
	public abstract void assignParameters(String responseFromClient);

	public abstract List<ImmediateEffect> addAllNewEffectsToThisSet(List<ImmediateEffect> secondaryInteractiveEffects);
}