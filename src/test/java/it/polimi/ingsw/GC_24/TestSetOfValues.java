package it.polimi.ingsw.GC_24;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_24.values.Coin;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class TestSetOfValues {
	
	private SetOfValues values = new SetOfValues();
	private Coin coin = new Coin(0);
	private SetOfValues values2 = new SetOfValues();
	private SetOfValues valuesexpected = new SetOfValues();

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
