package it.polimi.ingsw.GC_24.model.dice;

import java.util.Random;

public class SetOfDice implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7301515708684254512L;
	private Die die1;
	private Die die2;
	private Die die3;

	Random rnd = new Random();

	// constructor --> Set of Dice which value is 0
	public SetOfDice() {
		this.die1 = new Die(0, DieColour.BLACK);
		this.die2 = new Die(0, DieColour.WHITE);
		this.die3 = new Die(0, DieColour.ORANGE);
	}

	/**
	 * resets the SetOfDice without creating another one
	 */
	public void reset() {
		this.die1 = new Die(rnd.nextInt(6) + 1, DieColour.BLACK);
		this.die2 = new Die(rnd.nextInt(6) + 1, DieColour.WHITE);
		this.die3 = new Die(rnd.nextInt(6) + 1, DieColour.ORANGE);
	}

	// getters and setters
	public Die getDie1() {
		return die1;
	}

	public void setDie1(Die die1) {
		this.die1 = die1;
	}

	public Die getDie2() {
		return die2;
	}

	public void setDie2(Die die2) {
		this.die2 = die2;
	}

	public Die getDie3() {
		return die3;
	}

	public void setDie3(Die die3) {
		this.die3 = die3;
	}
}