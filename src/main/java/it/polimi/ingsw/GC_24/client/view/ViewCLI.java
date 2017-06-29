package it.polimi.ingsw.GC_24.client.view;

import java.util.*;
import java.util.concurrent.TimeUnit;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.*;

public class ViewCLI extends MyObservable implements MyObserver, Runnable {
	private static final Scanner scanner = new Scanner(System.in);

	private volatile Model miniModel;
	private String name = null;

	private HashMap<String, Object> hm;
	private Timer timer;

	private Object waitingForNameUpdate = new Object();
	private Object waitingForActionCompleted = new Object();

	private boolean myTurn = false;
	private List<Player> playerTurn;
	private int playerNumber = 0;
	private boolean actionDone = false;
	private volatile Player myself = null;

	@Override
	public synchronized void run() {

		this.miniModel = new Model(0);

		// SLEEP FOR TWO SECONDS
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Thread.currentThread().interrupt();
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
				Thread.currentThread().interrupt();
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
		if (scanner.hasNext()) {
			sc = scanner.next();
		}
		return sc;
	}

	public void sendPlayerString(String name) throws InterruptedException {

		String player = (playerNumber + " " + name);
		hm = new HashMap<>();
		hm.put("player", player);
		this.notifyMyObservers(hm);
		System.out.println("Your name has been sent");

		synchronized (waitingForNameUpdate) {
			while (!miniModel.getPlayers().get((playerNumber) - 1).getMyName().equalsIgnoreCase(name)) {
				waitingForNameUpdate.wait();
			}
		}
		System.out.println("Server received your name and updated it");
	}

	public void showTurnSituation() {
		System.out.println("The players' turn for this round is:");
		for (int i = 0, j = 1; i < miniModel.getPlayers().size(); i++, j++) {
			System.out.println(j + ") " + miniModel.getPlayers().get(i).getMyName() + " is the "
					+ miniModel.getPlayers().get(i).getMyColour() + " player \n");
		}
	}

	public void play() {
		System.out.println("Good luck " + myself.getMyName() + "!");
		if (myself.getAutocompleted()) {
			System.out.println("You were supposed to write your name! Press any key to start Playing");
		}
		while (true) {
			showAndGetOption();
		}

	}

	public void showAndGetOption() {

		System.out.println("Choose action:\n" + "a)Show board\n" + "b)Show personal board\n" + "c)Show leader cards\n"
				+ "d)Show family members\n" + "e)Show my resources\n" + "f)Place family member\n"
				+ "g)Use a leader card\n" + "h)Throw a leader card\n" + "i)End turn\n" + "j)Exit");
		String command = scanner.next();

		if (command.equalsIgnoreCase("a")) {

			System.out.println(miniModel.getBoard());

		} else if (command.equalsIgnoreCase("b")) {

			System.out.println(myself.getMyBoard());

		} else if (command.equalsIgnoreCase("c")) {

			// TODO: aggiungere leadercards alla personalBoard
			// System.out.println(myself.getMyBoard());
			System.out.println("This function is not been implemented yet");

		} else if (command.equalsIgnoreCase("d")) {

			System.out.println(myself.getMyFamily());

		} else if (command.equalsIgnoreCase("e")) {

			System.out.println(myself.getMyValues());

		} else if (command.equalsIgnoreCase("f")) {

			if (myTurn) {
				System.out.println(myself.getMyFamily());
				command = fourChoice("family member");
				if (command.contains("cancel")) {
					System.out.println("Action cancelled");

				} else {
					command += " " + choosePlace();
					if (command.contains("cancel")) {
						System.out.println("Action cancelled");
					} else {
						sendAction(command);
					}
				}
			} else {
				System.out.println("Not your turn. You can't do any action.\n");
			}

		} else if (command.equalsIgnoreCase("g")) {
			// miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase())).getLeaderCards().chooseLeaderCard();
			System.out.println("This function is not been implemented yet");
		} else if (command.equalsIgnoreCase("h")) {
			// miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase())).getLeaderCards().throwLeaderCard();
			System.out.println("This function is not been implemented yet");
		} else if (command.equalsIgnoreCase("i")) {
			command = "end";
			System.out.println("This function is not been implemented yet");
			// TODO: gestione della fine del turno
		} else if (command.equalsIgnoreCase("j")) {
			System.out.println("This function is not been implemented yet");
			// break;
			// TODO:gestire la disconnessione;
		} else {
			System.out.println("Wrong character");
		}

	}

	private String choosePlace() {
		String commandZone;
		String floor = "floor";
		do {
			System.out.println("Choose an area:\n" + "a)Tower territories\n" + "b)Tower characters\n"
					+ "c)Tower buildings\n" + "d)Tower ventures\n" + "e)Market\n" + "f)Production\n" + "g)Harvest\n"
					+ "h)Council Palace\n" + "i)Cancel");
			commandZone = scanner.next();
			String cf;
			if (commandZone.equalsIgnoreCase("a")) {
				System.out.println(miniModel.getBoard().getTowerTerritories());
				cf = fourChoice(floor);
				commandZone = "territories " + cf + " ";
			} else if (commandZone.equalsIgnoreCase("b")) {
				System.out.println(miniModel.getBoard().getTowerCharacters());
				cf = fourChoice(floor);
				commandZone = "characters " + cf + " ";

			} else if (commandZone.equalsIgnoreCase("c")) {
				System.out.println(miniModel.getBoard().getTowerBuildings());
				cf = fourChoice(floor);
				commandZone = "buildings " + cf + " ";

			} else if (commandZone.equalsIgnoreCase("d")) {
				System.out.println(miniModel.getBoard().getTowerVentures());
				cf = fourChoice(floor);
				commandZone = "ventures " + cf + " ";

			} else if (commandZone.equalsIgnoreCase("e")) {
				System.out.println(miniModel.getBoard().getMarket());
				cf = fourChoice("place");
				commandZone = "market " + cf + " ";

			} else if (commandZone.equalsIgnoreCase("f")) {
				System.out.println(miniModel.getBoard().getProduction());
				commandZone = "production 0 ";

			} else if (commandZone.equalsIgnoreCase("g")) {
				System.out.println(miniModel.getBoard().getHarvest());
				commandZone = "harvest 0 ";

			} else if (commandZone.equalsIgnoreCase("h")) {
				System.out.println(miniModel.getBoard().getCouncilPalace());
				commandZone = "council 0 ";

			} else if (commandZone.equalsIgnoreCase("i")) {
				commandZone = "cancel";
			} else {
				System.out.println("Wrong character");
				commandZone = null;
			}
		} while (commandZone == null);

		if (commandZone.contains("cancel")) {
			commandZone = "cancel";
			return commandZone;
		}
		commandZone = increaseDieValue(commandZone);
		return commandZone;
	}

	public String fourChoice(String s) {
		System.out.println("Choose " + s + " (1,2,3,4)  0 --> Cancel ");

		String choice = scanner.next();
		while (!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")
				|| choice.equals("0"))) {
			System.out.println("Wrong choice, try again;");
			choice = scanner.next();
		}
		if (choice.equals("0")) {
			choice = "cancel";
		}

		return choice;

		/*
		 * String validChoice = null; while (validChoice == null &&
		 * scanner.hasNextInt()) { try {
		 * 
		 * commandFloorInt = scanner.nextInt(); validChoice = "ok";
		 * 
		 * if ((commandFloorInt > 5 || commandFloorInt < 1)) {
		 * System.out.println("Invalid number, try again"); commandFloor = null;
		 * } } catch (InputMismatchException e) {
		 * System.out.println("Please, type a number!");
		 * 
		 * } }
		 */

	}

	public String increaseDieValue(String commandZone) {
		System.out.println("Do you want to use some servants to increment the die value?");
		System.out.println("Write the number of servants you want to use : ");
		String choice;
		do {
				choice = scanner.next();
				try {
					Integer.parseInt(choice);
				} catch (NumberFormatException e) {
					System.out.println("Please type a number:");
					choice = null;
				}
			
		} while (choice == null);
		return commandZone + " " + choice;
	}

	private void sendAction(String command) {
		hm = new HashMap<>();
		hm.put("action", command);

		/* This block of code notifies the Server of the action */
		synchronized (waitingForActionCompleted) {
			notifyMyObservers(hm);
			System.out.println("----Waiting for your action to be completed----");
			while (!actionDone) {
				try {
					waitingForActionCompleted.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	/**
	 * this method lets the user choose between two alternative costs. It
	 * contains a Military Point value because the alternative values are always
	 * associated with militaryPoints
	 */

	public void chooseAlternativeCost(SetOfValues cost1, SetOfValues cost2, MilitaryPoint militaryPoints) {
		System.out.println("The card you have chosen has two different costs:");
		System.out.println("The first one is " + cost1);

		System.out.println("The second one is " + cost2);
		System.out.println(
				"but it requires that you have " + militaryPoints.getQuantity() + " military points to be used");
		System.out.println("Make your choice (1/2):");
		String choice = scanner.next();

		while (!(choice.equals("1") || choice.equals("2"))) {
			System.out.println("Wrong choice, try again");
			choice = scanner.next();

		}

		hm = new HashMap<>();
		if (choice.equals("1")) {
			hm.put("chosenCost", cost1);
		} else {
			hm.put("chosenCost", cost2);

		}
		this.notifyMyObservers(hm);
	}

	public void chooseSale(IncreaseDieValueCard increase) {

		SetOfValues finalIncrease;
		do {
			System.out.println(
					"Choose sale: (1,2)\n" + "1." + increase.getSale() + "\n2." + increase.getAlternativeSale());
			int answer = 0;
			try {
				answer = scanner.nextInt();
				scanner.next();
			} catch (Exception e) {
				finalIncrease = null;
				answer = 0;
			}
			if (answer == 1) {
				finalIncrease = increase.getSale();
			} else if (answer == 2) {
				finalIncrease = increase.getAlternativeSale();
			} else {
				System.out.println("Wrong number");
				finalIncrease = null;
			}
		} while (finalIncrease == null);
		notifyMyObservers(new HashMap().put("sale", finalIncrease));
	}

	public void askForChooseNewCard(ChooseNewCard im) {
		System.out.println("With the choise you have made you can pick another card!");
		String zone;
		String floor;
		if ((im.getType()) == null) {
			System.out.println(miniModel.getBoard().getTowerTerritories());
			System.out.println(miniModel.getBoard().getTowerCharacters());
			System.out.println(miniModel.getBoard().getTowerBuildings());
			System.out.println(miniModel.getBoard().getTowerVentures());

			System.out.println("Write the tower you want to pick your card from");
			System.out.println("Territories/Characters/Buildings/Ventures");

			zone = scanner.next();
			while (!(zone.equalsIgnoreCase("Territories") || zone.equalsIgnoreCase("Buildings")
					|| zone.equalsIgnoreCase("Ventures") || zone.equalsIgnoreCase("Characters"))) {
				System.out.println("Wrong choice, try again");
				zone = scanner.next();
			}

		} else {
			zone = im.getType();
			System.out.println(miniModel.getBoard().getZoneFromString(zone));
		}

		System.out.println("Write the floor you want to pick your card from");
		System.out.println("1/2/3/4");

		scanner.next();
		floor = scanner.next();

		while (!(floor.equals("1") || floor.equals("2") || floor.equals("3") || floor.equals("4"))) {
			System.out.println("Wrong choice, try again");
			floor = scanner.next();

		}

		String answer = zone + " " + floor;
		answer = increaseDieValue(answer);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + answer);
		sendAnswerForParameters(answer);
	}

	private void sendAnswerForParameters(String answer) {
		hm = new HashMap<>();
		hm.put("answerForParameters", answer);
		this.notifyMyObservers(hm);

	}

	public void askForCouncilPrivilege(CouncilPrivilege immediateEffect) {

		// TODO: AYAYAY
		int number = immediateEffect.getNumberOfPrivileges();
		System.out.println("You have to choose " + number + " Council Privilege(s): ");
		System.out.println(immediateEffect.getCouncilPrivileges());
		System.out.println("Make your choice: (1/2/3/4/5)");
		String answer = "";
		for (int i = 1; i <= number; i++) {
			System.out.println("Choice number " + (i) + " of " + number);
			String choice = "";
			try {

				choice = scanner.next();
			} catch (IndexOutOfBoundsException e) {
				System.out.println(" AYAYAYAY");
			}
			while (!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")
					|| choice.equals("5")) || answer.contains(choice)) {
				System.out.println("Wrong choice, try again");

				choice = scanner.next();
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

		String choice = scanner.next();

		while (!(choice.equals("1") || choice.equals("2"))) {
			System.out.println("Wrong choice, try again");
			choice = scanner.next();

		}

		System.out.println("Well done!");
		sendAnswerForParameters(choice);
	}

	public void askForServantsForHarvestAndProduction(PerformActivity immediateEffect) {
		String answer = "";
		if (immediateEffect.getName().equalsIgnoreCase("performHarvest")) {
			System.out.println("The card you picked lets you perform the Harvest");
		} else {
			System.out.println("The card you picked lets you perform the Production");
		}

		answer = increaseDieValue(answer);
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
		if (myTurn) {
			System.out.println("**********It's your turn!!!!**********");
		} else {
			System.out.println("**********Not your turn**********");
		}
		;
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
		return waitingForNameUpdate;
	}

	public Object getWaitingForActionCompleted() {
		return waitingForActionCompleted;
	}

	public void setWaitingForActionCompleted(Object waitingForActionCompleted) {
		this.waitingForActionCompleted = waitingForActionCompleted;
	}

	public boolean isActionDone() {
		return actionDone;
	}

	public void setActionDone(boolean actionDone) {
		this.actionDone = actionDone;
	}

}