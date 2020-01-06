package com.arkinem.jobrep.client;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.arkinem.jobrep.components.SecondaryButton;
import com.arkinem.jobrep.components.BaseScreen;
import com.arkinem.jobrep.components.HeaderLabel;
import com.arkinem.jobrep.components.PrimaryButton;

public class StartScreen extends BaseScreen implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6715262679079405609L;
	private PrimaryButton startButton = new PrimaryButton("Start Questionnaire");
	private SecondaryButton adminButton = new SecondaryButton("Admin");
	private HeaderLabel headerLabel = new HeaderLabel("Employee satisfaction");
	private JPanel container;

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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(startButton)) {
			CardLayout layout = (CardLayout) container.getLayout();
			layout.next(container);
		}

		if (e.getSource().equals(adminButton)) {

		}
	}

}