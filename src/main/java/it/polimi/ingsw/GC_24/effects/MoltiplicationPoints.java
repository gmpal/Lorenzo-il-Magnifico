package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.*;

public class MoltiplicationPoints extends Moltiplication {

	private Value value2;

	public MoltiplicationPoints(String name, Value value, Value value2) {
		super(name, value);
		this.value2 = value2;
	}

	@Override
	public void moltiplicationEffect(Player player) {
		int factor1 = value.getQuantity();
		int quanityplayervalue2 = value2.findValueInPlayer(player).getQuantity();
		int dividervalue2 = value2.getQuantity();
		int factor2 = quanityplayervalue2 / dividervalue2;

		value.setQuantity(factor1 * factor2);

		value.addValueToSet(player.getMyValues());
	}

	@Override
	public void giveImmediateEffect(Player player) {
		moltiplicationEffect(player);
	}

	public Value getValue() {
		return this.value;
	}

	public Value getValue2() {
		return this.value2;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public void getValue2(Value value2) {
		this.value2 = value2;
	}
}
