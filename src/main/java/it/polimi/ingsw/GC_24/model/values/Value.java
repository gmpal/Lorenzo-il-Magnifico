package it.polimi.ingsw.GC_24.model.values;

import java.io.Serializable;
import it.polimi.ingsw.GC_24.model.Player;

public abstract class Value implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2157442837023199966L;
	protected int quantity;

	// constructor
	public Value(int quantity) {
		this.quantity = quantity;
	}

	// useful methods

	/** adds a single value to a set*/
	public abstract SetOfValues addValueToSet(SetOfValues values);
	
	/** subtracts a single value to a set*/
	public abstract SetOfValues subValuefromSet(SetOfValues values);

	/** adds a quantity to a single value*/
	public void addQuantity(int quantity) {
		if (quantity >= 0)
			this.quantity += quantity;
	}

	/** subtracts a quantity from a single value*/
	public void subQuantity(int quantity) {
		if (quantity >= 0)
			this.quantity -= quantity;
		if(this.quantity<0) {
			this.quantity=0;
		}
	}

	/** finds a value in the SetOfValue of a player if you don't
	 *  already know the type
	 */
	public abstract Value findValueInPlayer(Player player);// redefined in every
															// subclass


	public abstract String whatValueAmI();
	
	public abstract Boolean amIPresentInThisSet(SetOfValues setOfValues);
	
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
		return ""+quantity;
	}

	// getter and setter
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if (quantity >= 0)
			this.quantity = quantity;
	}

}
