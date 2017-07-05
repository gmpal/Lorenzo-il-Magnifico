package it.polimi.ingsw.GC_24.modeltest.effectstest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.effects.immediate.CouncilPrivilege;
import it.polimi.ingsw.GC_24.model.effects.immediate.ValueEffect;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

public class TestCouncilPrivilege {

	Player player;
	CouncilPrivilege cp;
	SetOfValues expectedSet;
	SetOfValues set;
	SetOfValues set1;
	ValueEffect ve;
	ValueEffect ve1;

	@Before
	public void setUp() {
		player = new Player(1);
		player.getMyValues().setInitialValues(1);
		set = new SetOfValues();
		set1 = new SetOfValues();
		set1.setInitialValues(1);
		expectedSet = new SetOfValues();
		expectedSet.setInitialValues(1);
		ve = new ValueEffect("valueEffect");
		cp = new CouncilPrivilege("CouncilPrivilege", 1);
	}

	@Test
	public void testAssignParameters() {
		cp.assignParameters("2");
		set.getServants().setQuantity(2);
		assertEquals(set, cp.getSet());
	}
	
	@Test
	public void testGIveImmediateEffects() {
		cp.assignParameters("3");
		set1.getCoins().addQuantity(2);
		cp.giveImmediateEffect(player);
		assertEquals(set1, player.getMyValues());
	}
}
