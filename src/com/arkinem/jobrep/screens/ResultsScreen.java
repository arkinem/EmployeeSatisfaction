package com.arkinem.jobrep.screens;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.arkinem.jobrep.client.QuestionSet;
import com.arkinem.jobrep.components.HeaderLabel;
import com.arkinem.jobrep.components.SecondaryButton;
import com.arkinem.jobrep.rmiinterface.Answer;
import com.arkinem.jobrep.rmiinterface.Question;

public class ResultsScreen extends BaseScreen implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6991683534267402390L;
	private HeaderLabel headerLabel = new HeaderLabel("Results");
	private SecondaryButton backButton = new SecondaryButton("Back");
	private QuestionSet questionnaire = new QuestionSet();
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
		test();
	}

	public void test() {

		List<Question> results = questionnaire.getData();

		Hashtable<String, List<Integer>> precentage = new Hashtable<String, List<Integer>>();
		List<Integer> questionSummaries = new ArrayList<Integer>();

		for (Question question : results) {
			int sum = 0;
			for (Answer answer : question.getAnswers()) {
				sum += answer.getFrequency();
			}

			questionSummaries.add(sum);
		}

		for (int i = 4; i >= 0; i--) {
			List<Integer> dataArray = new ArrayList<Integer>();

			for (Question question : results) {
				Answer answer = question.getAnswers().get(i);
				int frequency = answer.getFrequency();
				dataArray.add(frequency);
			}

			for (int j = 0; j < dataArray.size(); j++) {
				int sum = questionSummaries.get(j);
				double precentageValue = (double) dataArray.get(j) / (double) sum * 100;
				dataArray.set(j, (int) precentageValue);
			}

			precentage.put(i + "", dataArray);
		}

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

		JSONArray labels = new JSONArray();
		for (int i = 1; i <= results.size(); i++) {
			labels.add(i);
		}

		JSONArray datasets = new JSONArray();
		Set<String> keys = precentage.keySet();

		for (String key : keys) {
			List<Integer> values = precentage.get(key);
			JSONArray dataArray = new JSONArray();

			for (int value : values) {
				dataArray.add(value);
			}

			String[] colors = { "green", "limegreen", "yellow", "orange", "red" };

			JSONObject dataObj = new JSONObject();
			dataObj.put("label", key);
			dataObj.put("data", dataArray);
			System.out.println(key);
			dataObj.put("backgroundColor", colors[Integer.parseInt(key)]);
			datasets.add(dataObj);
		}

		JSONObject data = new JSONObject();
		data.put("labels", labels);
		data.put("datasets", datasets);

		JSONObject json = new JSONObject();
		json.put("type", "bar");
		json.put("options", options);
		json.put("data", data);

		String message = json.toString();
		System.out.println(message);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout layout = (CardLayout) container.getLayout();

		if (e.getSource().equals(backButton)) {
			layout.show(container, "adminScreen");
		}
	}

}
