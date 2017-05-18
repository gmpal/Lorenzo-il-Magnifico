package it.polimi.ingsw.GC_24.values;

public class Wood extends Value {
	public Wood(int value){
	super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setWoods(new Wood(values.getWoods().getQuantity()+this.getQuantity()));
		return values;
	}
}
