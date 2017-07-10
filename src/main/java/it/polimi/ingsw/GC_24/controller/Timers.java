package it.polimi.ingsw.GC_24.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class contains timers to start the game when there are already more than
 * two players and to disconnect player from the game when it doesn't do
 * anything.
 */
public class Timers {
	private int timeToStartGame;
	private int timeToDisconnectPlayer;
	
	public Timers() {
		this.timeToStartGame = takeTimer("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/timerToStart.json");
		this.timeToDisconnectPlayer = takeTimer("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/timerToDisconnectPlayer.json");		
	}

	/**
	 * This method takes the times from configuration files named
	 * timerToStart.json and timerToDisconnectPlayer.json.
	 * 
	 * @param String
	 *            fileName
	 * @return int time
	 */
	public int takeTimer(String fileName) {
		BufferedReader br;
		int time = 0;
		String line;
		try {
			br = new BufferedReader(new FileReader(fileName));

			while ((line = br.readLine()) != null) {
				time = Integer.parseInt(line);
			}
		} catch (IOException e) {
			;
		}
		return time;
	}

	// getters and setters
	public int getTimeToStartGame() {
		return timeToStartGame;
	}

	public void setTimeToStartGame(int timeToStartGame) {
		this.timeToStartGame = timeToStartGame;
	}

	public void setTimeToDisconnectPlayer(int timeToDisconnectPlayer) {
		this.timeToDisconnectPlayer = timeToDisconnectPlayer;
	}

	public int getTimeToDisconnectPlayer() {
		return timeToDisconnectPlayer;
	}

}
