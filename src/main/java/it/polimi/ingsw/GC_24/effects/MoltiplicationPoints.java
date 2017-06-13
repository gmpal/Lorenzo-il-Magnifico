package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.*;

public class MoltiplicationPoints extends Moltiplication{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4677049539299405233L;
	
	private Value value2;
	
	public MoltiplicationPoints(String name, Value value, Value value2) {
		super(name, value);
		this.value2 = value2;
	}

	@Override
public void moltiplicationEffect(Player player){
		int factor1 = value.getQuantity();
		int quanityplayervalue2 = value2.findValueInPlayer(player).getQuantity();
		int dividervalue2 = value2.getQuantity();
		int factor2 = (quanityplayervalue2/dividervalue2);
		
		value.setQuantity(factor1*factor2);
		
		value.addValueToSet(player.getMyValues());
	}
	
	@Override
	public void giveImmediateEffect(Player player){
		moltiplicationEffect(player);
	}
}
