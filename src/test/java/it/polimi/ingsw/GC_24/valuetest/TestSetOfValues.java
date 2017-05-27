package it.polimi.ingsw.GC_24.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.values.Coin;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class TestSetOfValues {
	
	SetOfValues values;
	Coin coin;
	SetOfValues values2;
	SetOfValues valuesexpected;
	
	@Before
	public void setUp() throws Exception {
		coin = new Coin(0);
		values = new SetOfValues();
		values2 = new SetOfValues();
		valuesexpected = new SetOfValues();
	}

	@Test
	public void testAddTwoSetsOfValues() {
		values.setInitialValues(1);
		values2.getCoins().setQuantity(8);
		values2.getMilitaryPoints().setQuantity(5);
		valuesexpected.setInitialValues(1);
		valuesexpected.getMilitaryPoints().setQuantity(5);
		valuesexpected.getCoins().setQuantity(13);
		assertEquals(valuesexpected, values2.addTwoSetsOfValues(values));
	}

}
