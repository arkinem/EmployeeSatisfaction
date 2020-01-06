package com.arkinem.jobrep.repository;

import java.util.ArrayList;
import java.util.List;

import com.arkinem.jobrep.rmiinterface.Answer;
import com.arkinem.jobrep.rmiinterface.Question;
import com.arkinem.jobrep.utils.FilestringParser;
import com.arkinem.jobrep.utils.TextFileDb;

public class QuestionsRepository {
	private List<Question> questions;
	private TextFileDb db;

	public QuestionsRepository() {
		db = new TextFileDb("questions");
		fetchData();

		if (questions.size() == 0) {
			ArrayList<Answer> answers = new ArrayList<Answer>();
			answers.add(new Answer("I strongly disagree"));
			answers.add(new Answer("I rather disagree"));
			answers.add(new Answer("I neither agree nor disagree"));
			answers.add(new Answer("I rather agree"));
			answers.add(new Answer("I definetly agree"));
			String questionText = "It is easy to get information about the personnel policy in the company";
			Question question = new Question(questionText, answers);

			addQuestion(question);
		}
	}

	public void addQuestion(Question question) {
		db.append(FilestringParser.convertQuestionToFilestring(question));
		fetchData();
	}

	public List<Question> getAllQuestions() {
		return questions;
	}

	private void fetchData() {
		String fileContent = db.readAll();
		questions = FilestringParser.convertFilestringToQuestions(fileContent);
	}

}
