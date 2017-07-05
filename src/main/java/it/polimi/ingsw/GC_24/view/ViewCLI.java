package it.polimi.ingsw.GC_24.view;

import java.util.*;

public class ViewCLI extends View {

	public ViewCLI(String name) {
		super(name);

	}

	private static Scanner scanner = new Scanner(System.in);

	/*
	 * public void showTurnSituation() {
	 * System.out.println("The players' turn for this round is:"); for (int i = 0, j
	 * = 1; i < miniModel.getPlayers().size(); i++, j++) { System.out.println(j +
	 * ") " + miniModel.getPlayers().get(i).getMyName() + " is the " +
	 * miniModel.getPlayers().get(i).getMyColour() + " player \n"); } }
	 */

	@Override
	public void play() {

		while (true) {
			showAndGetOption();
		}

	}

	public void showAndGetOption() {

		System.out.println("\nChoose Action:\n" + "a)Show Board\n" + "b)Show Personal Board\n"
				+ "c)Show Family Members\n" + "d)Show my Resources\n" + "e)Show Leader Cards\n"
				+ "f)Show Active Effects\n" + "g)Place Family Member\n" + "h)Activate a Leader Card\n"
				+ "i)Discard a Leader Card\n" + "j)End Turn\n" + "k)Exit");

		String command = scanner.nextLine();

		if (command.equalsIgnoreCase("a")) {

			printBoard();

		} else if (command.equalsIgnoreCase("b")) {

			printPersonalBoard();
		} else if (command.equalsIgnoreCase("c")) {

			System.out.println(family);

		} else if (command.equalsIgnoreCase("d")) {

			System.out.println(values);

		} else if (command.equalsIgnoreCase("e")) {

			System.out.println(personalLeaders);

		} else if (command.equalsIgnoreCase("f")) {

			System.out.println("Permanent Effects --> " + permanentEffects);
			System.out.println("\nOneTimePerTurn Effects --> " + oneTimePerTurnEffects);

		} else if (command.equalsIgnoreCase("g")) {

			if (myTurn) {
				System.out.println(family);
				command = fourChoice("family member");
				if (command.contains("cancel")) {
					System.out.println("Action cancelled");

				} else {
					command = choosePlace(command);
					if (command.contains("cancel")) {
						System.out.println("Action cancelled");
					} else {
						sendAction(command);
					}
				}
			} else {
				System.out.println("Not your turn. You can't do any action.\n");
			}

		} else if (command.equalsIgnoreCase("h")) {

			if (myTurn) {
				System.out.println(personalLeaders);
				command = "activate " + chooseLeader();
				if (command.contains("cancel")) {
					System.out.println("Action cancelled");
				} else {
					sendLeader(command);
				}

			} else {
				System.out.println("Not your turn. You can't do any action.\n");
			}

		} else if (command.equalsIgnoreCase("i")) {

			if (myTurn) {
				System.out.println(personalLeaders);
				command = "discard " + chooseLeader();
				if (command.contains("cancel")) {
					System.out.println("Action cancelled");
				} else {
					sendLeader(command);
				}

			} else {
				System.out.println("Not your turn. You can't do any action.\n");
			}

		} else if (command.equalsIgnoreCase("j")) {
			command = "end";
			System.out.println("This function is not been implemented yet");
			// TODO: gestione della fine del turno
		} else if (command.equalsIgnoreCase("k")) {
			System.out.println("This function is not been implemented yet");
			// break;
			// TODO:gestire la disconnessione;
		} else {
			System.out.println("Wrong character");
		}

	}

	private void printPersonalBoard() {
		System.out.println(personalTerritories);
		System.out.println(personalCharacters);
		System.out.println(personalBuildings);
		System.out.println(personalVentures);
		System.out.println(personalLeaders);

	}

	private void printBoard() {
		System.out.println(towerTerritories);
		System.out.println(towerCharacters);
		System.out.println(towerBuildings);
		System.out.println(towerVentures);
		System.out.println(market);
		System.out.println(harvest);
		System.out.println(production);
		System.out.println(council);

	}

	private String choosePlace(String command) {
		String commandZone;
		String floor = "floor";
		do {
			System.out.println("Choose an area:\n" + "a)Tower territories\n" + "b)Tower characters\n"
					+ "c)Tower buildings\n" + "d)Tower ventures\n" + "e)Market\n" + "f)Production\n" + "g)Harvest\n"
					+ "h)Council Palace\n" + "i)Cancel");
			commandZone = scanner.nextLine();
			String cf;
			if (commandZone.equalsIgnoreCase("a")) {
				System.out.println(towerTerritories);
				cf = fourChoice(floor);
				command += " territories " + cf + " ";
			} else if (commandZone.equalsIgnoreCase("b")) {
				System.out.println(towerCharacters);
				cf = fourChoice(floor);
				command += " characters " + cf + " ";

			} else if (commandZone.equalsIgnoreCase("c")) {
				System.out.println(towerBuildings);
				cf = fourChoice(floor);
				command += " buildings " + cf + " ";

			} else if (commandZone.equalsIgnoreCase("d")) {
				System.out.println(towerVentures);
				cf = fourChoice(floor);
				command += " ventures " + cf + " ";

			} else if (commandZone.equalsIgnoreCase("e")) {
				System.out.println(market);
				cf = fourChoice("place");
				command += " market " + cf + " ";

			} else if (commandZone.equalsIgnoreCase("f")) {
				System.out.println("My buildings:\n" + personalBuildings);
				System.out.println(production);
				command += " production 0 ";

			} else if (commandZone.equalsIgnoreCase("g")) {
				System.out.println("My territories:\n" + personalTerritories);
				System.out.println(harvest);
				command += " harvest 0 ";

			} else if (commandZone.equalsIgnoreCase("h")) {
				System.out.println(council);
				command += " council 0 ";

			} else if (commandZone.equalsIgnoreCase("i")) {
				command = "cancel";
			} else {
				System.out.println("Wrong character");
				commandZone = null;
			}
		} while (commandZone == null);

		if (command.contains("cancel")) {
			command = "cancel";

		} else {
			// not production nor harvest
			if (!command.contains("production") && !command.contains("harvest")) {
				command = increaseDieValue(command);
			} else {
				// neutral chosen
				if (command.contains("4")) {
					
					command = increaseDieValue(command);
					// non neutral chosen
				} else {
				
					command = command + "0";
				}
			}

		}
		return command;
	}

	public String chooseLeader() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nChoose Leader Card (");

		String string = "0";

		for (int i = 1; i <= 4; i++) {
			if (i == 4) {
				builder.append(i);
				string = string + i;
				break;
			}
			builder.append(i + ",");
			string = string + i;
		}
		builder.append(")  0 --> Cancel ");
		System.out.println(builder.toString());
		String choice = scanner.nextLine();

		while (!(string.contains(choice)) || choice.isEmpty()) {
			System.out.println("Wrong choice, try again");

			choice = scanner.nextLine();
		}
		if (choice.equals("0")) {
			choice = "cancel";
		}
		return choice;
	}

	public String fourChoice(String s) {
		System.out.println("Choose " + s + " (1,2,3,4)  0 --> Cancel ");

		String choice = scanner.nextLine();
		while (!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")
				|| choice.equals("0"))) {
			System.out.println("Wrong choice, try again;");
			choice = scanner.nextLine();
		}
		if (choice.equals("0")) {
			choice = "cancel";
		}

		return choice;

	}

	public String increaseDieValue(String commandZone) {
		System.out.println("Do you want to use some servants to increment the die value?");
		System.out.println("Write the number of servants you want to use : ");
		String choice;
		do {

			choice = scanner.nextLine();

			try {
				Integer.parseInt(choice);
			} catch (NumberFormatException e) {
				System.out.println("Please type a number:");
				choice = null;
			}
		} while (choice == null);

		return commandZone + " " + choice;
	}

	// TODO: controllare gli override
	@Override
	public void sendAction(String command) {
		if (myTurn) {
			actionDone = false;
			hm = new HashMap<>();
			hm.put("action", command);
			notifyMyObservers(hm);
			waitForActionDone();
		} else {
			System.out.println("Not your turn!");
		}
	}

	/**
	 * this method lets the user choose between two alternative costs. It contains a
	 * Military Point value because the alternative values are always associated
	 * with militaryPoints
	 */

	@Override
	public String chooseAlternativeCost(String request) {
		System.out.println("The card you picked has two different costs:");
		System.out.println(request);
		System.out.println("\nMake your choice (1/2):");
		String choice = scanner.nextLine();

		while (!(choice.equals("1") || choice.equals("2"))) {
			System.out.println("Wrong choice, try again");
			choice = scanner.nextLine();

		}

		return choice;

	}

	@Override
	public String chooseSale(String request) {

		System.out.println("The card you have chosen can give you two different sales on the next activities.");
		System.out.println("Here are the two costs\n" + request);
		System.out.println("Choose what you want (1/2)");

		String answer = "";

		answer = scanner.nextLine();

		while (!(answer.equals("1") || answer.equals("2"))) {
			System.out.println("Wrong choice, try again");
			answer = scanner.nextLine();
		}
		return answer;

	}

	@Override
	public String askForChooseNewCard(String request) {
		System.out.println("With the choise you have made you can pick another card!");
		String zone;
		String floor;
		if (request.equals("everyTower")) {
			System.out.println(towerTerritories);
			System.out.println(towerCharacters);
			System.out.println(towerBuildings);
			System.out.println(towerVentures);

			System.out.println("Write the tower you want to pick your card from");
			System.out.println("Territories/Characters/Buildings/Ventures");

			zone = scanner.nextLine();
			while (!(zone.equalsIgnoreCase("territories") || zone.equalsIgnoreCase("Buildings")
					|| zone.equalsIgnoreCase("Ventures") || zone.equalsIgnoreCase("Characters"))) {
				System.out.println("Wrong choice, try again");
				zone = scanner.nextLine();
			}

		} else {
			zone = request;
			if (zone.equalsIgnoreCase("territories"))
				System.out.println(towerTerritories);
			if (zone.equalsIgnoreCase("Characters"))
				System.out.println(towerCharacters);
			if (zone.equalsIgnoreCase("Buildings"))
				System.out.println(towerBuildings);
			if (zone.equalsIgnoreCase("Ventures"))
				System.out.println(towerVentures);
		}

		System.out.println("Write the floor you want to pick your card from");
		System.out.println("1/2/3/4 ----- \"null\" to ignore this effect");

		floor = scanner.nextLine();

		while (!(floor.equals("1") || floor.equals("2") || floor.equals("3") || floor.equals("4")
				|| floor.equals("null"))) {
			System.out.println("Wrong choice, try again");
			floor = scanner.nextLine();

		}

		String answer = zone + " " + floor;
		answer = increaseDieValue(answer);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + answer);

		return answer;
	}

	@Override
	public String askForCouncilPrivilege(String request) {

		StringTokenizer tokenizer = new StringTokenizer(request);
		int number = Integer.parseInt(tokenizer.nextToken());

		String answer = "";
		for (int i = 1; i <= number; i++) {
			System.out.println(request);
			System.out.println("Choice number " + (i) + " of " + number);
			String choice = "";
			try {
				choice = scanner.nextLine();

			} catch (IndexOutOfBoundsException e) {
				System.out.println(" AYAYAYAY");
			}
			while (!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")
					|| choice.equals("5")) || answer.contains(choice)) {
				System.out.println("Wrong choice, try again");

				choice = scanner.nextLine();
			}

			answer = answer + " " + choice;
		}

		return answer;
	}

	@Override
	public String askForExchange(String request) {

		System.out.println(request);

		String choice = scanner.nextLine();

		while (!(choice.equals("1") || choice.equals("2"))) {
			System.out.println("Wrong choice, try again");
			choice = scanner.nextLine();

		}
		return choice;

	}

	@Override
	public String askForServantsForHarvestOrProduction(String request) {
		System.out.println(request);
		String choice;
		do {
			choice = scanner.nextLine();

			try {
				Integer.parseInt(choice);
			} catch (NumberFormatException e) {
				System.out.println("Please type a number:");
				choice = null;
			}
		} while (choice == null);

		return choice;

	}

	@Override
	public void setMyTurn(String currentPlayer) {
		if (currentPlayer.equals(name)) {
			myTurn = true;

		} else {
			myTurn = false;
		}
		if (myTurn) {
			System.out.println("**********It's your turn!!!!**********");
		} else {
			System.out.println("**********Not your turn**********");
		}
	}

	@Override
	public void askForExcommunication() {
		System.out.println("Do you want to support the Vatican?(Y/N)");

		String answer = scanner.nextLine();

		while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
			answer = scanner.nextLine();
		}

		sendAnswerToVatican(answer);
	}

	@Override
	public void communicateActionDone() {
		synchronized (getWaitingForActionCompleted()) {
			setActionDone(true);
			getWaitingForActionCompleted().notify();
		}

	}

	@Override
	public void show(String message) {
		System.out.println(message);
	}

	@Override
	public <C> void update(C change) {
		// TODO: staccare la view dal observable
	}

}
