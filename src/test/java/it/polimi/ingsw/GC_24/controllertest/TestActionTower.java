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
import it.polimi.ingsw.GC_24.model.effects.immediate.*;
import it.polimi.ingsw.GC_24.model.effects.permanent.*;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalBuildings;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalTerritories;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;
import it.polimi.ingsw.GC_24.model.values.*;

public class TestActionTower {

	Player p1, p2;
	ActionTower actionTower, actionTower1, actionTower2, actionTower3, actionTower4, actionTower5, actionTower6,
			actionTower7;
	Model game;
	List<Player> players;
	SetOfValues set1 = new SetOfValues();
	TowerPlace towerPlace1, towerPlace2, towerPlace3, towerPlace4, towerPlace5, towerPlace6, towerPlace7;
	Territories t1, t2;
	SetOfValues set3, set2, set4, set5;
	Characters c1, c2;
	Ventures v1;
	Buildings b1;
	SetOfValues set6;

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

		t1 = new Territories("caio", null, 1, "territory", new SetOfValues(), null,
				new MoltiplicationPoints("molt", new Wood(3), new FaithPoint(3)), null, 1);
		t2 = new Territories("caio", null, 1, "territory", new SetOfValues(),
				new MoltiplicationPoints("molt", new Wood(3), new FaithPoint(1)), null, null, 1);
		c1 = new Characters("character", null, "character", set1, null,
				new IncreaseDieValueCard("increase", null, 2, set3, new SetOfValues()), null, 1);
		c2 = new Characters("character", null, "character", set1, null, null, null, 1);
		v1 = new Ventures("ciao", null, "venture", new SetOfValues(), null, new VictoryPoint(3), new MilitaryPoint(0), null, null, 1);
		b1 = new Buildings("building", null, 1, "building", new SetOfValues(), null, null, null, null, 1);

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
		set6 = new SetOfValues();

		set3.setMilitaryPoints(new MilitaryPoint(3));
		set2.setMilitaryPoints(new MilitaryPoint(4));
		set2.setCoins(new Coin(5));
		set4.setCoins(new Coin(4));
		set5.setCoins(new Coin(6));
		set6.setCoins(new Coin(20));
		set6.setWoods(new Wood(20));
		set6.setStones(new Stone(20));
		set6.setServants(new Servant(20));
		set6.setMilitaryPoints(new MilitaryPoint(20));

		
		actionTower = new ActionTower(game, "1", "territories", "1", "0", new SetOfValues(), new SetOfValues());
		actionTower1 = new ActionTower(game, "2", "territories", "1", "0", new SetOfValues(), new SetOfValues());
		actionTower2 = new ActionTower(game, "1", "territories", "1", "0", set2, set3);
		actionTower3 = new ActionTower(game, "1", "characters", "1", "0", set2, set3);
		actionTower4 = new ActionTower(game, "1", "buildings", "1", "0", set2, set3);
		actionTower5 = new ActionTower(game, "1", "ventures", "1", "0", set2, set3);
		actionTower6 = new ActionTower(game, "2", "buildings", "2", "0", set2, set3);
		actionTower7 = new ActionTower(game, "4", "buildings", "2", "0", set2, set3);

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
		actionTower3.getPlayer().setMyValues(set6);
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
		assertEquals(actionTower3.getTemporaryCardCost().getCoins().getQuantity(), 0);
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
	public void testVerifyBoardSpaceAvailabilityTerritories() {
		for (int i = 0; i < 6; i++) {
			actionTower1.getPlayer().getMyBoard().getPersonalTerritories().getCards().add(t1);
		}
		String s = actionTower1.verifyBoardSpaceAvailability("ok");
		assertEquals("okYou have already 6 Territory Cards, no more empty spaces \n", s);
	}

	@Test
	public void testVerifyBoardSpaceAvailabilityCharacters() {
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
	public void testVerifyBoardSpaceAvailabilityVentures() {
		for (int i = 0; i < 6; i++) {
			actionTower5.getPlayer().getMyBoard().getPersonalVentures().getCards().add(v1);
		}
		String s = actionTower5.verifyBoardSpaceAvailability("ok");
		assertEquals("okYou have already 6 Venture Cards, no more empty spaces \n", s);
	}

	@Test
	public void testVerifyOk() {
		assertEquals("ok", actionTower.verify());
	}

	@Test
	public void testVerifyNoOk() {
		assertEquals(
				"Answer: \n" + "You have not used enough servants for this place. Please choose another place. \n"
						+ "You don't have enough resources to take this card! Choose another card \n" + "",
				actionTower7.verify());
	}

	@Test
	public void testGetIncrementDieValueFromPermanentEffect() {
		actionTower.getPlayer().getActivePermanentEffects().add(new IncreaseDieValueCard("increaseDieValueCard",
				new PersonalTerritories(), 2, new SetOfValues(), new SetOfValues()));
		actionTower.getPlayer().getActivePermanentEffects().add(new IncreaseDieValueCard("increaseDieValueCard",
				new PersonalTerritories(), 3, new SetOfValues(), new SetOfValues()));
		actionTower.getPlayer().getActivePermanentEffects().add(new IncreaseDieValueCard("increaseDieValueCard",
				new PersonalBuildings(), 3, new SetOfValues(), new SetOfValues()));
		actionTower.getPlayer().getActivePermanentEffects()
				.add(new IncreaseDieValueCard("increaseDieValueCard", null, 2, new SetOfValues(), new SetOfValues()));
		int increment = actionTower.getIncrementDieValueFromPermanentEffect();
		assertEquals(7, increment);
	}

	/**
	 * This test checks the methods when the permanent effect "IncreaseDieValueCard"
	 * has not an alternative sale.
	 */
	@Test
	public void testGetIncrementDieValueFromPermanentEffect1() {
		actionTower.getPlayer().getActivePermanentEffects()
				.add(new IncreaseDieValueCard("increaseDieValueCard", new PersonalTerritories(), 2, set1, null));
		actionTower.getIncrementDieValueFromPermanentEffect();
		IncreaseDieValueCard pe = (IncreaseDieValueCard) actionTower.getPlayer().getActivePermanentEffects().get(0);
		assertEquals(set1, pe.getSale());
	}

	@Test
	public void testRun() {
		List<ImmediateEffect> effects = actionTower.run();
		List<ImmediateEffect> effects1 = new ArrayList<>();
		effects1.add(new MoltiplicationPoints("molt", new Wood(3), new FaithPoint(3)));
		assertEquals(effects, effects1);
	}
}
