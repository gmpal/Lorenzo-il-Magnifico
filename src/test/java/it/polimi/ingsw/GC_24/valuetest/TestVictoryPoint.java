package it.polimi.ingsw.GC_24.valuetest;

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
	public void setUp() throws Exception {
		victoryPoint = new VictoryPoint(0);
		values = new SetOfValues();
		player = new Player("Giorgia", PlayerColour.RED);
		valuesexpected = new SetOfValues();
	}
	
	@Test
	public void testAddValueToSetPositive() throws Exception {
		victoryPoint.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getVictoryPoints().addQuantity(3);
		assertEquals(valuesexpected, victoryPoint.addValueToSet(values));
	}

	@Test
	public void testAddValueToSetNegative() throws Exception {
		victoryPoint.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getVictoryPoints().subQuantity(3);
		assertFalse(valuesexpected.equals(victoryPoint.addValueToSet(values)));
	}
	
	@Test
	public void testAddValueToSetZero() throws Exception {
		victoryPoint.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getVictoryPoints().addQuantity(0);
		assertEquals(valuesexpected, victoryPoint.addValueToSet(values));
	}
	
	@Test
	public void testFindValueInPlayer() throws Exception {
		victoryPoint.setQuantity(5);
		player.getMyValues().getVictoryPoints().setQuantity(10);
		assertEquals(player.getMyValues().getVictoryPoints(), victoryPoint.findValueInPlayer(player));
	}
	
	@Test
	public void testAmIPresentInThisSetFalse() throws Exception {
		victoryPoint.setQuantity(5);
		values.getVictoryPoints().setQuantity(3);
		assertFalse(victoryPoint.amIPresentInThisSet(values));
	}
	
	@Test
	public void testAmIPresentInThisSetTrue() throws Exception {
		victoryPoint.setQuantity(5);
		values.getVictoryPoints().setQuantity(9);
		assertTrue(victoryPoint.amIPresentInThisSet(values));
	}
	
}