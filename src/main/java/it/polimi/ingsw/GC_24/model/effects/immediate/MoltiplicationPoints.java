package it.polimi.ingsw.GC_24.model.effects.immediate;

import java.util.HashMap;
import java.util.List;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.values.*;

public class MoltiplicationPoints extends Moltiplication {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4677049539299405233L;

	private Value value2;

	public MoltiplicationPoints(String name, Value value, Value value2) {
		super(name, value);
		this.value2 = value2;
	}

	/**
	 * the player will receive the quantity of value for every quantity of value2 in
	 * his possession
	 */
	@Override
	public void moltiplicationEffect(Player player) {
		int factor1 = getValue().getQuantity();
		int quanityplayervalue2 = value2.findValueInPlayer(player).getQuantity();
		int dividervalue2 = value2.getQuantity();
		int factor2 = quanityplayervalue2 / dividervalue2;

		getValue().setQuantity(factor1 * factor2);

		getValue().addValueToSet(player.getMyValues());
	}

	@Override
	public void giveImmediateEffect(Player player) {
		moltiplicationEffect(player);
	}
	
	/* Not necessary methods, but better than choosing with if... */
	@Override
	public String generateParametersRequest() {
		return null;
	}

	@Override
	public HashMap<String, Object> generateHashMapToSend(String response) {
		return null;
	}

	@Override
	public void assignParameters(String responseFromClient) {
	}

	@Override
	public List<ImmediateEffect> addAllNewEffectsToThisSet(List<ImmediateEffect> secondaryInteractiveEffects) {
		return secondaryInteractiveEffects;
	}

	@Override
	public String toString() {
		return "Moltiplication Points: for your every " + getValue() + " you will receive " + value2;
	}

	// getters and setters
	public Value getValue2() {
		return this.value2;
	}

	public void getValue2(Value value2) {
		this.value2 = value2;
	}
}
