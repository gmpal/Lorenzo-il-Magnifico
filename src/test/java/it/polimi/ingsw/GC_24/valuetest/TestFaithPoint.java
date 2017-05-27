package it.polimi.ingsw.GC_24.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.values.FaithPoint;


public class TestFaithPoint extends TestValue {

	FaithPoint faithPoint;
	
	@Before
	public void setUp() throws Exception {
		faithPoint = new FaithPoint(0);
	}
	
	@Override
	public void testAddValueToSetPositive() throws Exception {
		faithPoint.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getFaithPoints().addQuantity(3);
		assertEquals(valuesexpected, faithPoint.addValueToSet(values));
	}

	@Override
	public void testAddValueToSetNegative() throws Exception {
		faithPoint.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getFaithPoints().subQuantity(3);
		assertFalse(valuesexpected.equals(faithPoint.addValueToSet(values)));
	}
	
	@Override
	public void testAddValueToSetZero() throws Exception {
		faithPoint.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getFaithPoints().addQuantity(0);
		assertEquals(valuesexpected, faithPoint.addValueToSet(values));
	}
	
	@Override
	public void testFindValueInPlayer() throws Exception {
		faithPoint.setQuantity(5);
		player.getMyValues().getFaithPoints().setQuantity(10);
		assertEquals(player.getMyValues().getFaithPoints(), faithPoint.findValueInPlayer(player));
	}
	
}