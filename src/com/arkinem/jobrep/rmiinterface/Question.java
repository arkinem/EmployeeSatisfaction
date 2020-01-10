package com.arkinem.jobrep.rmiinterface;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


/**
 * Question and list of its answers
 * @author Blazej Golinski
 *	
 */
public class Question implements Serializable {

	/**
	 *  A question with list of its options. 
	 *  
	 */
	private static final long serialVersionUID = -792608717041576481L;
	private UUID id;
	private String questionText;
	private List<Answer> answers;

	
	/**
	 * A constructor that requires question text and its answers
	 * @param questionText text of the question visible to the end user
	 * @param answers list of the answers
	 */
	public Question(String questionText, List<Answer> answers) {
		this.id = UUID.randomUUID();
		this.questionText = questionText;
		this.answers = answers;
	}
	
	/**
	 *  A constructor that requires id, question text and its answers
	 * @param id questionId (UUID)
	 * @param questionText text of the question visible to end user
	 * @param answers list of the answers
	 */
	public Question(UUID id, String questionText, List<Answer> answers) {
		this.id = id;
		this.questionText = questionText;
		this.answers = answers;
	}
	
	/**
	 * What is the question id?
	 * @return id of the question
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * What is the question text?
	 * @return question text
	 */
	public String getQuestionText() {
		return questionText;
	}

	/**
	 * What are the answers to this question?
	 * @return list of the possible answers
	 */
	public List<Answer> getAnswers() {
		return answers;
	}
}
