package com.arkinem.jobrep.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

/**
 * RMI interface to enable to retrieve questions from the server and to submit
 * data to the server. It also allows to get summary data from the server.
 * 
 * @author Marc Conrad
 * @author Blazej Golinski
 */
public interface RemoteQuestions extends Remote {
	/**
	 * Number of questions on the server.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public int getNumberOfQuestions() throws RemoteException;

	/**
	 * Retrieve specific question from the server.
	 * 
	 * @param i number of the question.
	 * @return the Question.
	 * @throws RemoteException
	 */
	public Question getQuestion(int i) throws RemoteException;

	/**
	 * Submit the answer for given answer id.
	 * 
	 * @param answerId id of the answer
	 * @throws RemoteException
	 */
	public void submitAnswer(UUID answerId) throws RemoteException;

	/**
	 * Returns the answers to the questions given.
	 * 
	 * @return summary data of the questionnaire.
	 * @throws RemoteException
	 */
	public List<Question> getData() throws RemoteException;
}
