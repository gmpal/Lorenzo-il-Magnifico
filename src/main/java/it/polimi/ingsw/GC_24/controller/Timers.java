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
	private static int timeToStartGame;
	private static int timeToDisconnectPlayer;
	
	public Timers() {
		Timers.timeToStartGame = takeTimer("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/timerToStart.json");
		Timers.timeToDisconnectPlayer = takeTimer("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/timerToDisconnectPlayer.json");		
	}

	/**
	 * This method takes the times from configuration files named
	 * timerToStart.json and timerToDisconnectPlayer.json.
	 * 
	 * @param String
	 *            fileName
	 * @return int time
	 */
	public static int takeTimer(String fileName) {
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

	// getters and setters
	public static int getTimeToStartGame() {
		return timeToStartGame;
	}

	public static void setTimeToStartGame(int timeToStartGame) {
		Timers.timeToStartGame = timeToStartGame;
	}

	public static void setTimeToDisconnectPlayer(int timeToDisconnectPlayer) {
		Timers.timeToDisconnectPlayer = timeToDisconnectPlayer;
	}

	public static int getTimeToDisconnectPlayer() {
		return timeToDisconnectPlayer;
	}

}
