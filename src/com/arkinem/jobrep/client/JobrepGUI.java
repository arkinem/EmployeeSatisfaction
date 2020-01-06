package com.arkinem.jobrep.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.*;

public class JobrepGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 668891012501699891L;
	private QuestionsScreen questionsScreen;
	private StartScreen startScreen;
	private JPanel panels;

	public void init(JFrame frame) {
		setSize(700, 480);
		frame.setSize(700, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// center window
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().add(setUpPanels(), BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	private JPanel setUpPanels() {
		CardLayout layout = new CardLayout();
		panels = new JPanel(layout);
		
		startScreen = new StartScreen(panels);
		questionsScreen = new QuestionsScreen(panels);
	
		panels.add(startScreen);
		panels.add(questionsScreen);
		
		return panels;
	}

	private JobrepGUI() {
		super();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Employee satisfaction");
		JobrepGUI changer = new JobrepGUI();
		changer.init(frame);
	}
}