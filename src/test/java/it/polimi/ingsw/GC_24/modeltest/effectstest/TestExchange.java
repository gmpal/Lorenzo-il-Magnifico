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
	SetOfValues set;
	ValueEffect ve;

	@Before
	public void setUp() {
		player = new Player(1);
		player.getMyValues().setInitialValues(1);
		set = new SetOfValues();
		expectedSet = new SetOfValues();
		expectedSet.setInitialValues(1);
		set.getStones().setQuantity(2);
		ve = new ValueEffect("valueEffect");
		ve.getEffectValues().getCoins().setQuantity(3);
		ep = new ExchangePackage(set, ve);
		ex = new Exchange("exchange", ep, null);
	}

	@Test
	public void testGiveImmediateEffect() {
		ex.giveImmediateEffect(player);
		expectedSet.getStones().subQuantity(2);
		expectedSet.getCoins().addQuantity(3);
		assertEquals(expectedSet, player.getMyValues());
	}
}
