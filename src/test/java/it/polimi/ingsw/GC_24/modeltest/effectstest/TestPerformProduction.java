package it.polimi.ingsw.GC_24.modeltest.effectstest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.cards.Buildings;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.immediate.PerformHarvest;
import it.polimi.ingsw.GC_24.model.effects.immediate.PerformProduction;
import it.polimi.ingsw.GC_24.model.effects.immediate.ValueEffect;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

public class TestPerformProduction {

	Player player;
	PerformHarvest ph;
	ValueEffect ve;
	Buildings building;
	Buildings building2;
	SetOfValues expectedSet;
	List<ImmediateEffect> immediateEffectsProduction  = new ArrayList<>();
	PerformProduction pp;

	@Before
	public void setUp() {
		player = new Player(1);
		player.getMyValues().setInitialValues(1);
		ve = new ValueEffect("value");
		ve.getEffectValues().getVictoryPoints().setQuantity(3);
		ph = new PerformHarvest("harvest", 5);
		building = new Buildings("Building", 3, "Building", null, null, null, ve, ph, 2);
		building2 = new Buildings("Building2", 4, "Building", null, null, null, ph, null, 1);
		player.getMyBoard().getPersonalBuildings().getCards().add(building);
		player.getMyBoard().getPersonalBuildings().getCards().add(building2);
		pp = new PerformProduction("production", 4);
		expectedSet = new SetOfValues();
		expectedSet.setInitialValues(1);
	}

	@Test
	public void testGiveImmediateEffect() {
		pp.giveImmediateEffect(player);
		expectedSet.getVictoryPoints().addQuantity(3);
		assertEquals(expectedSet, player.getMyValues());
	}
	
	@Test
	public void testGiveImmediateEffect2() {
		pp.giveImmediateEffect(player);
		immediateEffectsProduction.add(ph);
		immediateEffectsProduction.add(ph);
		assertEquals(immediateEffectsProduction, pp.getImmediateEffectsProduction());
	}
}
