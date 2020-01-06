package com.arkinem.jobrep.client;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import com.arkinem.jobrep.rmiinterface.Answer;
import com.arkinem.jobrep.rmiinterface.RemoteQuestions;

/**
 * Represents the questionnaire locally. All requests from peripherals will be 
 * through this class. 
 * @author Marc Conrad
 * modified by arkinem
 */
public class QuestionSet {

	RemoteQuestions myQuestions; 
	/**
	 * Retrieves the questions from the server. 
	 * To do: connect to different servers, make configurable. 
	 */
	public QuestionSet() {
		super();
		try {
			myQuestions =   (RemoteQuestions) Naming.lookup("rmi://localhost/ArkinemQuestionService");
		} catch (Exception e) {
			System.out.println("A problem occured: "+e.toString());
			e.printStackTrace();
			System.out.println("Is your server running?");
		} 
	}
	/**
	 * How many questions are there in the questionnaire?
	 * @return number of questions.
	 */

	public int numberOfQuestions() { 
		try {
			return myQuestions.getNumberOfQuestions();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0; 
		} 
	}
	/**
	 * Retrieve a specific question. 		
	 * @param i the number of the question.
	 * @return text of the question. 
	 */
	public String getQuestion(int i) { 
		try {
			return myQuestions.getQuestion(i).getQuestionText();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "no connection to server"; 
		} 
	}
	/** 
	 * Gives a set of options for this question. 
	 * @param i the number of the question.
	 * @return an array of options. 
	 */
	public List<Answer> getOptions(int i) { 
		System.out.println(i);
		try {
			return myQuestions.getQuestion(i).getAnswers();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; 
		} 
	}
	/**
	 * Submit the answer to a given question.
	 * @param i the question.
	 * @param answer the chosen answer.
	 */
	public void submitAnswer(UUID answerId) { 
		try {
			myQuestions.submitAnswer(answerId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/** 
	 * Reports the answers in various ways. 
	 */
	public void reportAnswers() { 

//		try {
//			Answers myAnswers = new Answers(myQuestions.getData());
//
//			System.out.println("Basic analysis:");
//			System.out.println(myAnswers.basicAnalysis());
//
//			ShowImageFromURL.show(myAnswers.getBarChartURL());
//		} catch (RemoteException e) {
//			System.out.println("Something went wrong: "+e.toString());
//			e.printStackTrace();
//		}  
	}

}
