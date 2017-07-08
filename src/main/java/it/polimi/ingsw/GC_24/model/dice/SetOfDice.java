package it.polimi.ingsw.GC_24.model.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SetOfDice implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7301515708684254512L;
	private Die die1;
	private Die die2;
	private Die die3;
	private List<String> urlDice = new ArrayList<>();

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

	/**
	 * This method take the image urls of the dice and set they in an ArrayList.
	 */
	public List<String> urlDice() {
		getUrlDie(this.die1);
		getUrlDie(this.die2);
		getUrlDie(this.die3);
		return urlDice;
	}

	/**
	 * This method take the image url of one die and set it in an ArrayList.
	 * 
	 * @param Die
	 */
	public void getUrlDie(Die die) {
		if (die.getValue() == 1) {
			urlDice.add("src/main/java/it/polimi/ingsw/GC_24/img/dice/die1.png");
		} else if (die.getValue() == 2) {
			urlDice.add("src/main/java/it/polimi/ingsw/GC_24/img/dice/die2.png");
		} else if (die.getValue() == 2) {
			urlDice.add("src/main/java/it/polimi/ingsw/GC_24/img/dice/die3.png");
		} else if (die.getValue() == 2) {
			urlDice.add("src/main/java/it/polimi/ingsw/GC_24/img/dice/die4.png");
		} else if (die.getValue() == 2) {
			urlDice.add("src/main/java/it/polimi/ingsw/GC_24/img/dice/die5.png");
		} else if (die.getValue() == 2) {
			urlDice.add("src/main/java/it/polimi/ingsw/GC_24/img/dice/die6.png");
		}

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