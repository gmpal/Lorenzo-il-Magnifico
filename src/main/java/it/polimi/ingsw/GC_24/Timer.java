package it.polimi.ingsw.GC_24;

/*Semplice timer che usa thread.sleep();
 *fornisce due metodi, uno che stampa il tempo e uno che non lo stampa
 *entrambi prendono in ingresso un intero (i secondi di attesa) e una condizione che interrompe il timer **/
public class Timer {
	private static Thread thread = new Thread();

	public static void startTimer(int seconds) throws InterruptedException {
		for (int i = seconds; i >= 0; i--) {
			thread.sleep(1000);
			System.out.println(i);
		}
	}


	public static void startTimerNoPrint(int seconds) throws InterruptedException {
		for (int i = seconds; i >= 0; i--) {
			thread.sleep(1000);
		}

	}


}
