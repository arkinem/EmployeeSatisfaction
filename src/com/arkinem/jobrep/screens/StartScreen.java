package com.arkinem.jobrep.screens;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.arkinem.jobrep.components.SecondaryButton;
import com.arkinem.jobrep.components.HeaderLabel;
import com.arkinem.jobrep.components.PrimaryButton;

public class StartScreen extends BaseScreen implements ActionListener {
	private static final long serialVersionUID = 6715262679079405609L;
	private PrimaryButton startButton = new PrimaryButton("Start Questionnaire");
	private SecondaryButton adminButton = new SecondaryButton("Admin");
	private HeaderLabel headerLabel = new HeaderLabel("Employee satisfaction");
	private JPanel container;

	/**
	 * It initialises screen with start questionnaire and administrator button as well 
	 * as an header label
	 * @param container a parent component
	 */
	public StartScreen(JPanel container) {
		super();
		this.container = container;

		startButton.setBounds(250, 180, 200, 100);
		startButton.setFont(new Font("Roboto", Font.BOLD, 18));
		startButton.addActionListener(this);

		adminButton.setBounds(600, 415, 80, 20);
		adminButton.addActionListener(this);

		add(headerLabel);
		add(startButton);
		add(adminButton);

		repaint();
	}

	/**
	 * Depends on which button user clicks it navigates to the QuestionsScreen
	 * or to administrator password prompt.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout layout = (CardLayout) container.getLayout();
		
		if (e.getSource().equals(startButton)) {
			layout.show(container, "questionsScreen");
		}

		if (e.getSource().equals(adminButton)) {
			layout.show(container, "passwordScreen");
		}
	}

}