package it.polimi.ingsw.GC_24.controllertest;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.controller.*;
import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.model.cards.Characters;
import it.polimi.ingsw.GC_24.model.effects.permanent.IncreaseDieValueActivity;


public class TestHarvestAction {
	
	HarvestAction action;
	Model game;
	Player player;
	Player player2;
	List<Player> players;
	Characters character;
	Characters character2;
	IncreaseDieValueActivity increaseValue;

	@Before
	public void setUp() {
		players = new ArrayList<>();
		game = new Model(1);
		player = new Player("Giorgia", PlayerColour.RED);
		player2 = new Player("Carlo", PlayerColour.GREEN);
		increaseValue = new IncreaseDieValueActivity("increaseDieValueHarvest", 2);
		character = new Characters("Character", null, "Character", null, null, increaseValue, null, 1);
		character2 = new Characters("Character2", null, "Character", null, null, increaseValue, null, 2);
		character.setCardOnPersonalBoard(player.getMyBoard());
		character2.setCardOnPersonalBoard(player.getMyBoard());
		player.getActivePermanentEffects().add(character.getPermanentEffects());
		player.getActivePermanentEffects().add(character2.getPermanentEffects());
		players.add(player);
		players.add(player2);
		game.setModel(players);
		action = new HarvestAction(game, "2", "harvest", "1", "0");
	}
	
	@Test
	public void testVerify() {
		assertEquals("ok", action.verify());
	}
	
	@Test 
	public void testVerifyWrong() {
		player.getMyFamily().getMember2().setMemberValue(0);
		player.getMyFamily().getMember2().setAvailable(false);
		action.getPlace().setFamMemberOnPlace(new FamilyMember(PlayerColour.YELLOW));
		assertEquals("Answer: \nYou have not used enough servants for this place. Please choose another place\n"
				+ "Sorry, this familiar is not available! \nSorry, place not available!\n", action.verify());
	}
	
	@Test
	public void testGetFinalActionValue() {
		action.getFinalActionValue();
		int finalValue = action.getFamilyMember().getMemberValue() + 4;
		assertEquals(finalValue,action.getFinalValue());
	}

}
