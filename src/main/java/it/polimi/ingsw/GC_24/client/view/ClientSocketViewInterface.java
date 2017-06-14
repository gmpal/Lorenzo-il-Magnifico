package it.polimi.ingsw.GC_24.client.view;

import java.util.Map;

import it.polimi.ingsw.GC_24.MyObserver;

public interface ClientSocketViewInterface extends Runnable, MyObserver {
	public abstract void handleRequestFromServer(Map<String, Object> request);
}
