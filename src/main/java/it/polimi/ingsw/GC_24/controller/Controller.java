package it.polimi.ingsw.GC_24.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.cards.Ventures;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.State;
import it.polimi.ingsw.GC_24.places.TowerPlace;
import it.polimi.ingsw.GC_24.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.network.multi.Server;

//Just one server's side controller for each game
public class Controller extends MyObservable implements MyObserver, Runnable {

	private final Model game;
	private ActionFactory actionFactory;
	private SetOfValues tempCost = null;
	private Action action;
	private HashMap<String, Object> hashMap;
	private int controllerNumber = 0;
	private HashMap<String,Object> hm;
	private List<Player> councilTurnArray;
	private List<Player> playerTurn;
	private Player currentPlayer;
	private int currentIndex = 0;
	
	// constructor

	public Controller(Model game) {

		this.game = game;
		controllerNumber++;
	}

	@Override
	public void run() {
		try {
			game.setModel(game.getPlayers());
		} catch (IOException e) {
			// TODO Auto-generated catch block ---------> da rivedere
			e.printStackTrace();
		}
		sendModelToClients();
		this.currentPlayer = game.getCurrentPlayer();
		while (this.currentPlayer.equals(game.getCurrentPlayer())){ wait();}
			
		playerTurn = game.getPlayers();
			for(int i=0; i<playerTurn.size(); i++){
			
		
		councilTurnArray = game.getBoard().getCouncilPalace().getTemporaryTurn();
		playerTurn = game.getPlayers();
		
		game.setCurrentPlayer(playerTurn.get(i));
		sendTurnArray(playerTurn);
		letThemPlay();
		
	}
		
	
	
	//update the turn list from the councilPalace
	public void updateListOfPlayerTurn(List<Player> temporaryTurn){
		int i;
		for (Player player:temporaryTurn){
			if (playerTurn.contains(player)){
				playerTurn.remove(player);
			}
			i = temporaryTurn.indexOf(player);
			playerTurn.add(i, player);
		}
	}
	
	private void letThemPlay() {
		hm = new HashMap<>();
		hm.put("Play!",null);
		notifyMyObservers(hm);
		
	}

	private void sendTurnArray(List<Player> turnArray) {
		hm = new HashMap<>();
		hm.put("Turns",turnArray);
		notifyMyObservers(hm);
	}

	@Override
	public void update() {
	}

	@Override
	public <C> void update(MyObservable o, C change) {

		System.out.println("Controller: I have been notified by " + o.getClass().getSimpleName());
		System.out.println("Controller: i received this :" + change);

		try {
			String answer = handleRequestFromClient(o, (Map<String, Object>) change);
			System.out.println("--------------" + answer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * This method analyzes the incoming HashMap. If it finds specific keywords
	 * in the keySet, it does different things with different objects
	 * 
	 * @throws IOException
	 */
	private String handleRequestFromClient(MyObservable o, Map<String, Object> request) throws IOException {
	
		System.out.println("Controller: started handling a request from client...");
		Set<String> command = request.keySet();
		System.out.println(command);

		if (command.contains("player")) {
			System.out.println("-------------------------------->RECEIVED A FREAKING PLAYER");
			String playerString = (String) request.get("player");
			System.out.println(playerString);
			StringTokenizer tokenizer = new StringTokenizer(playerString);
			String clientNumber = tokenizer.nextToken();
			String name = tokenizer.nextToken();
			String colour = tokenizer.nextToken();
			int indexOfPlayer = Integer.parseInt(clientNumber)-1;	
			System.out.println("Lookin' for player n "+indexOfPlayer);
			Player tempPlayer = game.getPlayers().get(indexOfPlayer);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+tempPlayer);
					tempPlayer.setPlayer(name, PlayerColour.valueOf(colour.toUpperCase()));
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+tempPlayer);
			System.out.println("CONTROLLER GAME: "+game.getPlayers());
			game.sendModel();
			return "player "+clientNumber+" updated";
	
		}	
		

		else if (command.contains("colours")) {
			return handleColours(o, request);
		}

		else if (command.contains("chosenCost")) {
			this.tempCost = (SetOfValues) request.get("chosenCost");
			return "Controller: chosen cost updated";

		}

		else if (command.contains("action")) {

			return handleAction(o, request);
		}

		/* Checks if the colour has already been chosen */
		else if (command.contains("checkColour")) {
			return checkColour(o, request);
		}

		else {
			return "bad command";
		}

	}

	private String checkColour(MyObservable o, Map<String, Object> request) {
		System.out.println("Controller: started checking colour!");
		String colour = (String) request.get("checkColour");
		String availability;
		if (PlayerColour.checkValue(colour)) {
			availability = "Colour Available";
			PlayerColour.removeValue(colour);
		} else {
			availability = "Colour Not Available";
		}
		hashMap = new HashMap<>();
		hashMap.put("coloursAnswer", availability);
		this.notifySingleObserver((MyObserver) o, hashMap);
		return "Controller: Colour checked";
	}

	private String handleColours(MyObservable o, Map<String, Object> request) {
		System.out.println("Controller: started handling colours");
		List<String> playerColoursArray = PlayerColour.getValues();
		HashMap<String, Object> coloursMap = new HashMap<String, Object>();
		coloursMap.put("colours", playerColoursArray);
		this.notifySingleObserver((MyObserver) o, coloursMap);
		System.out.println("ServerOut: ArrayListOfColours sent");
		return " ArrayListOfColours sent";
	}



	private Player createPlayerFromString(String playerString) {
		StringTokenizer tokenizer = new StringTokenizer(playerString);
		String name = tokenizer.nextToken();
		String colour = tokenizer.nextToken();
		Player player = new Player();
		player.setPlayer(name, PlayerColour.valueOf(colour.toUpperCase()));
		notifyAll();
		return player;

	}

	public void sendModelToClients() {
		System.out.println("Sono il controller numero" + this.getControllerNumber());
		System.out.println("Sto inviando il game numero" + game.getModelNumber());
		game.setGameState(State.ENDED);
		hashMap = new HashMap<>();
		hashMap.put("model", game);
		notifyMyObservers(hashMap);
	}

	public int getControllerNumber() {
		return controllerNumber;
	}

	public void setControllerNumber(int controllerNumber) {
		this.controllerNumber = controllerNumber;
	}

	private String handleAction(MyObservable o, Map<String, Object> request) {
		System.out.println("Controller: started handling action");
		StringTokenizer tokenizer = new StringTokenizer((String) request.get("action"));

		String tempFamiliar = tokenizer.nextToken();
		String tempZone = tokenizer.nextToken();
		String tempFloor = tokenizer.nextToken();
		String tempServants = tokenizer.nextToken();

		if (tempZone.equalsIgnoreCase("ventures")) {

			handleVentures(o, request, tempZone, tempFloor);
		}

		this.action = actionFactory.makeAction(game, tempFamiliar, tempZone, tempFloor, tempServants, tempCost);

		if (action.verify().equals("ok")) {

			List<ImmediateEffect> interactiveEffects = action.run();
			if (!interactiveEffects.isEmpty()) {
				// TODO: interagisci con l'utente per prenderti i parametri
				// che ti servono
			}

		} else {
			// TODO: azione non valida

		}
		
		turnManager();
	}

	private void turnManager() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * If the player wants to take a ventures card, this method let him choose
	 * which one of the double costs to take (if a double cost exists)
	 */
	private void handleVentures(MyObservable o, Map<String, Object> request, String tempZone, String tempFloor) {
		TowerPlace placeRequested = (TowerPlace) this.game.getBoard().getZoneFromString(tempZone)
				.getPlaceFromStringOrFirstIfZero(tempFloor);
		Ventures cardRequested = (Ventures) placeRequested.getCorrespondingCard();
		SetOfValues cost1 = cardRequested.getCost();
		SetOfValues cost2 = cardRequested.getAlternativeCost();
		if (cost2 != null) {
			MilitaryPoint requirements = cardRequested.getRequiredMilitaryPoints();
			hashMap = new HashMap<>();
			hashMap.put("Cost1", cost1);
			hashMap.put("Cost2", cost2);
			hashMap.put("Requirements", requirements);
			this.notifySingleObserver((MyObserver) o, hashMap);
			while (this.tempCost == null) {
			}
		}

	}

	// game's getter
	public Model getGame() {
		return game;
	}

	
}
