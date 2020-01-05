package com.arkinem.jobrep.client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

import com.arkinem.jobrep.components.SecondaryButton;
import com.arkinem.jobrep.rmiinterface.Answer;
import com.arkinem.jobrep.components.AnswerPanel;
import com.arkinem.jobrep.components.BaseScreen;
import com.arkinem.jobrep.components.HeaderLabel;
import com.arkinem.jobrep.components.PrimaryButton;
import com.arkinem.jobrep.components.QuestionLabel;

public class JobrepGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 668891012501699891L;

	private PrimaryButton startButton = new PrimaryButton("Start Questionnaire");
	private SecondaryButton adminButton = new SecondaryButton("Admin");
	private HeaderLabel headerLabel = new HeaderLabel("Employee satisfaction");
	private QuestionLabel questionLabel = new QuestionLabel();
	private BaseScreen startScreen = new BaseScreen();
	private BaseScreen questionsScreen = new BaseScreen();
	QuestionSet questionnaire = new QuestionSet();

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(startButton)) {
			startScreen.setVisible(false);
//			questionsScreen.setVisible(true);
//			questionsScreen.setBackground(Color.pink);
			getContentPane().add(questionsScreen);
			questionsScreen.repaint();

			int noOfQuestions = questionnaire.numberOfQuestions();

//			for (int i = 0; i < noOfQuestions; i++) {
			for (int i = 0; i < 1; i++) {
				questionLabel.setText(questionnaire.getQuestion(i));
				questionsScreen.add(questionLabel);

				List<Answer> answers = questionnaire.getOptions(i);

				for (int j = 0; j < answers.size(); j++) {
					Answer answer = answers.get(j);
					AnswerPanel answerPanel = new AnswerPanel(answer.getId(), answer.getAnswerText());
					Dimension panelSize = answerPanel.getSize();
					answerPanel.setBounds(25, j * (panelSize.height + 5) + 90, panelSize.width, panelSize.height);
					answerPanel.addMouseListener(new MouseAdapter() { 
				          public void mousePressed(MouseEvent me) {
				        	  AnswerPanel source =(AnswerPanel) me.getSource();
				        	  
				              System.out.println(source.getAnswerId()); 
				            } 
				          }); 
					questionsScreen.add(answerPanel);
				}
			}

		}

		if (e.getSource().equals(adminButton)) {

		}
	}

	private JobrepGUI() {
		super();
		setSize(700, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// center window
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Employee satisfaction");

		startButton.setBounds(250, 180, 200, 100);
		startButton.setFont(new Font("Roboto", Font.BOLD, 18));
		startButton.addActionListener(this);

		adminButton.setBounds(600, 415, 80, 20);
		adminButton.addActionListener(this);

		startScreen.add(headerLabel);
		startScreen.add(startButton);
		startScreen.add(adminButton);

		getContentPane().add(startScreen);
		startScreen.repaint();
	}

	public static void main(String[] args) {
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