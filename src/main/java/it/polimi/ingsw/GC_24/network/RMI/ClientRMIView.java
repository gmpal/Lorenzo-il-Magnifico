package it.polimi.ingsw.GC_24.network.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import it.polimi.ingsw.GC_24.observers.MyObservable;
import it.polimi.ingsw.GC_24.observers.MyObserver;
import it.polimi.ingsw.GC_24.view.View;

public class ClientRMIView extends MyObservable implements ClientViewRemote, MyObserver {

	private View view;
	private ServerViewRemote serverStub;

	public ClientRMIView(ServerViewRemote serverStub, View view) {
		this.view = view;
		this.serverStub = serverStub;
	}

	@Override
	public void show(String message) throws RemoteException {
		
		view.show(message);

	}

	@Override
	public void signalCompletedAction() throws RemoteException {
		
		view.communicateActionDone();

	}

	@Override
	public void startPlaying() throws RemoteException {
		
		new Thread(new Runnable() {
			public void run() {
				view.play();
			}
		}).start();
	}

	@Override
	public void updateTurn(ArrayList<String> turn) throws RemoteException {
		
		view.updateTurn(turn);
	}

	@Override
	public void updateCurrentPlayerAndSetMyTurn(String currentPlayer) throws RemoteException {
		view.setMyTurn(currentPlayer);
	}

	@Override
	public String chooseAlternativeCost(String request) throws RemoteException {
		String response = view.chooseAlternativeCost(request);
		return response;
	}

	@Override
	public String chooseAlternativeSale(String increase) throws RemoteException {
		String response = view.chooseSale(increase);
		return response;
	}

	@Override
	public String askForCouncilPrivilege(String request) {
		String response = view.askForCouncilPrivilege(request);
		return response;
	}

	@Override
	public String askForExchange(String request) {
		String response = view.askForExchange(request);
		return response;
	}

	@Override
	public String askForServantsForHarvestOrProduction(String request) {
		String response = view.askForServantsForHarvestOrProduction(request);
		return response;
	}

	@Override
	public String askForChooseNewCard(String request) {
		String response = view.askForChooseNewCard(request);
		return response;
	}

	@Override
	public String askForVatican() {
		String response = view.askForExcommunication();
		return response;
	}

	@Override
	public void parseBoardInformations(String[] boardInformations) throws RemoteException {
		view.parseBoardInformation(boardInformations);

	}

	@Override
	public void parsePersonalInformations(String[] personalInformations) throws RemoteException {
		view.parsePersonalInformations(personalInformations);
	}

	@Override
	public void updateUrlPersonalBoard(ArrayList<String> urlPersonalBoard) throws RemoteException {
		view.setUrlPersonalBoard(urlPersonalBoard);
	}
	@Override
	public void ping() throws RemoteException {
		//to see if player is connected
		
	}
	
	@Override
	public void updateUrlBoard(ArrayList<String> urlBoard) throws RemoteException {
		view.setUrlBoard(urlBoard);
	}

	@Override
	public void updateUrlExcommunication(ArrayList<String> urlExcommunication) throws RemoteException {
		view.setUrlExcommunication(urlExcommunication);

	}
	
	@Override
	public void updateUrlColour(ArrayList<String> urlColour) throws RemoteException {
		view.setUrlColour(urlColour);
		
	}
	
	@Override
	public String getPlayerName() throws RemoteException {
		return view.getName();
	}

	@Override
	public void updateRankings(String rankings) throws RemoteException {
		view.setRankings(rankings);
	}

	@Override
	public <C> void update(C change) {
		HashMap<String, Object> request = (HashMap<String, Object>) change;
		try {
			handleRequestFromClient(request);
		} catch (RemoteException e) {
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

		if (command.contains("leader")) {
			String leaderRequest = (String) request.get("leader");
			serverStub.sendLeader(leaderRequest);
		}

		if (command.contains("answerForVatican")) {
			String playerRequest = (String) request.get("answerForVatican");
			serverStub.sendAnswerVatican(playerRequest);
		}
	}






}
