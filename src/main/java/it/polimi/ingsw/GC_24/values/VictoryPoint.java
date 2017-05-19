package it.polimi.ingsw.GC_24.values;

public class VictoryPoint extends Value {

	public VictoryPoint(int value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setVictoryPoints(new VictoryPoint(values.getVictoryPoints().getQuantity()+this.getQuantity()));
		return values;
	}
}
