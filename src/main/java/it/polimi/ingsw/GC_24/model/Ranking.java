package it.polimi.ingsw.GC_24.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import it.polimi.ingsw.GC_24.model.values.*;

/**
 * this class creates three array lists with the rankings of the players in the
 * game of the three types of points (victory, military, faith), and place the
 * values in the arrayLists in descending order
 */
public class Ranking implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2248100702854353172L;
	private List<Player> players;
	private List<String> victoryPoints;
	private List<String> militaryPoints;
	private List<String> faithPoints;

	// constructor
	public Ranking(List<Player> players) {
		this.setPlayers(players);
		this.victoryPoints = createList(new VictoryPoint(0));
		this.militaryPoints = createList(new MilitaryPoint(0));
		this.faithPoints = createList(new FaithPoint(0));
	}

	public List<String> createList(Value point) {
		List<Integer> points = sortPoints(point);
		List<String> stringPoints = new ArrayList<>();
		if (!sortPoints(point).isEmpty()) {

			List<Player> tempPlayers = new ArrayList<>();
			for (int i = 0; i < points.size(); i++) {
				for (Player p : players) {
					if (!tempPlayers.contains(p) && point.findValueInPlayer(p).getQuantity() == points.get(i)) {
						stringPoints.add(p.getMyName().toString() + ", " + p.getMyColour().toString() + " player \t--> "
								+ Integer.toString(point.findValueInPlayer(p).getQuantity()) + "\r\t\t\t");
						tempPlayers.add(p);

					}
				}
			}
		}
		return stringPoints;
	}

	public List<Integer> sortPoints(Value point) {
		List<Integer> points = new ArrayList<>();
		for (Player p : players) {
			points.add(point.findValueInPlayer(p).getQuantity());
		}
		Collections.sort(points);
		Collections.reverse(points);
		return points;
	}

	@Override
	public String toString() {
		return "Victory Points= \t" + victoryPoints + "\nMilitary Points= \t" + militaryPoints + "\nFaith Points= \t\t"
				+ faithPoints + "]";
	}

	// getters and setters
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<String> getVictoryPoints() {
		return victoryPoints;
	}

	public void setVictoryPoints(List<String> victoryPoints) {
		this.victoryPoints = victoryPoints;
	}

	public List<String> getMilitaryPoints() {
		return militaryPoints;
	}

	public void setMilitaryPoints(List<String> militaryPoints) {
		this.militaryPoints = militaryPoints;
	}

	public List<String> getFaithPoints() {
		return faithPoints;
	}

	public void setFaithPoints(List<String> faithPoints) {
		this.faithPoints = faithPoints;
	}
}