package it.polimi.ingsw.GC_24.values;

import java.io.Serializable;

import it.polimi.ingsw.GC_24.model.Player;

public abstract class Value implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2965545277592702641L;
	
	protected int quantity;

	// constructor
	public Value(int quantity) {
		this.quantity = quantity;
	}

	// useful methods

	// adds a single value to a set
	public abstract SetOfValues addValueToSet(SetOfValues values);
	
	// subtracts a single value to a set
	public abstract SetOfValues subValuefromSet(SetOfValues values);

	// adds a quantity to a single value
	public void addQuantity(int quantity) {
		if (quantity >= 0)
			this.quantity += quantity;
	}

	// subtracts a quantity from a single value
	public void subQuantity(int quantity) {
		if (quantity >= 0)
			this.quantity -= quantity;
	}

	// finds a value in the SetOfValue of a player if you don't already know the
	// type
	public abstract Value findValueInPlayer(Player player);// redefined in every
															// subclass

	// HashCode() redefined
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + quantity;
		return result;
	}

	// equals() redefined
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Value other = (Value) obj;
		return quantity == other.quantity;			
	}
	
	
	// toString method to print a Value
	@Override
	public String toString() {
		return "Value [quantity=" + quantity + "]";
	}

	// getter and setter
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if (quantity >= 0)
			this.quantity = quantity;
	}
	
	public abstract String whatValueAmI();
	
	public abstract Boolean amIpresentInThisSet(SetOfValues setOfValues);

}
