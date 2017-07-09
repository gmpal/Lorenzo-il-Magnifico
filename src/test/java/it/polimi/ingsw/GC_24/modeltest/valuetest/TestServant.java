package it.polimi.ingsw.GC_24.modeltest.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.values.Servant;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

public class TestServant {

	Servant servant;
	SetOfValues values;
	SetOfValues valuesexpected;
	Player player;
	
	
	@Before
	public void setUp() {
		servant = new Servant(0);
		values = new SetOfValues();
		player = new Player("Giorgia", PlayerColour.RED);
		valuesexpected = new SetOfValues();
	}
	
	@Test
	public void testAddValueToSetPositive() {
		servant.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getServants().addQuantity(3);
		assertEquals(valuesexpected, servant.addValueToSet(values));
	}

	@Test
	public void testAddValueToSetNegative() {
		servant.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getServants().subQuantity(3);
		assertFalse(valuesexpected.equals(servant.addValueToSet(values)));
	}
	
	@Test
	public void testAddValueToSetZero() {
		servant.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getServants().addQuantity(0);
		assertEquals(valuesexpected, servant.addValueToSet(values));
	}
	
	@Test
	public void testFindValueInPlayer() {
		servant.setQuantity(5);
		player.getMyValues().getServants().setQuantity(10);
		assertEquals(player.getMyValues().getServants(), servant.findValueInPlayer(player));
	}
	
	@Test
	public void testAmIPresentInThisSetFalse() {
		servant.setQuantity(5);
		values.getServants().setQuantity(3);
		assertFalse(servant.amIPresentInThisSet(values));
	}
	
	@Test
	public void testAmIPresentInThisSetTrue() {
		servant.setQuantity(5);
		values.getServants().setQuantity(9);
		assertTrue(servant.amIPresentInThisSet(values));
	}
}
