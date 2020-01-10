package com.arkinem.jobrep.components;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.arkinem.jobrep.client.Constants;

/**
 * Reusable ErrorLabel component 
 * @author Blazej Golinski
 *
 */
public class ErrorLabel extends JLabel {
	private static final long serialVersionUID = 6389107489710150117L;
	private Font font = new Font("Roboto", Font.BOLD, 14);

	/**
	 * sets initial layout and label text
	 * @param text label text
	 */
	public ErrorLabel(String text) {
		setSize(500, 40);
		setForeground(Constants.errorColor);
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
		setFont(font);
		setText(text);
		setVisible(false);
	}
}