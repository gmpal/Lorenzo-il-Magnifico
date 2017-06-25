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

	// Gives n different privileges to the selected player
	/*
	 * public void giveImmediateEffect(Player player){
	 * 
	 * Scanner scanner = new Scanner(System.in); System.out.println("Ciao " +
	 * player.getMyName());
	 * 
	 * 
	 * for (int i=0; i<numberOfPrivileges; i++){
	 * 
	 * System.out.println("Ecco i privilegi:\n" + this);
	 * 
	 * if (i==0) System.out.println("Scegli un elemento"); if (i!=0)
	 * System.out.println("Scegli un altro elemento");
	 * 
	 * int index = scanner.nextInt(); System.out.println(index); while (index<1
	 * || index>councilPrivileges.size()){
	 * System.out.println("Il valore non è corretto, riprova:"); index =
	 * scanner.nextInt(); } SetOfValues chosenPrivilege =
	 * councilPrivileges.get(index-1);
	 * chosenPrivilege.addTwoSetsOfValues(player.getMyValues());
	 * 
	 * councilPrivileges.remove(index-1);
	 * System.out.println("Privilegio ("+index+") aggiunto alle tue risorse"); }
	 * //reset the council scanner.close(); councilPrivileges.clear();
	 * 
	 * }
	 */

	public void assignParameter(SetOfValues set) {
		this.set = set;
	}

	@Override
	public void giveImmediateEffect(Player player) {
		set.addTwoSetsOfValues(player.getMyValues());
		this.councilPrivileges = CreateCouncil();
	}

	// Prints the composition of the Council
	@Override
	public String toString() {
		if (this.councilPrivileges != null) {
			List<SetOfValues> array = this.getCouncilPrivileges();
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < array.size(); i++) {
				builder.append(", " + (i + 1) + ") " + array.get(i));
			}
			builder.append("\n");
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
