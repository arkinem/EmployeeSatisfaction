package com.arkinem.jobrep.rmiinterface;

import java.util.List;
import java.util.UUID;

public class Question {

	private UUID id;
	private String questionText;
	private List<Answer> answers;

	public Question(String questionText, List<Answer> answers) {
		this.id = UUID.randomUUID();
		this.questionText = questionText;
		this.answers = answers;
	}
	
	public Question(UUID id, String questionText, List<Answer> answers) {
		this.id = id;
		this.questionText = questionText;
		this.answers = answers;
	}
	
	public UUID getId() {
		return id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public List<Answer> getAnswers() {
		return answers;
	}
}
