package it.polimi.ingsw.GC_24.view;

import java.util.Scanner;

public class ViewCLI extends ViewPlayer {
	private static Scanner scanner=new Scanner(System.in);
	public static void main(String args[]){
		ViewPlayer vp=new ViewCLI();
		vp.start();
	}
	
	@Override
	public void start() {
		name = setName();
		colour = setColour();
		notifyMyObservers(createMessage(name, colour));
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
}
