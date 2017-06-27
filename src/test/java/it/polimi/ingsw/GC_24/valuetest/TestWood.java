package it.polimi.ingsw.GC_24.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Wood;

public class TestWood {

	Wood wood;
	SetOfValues values;
	SetOfValues valuesexpected;
	Player player;
	
	@Before
	public void setUp() throws Exception {
		wood = new Wood(0);
		values = new SetOfValues();
		player = new Player("Giorgia", PlayerColour.RED);
		valuesexpected = new SetOfValues();
	}
	
	@Test
	public void testAddValueToSetPositive() throws Exception {
		wood.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getWoods().addQuantity(3);
		assertEquals(valuesexpected, wood.addValueToSet(values));
	}

	@Test
	public void testAddValueToSetNegative() throws Exception {
		wood.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getWoods().subQuantity(3);
		assertFalse(valuesexpected.equals(wood.addValueToSet(values)));
	}
	
	@Test
	public void testAddValueToSetZero() throws Exception {
		wood.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getWoods().addQuantity(0);
		assertEquals(valuesexpected, wood.addValueToSet(values));
	}
	
	@Test
	public void testFindValueInPlayer() throws Exception {
		wood.setQuantity(5);
		player.getMyValues().getWoods().setQuantity(10);
		assertEquals(player.getMyValues().getWoods(), wood.findValueInPlayer(player));
	}
	
	@Test
	public void testAmIPresentInThisSetFalse() throws Exception {
		wood.setQuantity(5);
		values.getWoods().setQuantity(3);
		assertFalse(wood.amIPresentInThisSet(values));
	}
	
	@Test
	public void testAmIPresentInThisSetTrue() throws Exception {
		wood.setQuantity(5);
		values.getWoods().setQuantity(9);
		assertTrue(wood.amIPresentInThisSet(values));
	}
	
}

