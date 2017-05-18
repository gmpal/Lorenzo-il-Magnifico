package it.polimi.ingsw.GC_24.values;

public class Servant extends Value {

	public Servant(int value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setServants(new Servant(values.getServants().getQuantity()+this.getQuantity()));
		return values;
	} 

}
