package it.polimi.ingsw.GC_24.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.model.cards.Leader;
import it.polimi.ingsw.GC_24.model.effects.PermanentEffect;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.model.places.Place;
import it.polimi.ingsw.GC_24.model.values.*;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2255445460112622580L;
	private String myName;
	private Family myFamily;
	private PersonalBoard myBoard;
	private SetOfValues myValues;
	private PlayerColour myColour;
	private int playerNumber;
	public boolean autoCompleted = false;
	private List<PermanentEffect> activePermanentEffects = new ArrayList<>();
	private List<Leader> leaderOneTimePerTurn = new ArrayList<>();
	private boolean lastExcommunication=false;

	// Constructor

	public Player(String name, int playerNumber){
		this.myColour = null;
		this.myName = name;
		this.myFamily = null;
		this.myBoard = new PersonalBoard(playerNumber);
		this.myValues = new SetOfValues();
		this.playerNumber = playerNumber;
	}

	// constructor for tests
	
	public Player(int playerNumber){
		this.myColour = null;
		this.myName = null;
		this.myFamily = null;
		this.myBoard = new PersonalBoard(playerNumber);
		this.myValues = new SetOfValues();
		this.playerNumber = playerNumber;
	}
	public Player(String name, PlayerColour colour) {
		this.myColour = colour;
		this.myName = name;
		this.myFamily = new Family(colour);
		this.myBoard = new PersonalBoard(1);
		this.myValues = new SetOfValues();
		this.playerNumber = 1;
	}

	/** useful to find the value of the player if you only know his colour */
	public SetOfValues getMyValuesFromColour(PlayerColour playerColour) {
		if (this.myColour.equals(playerColour))
			return myValues;
		else
			return null;
	}

	public void takeValuesFromPlace(Place place) {
		SetOfValues value = place.getValue().getEffectValues();
		this.myValues = value.addTwoSetsOfValues(this.myValues);
	}

	/**
	 * returns false if the increment is a negative number(not allowed) or if it is
	 * grater than the number of servants of the player, hence it is not possible to
	 * raise the die's value of the required increment
	 */
	public boolean isPossibleIncreaseDieValue(int increment) {
		int myservants = this.getMyValues().getServants().getQuantity();
		if (increment >= 0)
			return myservants >= increment;
		else
			return false;
	}

	/**
	 * This method checks the activePermanentEffects list of the player.
	 * 
	 * @param nameEffect
	 * @return first PermanentEffect with the name asked in the parameter, otherwise
	 *         null.
	 */
	public PermanentEffect getPermanentEffect(String nameEffect) {
		for (PermanentEffect pe : activePermanentEffects) {
			if (pe.getName().equalsIgnoreCase(nameEffect)) {
				return pe;
			}
		}
		return null;
	}

	/**
	 * This method checks the activePermanentEffects list of the player.
	 * 
	 * @param nameEffect
	 * @return all PermanentEffect with the name asked in the parameter, otherwise
	 *         null.
	 */
	public List<PermanentEffect> getPermanentEffectList(String nameEffect) {
		List<PermanentEffect> peList = new ArrayList<>();
		for (PermanentEffect pe : activePermanentEffects) {
			if (pe.getName().equalsIgnoreCase(nameEffect)) {
				peList.add(pe);
			}
		}
		return peList;
	}

	// Prints name of a Player
	@Override
	public String toString() {
		return "Player [myName=" + myName + ", myColour=" + myColour + "]";
	}
	

	// getters and setters
	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public Family getMyFamily() {
		return myFamily;
	}

	public void setMyFamily(Family myFamily) {
		this.myFamily = myFamily;
	}

	public PlayerColour getMyColour() {
		return myColour;
	}

	public void setMyColour(PlayerColour myColour) {
		this.myColour = myColour;
	}

	public PersonalBoard getMyBoard() {
		return myBoard;
	}

	public void setMyBoard(PersonalBoard myBoard) {
		this.myBoard = myBoard;
	}

	public SetOfValues getMyValues() {
		return myValues;
	}

	public void setMyValues(SetOfValues myValues) {
		this.myValues = myValues;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public boolean getAutocompleted() {
		return this.autoCompleted;
	}

	public void setAutocompleted(boolean autoCompleted) {
		this.autoCompleted = autoCompleted;
	}

	public List<PermanentEffect> getActivePermanentEffects() {
		return activePermanentEffects;

	}

	public void setActivePermanentEffects(List<PermanentEffect> activePermanentEffects) {
		this.activePermanentEffects = activePermanentEffects;
	}


	public List<Leader> getLeaderOneTimePerTurn() {
		return leaderOneTimePerTurn;
	}

	public void setLeaderOneTimePerTurn(List<Leader> leaderOneTimePerTurn) {
		this.leaderOneTimePerTurn = leaderOneTimePerTurn;
	}
	public boolean hasLastExcommunication() {
		return lastExcommunication;
	}

	public void setLastExcommunication(boolean lastExcommunication) {
		this.lastExcommunication = lastExcommunication;
	}
}
