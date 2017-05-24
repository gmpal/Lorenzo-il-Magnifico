package it.polimi.ingsw.GC_24;

import java.util.Random;

public class SetOfDice {
	/*EVERY TIME A NEW SET IS CREATED, IT IS RANDOM!
	No need to create a SetRandom or ThrowDice methods
	just a Reset() for next turns without destroying our existing set */
	
	private Die die1;
	private Die die2;
	private Die die3;
	
	Random rnd = new Random();
	
	//null constructor
	public SetOfDice() {
		this.die1 = new Die(rnd.nextInt(6)+1, DieColour.BLACK);
		this.die2 = new Die(rnd.nextInt(6)+1, DieColour.WHITE);
		this.die3 = new Die(rnd.nextInt(6)+1, DieColour.ORANGE);
	}
	
	//useful methods
	
	//resets the Set without creating another one
	public void reset() {
		this.die1 = new Die(rnd.nextInt(6)+1, DieColour.BLACK);
		this.die2 = new Die(rnd.nextInt(6)+1, DieColour.WHITE);
		this.die3 = new Die(rnd.nextInt(6)+1, DieColour.ORANGE);
	}
	
	//getters and setters
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
