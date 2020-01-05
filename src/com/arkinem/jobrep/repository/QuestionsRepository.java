package com.arkinem.jobrep.repository;

import java.util.List;

import com.arkinem.jobrep.rmiinterface.Question;
import com.arkinem.jobrep.utils.FilestringParser;
import com.arkinem.jobrep.utils.TextFileDb;

public class QuestionsRepository {
	private List<Question> questions;
	private TextFileDb db;
	
	public QuestionsRepository() {
		db = new TextFileDb("questions");
		fetchData();
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
