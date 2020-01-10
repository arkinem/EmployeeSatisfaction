package com.arkinem.jobrep.repository;

import java.util.ArrayList;
import java.util.List;

import com.arkinem.jobrep.rmiinterface.Answer;
import com.arkinem.jobrep.rmiinterface.Question;
import com.arkinem.jobrep.utils.FilestringParser;
import com.arkinem.jobrep.utils.TextFileDb;

/**
 * Repository class for questionnaire. 
 * It uses TextFileDb to persist data.
 * @author Blazej Golinski
 *
 */
public class QuestionsRepository {
	private List<Question> questions;
	private TextFileDb db;

	/**
	 * Constructor creates new instance of TextFileDb.
	 * If database is empty it will populated with one question.
	 */
	public QuestionsRepository() {
		db = new TextFileDb("questions");
		fetchData();
		populateIfEmpty();
	}

	/**
	 * Adds question to database
	 * @param question question to save
	 */
	public void addQuestion(Question question) {
		db.append(FilestringParser.convertQuestionToFilestring(question));
		fetchData();
	}

	/**
	 * Gives list of all questions
	 * @return list of questions
	 */
	public List<Question> getAllQuestions() {
		return questions;
	}

	/**
	 * refreshes data from database
	 */
	private void fetchData() {
		String fileContent = db.readAll();
		questions = FilestringParser.convertFilestringToQuestions(fileContent);
	}
	
	/**
	 * populates database with a question if empty
	 */
	private void populateIfEmpty() {
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

}
