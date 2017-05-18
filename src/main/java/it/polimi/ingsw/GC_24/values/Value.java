package it.polimi.ingsw.GC_24.values;

public abstract class Value {

	protected int quantity;


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}

	//costruttore
	public Value(int quantity){
		this.quantity=quantity;
	}

	public abstract SetOfValues addValueToSet(SetOfValues values);
}
