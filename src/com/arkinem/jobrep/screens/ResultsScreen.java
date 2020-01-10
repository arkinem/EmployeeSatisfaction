package com.arkinem.jobrep.screens;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.arkinem.jobrep.client.QuestionSet;
import com.arkinem.jobrep.components.HeaderLabel;
import com.arkinem.jobrep.components.SecondaryButton;
import com.arkinem.jobrep.rmiinterface.Answer;
import com.arkinem.jobrep.rmiinterface.Question;

/**
 * Screen that shows a graph of the answers
 * @author Blazej Golinski
 *
 */
public class ResultsScreen extends BaseScreen implements ActionListener {
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

		String chartParamsJson = json.toString();

		try {
			final String urlStr = "https://quickchart.io/chart?c=" + chartParamsJson;
			System.out.println("Get Image from " + urlStr);
			final URL url = new URL(urlStr);
			final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
			final BufferedImage image = ImageIO.read(connection.getInputStream());

			Image dimg = image.getScaledInstance(660, 320, Image.SCALE_SMOOTH);

			System.out.println("Load image into frame...");
			JLabel label = new JLabel(new ImageIcon(dimg));
			label.setBounds(20, 100, 660, 320);
			add(label);
			repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * When back button is pressed it navigates back to the AdminScreen
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout layout = (CardLayout) container.getLayout();

		if (e.getSource().equals(backButton)) {
			layout.show(container, "adminScreen");
		}
	}

}
