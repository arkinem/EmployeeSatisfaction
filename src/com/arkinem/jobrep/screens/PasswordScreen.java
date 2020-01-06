package com.arkinem.jobrep.screens;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import com.arkinem.jobrep.client.Authentication;
import com.arkinem.jobrep.components.ErrorLabel;
import com.arkinem.jobrep.components.HeaderLabel;
import com.arkinem.jobrep.components.PasswordField;
import com.arkinem.jobrep.components.PrimaryButton;
import com.arkinem.jobrep.components.SecondaryButton;

public class PasswordScreen extends BaseScreen implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9045249603564907250L;
	private Authentication authentication = new Authentication();
	private HeaderLabel headerLabel = new HeaderLabel("Provide password");
	private PasswordField passwordField = new PasswordField(40);
	private PrimaryButton signInButton = new PrimaryButton("Sign in");
	private SecondaryButton backButton = new SecondaryButton("Back");
	private ErrorLabel errorLabel = new ErrorLabel("The password you entered is incorrect.");
	private JPanel container;

	public PasswordScreen(JPanel container) {
		super();
		this.container = container;

		passwordField.setBounds(200, 160, 300, 40);

		signInButton.setBounds(360, 260, 100, 40);
		signInButton.setFontSize(16);
		signInButton.addActionListener(this);

		backButton.setBounds(240, 260, 100, 40);
		backButton.setFontSize(16);
		backButton.addActionListener(this);

		errorLabel.setBounds(100, 350, 500, 40);

		add(headerLabel);
		add(passwordField);
		add(signInButton);
		add(backButton);
		add(errorLabel);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout layout = (CardLayout) container.getLayout();

		if (e.getSource().equals(signInButton)) {
			String password = String.valueOf(passwordField.getPassword());

			if (authentication.authenticateAdmin(password)) {
				errorLabel.setVisible(false);
				passwordField.setText("");
				layout.show(container, "adminScreen");
			} else {
				errorLabel.setVisible(true);
			}
		} else if (e.getSource().equals(backButton)) {
			errorLabel.setVisible(false);
			layout.show(container, "startScreen");
		}

	}

}
