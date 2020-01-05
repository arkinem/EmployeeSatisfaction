package com.arkinem.jobrep.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class JobrepGUI extends JFrame implements ActionListener  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 668891012501699891L;

	public static void main(String [] args ) { 
		QuestionSet questionnaire = new QuestionSet(); 
		int number = questionnaire.numberOfQuestions();
		System.out.println(number);
		
		for (int i = 0; i < number; i++) {
String question = questionnaire.getQuestion(i);
			System.out.println(question);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}