package it.polimi.ingsw.GC_24.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;


public class TestMilitaryPoint {

	MilitaryPoint militaryPoint;
	SetOfValues values;
	SetOfValues valuesexpected;
	Player player;
	
	@Before
	public void setUp() throws Exception {
		militaryPoint = new MilitaryPoint(0);
		values = new SetOfValues();
		player = new Player("Giorgia", PlayerColour.RED);
		valuesexpected = new SetOfValues();
	}
	
	@Test
	public void testAddValueToSetPositive() throws Exception {
		militaryPoint.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getMilitaryPoints().addQuantity(3);
		assertEquals(valuesexpected, militaryPoint.addValueToSet(values));
	}

	@Test
	public void testAddValueToSetNegative() throws Exception {
		militaryPoint.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getMilitaryPoints().subQuantity(3);
		assertFalse(valuesexpected.equals(militaryPoint.addValueToSet(values)));
	}
	
	@Test
	public void testAddValueToSetZero() throws Exception {
		militaryPoint.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getMilitaryPoints().addQuantity(0);
		assertEquals(valuesexpected, militaryPoint.addValueToSet(values));
	}
	
	@Test
	public void testFindValueInPlayer() throws Exception {
		militaryPoint.setQuantity(5);
		player.getMyValues().getMilitaryPoints().setQuantity(10);
		assertEquals(player.getMyValues().getMilitaryPoints(), militaryPoint.findValueInPlayer(player));
	}
	
	@Test
	public void testAmIPresentInThisSetFalse() throws Exception {
		militaryPoint.setQuantity(5);
		values.getMilitaryPoints().setQuantity(3);
		assertFalse(militaryPoint.amIPresentInThisSet(values));
	}
	
	@Test
	public void testAmIPresentInThisSetTrue() throws Exception {
		militaryPoint.setQuantity(5);
		values.getMilitaryPoints().setQuantity(9);
		assertTrue(militaryPoint.amIPresentInThisSet(values));
	}
	
	
}