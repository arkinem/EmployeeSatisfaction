package com.arkinem.jobrep.components;

import java.awt.Font;

import javax.swing.JLabel;

import com.arkinem.jobrep.client.Constants;

public class QuestionLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9212206095564704217L;
	private Font font = new Font("Roboto", Font.BOLD, 18);

	public QuestionLabel() {
		setStyle();
	}

	public QuestionLabel(String text) {
		setStyle();
		setText(text);
	}

	private void setStyle() {
		setBounds(28, 30, 600, 40);
		setFont(font);
		setForeground(Constants.lightFontColor);
	}
}
