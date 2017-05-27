package it.polimi.ingsw.GC_24.valuetest;

import static org.junit.Assert.*;

import org.junit.Before;
import it.polimi.ingsw.GC_24.values.VictoryPoint;

public class TestVictoryPoint extends TestValue {
	
	VictoryPoint victoryPoint;

	@Before
	public void setUp() throws Exception {
		victoryPoint = new VictoryPoint(0);
	}
	
	@Override
	public void testAddValueToSetPositive() throws Exception {
		victoryPoint.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getVictoryPoints().addQuantity(3);
		assertEquals(valuesexpected, victoryPoint.addValueToSet(values));
	}

	@Override
	public void testAddValueToSetNegative() throws Exception {
		victoryPoint.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getVictoryPoints().subQuantity(3);
		assertFalse(valuesexpected.equals(victoryPoint.addValueToSet(values)));
	}
	
	@Override
	public void testAddValueToSetZero() throws Exception {
		victoryPoint.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getVictoryPoints().addQuantity(0);
		assertEquals(valuesexpected, victoryPoint.addValueToSet(values));
	}
	
	@Override
	public void testFindValueInPlayer() throws Exception {
		victoryPoint.setQuantity(5);
		player.getMyValues().getVictoryPoints().setQuantity(10);
		assertEquals(player.getMyValues().getVictoryPoints(), victoryPoint.findValueInPlayer(player));
	}
	
}