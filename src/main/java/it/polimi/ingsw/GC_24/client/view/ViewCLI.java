package it.polimi.ingsw.GC_24.client.view;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.effects.ChooseNewCard;
import it.polimi.ingsw.GC_24.effects.CouncilPrivilege;
import it.polimi.ingsw.GC_24.effects.Exchange;
import it.polimi.ingsw.GC_24.effects.PerformActivity;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.State;
import it.polimi.ingsw.GC_24.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class ViewCLI extends MyObservable implements MyObserver, Runnable {
	private static Scanner scanner = new Scanner(System.in);

	private volatile Model miniModel;
	private String name = null;

	private HashMap<String, Object> hm;
	private Timer timer;
	private Object waitingForAnswer = new Object();

	private boolean myTurn = false;
	private List<Player> playerTurn;
	private int playerNumber;

	private volatile Player myself = null;

	@Override
	public synchronized void run() {

		this.miniModel = new Model(0);

		// SLEEP FOR TWO SECONDS
		try {
			Thread.sleep(400);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		name = setName();

		if (myself.getMyName() == null) {
			System.out.println("ENTRATO NELL IF");

			try {
				this.sendPlayerString(name);
				System.out.println("NAME SENT");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("You have exceeded the time limit to choose your name and colour");
			System.out.println("They have been auto-completed, you are: " + myself.getMyName());
		}

		System.out.println("Waiting for other players");

	}

	public String setName() {
		String sc = null;
		System.out.println("Insert your name:");
		if (scanner.hasNextLine()) {
			sc = scanner.nextLine();
		}
		return sc;
	}

	public void sendPlayerString(String name) throws InterruptedException {
		System.out.println("ENTRATO NELL SEND");
		String player = (playerNumber + " " + name);
		hm = new HashMap<>();
		hm.put("player", player);
		this.notifyMyObservers(hm);

		System.out.println("STO PER INVIARE AL SERVER " + hm);

		synchronized (waitingForAnswer) {
			while (!miniModel.getPlayers().get((playerNumber) - 1).getMyName().equalsIgnoreCase(name)) {
				waitingForAnswer.wait();
			}
		}
		System.out.println("il server ha ricevuto la tua modifica e ha aggiornato il nome");
	}

	public synchronized void play() {

		System.out.println("The players' turn for the first round is:");
		for (int i = 0, j = 1; i < miniModel.getPlayers().size(); i++, j++) {
			System.out.println(j + ") " + miniModel.getPlayers().get(i).getMyName() + " is the "
					+ miniModel.getPlayers().get(i).getMyColour() + " player \n");
		}

		while (!miniModel.getGameState().equals(State.ENDED)) {
			showAndGetOption();
		}

		// TODO: view a partita terminata
	}

	public void showAndGetOption() {

		System.out.println("Choose action:\n" + "a)Show board\n" + "b)Show personal board\n" + "c)Show leader cards\n"
				+ "d)Place family member\n" + "e)Use a leader card\n" + "f)Throw a leader card\n" + "g)End turn\n"
				+ "h)Exit");
		String command = scanner.nextLine();

		if (command.equals("a")) {
			System.out.println(miniModel.getBoard());
		} else if (command.equals("b")) {
			System.out.println(myself.getMyBoard());
		} else if (command.equals("c")) {
			// TODO: aggiungere leadercards alla personalBoard
			// System.out.println(myself.getMyBoard().);
			System.out.println("This function is not been implemented yet");
		} else if (command.equals("d")) {
			System.out.println(myself.getMyFamily());
			command = fourChoice("family member") + " " + choosePlace();
			if (command.contains("cancel"))
				System.out.println("Action cancelled");
			else
				sendAction(command);
		} else if (command.equals("e")) {
			// miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase())).getLeaderCards().chooseLeaderCard();
			System.out.println("This function is not been implemented yet");
		} else if (command.equals("f")) {
			// miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase())).getLeaderCards().throwLeaderCard();
			System.out.println("This function is not been implemented yet");
		} else if (command.equals("g")) {
			command = "end";
			System.out.println("This function is not been implemented yet");
			// TODO: gestione della fine del turno
		} else if (command.equals("h")) {
			System.out.println("This function is not been implemented yet");
			// break;
			// TODO:gestire la disconnessione;
		} else {
			System.out.println("Wrong character");
		}

	}

	private void sendAction(String command) {
		hm = new HashMap<>();
		hm.put("action", command);
		notifyMyObservers(hm);
	}

	private String choosePlace() {
		String commandZone;
		String floor = "floor";
		do {
			System.out.println("Choose an area:\n" + "a)Tower territories\n" + "b)Tower characters\n"
					+ "c)Tower buildings\n" + "d)Tower ventures\n" + "e)Market\n" + "f)Production\n" + "g)Harvest\n"
					+ "h)Council Palace\n" + "i)Cancel");
			commandZone = scanner.nextLine();
			String cf;
			if (commandZone.equals("a")) {
				cf = fourChoice(floor);
				commandZone = "territories " + cf + " ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("b")) {
				cf = fourChoice(floor);
				commandZone = "characters " + cf + " ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("c")) {
				cf = fourChoice(floor);
				commandZone = "buildings " + cf + " ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("d")) {
				cf = fourChoice(floor);
				commandZone = "ventures " + cf + " ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("e")) {
				cf = fourChoice("place");
				commandZone = "market " + cf + " ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("f")) {
				commandZone = "production 0 ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("g")) {
				commandZone = "harvest 0 ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("h")) {
				commandZone = "council 0 ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("i")) {
				commandZone = "cancel";
			} else {
				System.out.println("Wrong character");
				commandZone = null;
			}
		} while (commandZone == null);
		if (commandZone.contains("cancel")) {
			commandZone = "cancel";
		}
		return commandZone;
	}

	public String fourChoice(String s) {
		String commandFloor;
		int commandFloorInt = 0;
		
			System.out.println("Choose " + s + " (1,2,3,4)  0 --> Cancel ");
			String validChoice = null;
			while (validChoice == null){
				try{
			
				commandFloorInt = scanner.nextInt(); 
				validChoice = "ok";
			
				if ((commandFloorInt > 5 || commandFloorInt < 1)) {
				System.out.println("Invalid number, try again");
				commandFloor = null;
			}
		}catch (InputMismatchException e){
			System.out.println("Please, type a number!");
			
		}
			}		
		if (commandFloorInt == 0) {
			commandFloor = "Cancel";
		}
		return Integer.toString(commandFloorInt);
	}

	public String increaseDieValue(String commandZone) {
		System.out.println("Do you want to use some servants to increment the die value?");
		System.out.println("Write the number of servants you want to use : ");

		String validChoice = null;
		int choice = 0;
		while (validChoice == null) {
			try {

				choice = scanner.nextInt();
				validChoice = "ok";
				if (choice < 0) {
					System.out.println("Invalid number, try again");
					validChoice = null;
				}

			} catch (InputMismatchException e) {
				System.out.println("Please, type a number!");

			}

		}
		return commandZone + " " + Integer.toString(choice);

	}

	/*
	 * this method lets the user choose between two alternative costs. It
	 * contains a Military Point value because the alternative values are always
	 * associated with militaryPoints
	 */
	// TODO: rendere scalabile il MilitaryPoint

	public void chooseAlternativeCost(SetOfValues cost1, SetOfValues cost2, MilitaryPoint militaryPoints) {
		System.out.println("The card you have chosen has two different costs:");
		System.out.println("The first one is " + cost1);

		System.out.println("The second one is " + cost2);
		System.out.println(
				"but it requires that you have " + militaryPoints.getQuantity() + " military points to be used");
		System.out.println("Make your choice (1/2):");
		int choice = scanner.nextInt();
		while (!(choice == 1 || choice == 2)) {
			System.out.println("Wrong choice, try again");
			choice = scanner.nextInt();
		}

		hm = new HashMap<>();
		if (choice == 1) {
			hm.put("chosenCost", cost1);
		} else {
			hm.put("chosenCost", cost2);

		}
		this.notifyMyObservers(hm);
	}


	public void askForChooseNewCard(ChooseNewCard im) {
		System.out.println("With the choise you have made you can pick another card!");
		String zone;
		if ((im.getType()) == null) {
			System.out.println("Write the tower you want to pick your card from");
			System.out.println("Territory/Character/Building/Venture");

			zone = scanner.nextLine();
			while (!(zone.equals("Territory") || zone.equals("Building") || zone.equals("Venture")
					|| zone.equals("Character"))) {
				System.out.println("Wrong choice, try again");
				zone = scanner.nextLine();
			}

		} else {
			zone = im.getType();
		}

		System.out.println("Write the floor you want to pick your card from");
		System.out.println("1/2/3/4");

		String floor = scanner.nextLine();

		while (!(floor.equals("1") || floor.equals("2") || floor.equals("3") || floor.equals("4"))) {
			System.out.println("Wrong choice, try again");
			floor = scanner.nextLine();

		}

		String answer = zone + " " + floor;
		increaseDieValue(answer);

		sendAnswerForParameters(answer);
	}

	private void sendAnswerForParameters(String answer) {
		hm = new HashMap<>();
		hm.put("answerForParameters", answer);
		this.notifyMyObservers(hm);

	}

	public void askForCouncilePrivilege(CouncilPrivilege immediateEffect) {
		int number = immediateEffect.getNumberOfPrivileges();
		System.out.println("You have to choose " + number + " Council Privilege(s): ");
		System.out.println(immediateEffect.getCouncilPrivileges());
		System.out.println("Make your choice: (1/2/3/4/5)");
		String answer = "";
		for (int i = 0; i < number; i++) {
			System.out.println("Choice number " + i + " of " + number);
			String choice = scanner.nextLine();
			while (!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")
					|| choice.equals("5")) || answer.contains(choice)) {
				System.out.println("Wrong choice, try again");
				choice = scanner.nextLine();
			}

			answer = answer + " " + choice;
		}

		sendAnswerForParameters(answer);
	}

	public void askForExchange(Exchange immediateEffect) {

		System.out.println("The card you picked has two choices");
		System.out.println("1) " + immediateEffect.getExchangePackage());
		System.out.println("2) " + immediateEffect.getExchangePackage1());
		System.out.println("What do you want to choose? Choose wisely: (1 / 2)");

		String choice = scanner.nextLine();

		while (!(choice.equals("1") || choice.equals("2"))) {
			System.out.println("Wrong choice, try again");
			choice = scanner.nextLine();

		}

		System.out.println("Well done!");
		sendAnswerForParameters(choice);
	}

	public void askForServantsForHarvestAndProduction(PerformActivity immediateEffect) {
		if (immediateEffect.getName().equals("performHarvest")) {
			System.out.println("The card you picked lets you perform the Harvest");
		} else {
			System.out.println("The card you picked lets you perform the Production");
		}

		String answer = "";
		increaseDieValue(answer);
		sendAnswerForParameters(answer);

	}

	


	@Override
	public <C> void update(MyObservable o, C change) {

		System.out.println(change);

	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Model getMiniModel() {
		return miniModel;
	}

	public void setMiniModel(Model model) {
		this.miniModel = model;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public List<Player> getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(List<Player> playerTurn) {
		this.playerTurn = playerTurn;
	}

	public Player getMyself() {
		return myself;
	}

	public void setMyself(Player myself) {
		this.myself = myself;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public Object getWaitingForAnswer() {
		return waitingForAnswer;
	}

}
