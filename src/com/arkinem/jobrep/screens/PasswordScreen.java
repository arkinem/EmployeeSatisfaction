package com.arkinem.jobrep.screens;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.arkinem.jobrep.components.HeaderLabel;
import com.arkinem.jobrep.components.PrimaryButton;
import com.arkinem.jobrep.components.SecondaryButton;

public class PasswordScreen extends BaseScreen implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9045249603564907250L;
	private HeaderLabel headerLabel = new HeaderLabel("Provide password");
	private PrimaryButton signInButton = new PrimaryButton("Sign in");
	private SecondaryButton backButton = new SecondaryButton("Back");
	private JTextField passwordField = new JTextField(40);
	private JPanel container;

	public PasswordScreen(JPanel container) {
		super();
		this.container = container;
		
		passwordField.setBounds(200, 160, 300, 40);
		
		signInButton.setBounds(370, 380, 100, 40);
		signInButton.setFont(new Font("Roboto", Font.BOLD, 18));
		signInButton.addActionListener(this);
		
		backButton.setBounds(250, 380, 100, 40);
		backButton.setFont(new Font("Roboto", Font.BOLD, 18));
		backButton.addActionListener(this);

		add(headerLabel);		
		add(passwordField);
		add(signInButton);
		add(backButton);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(signInButton)) {
			CardLayout layout = (CardLayout) container.getLayout();
			layout.show(container, "startScreen");
		}else	if (e.getSource().equals(backButton)) {
			CardLayout layout = (CardLayout) container.getLayout();
			layout.show(container, "startScreen");
		}
		
		
	}

}
