package com.arkinem.jobrep.screens;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.arkinem.jobrep.components.HeaderLabel;
import com.arkinem.jobrep.components.PrimaryButton;

public class AdminScreen extends BaseScreen implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9045249603564907250L;
	private HeaderLabel headerLabel = new HeaderLabel("Admin Panel");
	private PrimaryButton analyseButton = new PrimaryButton("Analyse results");
	private PrimaryButton backButton = new PrimaryButton("Main menu");
	private JPanel container;

	public AdminScreen(JPanel container) {
		super();
		this.container = container;

		analyseButton.setBounds(250, 180, 200, 60);
		analyseButton.setFontSize(18);
		analyseButton.addActionListener(this);
		
		backButton.setBounds(250, 280, 200, 60);
		backButton.setFontSize(18);
		backButton.addActionListener(this);
		
		add(headerLabel);
		add(analyseButton);
		add(backButton);
		
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
CardLayout layout = (CardLayout) container.getLayout();
		
		if (e.getSource().equals(analyseButton)) {
			layout.show(container, "analyseScreen");
		}

		if (e.getSource().equals(backButton)) {
			layout.show(container, "startScreen");
		}
	}

}
