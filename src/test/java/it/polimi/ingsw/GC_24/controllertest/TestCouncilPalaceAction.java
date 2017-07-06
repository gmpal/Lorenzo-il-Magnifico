package it.polimi.ingsw.GC_24.controllertest;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.controller.CouncilPalaceAction;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Characters;
import it.polimi.ingsw.GC_24.model.effects.permanent.IncreaseDieValueActivity;

public class TestCouncilPalaceAction {

	CouncilPalaceAction action;
	Model game;
	Player player;
	Player player2;
	List<Player> players;
	Characters character;
	Characters character2;
	IncreaseDieValueActivity increaseValue;

	@Before
	public void setUp() throws Exception {
		players = new ArrayList<>();
		game = new Model(1);
		player = new Player("Giorgia", PlayerColour.RED);
		player2 = new Player("Carlo", PlayerColour.GREEN);
		increaseValue = new IncreaseDieValueActivity("increaseDieValueHarvest", 2);
		character = new Characters("Character", "Character", null, null, increaseValue, null, 1);
		character2 = new Characters("Character2", "Character", null, null, increaseValue, null, 2);
		character.setCardOnPersonalBoard(player.getMyBoard());
		character2.setCardOnPersonalBoard(player.getMyBoard());
		player.getActivePermanentEffects().add(character.getPermanentEffects());
		player.getActivePermanentEffects().add(character2.getPermanentEffects());
		players.add(player);
		players.add(player2);
		game.setModel(players);
		action = new CouncilPalaceAction(game, "1", "council", "1", "1");
	}
	
	@Test
	public void testVerify() {
		assertEquals("ok", action.verify());
	}
	
	@Test 
	public void testVerifyWrong() {
		player.getMyValues().getServants().setQuantity(0);
		player.getMyFamily().getMember1().setMemberValue(0);
		player.getMyFamily().getMember1().setAvailable(false);
		action.getPlace().setFamMemberOnPlace(new FamilyMember(PlayerColour.YELLOW));
		assertEquals("Answer: \nYou don't have enough servants to use! \n"
				+ "Sorry, this familiar is not available! \n", action.verify());
	}
	
}
