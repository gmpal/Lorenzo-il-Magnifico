package it.polimi.ingsw.GC_24.effects;

import java.util.*;
import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Buildings;
import it.polimi.ingsw.GC_24.model.Player;

public class PerformProduction extends PerformActivity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4975317837362387770L;

		//constructor
		public PerformProduction(String name, int dieValue) {
			super(name, dieValue);
		}

		@Override
		public void giveImmediateEffect(Player player){
		player.getMyBoard().getBonusTile().giveProductiontValues(player.getMyValues());
		List<Development> productionCards = player.getMyBoard().getPersonalBuildings().getCards();
		for (Development  card:productionCards){
			Buildings building = (Buildings) card;
			if (building.getCostDie() <= getDieValue()){
				building.getImmediateEffect1().giveImmediateEffect(player);
				building.getImmediateEffect().giveImmediateEffect(player);
				} 
			}
		}
}
