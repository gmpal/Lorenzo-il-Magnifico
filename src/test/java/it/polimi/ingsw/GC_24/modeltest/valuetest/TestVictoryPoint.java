package it.polimi.ingsw.GC_24.modeltest.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;
import it.polimi.ingsw.GC_24.model.values.VictoryPoint;

public class TestVictoryPoint {
	
	VictoryPoint victoryPoint;
	SetOfValues values;
	SetOfValues valuesexpected;
	Player player;

	@Before
	public void setUp() {
		victoryPoint = new VictoryPoint(0);
		values = new SetOfValues();
		player = new Player("Giorgia", PlayerColour.RED);
		valuesexpected = new SetOfValues();
	}
	
	@Test
	public void testAddValueToSetPositive() {
		victoryPoint.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getVictoryPoints().addQuantity(3);
		assertEquals(valuesexpected, victoryPoint.addValueToSet(values));
	}

	@Test
	public void testAddValueToSetNegative() {
		victoryPoint.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getVictoryPoints().subQuantity(3);
		assertTrue(valuesexpected.equals(victoryPoint.addValueToSet(values)));
	}
	
	@Test
	public void testAddValueToSetZero() {
		victoryPoint.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getVictoryPoints().addQuantity(0);
		assertEquals(valuesexpected, victoryPoint.addValueToSet(values));
	}
	
	@Test
	public void testFindValueInPlayer() {
		victoryPoint.setQuantity(5);
		player.getMyValues().getVictoryPoints().setQuantity(10);
		assertEquals(player.getMyValues().getVictoryPoints(), victoryPoint.findValueInPlayer(player));
	}
	
	@Test
	public void testAmIPresentInThisSetFalse() {
		victoryPoint.setQuantity(5);
		values.getVictoryPoints().setQuantity(3);
		assertFalse(victoryPoint.amIPresentInThisSet(values));
	}
	
	@Test
	public void testAmIPresentInThisSetTrue() {
		victoryPoint.setQuantity(5);
		values.getVictoryPoints().setQuantity(9);
		assertTrue(victoryPoint.amIPresentInThisSet(values));
	}
	
}