package it.polimi.ingsw.GC_24.values;

public class Coin extends Value {

	public Coin(int value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setCoins(new Coin(values.getCoins().getQuantity()+this.getQuantity()));
		return values;
	}

}
