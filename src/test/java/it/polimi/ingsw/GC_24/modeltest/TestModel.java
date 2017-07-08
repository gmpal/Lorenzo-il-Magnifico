package it.polimi.ingsw.GC_24.modeltest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.model.dice.SetOfDice;
import it.polimi.ingsw.GC_24.model.effects.permanent.CustomizedPermanentEffect;
import it.polimi.ingsw.GC_24.model.effects.permanent.IncreaseDieValueActivity;

public class TestModel {

	List<Player> players;
	Player player;
	Player player2;
	Model game;
	IncreaseDieValueActivity setDiceValue;
	CustomizedPermanentEffect setValueFamilyMember;
	CustomizedPermanentEffect increaseValueNeutralFamilyMember;
	IncreaseDieValueActivity increase;
	IncreaseDieValueActivity decrease;
	Family family;
	SetOfDice dice;

	@Before
	public void setUp() {
		players = new ArrayList<>();
		player = new Player("Giorgia", PlayerColour.RED);
		player2 = new Player("Carlo", PlayerColour.GREEN);
		setDiceValue = new IncreaseDieValueActivity("setDiceValue", 5);
		setValueFamilyMember = new CustomizedPermanentEffect("setValueFamilyMember", null);
		increaseValueNeutralFamilyMember = new CustomizedPermanentEffect("increaseValueNeutralFamilyMember", null);
		increase = new IncreaseDieValueActivity("increaseDieValueFamiliar", 3);
		decrease = new IncreaseDieValueActivity("increaseDieValueFamiliar", -1);
		players.add(player);
		players.add(player2);
		game = new Model(1);
		game.setPlayers(players);
		game.setGameState(State.WAITINGFORPLAYERTHREE);
		family = new Family(PlayerColour.RED);
		dice = new SetOfDice();
		game.setDice(dice);
	}

	@Test
	public void testGetMyValuesFromColour() {
		assertEquals(player, game.getPlayerfromColour(PlayerColour.RED));
	}

	@Test
	public void testIncrementState() {
		game.incrementState();
		assertEquals(State.WAITINGFORPLAYERFOUR, game.getGameState());
	}

	@Test
	public void testChangeInDieValue() {
		dice.reset();
		game.updateModel();
		player.getActivePermanentEffects().add(increase);
		player.getActivePermanentEffects().add(setDiceValue);
		player.getActivePermanentEffects().add(decrease);
		player.getActivePermanentEffects().add(increaseValueNeutralFamilyMember);
		player.getActivePermanentEffects().add(setValueFamilyMember);
		family.getMember1().setMember(dice.getDie1());
		family.getMember1().setMemberValue(8);
		family.getMember2().setMember(dice.getDie2());
		family.getMember2().setMemberValue(7);
		family.getMember3().setMember(dice.getDie3());
		family.getMember3().setMemberValue(7);
		family.getMember4().setMemberValue(3);
		game.changeInDieValue(player);
		assertEquals(family, player.getMyFamily());
	}

	/**
	 * This test check the method "isEmpty" of class "Family" when there are not
	 * familiars available.
	 */
	@Test
	public void testFamilyIsEmpty() {
		assertFalse(player.getMyFamily().isEmpty());
	}

	/**
	 * This test check the method "isEmpty" of class "Family" when there is one
	 * familiar not available.
	 */
	@Test
	public void testFamilyIsEmpty1() {
		player.getMyFamily().getMember1().setAvailable(false);
		player.getMyFamily().getMember4().setAvailable(false);
		assertFalse(player.getMyFamily().isEmpty());
	}

	/**
	 * This method check the method "isFull" when there are all familiar available.
	 */
	@Test
	public void testFamilyIsFull() {
		assertTrue(player.getMyFamily().isFull());
	}

	/**
	 * This method check the method "isFull" when there are some familiar not
	 * available.
	 */
	@Test
	public void testFamilyIsFull1() {
		player.getMyFamily().getMember2().setAvailable(false);
		player.getMyFamily().getMember2().setAvailable(false);

		assertFalse(player.getMyFamily().isFull());
	}

	@Test
	public void testPlayerColourResetValue() {
		PlayerColour.removeValue("blue");
		PlayerColour.removeValue("green");
		PlayerColour.removeValue("red");
		PlayerColour.removeValue("yellow");
		PlayerColour.resetValues();
		List<String> d = new ArrayList<>();
		d.add("YELLOW");
		d.add("GREEN");
		d.add("BLUE");
		d.add("RED");
		assertEquals(PlayerColour.getValues(), d);
	}

	@Test
	public void testPlayerColourClearValues() {
		String colour = PlayerColour.getRandomColour();
		if (PlayerColour.checkValue(colour)) {
			PlayerColour.removeValue(colour);
		}
		PlayerColour.removeValue("blue");
		PlayerColour.removeValue("blue");
		PlayerColour.clearValues();
		List<String> d = new ArrayList<>();
		d.addAll(PlayerColour.getValues());
		assertEquals(d.get(0), PlayerColour.getValues().get(0));
	}

}
