package it.polimi.ingsw.GC_24.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.values.Servant;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class TestServant {

	Servant servant;
	SetOfValues values;
	SetOfValues valuesexpected;
	Player player;
	
	
	@Before
	public void setUp() throws Exception {
		servant = new Servant(0);
		values = new SetOfValues();
		player = new Player("Giorgia", PlayerColour.RED);
		valuesexpected = new SetOfValues();
	}
	
	@Test
	public void testAddValueToSetPositive() throws Exception {
		servant.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getServants().addQuantity(3);
		assertEquals(valuesexpected, servant.addValueToSet(values));
	}

	@Test
	public void testAddValueToSetNegative() throws Exception {
		servant.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getServants().subQuantity(3);
		assertFalse(valuesexpected.equals(servant.addValueToSet(values)));
	}
	
	@Test
	public void testAddValueToSetZero() throws Exception {
		servant.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getServants().addQuantity(0);
		assertEquals(valuesexpected, servant.addValueToSet(values));
	}
	
	@Test
	public void testFindValueInPlayer() throws Exception {
		servant.setQuantity(5);
		player.getMyValues().getServants().setQuantity(10);
		assertEquals(player.getMyValues().getServants(), servant.findValueInPlayer(player));
	}
	
}
