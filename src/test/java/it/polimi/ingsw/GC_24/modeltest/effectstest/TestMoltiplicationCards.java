package it.polimi.ingsw.GC_24.modeltest.effectstest;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.cards.Territories;
import it.polimi.ingsw.GC_24.model.effects.immediate.MoltiplicationCards;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalTerritories;
import it.polimi.ingsw.GC_24.model.values.FaithPoint;

public class TestMoltiplicationCards {
	Player player;
	MoltiplicationCards mc;
	FaithPoint faithPoints;
	Territories territory;
	Territories territory2;

	@Before
	public void setUp() {
		player = new Player(1);
		faithPoints = new FaithPoint(0);
		mc = new MoltiplicationCards("moltiplicationCards", new FaithPoint(2), new PersonalTerritories());
		territory = new Territories("Territory", null, 0, "Territory", null, null, null, null, 2);
		territory2 = new Territories("Territory2", null, 0, "Territory", null, null, null, null, 1);
	}

	@Test
	public void testGiveImmediateEffect() {
		player.getMyBoard().getPersonalTerritories().getCards().add(territory);
		player.getMyBoard().getPersonalTerritories().getCards().add(territory2);
		mc.giveImmediateEffect(player);
		faithPoints.setQuantity(4);
		assertEquals(faithPoints, player.getMyValues().getFaithPoints());
	}
}
