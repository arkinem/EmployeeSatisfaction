package com.arkinem.jobrep.rmiinterface;

public class Question {

	private String questionText;
	private Answer[] answers;

	public Question(String questionText, Answer[] answers) {
		this.questionText = questionText;
		this.answers = answers;
	}

	public String getQuestionText() {
		return questionText;
	}

	public Answer[] getAnswers() {
		return answers;
	}
}
