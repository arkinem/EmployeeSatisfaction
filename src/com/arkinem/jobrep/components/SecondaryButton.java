package com.arkinem.jobrep.components;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.arkinem.jobrep.client.Constants;

public class SecondaryButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8876281546964470715L;

    public SecondaryButton (String text) {
        super(text);
        setBorderPainted(false);
        setFocusPainted(false);

        setContentAreaFilled(false);
        setOpaque(true);

        setBackground(Constants.secondaryColor);
        setForeground(Constants.lightFontColor);
        setFont(new Font("Roboto", Font.BOLD, 14));
        setText(text);

        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setBackground(Constants.pressedSecondaryColor);
                } else if (getModel().isRollover()) {
                    setBackground(Constants.rolloverSecondaryColor);
                } else {
                    setBackground(Constants.secondaryColor);
                }
            }
        });
    }
}