package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.cards.*;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Wood;
import it.polimi.ingsw.GC_24.values.*;

public class Main {
	public static void main(String[] args) {


public class Main {
	public static void main(String[] args) {
		Wood w2= new Wood(3);
	SetOfValues insieme = new SetOfValues(1);
	Wood w1=new Wood(6);
	
	w1.addValueToSet(insieme);

	System.out.println(insieme.getWoods().getQuantity());
	}
}
