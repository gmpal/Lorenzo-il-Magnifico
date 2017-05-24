package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.*;

public class Rankings {
	
	private Player player;
	private FaithPoint faithPoints;
	private MilitaryPoint militaryPoints;
	private VictoryPoint victoryPoints;
	
	//constructor
	public Rankings(Player player) {
		this.player = player;
		this.militaryPoints = player.getMyValues().getMilitaryPoints();
		this.faithPoints = player.getMyValues().getFaithPoints();
		this.victoryPoints = player.getMyValues().getVictoryPoints();
	}
	
	@Override
	public String toString() {
		return player + "'s rankings ( player " + player.getMyColour() +" ):" +
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
