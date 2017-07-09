package it.polimi.ingsw.GC_24.modeltest.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.values.FaithPoint;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;


public class TestFaithPoint {

	FaithPoint faithPoint;
	SetOfValues values;
	SetOfValues valuesexpected;
	Player player;
	
	@Before
	public void setUp() {
		faithPoint = new FaithPoint(0);
		values = new SetOfValues();
		player = new Player("Giorgia", PlayerColour.RED);
		valuesexpected = new SetOfValues();
	}
	
	@Test
	public void testAddValueToSetPositive() {
		faithPoint.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getFaithPoints().addQuantity(3);
		assertEquals(valuesexpected, faithPoint.addValueToSet(values));
	}

	@Test
	public void testAddValueToSetNegative() {
		faithPoint.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getFaithPoints().subQuantity(3);
		assertTrue(valuesexpected.equals(faithPoint.addValueToSet(values)));
	}
	
	@Test
	public void testAddValueToSetZero() {
		faithPoint.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getFaithPoints().addQuantity(0);
		assertEquals(valuesexpected, faithPoint.addValueToSet(values));
	}
	
	@Test
	public void testFindValueInPlayer() {
		faithPoint.setQuantity(5);
		player.getMyValues().getFaithPoints().setQuantity(10);
		assertEquals(player.getMyValues().getFaithPoints(), faithPoint.findValueInPlayer(player));
	}
	
	@Test
	public void testAmIPresentInThisSetFalse() {
		faithPoint.setQuantity(5);
		values.getFaithPoints().setQuantity(3);
		assertFalse(faithPoint.amIPresentInThisSet(values));
	}
	
	@Test
	public void testAmIPresentInThisSetTrue() {
		faithPoint.setQuantity(5);
		values.getFaithPoints().setQuantity(9);
		assertTrue(faithPoint.amIPresentInThisSet(values));
	}
}