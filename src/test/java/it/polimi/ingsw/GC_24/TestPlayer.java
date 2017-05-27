package it.polimi.ingsw.GC_24;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.places.*;
import it.polimi.ingsw.GC_24.values.*;

public class TestPlayer {
	
	Player player;
	SetOfValues values;
	Coin coin;
	Place place;
	
	@Before
	public void setUp() throws Exception {
		coin = new Coin(2);
		values = new SetOfValues();
		player = new Player("Giorgia", PlayerColour.RED);
		place = new CouncilPlace(3, coin, null);
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
