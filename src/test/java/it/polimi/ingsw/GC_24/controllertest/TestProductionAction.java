package it.polimi.ingsw.GC_24.controllertest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.controller.ProductionAction;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Characters;
import it.polimi.ingsw.GC_24.model.effects.IncreaseDieValueActivity;

public class TestProductionAction {

	ProductionAction action;
	ProductionAction action2;
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
		increaseValue = new IncreaseDieValueActivity("increaseDieValueProduction", 2);
		character = new Characters("Character", "Character", null, null, increaseValue, null, 1);
		character2 = new Characters("Character2", "Character", null, null, increaseValue, null, 2);
		character.setCardOnPersonalBoard(player.getMyBoard());
		character2.setCardOnPersonalBoard(player.getMyBoard());
		player.getActivePermanentEffects().add(character.getPermanentEffects());
		player.getActivePermanentEffects().add(character2.getPermanentEffects());
		players.add(player);
		players.add(player2);
		game.setModel(players);
		action = new ProductionAction(game, "2", "production", "1", "0");
		action2 = new ProductionAction(game, "1", "production", "1", "2");
	}
	
	@Test
	public void testVerify() {
		assertEquals("ok", action2.verify());
	}
	
	@Test 
	public void testVerifyWrong() {
		player.getMyValues().getServants().setQuantity(1);
		player.getMyFamily().getMember1().setMemberValue(0);
		player.getMyFamily().getMember1().setAvailable(false);
		action2.getPlace().setFamMemberOnPlace(new FamilyMember(PlayerColour.YELLOW));
		assertEquals("Answer: \nYou don't have enough servants to use! \n"
				+ "Sorry, this familiar is not available! \nSorry, place not available!\n", action2.verify());
	}
	
	@Test
	public void testGetFinalActionValue() {
		action.getFinalActionValue();
		int finalValue = action.getFamilyMember().getMemberValue() + 4;
		assertEquals(finalValue,action.getFinalValue());
	}
}
