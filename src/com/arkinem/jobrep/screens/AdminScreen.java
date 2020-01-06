package com.arkinem.jobrep.screens;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.arkinem.jobrep.components.HeaderLabel;

public class AdminScreen extends BaseScreen implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9045249603564907250L;
	private HeaderLabel headerLabel = new HeaderLabel("Admin");
	private JPanel container;

	public AdminScreen(JPanel container) {
		super();
		this.container = container;


		add(headerLabel);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
