package com.arkinem.jobrep.components;

import java.awt.GridBagLayout;
import java.util.UUID;

import javax.swing.JPanel;

import com.arkinem.jobrep.client.Constants;

/**
 * Reusable AnswerPanel component.
 * Represents single container for the answer.
 * @author Blazej Golinski
 *
 */
public class AnswerPanel extends JPanel {
	private static final long serialVersionUID = 7742259331614016705L;
	private AnswerLabel answerText = new AnswerLabel();
	private UUID answerId;

	/**
	 * sets initial styles
	 */
	public AnswerPanel() {
		setStyle();
	}
	
	/**
	 * sets initial styles and answer id
	 * @param answerId answer id
	 */
	public AnswerPanel(UUID answerId) {
		this.answerId = answerId;
		setStyle();
	}
	
	/**
	 * sets initial styles and answer id as well as label text
	 * @param answerId answer id
	 * @param text label text
	 */
	public AnswerPanel(UUID answerId, String text) {
		this.answerId = answerId;
		answerText.setText(text);
		setStyle();
	}

	/**
	 * sets label text
	 * @param text label text
	 */
	public void setText(String text) {
		answerText.setText(text);
	}

	/**
	 * sets answer id
	 * @param id answer id
	 */
	public void setAnswerId(UUID id) {
		answerId = id;
	}

	/**
	 * what is answer id?
	 * @return answer id
	 */
	public UUID getAnswerId() {
		return answerId;
	}

	/**
	 * sets initial styles
	 */
	private void setStyle() {
		setBackground(Constants.pressedSecondaryColor);
		setSize(650, 60);
		setLayout(new GridBagLayout());
		add(answerText);
	}

}