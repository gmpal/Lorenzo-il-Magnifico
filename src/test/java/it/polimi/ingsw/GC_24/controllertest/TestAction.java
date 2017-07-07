package it.polimi.ingsw.GC_24.controllertest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.controller.*;
import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.model.effects.immediate.CouncilPrivilege;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.immediate.ValueEffect;
import it.polimi.ingsw.GC_24.model.values.*;

public class TestAction {

	List<ImmediateEffect> immediateEffects;
	List<ImmediateEffect> immediateEffectsExpected;
	List<Player> players;
	Action action;
	Action action2;
	Action action3;
	Model game;
	Player player;
	Player player2;
	ImmediateEffect chooseNewCard;
	ImmediateEffect valueEffect;
	ImmediateEffect privilege;
	Value value;
	SetOfValues values;

	@Before
	public void setUp() {
		immediateEffects = new ArrayList<>();
		immediateEffectsExpected = new ArrayList<>();
		players = new ArrayList<>();
		game = new Model(1);
		player = new Player("Giorgia", PlayerColour.RED);
		player2 = new Player("Carlo", PlayerColour.GREEN);
		players.add(player);
		players.add(player2);
		game.setModel(players);
		action = new CouncilPalaceAction(game, "1", "council", "1", "0");
		action2 = new HarvestAction(game, "2", "harvest", "1", "4");
		action3 = new ProductionAction(game, "4", "production", "1", "0");
		privilege = new CouncilPrivilege("CouncilPrivilege", 1);
		valueEffect = new ValueEffect("value");
		value = new Coin(0);
		values = new SetOfValues();
	}

	@Test
	public void testGiveValueEffect() {
		immediateEffects.add(privilege);
		immediateEffects.add(valueEffect);
		immediateEffectsExpected.add(privilege);
		action.giveValueEffect(immediateEffects);
		assertEquals(immediateEffectsExpected, immediateEffects);
	}

	/**
	 * two assertions to verify if verifyIfEnoughServants() works right both in case
	 * the player have enough servants to perform the action and if they don't
	 */
	@Test
	public void testVerifyIfEnoughServants() {
		assertEquals("answerToPlayer", action.verifyIfEnoughServants("answerToPlayer"));
	}

	@Test
	public void testVerifyIfEnoughServantsWrong() {
		assertEquals("answerToPlayerYou don't have enough servants to use! \n",
				action2.verifyIfEnoughServants("answerToPlayer"));
	}

	/**
	 * two assertions to verify if verifyPlaceAvailability() works right both in case
	 * the place is available and if it isn't
	 */
	@Test
	public void testVerifyPlaceAvailability() {
		assertEquals("answerToPlayer", action.verifyPlaceAvailability("answerToPlayer"));
	}

	@Test
	public void testVerifyPlaceAvailabilityWrong() {
		action.getPlace().setFamMemberOnPlace(new FamilyMember(PlayerColour.BLUE));
		assertEquals("answerToPlayerSorry, place not available!\n", action.verifyPlaceAvailability("answerToPlayer"));
	}

	@Test
	public void testVerifyFamilyMemberAvailability() {
		assertEquals("answerToPlayer", action.verifyFamilyMemberAvailability("answerToPlayer"));
	}

	@Test
	public void testVerifyFamilyMemberAvailabilityWrong() {
		action2.getPlayer().getMyFamily().getMember1().setAvailable(false);
		assertEquals("answerToPlayerSorry, this familiar is not available! \n",
				action.verifyFamilyMemberAvailability("answerToPlayer"));
	}

	@Test
	public void testVerifyZoneOccupiedByMe() {
		action2.getZone().getFirstEmptyPlace().setFamMemberOnPlace(new FamilyMember(PlayerColour.BLUE));
		assertEquals("answerToPlayer", action2.verifyZoneOccupiedByMe("answerToPlayer"));
	}

	@Test
	public void testVerifyZoneOccupiedByMeWrong() {
		action.getZone().getPlacesArray().get(3).setFamMemberOnPlace(player.getMyFamily().getMember2());
		assertEquals("answerToPlayerThis zone is already occupied by one of your family members. Choose another zone\n",
				action.verifyZoneOccupiedByMe("answerToPlayer"));
	}

	@Test
	public void testVerifyZoneOccupiedByMeEffect() {
		action2.getPlacementEverywhereLeaderEffect().add("harvest yellow");
		action2.getZone().getFirstEmptyPlace().setFamMemberOnPlace(new FamilyMember(PlayerColour.BLUE));
		assertEquals("answerToPlayerThis zone is already occupied by one of your family members. Choose another zone\n",
				action2.verifyZoneOccupiedByMe("answerToPlayer"));
	}

	@Test
	public void testVerifyIfEnoughServantsForThisPlace() {
		assertEquals("answerToPlayer", action2.verifyIfEnoughServantsForThisPlace("answerToPlayer"));
	}

	@Test
	public void testVerifyIfEnoughServantsForThisPlaceWrong() {
		assertEquals("answerToPlayerYou have not used enough servants for this place. Please choose another place\n",
				action3.verifyIfEnoughServantsForThisPlace("answerToPlayer"));
	}

	@Test
	public void testPlaceFamiliar() {
		action2.placeFamiliar();
		assertFalse(action2.getFamilyMember().isAvailable());
	}

	@Test
	public void testPayValue() {
		value.setQuantity(3);
		action2.payValue(value);
		value.setQuantity(2);
		assertEquals(value, action2.getPlayer().getMyValues().getCoins());
	}

	@Test
	public void testTakeValueFromPlace() {
		action.takeValueFromPlace();
		values.setInitialValues(1);
		values.getCoins().addQuantity(1);
		assertEquals(values, player.getMyValues());
	}
}
