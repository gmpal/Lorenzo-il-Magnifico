package it.polimi.ingsw.GC_24.values;

public class Value {

	private int quantity;


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
}
	

	


