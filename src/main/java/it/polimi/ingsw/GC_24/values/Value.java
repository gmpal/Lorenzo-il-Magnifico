package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.Player;

public abstract class Value {

	protected int quantity;
	
	//constructor
	public Value(int quantity){
		this.quantity=quantity;
	}


	//useful methods
	
	//adds a single value to a set
	public abstract SetOfValues addValueToSet(SetOfValues values);
	
	//adds quantity to a single value
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	//finds a value in the SetOfValue of a player if you don't already know the type
	public abstract Value FindValueInPlayer(Player player);// redefined in every subclass
	
	//toString method to print a Value
	@Override
	public String toString() {
		return "Value [quantity=" + quantity + "]";
	}


	//getter and setter
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
