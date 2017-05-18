package it.polimi.ingsw.GC_24.values;

public class Value {

	protected int quantity;

	//constructor
	public Value(int quantity){
		this.quantity=quantity;
	}
	
	//getter and setter
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//adds a parameter quantity to the object's quantity 
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}

	//adds a specific value to a paramenter setofvalues
	public SetOfValues addValueToSet(SetOfValues values){
		return values;		//redefined in subclasses
	}
}
