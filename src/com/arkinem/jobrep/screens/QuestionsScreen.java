package com.arkinem.jobrep.screens;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.arkinem.jobrep.client.QuestionSet;
import com.arkinem.jobrep.components.AnswerPanel;
import com.arkinem.jobrep.components.QuestionLabel;
import com.arkinem.jobrep.rmiinterface.Answer;

public class QuestionsScreen extends BaseScreen {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9146101605724855605L;
	private QuestionLabel questionLabel = new QuestionLabel();
	private QuestionSet questionnaire = new QuestionSet();
	private List<AnswerPanel> answerPanels = new ArrayList<AnswerPanel>();
	private int questionIndex = 0;

	public QuestionsScreen(JPanel container) {
		add(questionLabel);
		for (int i = 0; i < 5; i++) {
			AnswerPanel answerPanel = new AnswerPanel();
			add(answerPanel);
			answerPanels.add(answerPanel);

			Dimension panelSize = answerPanel.getSize();
			answerPanel.setBounds(25, i * (panelSize.height + 5) + 90, panelSize.width, panelSize.height);

			answerPanel.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent me) {
					AnswerPanel source = (AnswerPanel) me.getSource();

					questionnaire.submitAnswer(source.getAnswerId());

					if (questionnaire.numberOfQuestions() > questionIndex + 1) {
						questionIndex++;
					} else {
						questionIndex = 0;
						CardLayout layout = (CardLayout) container.getLayout();
						layout.first(container);
					}
					
					renderQuestion();
					repaint();

				}
			});

		}

		renderQuestion();
	}

	private void renderQuestion() {
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
}
