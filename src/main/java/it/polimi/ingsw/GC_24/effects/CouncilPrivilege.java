package it.polimi.ingsw.GC_24.effects;

import java.util.ArrayList;
import java.util.Scanner;
import it.polimi.ingsw.GC_24.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class CouncilPrivilege extends ImmediateEffect {
	/*ATTENTION: when created, the constructor creates an empty ArrayList<SetOfValues>, 
	 * but calls the method SetCoucil() and fills itself*/
	private ArrayList<SetOfValues> councilPrivileges;
	
	/*constructor --> Creates an empty ArrayList and fills it with SetCouncil() method 
	(Needs a name because it's an effect).*/
	public CouncilPrivilege(String name, SetOfValues effectValues) {
		super(name, effectValues);
		this.councilPrivileges = new ArrayList<>();
		setCouncil();
	}
	
	//useful methods
	
	//SETS THE COUNCIL ARRAY WITH THE CORRECT ELEMENTS
	public void setCouncil(){
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
		
		
		councilPrivileges.add(0,element1);
		councilPrivileges.add(1,element2);
		councilPrivileges.add(2,element3);
		councilPrivileges.add(3,element4);
		councilPrivileges.add(4,element5);
			
	}

	//Gives n different privileges to the selected player 
	public void givePrivilegeEffect(Player player, int n){
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ciao " + player.getMyName());
		
		for (int i=0; i<n; i++){
			
			System.out.println("Ecco i privilegi:\n" + this);
			
			if (i==0) 
				System.out.println("Scegli un elemento");
			if (i!=0) 
				System.out.println("Scegli un altro elemento");
			
			int index = scanner.nextInt();
			while (index<1 || index>councilPrivileges.size()){
				System.out.println("Il valore non è corretto, riprova:");
				index = scanner.nextInt();;
			}
			SetOfValues chosenPrivilege = councilPrivileges.get(index-1);
			chosenPrivilege.addTwoSetsOfValues(player.getMyValues());
			
			councilPrivileges.remove(index-1);
			System.out.println("Privilegio ("+index+") aggiunto alle tue risorse");
		}
		//reset the council
		scanner.close();
		councilPrivileges.clear();
		setCouncil();
	}

	//Prints the composition of the Council
	@Override
	public String toString() {
			ArrayList<SetOfValues> array = this.getCouncilPrivileges();
			StringBuilder builder = new StringBuilder();
			for (int i=0; i<array.size(); i++){
				builder.append( "(" +(i+1)+ ") " + array.get(i) + "\n");
			} 
			return builder.toString();

	}

	//getters and setters
	public ArrayList<SetOfValues> getCouncilPrivileges() {
		return councilPrivileges;
	}

	public void setCouncilPrivileges(ArrayList<SetOfValues> councilPrivileges) {
		this.councilPrivileges = councilPrivileges;
	}
	
	
	
	
}
