package com.arkinem.jobrep.components;

import java.awt.Font;

import javax.swing.JLabel;

import com.arkinem.jobrep.client.Constants;

/**
 * Reusable AnswerLabel component 
 * @author Blazej Golinski
 *
 */
public class AnswerLabel extends JLabel {
	private static final long serialVersionUID = 6454561453742376269L;
	private Font font = new Font("Roboto", Font.BOLD, 14);

	/**
	 * sets initial styles
	 */
	public AnswerLabel() {
		setStyle();
	}
	
	/**
	 * sets initial styles and answer label text
	 * @param text label text
	 */
	public AnswerLabel(String text) {
		setText(text);
		setStyle();
	}
	
	/**
	 * sets initial styles
	 */
	private void setStyle() {
		setBounds(0, 0, 100, 60);
		setFont(font);
		setForeground(Constants.lightFontColor);
	}
}
