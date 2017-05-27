package it.polimi.ingsw.GC_24.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.values.Coin;

public class TestCoin extends TestValue {
	
	Coin coin;
	
	@Before
	public void setUp() throws Exception {
		coin = new Coin(0);
	}
	
	@Override
	public void testAddValueToSetPositive() throws Exception {
		coin.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getCoins().addQuantity(3);
		assertEquals(valuesexpected, coin.addValueToSet(values));
	}

	@Override
	public void testAddValueToSetNegative() throws Exception {
		coin.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getCoins().subQuantity(3);
		assertFalse(valuesexpected.equals(coin.addValueToSet(values)));
	}
	
	@Override
	public void testAddValueToSetZero() throws Exception {
		coin.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getCoins().addQuantity(0);
		assertEquals(valuesexpected, coin.addValueToSet(values));
	}
	
	@Override
	public void testFindValueInPlayer() throws Exception {
		coin.setQuantity(5);
		player.getMyValues().getCoins().setQuantity(10);
		assertEquals(player.getMyValues().getCoins(), coin.findValueInPlayer(player));
	}
	
}
