package com.arkinem.jobrep.rmiinterface;

import java.io.Serializable;
import java.util.UUID;

/**
 * 
 * Answer and how often it has been chosen
 * @author Blazej Golinski
 *
 */
public class Answer implements Serializable {

	/**
	 * An answer with frequency
	 * 
	 */
	private static final long serialVersionUID = 3993665747881677110L;
	private UUID id;
	private String answerText;
	private int frequency = 0;

	/**
	 * A constructor that requires answer text and frequency.
	 * New answer id will be generated.
	 * @param answerText text of the answer visible to the end user
	 * @param frequency how often answer has been chosen in questionnaire
	 */
	public Answer(String answerText, int frequency) {
		this.id = UUID.randomUUID();
		this.answerText = answerText;
		this.frequency = frequency;
	}

	/**
	 * A constructor that requires answer id and text. Frequency 
	 * will be set to its default value
	 * @param id answerId (UUID)
	 * @param frequency how often answer has been chosen in the questionnaire
	 */
	public Answer(UUID id, String answerText) {
		this.id = id;
		this.answerText = answerText;
		this.frequency = 0;
	}

	/**
	 * A constructor that requires answer text. Frequency 
	 * will be set to its default value and new id will be generated
	 * @param answerText text of the answer visible to the end user
	 */
	public Answer(String answerText) {
		this.id = UUID.randomUUID();
		this.answerText = answerText;
		this.frequency = 0;
	}

	/**
	 * What is the answer id?
	 * @return id of the answer
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * What is the answer text?
	 * @return answer text
	 */
	public String getAnswerText() {
		return answerText;
	}

	/**
	 * What is the frequency?
	 * @return how often answer has been chosen in the questionnaire
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Sets frequency value
	 * @param frequency frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
