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
import it.polimi.ingsw.GC_24.model.cards.*;
import it.polimi.ingsw.GC_24.model.effects.ChooseNewCard;
import it.polimi.ingsw.GC_24.model.effects.CustomizedPermanentEffect;
import it.polimi.ingsw.GC_24.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.effects.MoltiplicationPoints;
import it.polimi.ingsw.GC_24.model.effects.NoValueEffectFromTowerPlace;
import it.polimi.ingsw.GC_24.model.effects.SubSetOfValues;
import it.polimi.ingsw.GC_24.model.effects.ValueEffect;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;
import it.polimi.ingsw.GC_24.model.values.Coin;
import it.polimi.ingsw.GC_24.model.values.FaithPoint;
import it.polimi.ingsw.GC_24.model.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;
import it.polimi.ingsw.GC_24.model.values.VictoryPoint;
import it.polimi.ingsw.GC_24.model.values.Wood;

public class TestActionTower {

	Player p1, p2;
	ActionTower actionTower, actionTower1, actionTower2, actionTower3, actionTower4, actionTower5, actionTower6;
	Model game;
	List<Player> players;
	SetOfValues set1 = new SetOfValues();
	TowerPlace towerPlace1, towerPlace2, towerPlace3, towerPlace4, towerPlace5, towerPlace6, towerPlace7;
	Territories t1, t2;
	SetOfValues set3, set2, set4, set5;
	Characters c1, c2;
	Ventures v1;
	Buildings b1;

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
		towerPlace5 = new TowerPlace(1, new ValueEffect("value"), null);
		towerPlace6 = new TowerPlace(1, new ValueEffect("value"), null);
		towerPlace7 = new TowerPlace(1, new ValueEffect("value"), null);

		t1 = new Territories("caio", 1, "territory", new SetOfValues(), null,
				new MoltiplicationPoints("molt", new Wood(3), new FaithPoint(3)), null, 1);
		t2 = new Territories("caio", 1, "territory", new SetOfValues(),
				new MoltiplicationPoints("molt", new Wood(3), new FaithPoint(1)), null, null, 1);
		c1 = new Characters("character", "character", set1, null,
				new IncreaseDieValueCard("increase", null, 2, set3, new SetOfValues()), null, 1);
		c2 = new Characters("character", "character", set1, null, null, null, 1);
		v1 = new Ventures("ciao", "venture", null, null, new VictoryPoint(3), new MilitaryPoint(0), null, null, 1);
		b1 = new Buildings("building", 1, "building", new SetOfValues(), null, null, null, null, 1);

		towerPlace1.setCorrespondingCard(t1);
		towerPlace2.setCorrespondingCard(t2);
		towerPlace3.setCorrespondingCard(c1);
		towerPlace4.setCorrespondingCard(c2);
		towerPlace5.setCorrespondingCard(null);
		towerPlace6.setCorrespondingCard(v1);
		towerPlace7.setCorrespondingCard(b1);

		game.getBoard().getTowerTerritories().getPlacesArray().set(0, towerPlace1);
		game.getBoard().getTowerTerritories().getPlacesArray().set(1, towerPlace2);
		game.getBoard().getTowerCharacters().getPlacesArray().set(0, towerPlace3);
		game.getBoard().getTowerBuildings().getPlacesArray().set(0, towerPlace5);
		game.getBoard().getTowerVentures().getPlacesArray().set(0, towerPlace6);
		game.getBoard().getTowerBuildings().getPlacesArray().set(1, towerPlace7);


		set3 = new SetOfValues();
		set2 = new SetOfValues();
		set4 = new SetOfValues();
		set5 = new SetOfValues();

		set3.setMilitaryPoints(new MilitaryPoint(3));
		set2.setMilitaryPoints(new MilitaryPoint(4));
		set2.setCoins(new Coin(5));
		set4.setCoins(new Coin(4));
		set5.setCoins(new Coin(6));

		actionTower = new ActionTower(game, "1", "territories", "1", "0", new SetOfValues(), new SetOfValues());
		actionTower1 = new ActionTower(game, "2", "territories", "1", "0", new SetOfValues(), new SetOfValues());
		actionTower2 = new ActionTower(game, "1", "territories", "1", "0", set2, set3);
		actionTower3 = new ActionTower(game, "1", "characters", "1", "0", set2, set3);
		actionTower4 = new ActionTower(game, "1", "buildings", "1", "0", set2, set3);
		actionTower5 = new ActionTower(game, "1", "ventures", "1", "0", set2, set3);
		actionTower6 = new ActionTower(game, "2", "buildings", "2", "0", set2, set3);


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
		assertEquals(t1, actionTower2.getPlayer().getMyBoard().getPersonalTerritories().getCards().get(0));
	}

	@Test
	public void testTakeCardAndPayWithCharacterWithPermanentEffect() {
		actionTower3.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(10));
		actionTower3.takeCardAndPay();
		assertEquals(1, actionTower3.getPlayer().getActivePermanentEffects().size());
	}

	@Test
	public void testTakeCardAndPayWithCharacterWithoutPermanentEffect() {
		game.getBoard().getTowerCharacters().getPlacesArray().set(0, towerPlace4);
		actionTower3.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(10));
		actionTower3.takeCardAndPay();
		assertEquals(1, actionTower3.getPlayer().getActivePermanentEffects().size());
	}

	@Test
	public void takeRealCostWithCost() {
		actionTower3.takeRealCost();
		assertEquals(set2, actionTower3.getTemporaryCardCost());
	}

	@Test
	public void takeRealCostWithoutCost() {
		actionTower1.takeRealCost();
		assertEquals(new SetOfValues(), actionTower1.getTemporaryCardCost());
	}

	@Test
	public void testVerifyMoneyForTowerOccupiedWithCoinZoneOccupiedNoPermanentEffect() {
		game.getBoard().getTowerTerritories().getPlacesArray().get(2)
				.setFamMemberOnPlace(p2.getMyFamily().getMember1());
		p1.getMyValues().setCoins(new Coin(3));
		String g = actionTower.verifyMoneyForTowerOccupied("ok");
		assertEquals("ok", g);
	}

	@Test
	public void testVerifyMoneyForTowerOccupiedWithCoinZoneOccupiedWithPermanentEffect() {
		actionTower.getPlayer().getActivePermanentEffects()
				.add(new CustomizedPermanentEffect("noCoinsForOccupiedTower", "ciao"));
		game.getBoard().getTowerTerritories().getPlacesArray().get(2)
				.setFamMemberOnPlace(p2.getMyFamily().getMember1());
		actionTower.getPlayer().getMyValues().setCoins(new Coin(2));
		String g = actionTower.verifyMoneyForTowerOccupied("ok");
		assertEquals("ok", g);
	}

	@Test
	public void testVerifyMoneyForTowerOccupiedNoCoinZoneOccupiedNoPermanentEffect() {
		game.getBoard().getTowerTerritories().getPlacesArray().get(2)
				.setFamMemberOnPlace(p2.getMyFamily().getMember1());
		actionTower.getPlayer().getMyValues().setCoins(new Coin(2));
		String g = actionTower.verifyMoneyForTowerOccupied("ok");
		assertEquals("okYou don't have enough coins to place your family member in a tower already occupied\n", g);
	}

	@Test
	public void testVerifyMoneyForTowerOccupiedZoneNotOccupied() {
		actionTower.getPlayer().getActivePermanentEffects()
				.add(new CustomizedPermanentEffect("noCoinsForOccupiedTower", "ciao"));
		actionTower.getPlayer().getMyValues().setCoins(new Coin(2));
		String g = actionTower.verifyMoneyForTowerOccupied("ok");
		assertEquals("ok", g);
	}

	@Test
	public void testVerifyCardResourcesWithoutCard() {
		String s = actionTower4.verifyCardResources("ok");
		assertEquals("ok", s);
	}

	@Test
	public void testVerifyCardResourcesWithCardNoResources() {
		actionTower3.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(1));
		String s = actionTower3.verifyCardResources("ok");
		assertEquals("okYou don't have enough resources to take this card! Choose another card \n", s);
	}

	@Test
	public void testVerifyCardResourcesWithCardWithResources() {
		actionTower3.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(5));
		String s = actionTower3.verifyCardResources("ok");
		assertEquals("ok", s);
	}

	@Test
	public void testVerifyCardResourcesWithCardWithResourcesWithEffect() {
		actionTower3.getPlayer().getActivePermanentEffects().add(new SubSetOfValues("discountCoinsCard", set4));
		actionTower3.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(5));
		actionTower3.verifyCardResources("ok");
		assertEquals(actionTower3.getTemporaryCardCost().getCoins().getQuantity(), 1);
	}

	/**
	 * This test checks if the sale of permanent effect is bigger than the card's
	 * cost
	 */
	@Test
	public void testVerifyCardResourcesWithCardWithResourcesWithEffect1() {
		actionTower3.getPlayer().getActivePermanentEffects().add(new SubSetOfValues("discountCoinsCard", set5));
		actionTower3.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(5));
		actionTower3.verifyCardResources("ok");
		assertEquals(actionTower3.getTemporaryCardCost().getCoins().getQuantity(), 0);
	}

	/**
	 * This test checks the method verifyCardResources in case the player wants to
	 * take a venture card
	 */
	@Test
	public void testVerifyCardResourcesWithCardWithResourcesWithEffect2() {
		actionTower5.getPlayer().getActivePermanentEffects().add(new SubSetOfValues("discountCoinsCard", set5));
		actionTower5.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(5));
		String s = actionTower5.verifyCardResources("ok");
		assertEquals("ok", s);
	}

	/**
	 * This test checks the method when the player has the permanent effect
	 * "noMilitaryPointsForTerritories"
	 */
	@Test
	public void testVerifyTerritorySpaceAvailability() {
		actionTower.getPlayer().getActivePermanentEffects()
				.add(new CustomizedPermanentEffect("noMilitaryPointsForTerritories", null));
		actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t2);
		actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		String s = actionTower.verifyTerritorySpaceAvailability("ok");
		assertEquals("ok", s);
	}

	/**
	 * This test checks the method when the player has not three military points and
	 * he wants to take the third territory card.
	 */
	@Test
	public void testVerifyTerritorySpaceAvailability1() {
		actionTower.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(0));
		actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t2);
		String s = actionTower.verifyTerritorySpaceAvailability("ok");
		assertEquals("okSorry, you need 3 Military Points to unlock the next Territory Space\n", s);
	}

	/**
	 * This test checks the method when the player has not seven military points and
	 * he wants to take the forth territory card.
	 */
	@Test
	public void testVerifyTerritorySpaceAvailability2() {
		actionTower.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(0));
		for (int i = 0; i < 3; i++) {
			actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		}
		String s = actionTower.verifyTerritorySpaceAvailability("ok");
		assertEquals("okSorry, you need 7 Military Points to unlock the next Territory Space\n", s);
	}

	/**
	 * This test checks the method when the player has not twelve military points
	 * and he wants to take the fifth territory card.
	 */
	@Test
	public void testVerifyTerritorySpaceAvailability3() {
		actionTower.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(0));
		for (int i = 0; i < 4; i++) {
			actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		}
		String s = actionTower.verifyTerritorySpaceAvailability("ok");
		assertEquals("okSorry, you need 12 Military Points to unlock the next Territory Space\n", s);
	}

	/**
	 * This test checks the method when the player has not eighteen military points
	 * and he wants to take the sixth territory card.
	 */
	@Test
	public void testVerifyTerritorySpaceAvailability4() {
		actionTower.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(0));
		for (int i = 0; i < 5; i++) {
			actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		}
		String s = actionTower.verifyTerritorySpaceAvailability("ok");
		assertEquals("okSorry, you need 18 Military Points to unlock the next Territory Space\n", s);
	}

	/**
	 * This test checks the method when the player has enough military points to
	 * take another territory card.
	 */
	@Test
	public void testVerifyTerritorySpaceAvailability5() {
		actionTower.getPlayer().getMyValues().setMilitaryPoints(new MilitaryPoint(30));
		actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		String s = actionTower.verifyTerritorySpaceAvailability("ok");
		actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		s = actionTower.verifyTerritorySpaceAvailability("ok");
		actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		s = actionTower.verifyTerritorySpaceAvailability("ok");
		actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		s = actionTower.verifyTerritorySpaceAvailability("ok");
		actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		s = actionTower.verifyTerritorySpaceAvailability("ok");
		actionTower.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		s = actionTower.verifyTerritorySpaceAvailability("ok");
		assertEquals("ok", s);
	}

	@Test
	public void verifyBoardSpaceAvailabilityTerritories() {
		for (int i = 0; i < 6; i++) {
			actionTower1.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		}
		String s = actionTower1.verifyBoardSpaceAvailability("ok");
		assertEquals("okYou have already 6 Territory Cards, no more empty spaces \n", s);
	}

	@Test
	public void verifyBoardSpaceAvailabilityCharacters() {
		for (int i = 0; i < 6; i++) {
			actionTower3.getPlayer().getMyBoard().getPersonalCharacters().getCards().add(c1);
		}
		String s = actionTower3.verifyBoardSpaceAvailability("ok");
		assertEquals("okYou have already 6 Character Cards, no more empty spaces \n", s);
	}
	
	@Test
	public void verifyBoardSpaceAvailabilityBuildings() {
		for (int i = 0; i < 6; i++) {
			actionTower6.getPlayer().getMyBoard().getPersonalBuildings().getCards().add(b1);
		}
		String s = actionTower6.verifyBoardSpaceAvailability("ok");
		assertEquals("okYou have already 6 Building Cards, no more empty spaces \n", s);
	}

	@Test
	public void verifyBoardSpaceAvailabilityVentures() {
		for (int i = 0; i < 6; i++) {
			actionTower5.getPlayer().getMyBoard().getPersonalVentures().getCards().add(v1);
		}
		String s = actionTower5.verifyBoardSpaceAvailability("ok");
		assertEquals("okYou have already 6 Venture Cards, no more empty spaces \n", s);
	}
}
