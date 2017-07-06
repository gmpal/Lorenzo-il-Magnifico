package it.polimi.ingsw.GC_24.modeltest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
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
		player = new Player(1);
		player2 = new Player(2);
		player3 = new Player(3);
		player.getMyValues().getFaithPoints().setQuantity(6);
		player2.getMyValues().getFaithPoints().setQuantity(0);
		player3.getMyValues().getFaithPoints().setQuantity(3);
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
		points.add(player2.getMyColour()+ " player --> "+player2.getMyValues().getVictoryPoints().getQuantity());
		points.add(player.getMyColour()+ " player --> "+player.getMyValues().getVictoryPoints().getQuantity());
		points.add(player3.getMyColour()+ " player --> "+player3.getMyValues().getVictoryPoints().getQuantity());
		assertEquals(points, ranking.getVictoryPoints());
	}
	
	@Test
	public void testRankingMilitaryPoints() {
		points.add(player2.getMyColour()+ " player --> "+player2.getMyValues().getMilitaryPoints().getQuantity());
		points.add(player.getMyColour()+ " player --> "+player.getMyValues().getMilitaryPoints().getQuantity());
		points.add(player3.getMyColour()+ " player --> "+player3.getMyValues().getMilitaryPoints().getQuantity());
		assertEquals(points, ranking.getMilitaryPoints());
	}
	
	@Test
	public void testRankingFaithPoints() {
		points.add(player.getMyColour()+ " player --> "+player.getMyValues().getFaithPoints().getQuantity());
		points.add(player3.getMyColour()+ " player --> "+player3.getMyValues().getFaithPoints().getQuantity());
		points.add(player2.getMyColour()+ " player --> "+player2.getMyValues().getFaithPoints().getQuantity());
		assertEquals(points, ranking.getFaithPoints());
	}
}
