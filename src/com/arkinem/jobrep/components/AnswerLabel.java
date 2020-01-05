package com.arkinem.jobrep.components;

import java.awt.Font;

import javax.swing.JLabel;

import com.arkinem.jobrep.client.Constants;


public class AnswerLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6454561453742376269L;
	/**
	 * 
	 */
	private Font font = new Font("Roboto", Font.BOLD, 14);

	public AnswerLabel() {
		setStyle();
	}
	
	public AnswerLabel(String text) {
		setText(text);
		setStyle();
	}
	
	private void setStyle() {
		setBounds(0, 0, 100, 60);
		setFont(font);
		setForeground(Constants.lightFontColor);
	}
}
