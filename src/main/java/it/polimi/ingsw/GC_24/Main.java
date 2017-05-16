package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Wood;

public class Main {
	public static void main(String[] args) {
	SetOfValues insieme = new SetOfValues();
	Wood legno = new Wood(5);
	insieme.setWoods(legno);
	System.out.println(insieme.getWoods().getValue());}
}
