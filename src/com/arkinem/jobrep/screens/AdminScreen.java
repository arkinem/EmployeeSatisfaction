package com.arkinem.jobrep.screens;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.arkinem.jobrep.components.HeaderLabel;
import com.arkinem.jobrep.components.PrimaryButton;

/**
 * Screen that represents the administrator part of the application.
 * 
 * @author Blazej Golinski
 *
 */
public class AdminScreen extends BaseScreen implements ActionListener {
	private static final long serialVersionUID = 9045249603564907250L;
	private HeaderLabel headerLabel = new HeaderLabel("Admin Panel");
	private PrimaryButton analyseButton = new PrimaryButton("Analyse results");
	private PrimaryButton backButton = new PrimaryButton("Main menu");
	private JPanel container;

	/**
	 * It initialises screen with analyse and back button as well as headerLabel.
	 * 
	 * @param container a parent component
	 */
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

	/**
	 * Depends on which button user clicks it navigates to ResultScreen
	 * or back to the MainScreen
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout layout = (CardLayout) container.getLayout();

		if (e.getSource().equals(analyseButton)) {
			layout.show(container, "resultsScreen");
		}

		if (e.getSource().equals(backButton)) {
			layout.show(container, "startScreen");
		}
	}

}
