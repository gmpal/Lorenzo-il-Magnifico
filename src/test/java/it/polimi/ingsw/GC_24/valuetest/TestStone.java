package it.polimi.ingsw.GC_24.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Stone;


public class TestStone {

	Stone stone;
	SetOfValues values;
	SetOfValues valuesexpected;
	Player player;
	
	@Before
	public void setUp() throws Exception {
		stone = new Stone(0);
		values = new SetOfValues();
		player = new Player("Giorgia", PlayerColour.RED);
		valuesexpected = new SetOfValues();
	}
	
	@Test
	public void testAddValueToSetPositive() throws Exception {
		stone.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getStones().addQuantity(3);
		assertEquals(valuesexpected, stone.addValueToSet(values));
	}

	@Test
	public void testAddValueToSetNegative() throws Exception {
		stone.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getStones().subQuantity(3);
		assertFalse(valuesexpected.equals(stone.addValueToSet(values)));
	}
	
	@Test
	public void testAddValueToSetZero() throws Exception {
		stone.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getStones().addQuantity(0);
		assertEquals(valuesexpected, stone.addValueToSet(values));
	}
	
	@Test
	public void testFindValueInPlayer() throws Exception {
		stone.setQuantity(5);
		player.getMyValues().getStones().setQuantity(10);
		assertEquals(player.getMyValues().getStones(), stone.findValueInPlayer(player));
	}
	
	@Test
	public void testAmIPresentInThisSetFalse() throws Exception {
		stone.setQuantity(5);
		values.getStones().setQuantity(3);
		assertFalse(stone.amIPresentInThisSet(values));
	}
	
	@Test
	public void testAmIPresentInThisSetTrue() throws Exception {
		stone.setQuantity(5);
		values.getStones().setQuantity(9);
		assertTrue(stone.amIPresentInThisSet(values));
	}
}
