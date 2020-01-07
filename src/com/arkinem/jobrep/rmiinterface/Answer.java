package com.arkinem.jobrep.rmiinterface;

import java.io.Serializable;
import java.util.UUID;

public class Answer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3993665747881677110L;
	private UUID id;
	private String answerText;
	private int frequency = 0;

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

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
