package it.polimi.ingsw.GC_24.modeltest.effectstest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.effects.immediate.MoltiplicationPoints;
import it.polimi.ingsw.GC_24.model.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.model.values.Servant;

public class TestMoltiplicationPoints {

	Player player;
	MoltiplicationPoints mp;
	MilitaryPoint militaryPoints;

	@Before
	public void setUp() {
		player = new Player(1);
		militaryPoints = new MilitaryPoint(0);
		mp = new MoltiplicationPoints("moltiplicationPoints", new MilitaryPoint(1), new Servant(3));
	}

	@Test
	public void testGiveImmediateEffect() {
		player.getMyValues().getServants().setQuantity(10);
		mp.giveImmediateEffect(player);
		militaryPoints.setQuantity(3);
		assertEquals(militaryPoints, player.getMyValues().getMilitaryPoints());
	}
}
