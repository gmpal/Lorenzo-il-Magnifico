package it.polimi.ingsw.GC_24.modeltest.effectstest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.effects.permanent.ChangeServantsValue;
import it.polimi.ingsw.GC_24.model.values.Servant;

public class TestChangeServatsValue {

	ChangeServantsValue effect;
	Servant servantsForAction;
	
	@Before
	public void setUp() {
		effect = new ChangeServantsValue("ChangeServantsValue", 2);
		servantsForAction = new Servant(3);
		
	}
	
	@Test
	public void testChangeServantsValue() {
		effect.changeServantsValue(servantsForAction);
		assertEquals(1, servantsForAction.getQuantity());
	}
}
