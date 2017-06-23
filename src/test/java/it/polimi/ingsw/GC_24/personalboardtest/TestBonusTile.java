package it.polimi.ingsw.GC_24.personalboardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.personalboard.BonusTile;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class TestBonusTile {
	
	BonusTile bonusTile;
	SetOfValues values;
	SetOfValues valuesExpected;
	SetOfValues harvestValues;
	SetOfValues productionValues;

	
	@Before
	public void setUp() throws Exception {
		values = new SetOfValues();
		valuesExpected = new SetOfValues();
		harvestValues = new SetOfValues();
		productionValues = new SetOfValues();
		bonusTile = new BonusTile(true, 1);
	}

	@Test
	public void testGiveHarvestValues() throws Exception {
		values.setInitialValues(1);
		harvestValues.getCoins().setQuantity(5);
		harvestValues.getServants().setQuantity(3);
		valuesExpected.setInitialValues(1);
		valuesExpected.getCoins().addQuantity(5);
		valuesExpected.getServants().addQuantity(3);
		bonusTile.giveHarvestValues(values);
		assertEquals(valuesExpected, values);
	}

	@Test
	public void testGiveProductiontValues() throws Exception {
		values.setInitialValues(1);
		productionValues.getStones().setQuantity(3);
		productionValues.getWoods().setQuantity(6);
		valuesExpected.setInitialValues(1);
		valuesExpected.getStones().addQuantity(3);
		valuesExpected.getWoods().addQuantity(6);
		bonusTile.giveProductionValues(values);
		assertEquals(valuesExpected, values);
	}

}
