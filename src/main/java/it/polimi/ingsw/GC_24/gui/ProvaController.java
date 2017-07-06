package it.polimi.ingsw.GC_24.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ProvaController {

	@FXML
	private Button button1;
	
	
	
	public void pressButton( ActionEvent event) {
		System.out.println("Prova");
	}
}
