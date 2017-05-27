package it.polimi.ingsw.GC_24.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.values.Stone;


public class TestStone extends TestValue {

	Stone stone;
	
	@Before
	public void setUp() throws Exception {
		stone = new Stone(0);
	}
	
	@Override
	public void testAddValueToSetPositive() throws Exception {
		stone.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getStones().addQuantity(3);
		assertEquals(valuesexpected, stone.addValueToSet(values));
	}

	@Override
	public void testAddValueToSetNegative() throws Exception {
		stone.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getStones().subQuantity(3);
		assertFalse(valuesexpected.equals(stone.addValueToSet(values)));
	}
	
	@Override
	public void testAddValueToSetZero() throws Exception {
		stone.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getStones().addQuantity(0);
		assertEquals(valuesexpected, stone.addValueToSet(values));
	}
	
	@Override
	public void testFindValueInPlayer() throws Exception {
		stone.setQuantity(5);
		player.getMyValues().getStones().setQuantity(10);
		assertEquals(player.getMyValues().getStones(), stone.findValueInPlayer(player));
	}
	
}
