package com.arkinem.jobrep.rmiinterface;

public class Answer {
	private String answerText;
	private int frequency;

	public Answer(String answerText, int frequency) {
		this.answerText = answerText;
		this.frequency = frequency;
	}

	public Answer(String answerText) {
		this.answerText = answerText;
		this.frequency = 0;
	}

	public String getAnswerText() {
		return answerText;
	}

	public int getFrequency() {
		return frequency;
	}

	public void incrementFrequency() {
		this.frequency += 1;
	}
}
