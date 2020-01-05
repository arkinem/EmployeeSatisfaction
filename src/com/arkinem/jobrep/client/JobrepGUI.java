package com.arkinem.jobrep.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.arkinem.jobrep.components.SecondaryButton;
import com.arkinem.jobrep.components.BaseScreen;
import com.arkinem.jobrep.components.HeaderLabel;
import com.arkinem.jobrep.components.PrimaryButton;


public class JobrepGUI extends JFrame implements ActionListener  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 668891012501699891L;
	
	PrimaryButton startButton = new PrimaryButton("Start Questionnaire");	
	SecondaryButton adminButton = new SecondaryButton("Admin");
	HeaderLabel headerLabel = new HeaderLabel("Employee satisfaction");
	BaseScreen startScreen = new BaseScreen();
	BaseScreen questionsScreen = new BaseScreen();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(startButton)) { 
//			startScreen.setVisible(false);
//			questionsScreen.setVisible(true);
//			questionsScreen.setBackground(Color.pink);
//			getContentPane().add(questionsScreen);
//			questionsScreen.repaint();
		}
		
		if(e.getSource().equals(adminButton)) { 
		
		}
	}
	
	private JobrepGUI() {
		super();
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		//center window
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Employee satisfaction");
		
	
		
		startButton.setBounds(250, 180, 200, 100);
		startButton.setFont(new Font("Roboto", Font.BOLD, 18));
		startButton.addActionListener(this); 
		
		adminButton.setBounds(600, 435, 80, 20);
		adminButton.addActionListener(this); 
		
		startScreen.add(headerLabel);
		startScreen.add(startButton); 
		startScreen.add(adminButton); 		
				
		getContentPane().add(startScreen);
		startScreen.repaint();
	}

	public static void main(String [] args ) { 
//		QuestionSet questionnaire = new QuestionSet(); 
//		int number = questionnaire.numberOfQuestions();
//		System.out.println(number);
//		
//		long prevTime = System.currentTimeMillis();
//		for (int i = 0; i < number; i++) {
//String question = questionnaire.getQuestion(i);
//			System.out.println(question + " " + (System.currentTimeMillis() - prevTime) + "ms");
//			prevTime = System.currentTimeMillis();
//		}
		JobrepGUI gui = new JobrepGUI();
		gui.setVisible(true);
	}



}