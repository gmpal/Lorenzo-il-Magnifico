package it.polimi.ingsw.GC_24.values;

public class FaithPoint extends Value {

	public FaithPoint(int value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setFaithPoints(new FaithPoint(values.getFaithPoints().getQuantity()+this.getQuantity()));
		return values;
	}

}
