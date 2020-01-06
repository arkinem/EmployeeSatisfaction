package com.arkinem.jobrep.screens;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.arkinem.jobrep.components.HeaderLabel;
import com.arkinem.jobrep.components.SecondaryButton;

public class ResultsScreen extends BaseScreen implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6991683534267402390L;
	private HeaderLabel headerLabel = new HeaderLabel("Results");
	private SecondaryButton backButton = new SecondaryButton("Back");
	private JPanel container;

	public ResultsScreen(JPanel container) {
		super();
		this.container = container;

		headerLabel.setBounds(150, 30, 400, 40);
		backButton.setBounds(15, 30, 100, 40);
		backButton.setFontSize(18);
		backButton.addActionListener(this);

		add(headerLabel);
		add(backButton);

		repaint();
	}

	public void test() { 
		String message;
		JSONObject json = new JSONObject();
		json.put("type", "bar");

		JSONObject legend = new JSONObject();
		legend.put("position", "bottom");
		
		JSONObject xAxes = new JSONObject();
		xAxes.put("stacked", true);
		JSONArray xAxesArray = new JSONArray();
		xAxesArray.add(xAxes);
		
		JSONObject yAxes = new JSONObject();
		yAxes.put("stacked", true);
		JSONArray yAxesArray = new JSONArray();
		yAxesArray.add(yAxes);
		
		JSONObject scales = new JSONObject();
		scales.put("xAxes", xAxesArray);
		scales.put("yAxes", yAxesArray);
				
		JSONObject options = new JSONObject();
		options.put("legend", legend);
		options.put("scales", scales);	
		json.put("options", options);

		



		message = json.toString();

		// message
		// {"course":[{"id":3,"information":"test","name":"course1"}],"name":"student"}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout layout = (CardLayout) container.getLayout();

		if (e.getSource().equals(backButton)) {
			layout.show(container, "adminScreen");
		}
	}

}
