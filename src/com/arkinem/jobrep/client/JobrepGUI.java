package com.arkinem.jobrep.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.*;

import com.arkinem.jobrep.screens.AdminScreen;
import com.arkinem.jobrep.screens.PasswordScreen;
import com.arkinem.jobrep.screens.QuestionsScreen;
import com.arkinem.jobrep.screens.ResultsScreen;
import com.arkinem.jobrep.screens.StartScreen;

public class JobrepGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 668891012501699891L;

	public void init(JFrame frame) {
		frame.setSize(700, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);	// center window
		frame.setResizable(false);
		frame.getContentPane().add(setUpPanels(), BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	private JPanel setUpPanels() {
		CardLayout layout = new CardLayout();
		JPanel panels = new JPanel(layout);
		
		StartScreen startScreen = new StartScreen(panels);
		QuestionsScreen questionsScreen = new QuestionsScreen(panels);
		PasswordScreen passwordScreen = new PasswordScreen(panels);
		AdminScreen adminScreen = new AdminScreen(panels);
		ResultsScreen resultsScreen = new ResultsScreen(panels);
	
		panels.add(startScreen, "startScreen");
		panels.add(questionsScreen, "questionsScreen");
		panels.add(passwordScreen, "passwordScreen");
		panels.add(adminScreen, "adminScreen");
		panels.add(resultsScreen, "resultsScreen");
		
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