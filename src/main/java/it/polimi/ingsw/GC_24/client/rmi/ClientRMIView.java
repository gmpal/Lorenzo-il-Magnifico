package it.polimi.ingsw.GC_24.client.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.client.view.ViewInterface;
import it.polimi.ingsw.GC_24.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class ClientRMIView extends MyObservable implements ClientViewRemote, Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2902379951860570079L;
	private ViewInterface view;
	private ServerViewRemote serverStub;

	public ClientRMIView(ServerViewRemote serverStub, ViewInterface view) throws RemoteException {
		this.view = view;
		this.serverStub = serverStub;
		}



	@Override
	public void show(String message) throws RemoteException {
		System.out.println("RMI Client View -->");
		view.show(message);
		
	}


	@Override
	public void updateModelAndRelatedFields(Model model) throws RemoteException {
		System.out.println("RMI Client View --> updateModelAndRelatedFields ");
		view.getInformationForReceivedModel(model);
		
	}


	@Override
	public void signalCompletedAction() throws RemoteException {
		System.out.println("RMI Client View --> signalCompletedAction ");
		view.communicateActionDone();
		
	}

	@Override
	public void startPlaying() throws RemoteException {
		System.out.println("RMI Client View --> startPlaying ");
		view.play();
	}

	@Override
	public void updateTurn(ArrayList<Player> turn) throws RemoteException {
		System.out.println("RMI Client View --> updateTurn ");
		view.updateTurn(turn);
	}

	@Override
	public void updateCurrentPlayerAndSetMyTurn(Player currentPlayer) throws RemoteException {
		System.out.println("RMI Client View --> updateCurrentPlayerAndSetMyTurn");
		view.setMyTurn(currentPlayer);
	}

	@Override
	public void setPlayerNumber(int playerNumber, int modelNumber) throws RemoteException {
		System.out.println("RMI Client View --> setPlayerNumber ");
		view.updatePlayerNumber(playerNumber, modelNumber);
		
	}



	@Override
	public String chooseAlternativeCost(String request) throws RemoteException {
		System.out.println("RMI Client View --> ChooseAlternativeCost ");
		String response = view.chooseAlternativeCost(request);
		return response;
	}



	@Override
	public SetOfValues chooseAlternativeSale(IncreaseDieValueCard increase) throws RemoteException {
		System.out.println("RMI Client View --> ChooseAlternativeSale ");
		SetOfValues response = view.chooseSale(increase);
		return response;
	}



	@Override
	public String askForCouncilPrivilege(String request) {
		System.out.println("RMI Client View --> askForCouncilPrivilege ");
		String response = view.askForCouncilPrivilege(request);
		return response;
	}



	@Override
	public String askForExchange(String request) {
		System.out.println("RMI Client View --> askForExchange ");
		String response = view.askForExchange(request);
		return response;
	}



	@Override
	public String askForServantsForHarvestOrProduction(String request) {
		System.out.println("RMI Client View --> askForServantsForHarvestOrProduction ");
		String response = view.askForServantsForHarvestOrProduction(request);
		return response;
	}



	@Override
	public String askForChooseNewCard(String request) {
		System.out.println("RMI Client View --> askForChooseNewCard ");
		String response = view.askForChooseNewCard(request);
		return response;
	}



	@Override
	public String getPlayerName() throws RemoteException {
		return view.getName();
	}



	@Override
	public <C> void update(C change) {
		HashMap<String,Object> request = (HashMap<String,Object>) change;
		try {
			handleRequestFromClient(request);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}



	public void handleRequestFromClient(HashMap<String, Object> request) throws RemoteException {
		Set<String> command = request.keySet();
		 	if (command.contains("action")) {
		 		String actionRequest = (String) request.get("action");
		 		serverStub.sendAction(actionRequest);
		 	}
		 	
			if (command.contains("player")) {
		 		String playerRequest = (String) request.get("player");
		 		serverStub.sendPlayerString(playerRequest);
		 	}
			
			if (command.contains("player")) {
		 		String playerRequest = (String) request.get("player");
		 		serverStub.sendPlayerString(playerRequest);
		 	}
		
	}

	

}
