package com.arkinem.jobrep.components;

import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
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
	
	public AnswerPanel(UUID answerId, String text) {
		this.answerId = answerId;
		answerText.setText(text);
		setStyle();
	}
	
	public AnswerPanel(UUID answerId) {	
		this.answerId = answerId;
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
	
	public void removeMouseListeners() {
		MouseListener[] mouseListeners = getMouseListeners();
		for (MouseListener mouseListener : mouseListeners) {
			removeMouseListener(mouseListener);
		}
	}
	
	private void setStyle() {
		setBackground(Constants.pressedSecondaryColor);
		setSize(650, 60);
//		setLayout(new FlowLayout(FlowLayout.LEFT));
//		setAlignmentY(Component.CENTER_ALIGNMENT);
		setLayout(new GridBagLayout());
		add(answerText);
	}

}