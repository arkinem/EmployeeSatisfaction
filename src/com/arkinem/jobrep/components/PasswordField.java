package com.arkinem.jobrep.components;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

import com.arkinem.jobrep.client.Constants;

/**
 * Reusable component for password entry
 * @author Blazej Golinski
 *
 */
public class PasswordField extends JPasswordField {
	private static final long serialVersionUID = 7332819829699511569L;

	/**
	 * Sets initial layout and number of columns
	 * @param columns number of columns
	 */
	public PasswordField(int columns) {
		setColumns(columns);
		setSize(300, 40);
		setBackground(Constants.backgroundColor);
		setForeground(Constants.lightFontColor);
		setCaretColor(Constants.lightFontColor);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Constants.lightFontColor, 2, true),
				BorderFactory.createEmptyBorder(8, 8, 8, 8)));
	}

}
