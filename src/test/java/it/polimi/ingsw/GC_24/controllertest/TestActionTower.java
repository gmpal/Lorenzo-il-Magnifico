package it.polimi.ingsw.GC_24.controllertest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.controller.ActionTower;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Territories;
import it.polimi.ingsw.GC_24.model.effects.ChooseNewCard;
import it.polimi.ingsw.GC_24.model.effects.CustomizedPermanentEffect;
import it.polimi.ingsw.GC_24.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.MoltiplicationPoints;
import it.polimi.ingsw.GC_24.model.effects.NoValueEffectFromTowerPlace;
import it.polimi.ingsw.GC_24.model.effects.ValueEffect;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;
import it.polimi.ingsw.GC_24.model.values.Coin;
import it.polimi.ingsw.GC_24.model.values.FaithPoint;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;
import it.polimi.ingsw.GC_24.model.values.Wood;

public class TestActionTower {

	Player p1, p2;
	ActionTower actionTower;
	Model game;
	List<Player> players;
	SetOfValues set1 = new SetOfValues();
	TowerPlace towerPlace1, towerPlace2;
	Territories t1, t2;

	@Before
	public void setUp() {
		players = new ArrayList<>();
		p1 = new Player("Giorgia", PlayerColour.RED);
		p2 = new Player("Carlo", PlayerColour.RED);
		players.add(p1);
		players.add(p2);
		game = new Model(1);
		game.setModel(players);
		set1.setCoins(new Coin(4));
		towerPlace1 = new TowerPlace(1, new ValueEffect("value"), null);
		towerPlace2 = new TowerPlace(1, new ValueEffect("value"), null);
		t1 = new Territories("caio", 1, "territory", new SetOfValues(), null,
				new MoltiplicationPoints("molt", new Wood(3), new FaithPoint(3)), null, 1);
		// t2=new Territories("caio", 1, "territory", new SetOfValues(), new
		// MoltiplicationPoints("molt", new Wood(3), new FaithPoint(1)), null, null, 1);
		towerPlace1.setCorrespondingCard(t1);
		// towerPlace2.setCorrespondingCard(t2);
		game.getBoard().getTowerTerritories().getPlacesArray().set(0, towerPlace1);
		actionTower = new ActionTower(game, "1", "territories", "1", "0", new SetOfValues(), new SetOfValues());

	}

	@Test
	public void testNoValueEffectWithEffect() {
		p1.getActivePermanentEffects().add(new NoValueEffectFromTowerPlace("noValueEffectFromTowerPlace"));
		assertTrue(actionTower.isThereNoValueEffect());
	}

	@Test
	public void testNoValueEffectWithoutEffect() {
		assertFalse(actionTower.isThereNoValueEffect());
	}

	@Test
	public void testNoValueEffectToString() {
		assertEquals((new NoValueEffectFromTowerPlace("noValueEffectFromTowerPlace")).toString(),
				"No Value Effect From Tower Place: when you place a family member on a tower place you won't get the values anymore ");
	}

	@Test
	public void testPayCoinsforOccupiedTowerWithEffect() {
		p1.setMyValues(set1);
		game.getBoard().getTowerTerritories().getPlacesArray().get(3).setAvailable(false);
		p1.getActivePermanentEffects().add(new CustomizedPermanentEffect("noCoinsForOccupiedTower", null));
		actionTower.payCoinsforOccupiedTower();
		assertEquals((new Coin(4)), p1.getMyValues().getCoins());
	}

	@Test
	public void testPayCoinsforOccupiedTowerWithoutEffect() {
		p1.setMyValues(set1);
		game.getBoard().getTowerTerritories().getPlacesArray().get(3).setAvailable(false);
		actionTower.payCoinsforOccupiedTower();
		assertEquals(new Coin(1), p1.getMyValues().getCoins());
	}

	@Test
	public void testTakeEffectsAndRemoveCard() {
		actionTower.takeEffectsAndRemoveCard();
		assertEquals(1, actionTower.getImmediateEffects().size());
	}

}
