package it.polimi.ingsw.GC_24.values;

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
	
	
	//getter and setter

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
