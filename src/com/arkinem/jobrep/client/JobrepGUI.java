package com.arkinem.jobrep.client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
	int questionIndex = 0;
	private List<AnswerPanel> answerPanels = new ArrayList<AnswerPanel>();

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(startButton)) {
			startScreen.setVisible(false);

			questionLabel.setText(questionnaire.getQuestion(0));
			questionsScreen.add(questionLabel);
			questionsScreen.setVisible(true);
			getContentPane().add(questionsScreen);
			questionsScreen.repaint();

			List<Answer> answers = questionnaire.getOptions(0);

			repaint();
			for (int j = 0; j < answers.size(); j++) {
				Answer answer = answers.get(j);
				AnswerPanel answerPanel = new AnswerPanel(answer.getId(), answer.getAnswerText());
				questionsScreen.add(answerPanel);
				answerPanels.add(answerPanel);
				Dimension panelSize = answerPanel.getSize();
				answerPanel.setBounds(25, j * (panelSize.height + 5) + 90, panelSize.width, panelSize.height);
				answerPanel.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent me) {
						AnswerPanel source = (AnswerPanel) me.getSource();

						questionnaire.submitAnswer(source.getAnswerId());

						if (questionnaire.numberOfQuestions() > questionIndex + 1) {
							questionIndex++;
							test();
						} else {
							questionsScreen.setVisible(false);
							startScreen.setVisible(true);
							questionIndex = 0;
							for (AnswerPanel panel : answerPanels) {
								questionsScreen.remove(panel);
							}
							answerPanels = new ArrayList<AnswerPanel>();
							repaint();
						}

					}
				});

			}

		}

		if (e.getSource().equals(adminButton)) {

		}
	}

	private void test() {
		questionLabel.setText(questionnaire.getQuestion(questionIndex));

		List<Answer> answers = questionnaire.getOptions(questionIndex);

		for (int i = 0; i < answers.size(); i++) {
			Answer answer = answers.get(i);
			AnswerPanel answerPanel = answerPanels.get(i);
			answerPanel.setText(answer.getAnswerText());
			answerPanel.setAnswerId(answer.getId());
			answerPanel.repaint();
		}

		repaint();
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
		JobrepGUI gui = new JobrepGUI();
		gui.setVisible(true);
	}

}