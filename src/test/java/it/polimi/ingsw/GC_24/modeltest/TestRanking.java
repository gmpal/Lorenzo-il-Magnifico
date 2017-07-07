package it.polimi.ingsw.GC_24.modeltest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.Ranking;

public class TestRanking {

	Player player;
	Player player2;
	Player player3;
	List<Player> players;
	Ranking ranking;
	List<String> points;
	
	@Before
	public void setUp() {
		players = new ArrayList<>();
		player = new Player("Giorgia", PlayerColour.RED);
		player2 = new Player("Carlo", PlayerColour.GREEN);
		player3 = new Player("Gian Marco", PlayerColour.BLUE);
		player.getMyValues().getFaithPoints().setQuantity(6);
		player2.getMyValues().getFaithPoints().setQuantity(0);
		player3.getMyValues().getFaithPoints().setQuantity(6);
		player.getMyValues().getMilitaryPoints().setQuantity(9);
		player2.getMyValues().getMilitaryPoints().setQuantity(20);
		player3.getMyValues().getMilitaryPoints().setQuantity(4);
		player.getMyValues().getVictoryPoints().setQuantity(10);
		player2.getMyValues().getVictoryPoints().setQuantity(15);
		player3.getMyValues().getVictoryPoints().setQuantity(7);
		players.add(player);
		players.add(player2);
		players.add(player3);
		ranking = new Ranking(players);
		points = new ArrayList<>();
	}
	
	@Test
	public void testRankingVictoryPoints() {
		points.add(player2.getMyName().toString() + ", " + player2.getMyColour().toString() + " player \t--> "
				+ Integer.toString(player2.getMyValues().getVictoryPoints().getQuantity()) + "\r\t\t\t");
		points.add(player.getMyName().toString() + ", " + player.getMyColour().toString() + " player \t--> "
				+ Integer.toString(player.getMyValues().getVictoryPoints().getQuantity()) + "\r\t\t\t");
		points.add(player3.getMyName().toString() + ", " + player3.getMyColour().toString() + " player \t--> "
				+ Integer.toString(player3.getMyValues().getVictoryPoints().getQuantity()) + "\r\t\t\t");
		assertEquals(points, ranking.getVictoryPoints());
	}
	
	@Test
	public void testRankingMilitaryPoints() {
		points.add(player2.getMyName().toString() + ", " + player2.getMyColour().toString() + " player \t--> "
				+ Integer.toString(player2.getMyValues().getMilitaryPoints().getQuantity()) + "\r\t\t\t");
		points.add(player.getMyName().toString() + ", " + player.getMyColour().toString() + " player \t--> "
				+ Integer.toString(player.getMyValues().getMilitaryPoints().getQuantity()) + "\r\t\t\t");
		points.add(player3.getMyName().toString() + ", " + player3.getMyColour().toString() + " player \t--> "
				+ Integer.toString(player3.getMyValues().getMilitaryPoints().getQuantity()) + "\r\t\t\t");
		assertEquals(points, ranking.getMilitaryPoints());
	}
	
	@Test
	public void testRankingFaithPoints() {
		points.add(player.getMyName().toString() + ", " + player.getMyColour().toString() + " player \t--> "
				+ Integer.toString(player.getMyValues().getFaithPoints().getQuantity()) + "\r\t\t\t");
		points.add(player3.getMyName().toString() + ", " + player3.getMyColour().toString() + " player \t--> "
				+ Integer.toString(player3.getMyValues().getFaithPoints().getQuantity()) + "\r\t\t\t");
		points.add(player2.getMyName().toString() + ", " + player2.getMyColour().toString() + " player \t--> "
				+ Integer.toString(player2.getMyValues().getFaithPoints().getQuantity()) + "\r\t\t\t");
		assertEquals(points, ranking.getFaithPoints());
	}
}
