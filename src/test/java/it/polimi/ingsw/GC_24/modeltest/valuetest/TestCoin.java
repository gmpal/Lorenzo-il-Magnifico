package it.polimi.ingsw.GC_24.modeltest.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.values.Coin;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

public class TestCoin {
	
	Coin coin;
	SetOfValues values;
	SetOfValues valuesexpected;
	Player player;
	
	@Before
	public void setUp() {
		coin = new Coin(0);
		values = new SetOfValues();
		player = new Player("Giorgia", PlayerColour.RED);
		valuesexpected = new SetOfValues();
	}
	
	@Test
	public void testAddValueToSetPositive() {
		coin.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getCoins().addQuantity(3);
		assertEquals(valuesexpected, coin.addValueToSet(values));
	}

	@Test
	public void testAddValueToSetNegative() {
		coin.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getCoins().subQuantity(3);
		assertFalse(valuesexpected.equals(coin.addValueToSet(values)));
	}
	
	@Test
	public void testAddValueToSetZero() {
		coin.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getCoins().addQuantity(0);
		assertEquals(valuesexpected, coin.addValueToSet(values));
	}
	
	@Test
	public void testFindValueInPlayer() {
		coin.setQuantity(5);
		player.getMyValues().getCoins().setQuantity(10);
		assertEquals(player.getMyValues().getCoins(), coin.findValueInPlayer(player));
	}
	
	@Test
	public void testAmIPresentInThisSetFalse() {
		coin.setQuantity(5);
		values.getCoins().setQuantity(3);
		assertFalse(coin.amIPresentInThisSet(values));
	}
	
	@Test
	public void testAmIPresentInThisSetTrue() {
		coin.setQuantity(5);
		values.getCoins().setQuantity(9);
		assertTrue(coin.amIPresentInThisSet(values));
	}
	
}
