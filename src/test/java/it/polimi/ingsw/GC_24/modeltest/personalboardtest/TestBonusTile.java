package it.polimi.ingsw.GC_24.modeltest.personalboardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.personalboard.BonusTile;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

public class TestBonusTile {
	
	BonusTile bonusTile;
	SetOfValues values;
	SetOfValues valuesExpected;
	SetOfValues harvestValues;
	SetOfValues productionValues;
	
	@Before
	public void setUp() {
		values = new SetOfValues();
		valuesExpected = new SetOfValues();
	}
	
	@Test
	public void testGiveHarvestValuesBasic() {
		bonusTile = new BonusTile(false, 0);
		harvestValues = bonusTile.getHarvestValues();
		values.setInitialValues(1);
		valuesExpected.setInitialValues(1);
		valuesExpected.getWoods().addQuantity(1);
		valuesExpected.getStones().addQuantity(1);
		valuesExpected.getServants().addQuantity(1);
		bonusTile.giveHarvestValues(values);
		assertEquals(valuesExpected, values);
	}

	@Test
	public void testGiveHarvestValuesAdvanced() {
		bonusTile = new BonusTile(true, 1);
		harvestValues = bonusTile.getHarvestValues();
		values.setInitialValues(1);
		valuesExpected.setInitialValues(1);
		valuesExpected.getWoods().addQuantity(1);
		valuesExpected.getStones().addQuantity(1);
		valuesExpected.getMilitaryPoints().addQuantity(1);
		bonusTile.giveHarvestValues(values);
		assertEquals(valuesExpected, values);
	}
	
	@Test
	public void testGiveProductiontValuesBasic() {
		bonusTile = new BonusTile(false, 0);
		productionValues = bonusTile.getProductionValues();
		values.setInitialValues(1);
		valuesExpected.setInitialValues(1);
		valuesExpected.getCoins().addQuantity(2);
		valuesExpected.getMilitaryPoints().addQuantity(1);
		bonusTile.giveProductionValues(values);
		assertEquals(valuesExpected, values);
	}

	@Test
	public void testGiveProductiontValuesAdvanced() {
		bonusTile = new BonusTile(true, 3);
		productionValues = bonusTile.getProductionValues();
		values.setInitialValues(1);
		valuesExpected.setInitialValues(1);
		valuesExpected.getCoins().addQuantity(2);
		valuesExpected.getServants().addQuantity(1);
		bonusTile.giveProductionValues(values);
		assertEquals(valuesExpected, values);
	}

}
