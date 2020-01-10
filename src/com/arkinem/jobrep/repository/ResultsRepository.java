package com.arkinem.jobrep.repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.arkinem.jobrep.utils.TextFileDb;

/**
 * Repository class for questionnaire results. 
 * It uses TextFileDb to persist data.
 * @author Blazej Golinski
 *
 */
public class ResultsRepository {
	private TextFileDb db;

	/**
	 * Constructor creates new instance of TextFileDb
	 */
	public ResultsRepository() {
		db = new TextFileDb("results");
	}

	/**
	 * Submit result to the repository
	 * @param answerId answer id (UUID)
	 */
	public void submitResult(UUID answerId) {
		db.append(Arrays.asList(answerId.toString() + ";"));
	}

	/**
	 * Get results as a list of answerId submitted.
	 * Each id represents one vote. 
	 * @return list of answerIds
	 */
	public List<String> getResults() {
		return Arrays.asList(db.readAll().split(";"));
	}

}
