package com.arkinem.jobrep.rmiinterface;

import java.util.UUID;

public class Answer {

	private UUID id;
	private String answerText;
	private int frequency;
	
	public Answer(String answerText, int frequency) {
		this.id = UUID.randomUUID();
		this.answerText = answerText;
		this.frequency = frequency;
	}
	
	public Answer(UUID id, String answerText) {
		this.id = id;
		this.answerText = answerText;
		this.frequency = 0;
	}

	public Answer(String answerText) {
		this.id = UUID.randomUUID();
		this.answerText = answerText;
		this.frequency = 0;
	}
	
	public UUID getId() {
		return id;
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
