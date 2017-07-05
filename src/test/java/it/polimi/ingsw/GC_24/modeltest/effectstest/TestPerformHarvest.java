package it.polimi.ingsw.GC_24.modeltest.effectstest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.cards.Territories;
import it.polimi.ingsw.GC_24.model.effects.immediate.ChooseNewCard;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.immediate.PerformHarvest;
import it.polimi.ingsw.GC_24.model.effects.immediate.ValueEffect;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

public class TestPerformHarvest {

	Player player;
	PerformHarvest ph;
	ValueEffect ve;
	Territories territory;
	Territories territory2;
	SetOfValues expectedSet;
	List<ImmediateEffect> immediateEffectsHarvest  = new ArrayList<>();
	ChooseNewCard  cnc;

	@Before
	public void setUp() {
		player = new Player(1);
		player.getMyValues().setInitialValues(1);
		ve = new ValueEffect("valueEffect");
		ve.getEffectValues().getCoins().setQuantity(3);
		cnc = new ChooseNewCard("choose", "Character", 2, null);
		territory = new Territories("Territory", 3, "Territory", null, null, null, ve, 2);
		territory2 = new Territories("Territory2", 4, "Territory", null, null, null, cnc, 1);
		player.getMyBoard().getPersonalTerritories().getCards().add(territory);
		player.getMyBoard().getPersonalTerritories().getCards().add(territory2);
		ph = new PerformHarvest("harvest", 5);
		expectedSet = new SetOfValues();
		expectedSet.setInitialValues(1);
	}

	@Test
	public void testGiveImmediateEffect() {
		ph.giveImmediateEffect(player);
		expectedSet.getCoins().addQuantity(3);
		assertEquals(expectedSet, player.getMyValues());
	}
	
	@Test
	public void testGiveImmediateEffect2() {
		ph.giveImmediateEffect(player);
		immediateEffectsHarvest.add(cnc);
		assertEquals(immediateEffectsHarvest,ph.getImmediateEffectsHarvest());
	}
}
