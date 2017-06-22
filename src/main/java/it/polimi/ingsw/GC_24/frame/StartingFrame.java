package it.polimi.ingsw.GC_24.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartingFrame extends JFrame {
	
	private JRadioButton buttonSocket;
	private JRadioButton buttonRMI;
	private JRadioButton buttonCLI;
	private JRadioButton buttonGUI;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JSplitPane splitPane;
	
	private Image board = Toolkit.getDefaultToolkit().getImage("gameboard.jpeg");
	
	public StartingFrame(String title){
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("Select the connection");
		buttonSocket = new JRadioButton("SOCKET");
		buttonRMI = new JRadioButton("RMI");		
		JLabel label2 = new JLabel("Select the interface");
		buttonCLI = new JRadioButton("CLI");
		buttonGUI = new JRadioButton("GUI");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, panel1, panel3);
	
		buttonSocket.addActionListener(new Connection());
		buttonRMI.addActionListener(new Connection());
		buttonCLI.addActionListener(new Interface());
		buttonGUI.addActionListener(new Interface());
		
		//Layout
		setLayout(new BorderLayout());
		getContentPane().add(splitPane);
		
		panel1.setLayout(new BorderLayout());
		panel3.setLayout(new BorderLayout());
		panel2.setLayout(new FlowLayout());
		panel4.setLayout(new FlowLayout());
		panel2.add(buttonSocket);
		panel2.add(buttonRMI);
		panel4.add(buttonCLI);
		panel4.add(buttonGUI);
		label.setHorizontalAlignment(JLabel.CENTER);
		label2.setHorizontalAlignment(JLabel.CENTER);
		panel1.add(label, BorderLayout.CENTER);
		panel3.add(label2, BorderLayout.CENTER);
		panel1.add(panel2, BorderLayout.SOUTH);
		panel3.add(panel4, BorderLayout.SOUTH);
		
		pack();
		setSize(640, 480);
		splitPane.setDividerLocation(200);
		setVisible(true);
	}
	
	//Connection's radiobuttons listener
	class Connection implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			buttonSocket.isSelected();
		}
	}
	// Interface's radiobuttons listener
	class Interface implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			buttonCLI.isSelected();
		}
	}
	
	private void loadImage (Image image){
		try {
			MediaTracker track = new MediaTracker (this);
			track.addImage(image,0);
			track.waitForID(0);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponents(Graphics graphic) {
		super.paintComponents(graphic);
		loadImage(board);
		graphic.drawImage(board,0,0,getWidth(),getHeight(),this);
	}
}


