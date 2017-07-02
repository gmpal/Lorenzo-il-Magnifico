package it.polimi.ingsw.GC_24.model;

import it.polimi.ingsw.GC_24.model.values.*;

public class Ranking implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2248100702854353172L;
	private Player player;
	private FaithPoint faithPoints;
	private MilitaryPoint militaryPoints;
	private VictoryPoint victoryPoints;
	
	//constructor
	public Ranking(Player player) {
		this.player = player;
		this.militaryPoints = player.getMyValues().getMilitaryPoints();
		this.faithPoints = player.getMyValues().getFaithPoints();
		this.victoryPoints = player.getMyValues().getVictoryPoints();
	}
	
	@Override
	public String toString() {
		return player + "'s ranking ( player " + player.getMyColour() +" ):" +
				"\nfaithPoints=" + faithPoints.getQuantity() + 
				", militaryPoints=" + militaryPoints.getQuantity()
				+ ", victoryPoints=" + victoryPoints.getQuantity() + "]";
	}

	//getters and setters
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
