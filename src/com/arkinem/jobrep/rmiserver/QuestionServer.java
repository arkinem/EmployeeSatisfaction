package com.arkinem.jobrep.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.arkinem.jobrep.repository.QuestionsRepository;
import com.arkinem.jobrep.repository.ResultsRepository;
import com.arkinem.jobrep.rmiinterface.Answer;
import com.arkinem.jobrep.rmiinterface.Question;
import com.arkinem.jobrep.rmiinterface.RemoteQuestions;

/**
 * Implementation of the questionnaire. 
 * @author Blazej Golinski
 *
 */
public class QuestionServer extends UnicastRemoteObject implements RemoteQuestions {
	private static final long serialVersionUID = -2834956567289941412L;
	private QuestionsRepository questions;
	private ResultsRepository results;

	/**
	 * QuestionServer initialises questions and results repositories.
	 * Prints message to the console  when server is created.
	 */
	QuestionServer() throws RemoteException {
		super();
		questions = new QuestionsRepository();
		results = new ResultsRepository();
		System.out.println("Questions Server created");
	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public int getNumberOfQuestions() throws RemoteException {
		return questions.getAllQuestions().size();
	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public Question getQuestion(int i) throws RemoteException {
		return questions.getAllQuestions().get(i);
	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public void submitAnswer(UUID answerId) throws RemoteException {
		results.submitResult(answerId);
	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public List<Question> getData() {
		List<Question> data = questions.getAllQuestions();
		Hashtable<String, Integer> grouped = new Hashtable<String, Integer>();

		Arrays.stream(results.getResults().toArray()).collect(Collectors.groupingBy(s -> s))
				.forEach((k, v) -> grouped.put(k.toString(), v.size()));

		for (Question question : data) {
			for (Answer answer : question.getAnswers()) {
				answer.setFrequency(grouped.get(answer.getId().toString()));
			}
		}

		return data;
	}

}
