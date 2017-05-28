package it.polimi.ingsw.GC_24.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.values.MilitaryPoint;


public class TestMilitaryPoint extends TestValue {

	MilitaryPoint militaryPoint;
	
	@Before
	public void setUp() throws Exception {
		militaryPoint = new MilitaryPoint(0);
	}
	
	@Override
	public void testAddValueToSetPositive() throws Exception {
		militaryPoint.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getMilitaryPoints().addQuantity(3);
		assertEquals(valuesexpected, militaryPoint.addValueToSet(values));
	}

	@Override
	public void testAddValueToSetNegative() throws Exception {
		militaryPoint.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getMilitaryPoints().subQuantity(3);
		assertFalse(valuesexpected.equals(militaryPoint.addValueToSet(values)));
	}
	
	@Override
	public void testAddValueToSetZero() throws Exception {
		militaryPoint.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getMilitaryPoints().addQuantity(0);
		assertEquals(valuesexpected, militaryPoint.addValueToSet(values));
	}
	
	@Override
	public void testFindValueInPlayer() throws Exception {
		militaryPoint.setQuantity(5);
		player.getMyValues().getMilitaryPoints().setQuantity(10);
		assertEquals(player.getMyValues().getMilitaryPoints(), militaryPoint.findValueInPlayer(player));
	}
	
}