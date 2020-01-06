package com.arkinem.jobrep.screens;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import com.arkinem.jobrep.client.Authentication;
import com.arkinem.jobrep.client.Constants;
import com.arkinem.jobrep.components.HeaderLabel;
import com.arkinem.jobrep.components.PrimaryButton;
import com.arkinem.jobrep.components.SecondaryButton;

public class PasswordScreen extends BaseScreen implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9045249603564907250L;
	private Authentication authentication = new Authentication();
	private HeaderLabel headerLabel = new HeaderLabel("Provide password");
	private JPasswordField passwordField = new JPasswordField(40);
	private PrimaryButton signInButton = new PrimaryButton("Sign in");
	private SecondaryButton backButton = new SecondaryButton("Back");
	private JLabel errorLabel = new JLabel("The password you entered is incorrect.");
	private JPanel container;

	public PasswordScreen(JPanel container) {
		super();
		this.container = container;

		passwordField.setBounds(200, 160, 300, 40);
		passwordField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Constants.lightFontColor, 2, true),
				BorderFactory.createEmptyBorder(8, 8, 8, 8))
				);
		passwordField.setBackground(Constants.backgroundColor);
		passwordField.setForeground(Constants.lightFontColor);
		passwordField.setCaretColor(Constants.lightFontColor);

		signInButton.setBounds(360, 280, 100, 40);
		signInButton.setFont(new Font("Roboto", Font.BOLD, 18));
		signInButton.addActionListener(this);

		backButton.setBounds(240, 280, 100, 40);
		backButton.setFont(new Font("Roboto", Font.BOLD, 18));
		backButton.addActionListener(this);
		
		errorLabel.setBounds(100, 350, 500, 40);
		errorLabel.setForeground(Constants.errorColor);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setVerticalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		errorLabel.setVisible(false);
		

		add(headerLabel);
		add(passwordField);
		add(signInButton);
		add(backButton);
		add(errorLabel);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(signInButton)) {
			CardLayout layout = (CardLayout) container.getLayout();
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
			CardLayout layout = (CardLayout) container.getLayout();
			layout.show(container, "startScreen");
		}

	}

}
