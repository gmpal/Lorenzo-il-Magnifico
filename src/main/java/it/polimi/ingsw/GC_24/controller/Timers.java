package it.polimi.ingsw.GC_24.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains timers to start the game when there are already more than
 * two players and to disconnect player from the game when it doesn't do
 * anything.
 */
public class Timers {
	private int timeToStartGame;
	private int timeToDisconnectPlayer;
	private List<String> listFileName = new ArrayList<>();

	public Timers() {
		this.timeToStartGame = 0;
		this.timeToDisconnectPlayer = 0;
		this.listFileName.add("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/timerToStart.json");
		this.listFileName.add("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/timerToDisconnectPlayer.json");
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
			System.out.println("There is a problem with the configuration file");
		}
		return time;
	}

	/**
	 * This method set time to start game and time to disconnect player both
	 * using the method takeTimer().
	 */
	public void setTimes() {
		timeToStartGame = takeTimer(listFileName.get(0));
		timeToDisconnectPlayer = takeTimer(listFileName.get(1));
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
