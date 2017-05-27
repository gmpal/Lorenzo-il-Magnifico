package it.polimi.ingsw.GC_24.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.values.Servant;

public class TestServant extends TestValue {

	Servant servant;
	
	@Before
	public void setUp() throws Exception {
		servant = new Servant(0);
	}
	
	@Override
	public void testAddValueToSetPositive() throws Exception {
		servant.setQuantity(3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getServants().addQuantity(3);
		assertEquals(valuesexpected, servant.addValueToSet(values));
	}

	@Override
	public void testAddValueToSetNegative() throws Exception {
		servant.setQuantity(-3);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getServants().subQuantity(3);
		assertFalse(valuesexpected.equals(servant.addValueToSet(values)));
	}
	
	@Override
	public void testAddValueToSetZero() throws Exception {
		servant.setQuantity(0);
		values.setInitialValues(1);
		valuesexpected.setInitialValues(1);
		valuesexpected.getServants().addQuantity(0);
		assertEquals(valuesexpected, servant.addValueToSet(values));
	}
	
	@Override
	public void testFindValueInPlayer() throws Exception {
		servant.setQuantity(5);
		player.getMyValues().getServants().setQuantity(10);
		assertEquals(player.getMyValues().getServants(), servant.findValueInPlayer(player));
	}
	
}
