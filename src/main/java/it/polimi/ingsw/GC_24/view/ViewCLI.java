package it.polimi.ingsw.GC_24.view;

import java.util.HashMap;
import java.util.Scanner;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class ViewCLI extends ViewPlayer {
	private static Scanner scanner=new Scanner(System.in);
	public static void main(String args[]){
		ViewPlayer vp=new ViewCLI();
		vp.start();
	}
	
	@Override
	public void start() {
		System.out.println("STARTING VIEW");
		SetOfValues set = new SetOfValues();
		set.getCoins().addQuantity(1);
		HashMap<String, Object> obj = new HashMap<String, Object>();
		obj.put("TEST", set);
		System.out.println("Object created in VIEW");
	/*	name = setName();
		colour = setColour();
		notifyMyObservers(createMessage(name, colour));
	*/notifyMyObservers(obj);
	}

	@Override
	public String setName() {
		System.out.println("Name:");
		return scanner.nextLine();
	}

	@Override
	public String setColour() {
		System.out.println("Colour:");
		return scanner.nextLine();
	}

	@Override
	public String createMessage(String name, String colour) {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(" ");
		sb.append(colour);
		return sb.toString();
}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O extends MyObservable, C> void update(O observed, C change) {
		// TODO Auto-generated method stub
		
	}
}
