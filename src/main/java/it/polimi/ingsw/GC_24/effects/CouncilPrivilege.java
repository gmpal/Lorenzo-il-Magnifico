package it.polimi.ingsw.GC_24.effects;

import java.util.*;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class CouncilPrivilege extends ImmediateEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3049611758848567938L;

	private List<SetOfValues> councilPrivileges;
	private int numberOfPrivileges;
	private SetOfValues set;

	public CouncilPrivilege(String name, int numberOfPrivileges) {
		super(name);
		this.councilPrivileges = CreateCouncil();
		this.numberOfPrivileges = numberOfPrivileges;
	}

	// useful methods

	// SETS THE COUNCIL ARRAY WITH THE CORRECT ELEMENTS
	public List<SetOfValues> CreateCouncil() {
		List<SetOfValues> al = new ArrayList<>();

		SetOfValues element1 = new SetOfValues();
		element1.getWoods().setQuantity(1);
		element1.getStones().setQuantity(1);

		SetOfValues element2 = new SetOfValues();
		element2.getServants().setQuantity(2);

		SetOfValues element3 = new SetOfValues();
		element3.getCoins().setQuantity(2);

		SetOfValues element4 = new SetOfValues();
		element4.getMilitaryPoints().setQuantity(2);

		SetOfValues element5 = new SetOfValues();
		element5.getFaithPoints().setQuantity(1);

		al.add(0, element1);
		al.add(1, element2);
		al.add(2, element3);
		al.add(3, element4);
		al.add(4, element5);
		return al;

	}

	/**This method receives a String that contains 1/2/3 number from 1 to 5,
	 * they represent the position on the councilPrivileges the user wants to take
	 * --> this input is parsed and saved. THEN giveImmediateEffect should be called */

	public void assignParameters(String string) {
		this.set = new SetOfValues();
		StringTokenizer reader = new StringTokenizer(string);
		while (reader.hasMoreTokens()){
			
			int value = Integer.parseInt(reader.nextToken());
		
			(councilPrivileges.get(value-1)).addTwoSetsOfValues(set);
		
		}
		
	}

	

	@Override
	public void giveImmediateEffect(Player player) {
		System.out.println("Before"+player.getMyValues());
		set.addTwoSetsOfValues(player.getMyValues());
	
		System.out.println("After"+player.getMyValues());
	}

	// Prints the composition of the Council
	@Override
	public String toString() {
		if (this.councilPrivileges != null) {
			List<SetOfValues> array = this.getCouncilPrivileges();
			StringBuilder builder = new StringBuilder();
			builder.append("Council Privilege: ");
			for (int i = 0; i < array.size(); i++) {
				builder.append(" "+(i + 1) + ") " + array.get(i));
			}
			return builder.toString();
		} else {
			return "noEffect";
		}

	}

	// getters and setters
	public List<SetOfValues> getCouncilPrivileges() {
		return councilPrivileges;
	}

	public void setCouncilPrivileges(List<SetOfValues> councilPrivileges) {
		this.councilPrivileges = councilPrivileges;
	}

	public int getNumberOfPrivileges() {
		return numberOfPrivileges;
	}

	public void setNumberOfPrivileges(int numberOfPrivileges) {
		this.numberOfPrivileges = numberOfPrivileges;
	}

	
	
}
