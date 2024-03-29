package it.polimi.ingsw.GC_24.modeltest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.effects.immediate.ValueEffect;
import it.polimi.ingsw.GC_24.model.places.*;
import it.polimi.ingsw.GC_24.model.values.*;

public class TestPlayer {
	
	Player player;
	SetOfValues values;
	ValueEffect value;
	Place place;
	
	@Before
	public void setUp() {
		value = new ValueEffect("value");
		value.getEffectValues().getCoins().setQuantity(2);
		values = new SetOfValues();
		player = new Player("Giorgia", PlayerColour.RED);
		place = new CouncilPlace(3, value, null);
	}

	@Test
	public void testGetMyValuesFromColour() {
		player.getMyValues().setInitialValues(1);
		values.setInitialValues(1);
		assertEquals(values, player.getMyValuesFromColour(PlayerColour.RED));
	}
	
	@Test
	public void testGetMyValuesFromColourTwoDifferentColours() {
		player.getMyValues().setInitialValues(1);
		values.setInitialValues(1);
		assertFalse(values.equals(player.getMyValuesFromColour(PlayerColour.GREEN)));
	}

	@Test
	public void testTakeValuesFromPlace() {
		player.getMyValues().setInitialValues(1);
		values.setInitialValues(1);
		values.getCoins().addQuantity(2);
		player.takeValuesFromPlace(place);
		assertEquals(values, player.getMyValues());
	}

	@Test
	public void testIsPossibleIncreaseDieValueTrue() {
		player.getMyValues().setInitialValues(1);
		values.getCoins().setQuantity(4);
		assertTrue(player.isPossibleIncreaseDieValue(2));
	}
	
	@Test
	public void testIsPossibleIncreaseDieValueFalse() {
		player.getMyValues().setInitialValues(1);
		values.getCoins().setQuantity(4);
		assertFalse(player.isPossibleIncreaseDieValue(5));
	}
	
	@Test
	public void testIsPossibleIncreaseDieValueNegative() {
		player.getMyValues().setInitialValues(1);
		values.getCoins().setQuantity(4);
		assertFalse(player.isPossibleIncreaseDieValue(-2));
	}
}