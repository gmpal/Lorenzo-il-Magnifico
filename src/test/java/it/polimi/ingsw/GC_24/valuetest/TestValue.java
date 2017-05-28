package it.polimi.ingsw.GC_24.valuetest;

import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public abstract class TestValue {

	protected SetOfValues values = new SetOfValues();
	protected SetOfValues valuesexpected = new SetOfValues();
	protected Player player = new Player("Giorgia", PlayerColour.RED);
	
	@Test
	public abstract void testAddValueToSetPositive() throws Exception;
	
	@Test
	public abstract void testAddValueToSetNegative() throws Exception;
	
	@Test
	public abstract void testAddValueToSetZero() throws Exception;
	
	@Test
	public abstract void testFindValueInPlayer() throws Exception;
}
