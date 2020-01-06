package com.arkinem.jobrep.components;

import java.awt.GridBagLayout;
import java.util.UUID;

import javax.swing.JPanel;

import com.arkinem.jobrep.client.Constants;

public class AnswerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7742259331614016705L;
	private AnswerLabel answerText = new AnswerLabel();
	private UUID answerId;

	public AnswerPanel() {
		setStyle();
	}
	
	public AnswerPanel(UUID answerId) {
		this.answerId = answerId;
		setStyle();
	}
	
	public AnswerPanel(UUID answerId, String text) {
		this.answerId = answerId;
		answerText.setText(text);
		setStyle();
	}

	public void setText(String text) {
		answerText.setText(text);
	}

	public void setAnswerId(UUID id) {
		answerId = id;
	}

	public UUID getAnswerId() {
		return answerId;
	}

	private void setStyle() {
		setBackground(Constants.pressedSecondaryColor);
		setSize(650, 60);
		setLayout(new GridBagLayout());
		add(answerText);
	}

}