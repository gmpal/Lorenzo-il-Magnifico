package it.polimi.ingsw.GC_24.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;

//Just one server's side controller for each game
public class Controller extends MyObservable implements MyObserver {

	private final Model game;
	private ActionFactory actionFactory;
	// constructor

	public Controller(Model game) {

		this.game = game;

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

	// IN CHE MODO LA questa VIEW GESTISCE CIO' CHE RICEVE?
	// è davvero lei che gestisce o si limita ad inoltrare al controller?
	// oppure --> in base al tipo di richiesta decide se inoltrare oppure no

	/**
	 * This method analyzes the incoming HashMap. If it finds specific keywords
	 * in the keySet, it does different things with different objects
	 * 
	 * @throws IOException
	 */
	private String handleRequestFromClient(MyObservable o, Map<String, Object> request) throws IOException {
		System.out.println("Controller: handling the request...");
		Set<String> command = request.keySet();
		System.out.println(command);

		if (command.contains("PLAYERNAME")) {
			StringTokenizer tokenizer = new StringTokenizer((String) request.get("PLAYERNAME"));
			String name = tokenizer.nextToken();
			String colour = tokenizer.nextToken();
			
			Player player = new Player(name, PlayerColour.valueOf(colour.toUpperCase()));

			return player.toString();
		}

		else if (command.contains("colours")) {
			List<String> playerColoursArray = PlayerColour.getValues();
			HashMap<String, Object> coloursMap = new HashMap<String, Object>();
			coloursMap.put("colours", playerColoursArray);
			this.notifySingleObserver((MyObserver) o, coloursMap);
			System.out.println("ServerOut: ArrayListOfColours sent");
			return " ArrayListOfColours sent";
		}
		
		else if (command.contains("place")) {
			StringTokenizer tokenizer = new StringTokenizer((String) request.get("action"));
			
			String tempFamiliar = tokenizer.nextToken();
			String tempZone = tokenizer.nextToken();
			String tempFloor = tokenizer.nextToken();
			String tempServants = tokenizer.nextToken();
			
			Action action = actionFactory.makeAction(game, tempFamiliar, tempZone, tempFloor, tempServants );
			
			if (action.verify()){
				
				List <ImmediateEffect> interactiveEffects = action.run();
				if (!interactiveEffects.isEmpty()){
					//TODO: interagisci con l'utente per prenderti i parametri che ti servono
				}
			
			}else
				{
				//TODO: azione non valida
			}
			
		//	HashMap<String, Object> coloursMap = new HashMap<String, Object>();
		//	coloursMap.put("colours", playerColoursArray);
		//	this.notifySingleObserver((MyObserver) o, coloursMap);

			
			return " sent";
		}
		else if (command.contains("checkColour")) {
			String colour = (String) request.get("checkColour");
			String availability;
			if (PlayerColour.checkValue(colour)) {
				// System.out.println("Sono entrato nel controllo del colore");
				availability = "Colour Available";
				PlayerColour.removeValue(colour);
			} else {
				// System.out.println("Sono uscito nel controllo del colore");
				availability = "Colour Not Available";

			}

			HashMap<String, Object> coloursAnswerMap = new HashMap<String, Object>();
			coloursAnswerMap.put("coloursAnswer", availability);
			// this.notifySingleObserver((MyObserver)observed, coloursAnswerMap
			// );
			this.notifySingleObserver((MyObserver) o, coloursAnswerMap);
			return "Colour checked";
		} else if (command.contains("player")) {
			StringTokenizer tokenizer = new StringTokenizer((String) request.get("player"));
			String name = tokenizer.nextToken();
			String colour = tokenizer.nextToken();
			Player player = new Player(name, PlayerColour.valueOf(colour.toUpperCase()));
			game.getPlayers().add(player);
			return colour.toUpperCase() + " player created";
		}

		else {
			return "bad command";
		}

	}

	// game's getter
	public Model getGame() {
		return game;
	}
}
