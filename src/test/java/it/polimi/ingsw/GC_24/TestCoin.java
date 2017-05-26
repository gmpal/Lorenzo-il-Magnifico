package it.polimi.ingsw.GC_24;

import static org.junit.Assert.*;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.values.*;

public class TestCoin {
	
	private SetOfValues values = new SetOfValues();
	private Coin coin = new Coin(0);
	private SetOfValues valuesexpected = new SetOfValues();
	private Player player = new Player("Giorgia", PlayerColour.RED);
	
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
	
}
