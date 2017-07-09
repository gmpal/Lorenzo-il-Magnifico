package it.polimi.ingsw.GC_24.modeltest.valuetest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.values.Coin;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;
import it.polimi.ingsw.GC_24.model.values.Value;
import it.polimi.ingsw.GC_24.model.values.VictoryPoint;

public class TestSetOfValues {
	
	SetOfValues values;
	Coin coin;
	SetOfValues values2;
	SetOfValues valuesexpected;
	Value victoryPoints;
	
	@Before
	public void setUp() {
		coin = new Coin(0);
		values = new SetOfValues();
		values2 = new SetOfValues();
		valuesexpected = new SetOfValues();
		victoryPoints = new VictoryPoint(0);
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
	
	@Test
	public void testSubTwoSetsOfValues() {
		values.setInitialValues(1);
		values2.getCoins().setQuantity(2);
		values2.getWoods().setQuantity(3);
		valuesexpected.setInitialValues(1);
		valuesexpected.getWoods().setQuantity(0);
		valuesexpected.getCoins().setQuantity(3);
		assertEquals(valuesexpected, values2.subTwoSetsOfValues(values));
	}
	
	@Test
	public void testDoIHaveEnoughOfThisFalse() {
		coin.setQuantity(6);
		values.setInitialValues(1);
		assertFalse(values.doIHaveEnoughOfThis(coin));
	}
	
	@Test
	public void testDoIHaveEnoughOfThisTrue() {
		coin.setQuantity(2);
		values.setInitialValues(1);
		assertTrue(values.doIHaveEnoughOfThis(coin));
	}
	
	@Test
	public void testDoIHaveThisSetFalse() {
		values.setInitialValues(1);
		values2.getMilitaryPoints().setQuantity(2);
		values2.getStones().setQuantity(1);
		assertFalse(values.doIHaveThisSet(values2));
	}
	
	@Test
	public void testDoIHaveThisSetTrue() {
		values.setInitialValues(1);
		values2.getCoins().setQuantity(4);
		values2.getStones().setQuantity(1);
		assertTrue(values.doIHaveThisSet(values2));
	}

	@Test
	public void testIsAcceptableFalse() {
		values.setInitialValues(1);
		values2.getMilitaryPoints().setQuantity(5);
		values2.subTwoSetsOfValues(values);
		assertFalse(values.isAcceptable());
	}
	
	@Test
	public void testIsAcceptableTrue() {
		values.setInitialValues(1);
		assertTrue(values.isAcceptable());
	}
	
	@Test
	public void testIsEmptyFalse() {
		values.setInitialValues(1);
		assertFalse(values.isEmpty());
	}
	
	@Test
	public void testIsEmptyTrue() {
		assertTrue(values.isEmpty());
	}
	
	@Test
	public void testConvertSetToVictoryPoints() {
		values.setInitialValues(1);
		victoryPoints.setQuantity(2);
		assertEquals(victoryPoints, values.convertSetToVictoryPoints());
	}
}
