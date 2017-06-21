package it.polimi.ingsw.GC_24.frame;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame{
	
	private JSplitPane splitPanel;
	
	private Image board = Toolkit.getDefaultToolkit().getImage("gameboard.jpeg");
	
	public Frame(String title) {
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void paintComponent(Graphics graphic) {
		loadImage(board);
	}
}
