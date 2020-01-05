package com.arkinem.jobrep.repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.arkinem.jobrep.utils.TextFileDb;

public class ResultsRepository {
	private TextFileDb db;

	public ResultsRepository() {
		db = new TextFileDb("results");
	}

	public void submitResult(UUID answerId) {
		db.append(Arrays.asList(answerId.toString() + ";"));
	}

	public List<String> getResults() {
		return Arrays.asList(db.readAll().split(";"));
	}

}
