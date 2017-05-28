package it.polimi.ingsw.GC_24.effects;

import java.util.ArrayList;
import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class PerformProduction extends PerformActivity{

	//constructor
		public PerformProduction(String name, SetOfValues effectValues, int dieValue) {
			super(name, effectValues, dieValue);
		}

		@Override
		public void giveImmediateEffect(Player player){
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
		ArrayList<Development> harvestCards = player.getMyBoard().getPersonalBuildings().getCards();
		for (Development  card:harvestCards){
			Territories territory = (Territories) card;
			if (territory.getCostDie() <= getDieValue()){
				territory.getImmediateEffect().giveEffectValues(player);
				territory.getImmediateEffect().giveImmediateEffect(player);
				} 
			}
		}
}
