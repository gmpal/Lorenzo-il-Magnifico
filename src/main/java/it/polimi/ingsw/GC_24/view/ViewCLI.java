package it.polimi.ingsw.GC_24.view;

import java.util.*;
import java.util.concurrent.TimeUnit;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.effects.*;
import it.polimi.ingsw.GC_24.model.values.*;

public class ViewCLI extends View {

	private static Scanner scanner = new Scanner(System.in);



	@Override
	public synchronized void run() {

		this.miniModel = new Model(0);

		// SLEEP FOR TWO SECONDS
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Thread.currentThread().interrupt();
		}
		name = setName();
		System.out.println("SetName superato");
		System.out.println(myself);
		if (myself.getMyName() == null) {
			System.out.println("ENTRATO NELL IF");

			this.sendPlayerString(name);
			System.out.println("NAME SENT");
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
			System.out.println("Nome letto");

		}
		return sc;

	}


	public void showTurnSituation() {
		System.out.println("The players' turn for this round is:");
		for (int i = 0, j = 1; i < miniModel.getPlayers().size(); i++, j++) {
			System.out.println(j + ") " + miniModel.getPlayers().get(i).getMyName() + " is the "
					+ miniModel.getPlayers().get(i).getMyColour() + " player \n");
		}
	}

	@Override
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

		System.out.println("Choose action:\n" + "a)Show board\n" + "b)Show personal board\n" + "c)Show family members\n"
				+ "d)Show my resources\n" + "e)Place family member\n" + "f)Activate a leader card\n"
				+ "g)Discard a leader card\n" + "h)End turn\n" + "i)Exit");

		String command = scanner.nextLine();


		if (command.equalsIgnoreCase("a")) {

			System.out.println(miniModel.getBoard());

		} else if (command.equalsIgnoreCase("b")) {

			System.out.println(myself.getMyBoard());

		} else if (command.equalsIgnoreCase("c")) {

			System.out.println(myself.getMyFamily());

		} else if (command.equalsIgnoreCase("d")) {

			System.out.println(myself.getMyValues());

		} else if (command.equalsIgnoreCase("e")) {

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

		} else if (command.equalsIgnoreCase("f")) {

			if (myTurn) {
				System.out.println(myself.getMyBoard().getPersonalLeader());
				command = "activate " + chooseLeader();
				if (command.contains("cancel")) {
					System.out.println("Action cancelled");
				} else {
					sendLeader(command);
				}

			} else {
				System.out.println("Not your turn. You can't do any action.\n");
			}

		} else if (command.equalsIgnoreCase("g")) {

			if (myTurn) {
				System.out.println(myself.getMyBoard().getPersonalLeader());
				command = "discard " + chooseLeader();
				if (command.contains("cancel")) {
					System.out.println("Action cancelled");
				} else {
					sendLeader(command);
				}

			} else {
				System.out.println("Not your turn. You can't do any action.\n");
			}

		} else if (command.equalsIgnoreCase("h")) {
			command = "end";
			System.out.println("This function is not been implemented yet");
			// TODO: gestione della fine del turno
		} else if (command.equalsIgnoreCase("i")) {
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
			commandZone = scanner.nextLine();
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
				System.out.println("My buildings:\n"+myself.getMyBoard().getPersonalBuildings().getCards());
				System.out.println(miniModel.getBoard().getProduction());
				commandZone = "production 0 ";

			} else if (commandZone.equalsIgnoreCase("g")) {
				System.out.println("My territories:\n"+myself.getMyBoard().getPersonalTerritories().getCards());
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

		} else {
			commandZone = increaseDieValue(commandZone);

		}
		return commandZone;
	}

	public String chooseLeader() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nChoose Leader Card (");


		String string = "0";


		for (int i = 1; i <= myself.getMyBoard().getPersonalLeader().size(); i++) {
			if (i == myself.getMyBoard().getPersonalLeader().size()) {
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

		while (!(string.contains(choice))) {
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
	public SetOfValues chooseSale(IncreaseDieValueCard increase) {

		SetOfValues finalIncrease;
		do {
			System.out.println();
			String answer = "";
			try {

				answer = scanner.nextLine();
			
			} catch (Exception e) {
				finalIncrease = null;
				answer = "";
			}
			if (answer.equals(1)) {
				finalIncrease = increase.getSale();
			} else if (answer.equals(1)) {
				finalIncrease = increase.getAlternativeSale();
			} else {
				System.out.println("Wrong number");
				finalIncrease = null;
			}
		} while (finalIncrease == null);

		return finalIncrease;

	}

	@Override
	public String askForChooseNewCard(String request) {
		System.out.println("With the choise you have made you can pick another card!");
		String zone;
		String floor;
		if (request.equals("everyTower")) {
			System.out.println(miniModel.getBoard().getTowerTerritories());
			System.out.println(miniModel.getBoard().getTowerCharacters());
			System.out.println(miniModel.getBoard().getTowerBuildings());
			System.out.println(miniModel.getBoard().getTowerVentures());

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
			System.out.println(miniModel.getBoard().getZoneFromString(zone));
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
	public void setMyTurn(Player currentPlayer) {
		if (currentPlayer.getPlayerNumber() == getMyself().getPlayerNumber()) {
			System.out.println("Player #" + getMyself().getPlayerNumber() + " turn is TRUE  ");
			myTurn = true;

		} else {
			System.out.println("Player #" + getMyself().getPlayerNumber() + " turn is FALSE  ");
			myTurn = false;
		}
		if (myTurn) {
			System.out.println("**********It's your turn!!!!**********");
		} else {
			System.out.println("**********Not your turn**********");
		}
	}

	@Override
	public void updatePlayerNumber(int playerNumber2, int modelNumber) {
		System.out.println("View CLI --> Ricevuto un player number");
		if (getPlayerNumber() == 0) {
			setPlayerNumber(playerNumber2);
			System.out.println("You are the player #" + playerNumber2 + ", connected to game #" + modelNumber);
		} else {
			System.out.println("View CLI --> Non era un player number per me");
		}
	}
	

	@Override
	public void askForExcommunication() {
		System.out.println("Do you want to support the Vatican?(Y/N)");

		String answer=scanner.nextLine();

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
	public void showToSinglePlayer(Player currentPlayer, String message) {
		if (currentPlayer.getMyName().equals(myself.getMyName())) {
			System.out.println(message);
		}

	}


	@Override
	public <C> void update(C change) {
		// TODO Auto-generated method stub

	}

	

}
