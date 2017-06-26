package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import it.polimi.ingsw.GC_24.client.view.ViewCLI;
import it.polimi.ingsw.GC_24.client.view.ViewGUI;

public class Client {


	private ViewCLI viewCLI;
	private ViewGUI viewGUI;

	public void main(String[] args) throws IOException {
		
		Client client = new Client();
//		client.chooseInterface();
			

	}


	/* Shows an Option Dialog that lets the user choose between CLI and GUI */
/*	public String chooseInterface() {

		String[] array = { "GUI", "CLI" };


		int choice = JOptionPane.showOptionDialog(null, "GUI or CLI?", "Choose an option", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, array, null);

		if (choice == 0) {
			viewGUI = new ViewGUI();
			viewGUI.start();
			
		} else {
			viewCLI = new ViewCLI();
			viewCLI.();
			
		}
		
		return array[choice];
		
	}

	public int chooseNetwork() {

		String[] array = { "RMI", "SOCKET" };

		int choice = JOptionPane.showOptionDialog(null, "RMI or SOCKET", "Choose an option", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, array, null);

		if (choice == 0) {
			clientSocket = new ClientSocket();
			clientSocket.startClient();
		} else {
			clientRMI = new ClientRMI();
			clientRMI.startClient();
		}

		return choice;
	}

*/
	
}

