package com.arkinem.jobrep.components;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.arkinem.jobrep.client.Constants;

/**
 * Label used for screen headers.
 * @author Blazej Golinski
 *
 */
public class HeaderLabel extends JLabel {
	private static final long serialVersionUID = -9212206095564704217L;
	private Font font = new Font("Roboto", Font.BOLD, 36);

	/**
	 * sets initial layout and label text
	 * @param text label text
	 */
	public HeaderLabel(String text) {
		setBounds(150, 50, 400, 80);
		setFont(font);
		setForeground(Constants.lightFontColor);
		setText(text);
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
	}
}
