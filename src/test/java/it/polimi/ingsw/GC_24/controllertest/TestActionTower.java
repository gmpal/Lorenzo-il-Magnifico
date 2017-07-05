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
import it.polimi.ingsw.GC_24.model.cards.Characters;
import it.polimi.ingsw.GC_24.model.cards.Territories;
import it.polimi.ingsw.GC_24.model.effects.ChooseNewCard;
import it.polimi.ingsw.GC_24.model.effects.CustomizedPermanentEffect;
import it.polimi.ingsw.GC_24.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.effects.MoltiplicationPoints;
import it.polimi.ingsw.GC_24.model.effects.NoValueEffectFromTowerPlace;
import it.polimi.ingsw.GC_24.model.effects.ValueEffect;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;
import it.polimi.ingsw.GC_24.model.values.Coin;
import it.polimi.ingsw.GC_24.model.values.FaithPoint;
import it.polimi.ingsw.GC_24.model.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;
import it.polimi.ingsw.GC_24.model.values.Wood;

public class TestActionTower {

	Player p1, p2;
	ActionTower actionTower, actionTower1, actionTower2, actionTower3;
	Model game;
	List<Player> players;
	SetOfValues set1 = new SetOfValues();
	TowerPlace towerPlace1, towerPlace2, towerPlace3, towerPlace4;
	Territories t1, t2;
	SetOfValues set3, set2;
	Characters c1,c2;

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
		towerPlace3 = new TowerPlace(1, new ValueEffect("value"), null);
		towerPlace4 = new TowerPlace(1, new ValueEffect("value"), null);
		t1 = new Territories("caio", 1, "territory", new SetOfValues(), null,
				new MoltiplicationPoints("molt", new Wood(3), new FaithPoint(3)), null, 1);
		t2 = new Territories("caio", 1, "territory", new SetOfValues(),
				new MoltiplicationPoints("molt", new Wood(3), new FaithPoint(1)), null, null, 1);
		c1= new Characters("character", "character", set1, null, new IncreaseDieValueCard("increase", null, 2, set3, new SetOfValues()), null, 1);
		c2= new Characters("character", "character", set1, null, null, null, 1);
		towerPlace1.setCorrespondingCard(t1);
		towerPlace2.setCorrespondingCard(t2);
		towerPlace3.setCorrespondingCard(c1);
		towerPlace4.setCorrespondingCard(c2);
		game.getBoard().getTowerTerritories().getPlacesArray().set(0, towerPlace1);
		game.getBoard().getTowerTerritories().getPlacesArray().set(1, towerPlace2);
		game.getBoard().getTowerCharacters().getPlacesArray().set(0, towerPlace3);
		set3=new SetOfValues();
		set2=new SetOfValues();
		set3.setMilitaryPoints(new MilitaryPoint(3));
		set2.setMilitaryPoints(new MilitaryPoint(4));
		actionTower = new ActionTower(game, "1", "territories", "1", "0", new SetOfValues(), new SetOfValues());
		actionTower1 = new ActionTower(game, "2", "territories", "1", "0", new SetOfValues(), new SetOfValues());
		actionTower2 = new ActionTower(game, "1", "territories", "1", "0", set2, set3);
		actionTower3 = new ActionTower(game, "1", "characters", "1", "0", set2, set3);

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
	public void testTakeEffectsAndRemoveCardListEffect() {
		actionTower.takeEffectsAndRemoveCard();
		assertEquals(1, actionTower.getImmediateEffects().size());
	}

	@Test
	public void testTakeEffectsAndRemoveCardListEffect2() {
		actionTower1.takeEffectsAndRemoveCard();
		assertEquals(1, actionTower1.getImmediateEffects().size());
	}

	@Test
	public void testTakeEffectsAndRemoveCard() {
		actionTower.takeEffectsAndRemoveCard();
		TowerPlace tp = (TowerPlace) actionTower.getPlace();
		assertEquals(null, tp.getCorrespondingCard());
	}
	
	@Test
	public void testTakeCardAndPay() {
		actionTower2.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(10));
		actionTower2.takeCardAndPay();
		assertEquals(t1,actionTower2.getPlayer().getMyBoard().getPersonalTerritories().getCards().get(0));
	}
	
	@Test
	public void testTakeCardAndPayWithCharacterWithPermanentEffect() {
		actionTower3.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(10));
		actionTower3.takeCardAndPay();
		assertEquals(1,actionTower3.getPlayer().getActivePermanentEffects().size());
	}

	@Test
	public void testTakeCardAndPayWithCharacterWithoutPermanentEffect() {
		game.getBoard().getTowerCharacters().getPlacesArray().set(0, towerPlace4);
		actionTower3.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(10));
		actionTower3.takeCardAndPay();
		assertEquals(1,actionTower3.getPlayer().getActivePermanentEffects().size());
	}
	
	@Test
	public void takeRealCostWithCost() {
		actionTower3.takeRealCost();
		assertEquals(set2,actionTower3.getTemporaryCardCost());
	}
	
	@Test
	public void takeRealCostWithoutCost() {
		actionTower1.takeRealCost();
		assertEquals(new SetOfValues(),actionTower1.getTemporaryCardCost());
	}
	
	@Test
	public void testVerifyMoneyForTowerOccupiedWithCoinZoneOccupiedNoPermanentEffect() {
		game.getBoard().getTowerTerritories().getPlacesArray().get(2).setFamMemberOnPlace(p2.getMyFamily().getMember1());
		p1.getMyValues().setCoins(new Coin(3));
		String g=actionTower.verifyMoneyForTowerOccupied("ok");
		assertEquals("ok",g);
	}
	
	@Test
	public void testVerifyMoneyForTowerOccupiedWithCoinZoneOccupiedWithPermanentEffect() {
		actionTower.getPlayer().getActivePermanentEffects().add(new CustomizedPermanentEffect("noCoinsForOccupiedTower", "ciao"));
		game.getBoard().getTowerTerritories().getPlacesArray().get(2).setFamMemberOnPlace(p2.getMyFamily().getMember1());
		actionTower.getPlayer().getMyValues().setCoins(new Coin(2));
		String g=actionTower.verifyMoneyForTowerOccupied("ok");
		assertEquals("ok",g);
	}
	
	@Test
	public void testVerifyMoneyForTowerOccupiedNoCoinZoneOccupiedNoPermanentEffect() {
		game.getBoard().getTowerTerritories().getPlacesArray().get(2).setFamMemberOnPlace(p2.getMyFamily().getMember1());
		actionTower.getPlayer().getMyValues().setCoins(new Coin(2));
		String g=actionTower.verifyMoneyForTowerOccupied("ok");
		assertEquals("okYou don't have enough coins to place your family member in a tower already occupied\n",g);
	}
	
	@Test
	public void testVerifyMoneyForTowerOccupiedZoneNotOccupied() {
		actionTower.getPlayer().getActivePermanentEffects().add(new CustomizedPermanentEffect("noCoinsForOccupiedTower", "ciao"));
		actionTower.getPlayer().getMyValues().setCoins(new Coin(2));
		String g=actionTower.verifyMoneyForTowerOccupied("ok");
		assertEquals("ok",g);
	}
}
