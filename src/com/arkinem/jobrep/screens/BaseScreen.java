package com.arkinem.jobrep.screens;

import javax.swing.JPanel;

import com.arkinem.jobrep.client.Constants;

/**
 * BaseScreen sets common layout rules
 * @author Blazej Golinski
 *
 */
public class BaseScreen extends JPanel {
	private static final long serialVersionUID = -6901848110538801857L;

	public BaseScreen () {
		setLayout(null);
		setBackground(Constants.backgroundColor);
	}
}
