package it.polimi.ingsw.GC_24.modeltest.effectstest;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.effects.immediate.Exchange;
import it.polimi.ingsw.GC_24.model.effects.immediate.ExchangePackage;
import it.polimi.ingsw.GC_24.model.effects.immediate.ValueEffect;
import it.polimi.ingsw.GC_24.model.values.*;

public class TestExchange {

	Player player;
	Exchange ex;
	SetOfValues expectedSet;
	ExchangePackage ep;
	ExchangePackage ep1;
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
		expectedSet = new SetOfValues();
		expectedSet.setInitialValues(1);
		set.getStones().setQuantity(2);
		set1.getWoods().setQuantity(1);
		ve = new ValueEffect("valueEffect");
		ve.getEffectValues().getCoins().setQuantity(3);
		ve1 = new ValueEffect("valueEffect");
		ve1.getEffectValues().getMilitaryPoints().setQuantity(2);
		ep = new ExchangePackage(set, ve);
		ep1 = new ExchangePackage(set1, ve1);
		ex = new Exchange("exchange", ep, ep1);
	}

	@Test
	public void testGiveImmediateEffect() {
		ex.giveImmediateEffect(player);
		expectedSet.getStones().subQuantity(2);
		expectedSet.getCoins().addQuantity(3);
		assertEquals(expectedSet, player.getMyValues());
	}
	
	@Test
	public void testAssignParameters1() {
		ex.assignParameters("1");
		assertEquals(ep, ex.getFinalExchange());
	}
	
	@Test
	public void testAssignParameters2() {
		ex.assignParameters("2");
		assertEquals(ep1, ex.getFinalExchange());
	}
	
	@Test
	public void testGenerateParametersRequest() {
		assertEquals("You have to choose between exchanging \n "+ ep.toString() + "\nand\n "
				+ ep1.toString(), ex.generateParametersRequest());
	}
}
