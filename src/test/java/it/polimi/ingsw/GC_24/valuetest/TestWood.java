package it.polimi.ingsw.GC_24.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.values.Wood;

public class TestWood extends TestValue {

	Wood wood;
	
	@Before
	public void setUp() throws Exception {
		wood = new Wood(0);
	}
	
	@Override
	public void testAddValueToSetPositive() throws Exception {
		wood.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getWoods().addQuantity(3);
		assertEquals(valuesexpected, wood.addValueToSet(values));
	}

	@Override
	public void testAddValueToSetNegative() throws Exception {
		wood.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getWoods().subQuantity(3);
		assertFalse(valuesexpected.equals(wood.addValueToSet(values)));
	}
	
	@Override
	public void testAddValueToSetZero() throws Exception {
		wood.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getWoods().addQuantity(0);
		assertEquals(valuesexpected, wood.addValueToSet(values));
	}
	
	@Override
	public void testFindValueInPlayer() throws Exception {
		wood.setQuantity(5);
		player.getMyValues().getWoods().setQuantity(10);
		assertEquals(player.getMyValues().getWoods(), wood.findValueInPlayer(player));
	}
	
}

