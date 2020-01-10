package com.arkinem.jobrep.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.arkinem.jobrep.rmiinterface.Answer;
import com.arkinem.jobrep.rmiinterface.Question;

/**
 * This class helps with conversion between Question objects and string from file
 * @author Blazej Golinski
 *
 */
public final class FilestringParser {
	public enum TokenType {
		ID, QUESTION, ANSWER
	}

	/**
	 * converts question to the string that can be saved into config
	 * @param question question to convert
	 * @return filestring
	 */
	public static List<String> convertQuestionToFilestring(Question question) {
		List<String> result = new ArrayList<String>();
		String questionId = tokenize(question.getId().toString(), TokenType.ID);
		String questionText = tokenize(questionId + question.getQuestionText(), TokenType.QUESTION);
		result.add(questionText);

		for (Answer answer : question.getAnswers()) {
			String answerId = tokenize(answer.getId().toString(), TokenType.ID);
			String answerText = tokenize(answerId + answer.getAnswerText(), TokenType.ANSWER);
			result.add(answerText);
		}

		return result;
	}

	/**
	 * converts string from file to questions list
	 * @param filestring string from file
	 * @return	question list
	 */
	public static List<Question> convertFilestringToQuestions(String filestring) {
		List<Question> result = new ArrayList<Question>();

		String[] questions = filestring.split("<q>");

		for (String question : questions) {
			if (question.length() != 0) {

				UUID id = UUID.fromString(question.substring(4).split("</id>")[0]);
				String questionText = question.split("</id>")[1].split("</q>")[0];
				String answers = question.split("</q>")[1];

				List<Answer> answersResult = new ArrayList<Answer>();

				for (String answer : answers.split("</a>")) {
					UUID answerId = UUID.fromString(answer.substring(7).split("</id>")[0]);
					String answerText = answer.split("</id>")[1];
					answersResult.add(new Answer(answerId, answerText));
				}

				result.add(new Question(id, questionText, answersResult));
			}
		}

		return result;
	}

	/**
	 * adds html-like token around text
	 * @param text text to wrap
	 * @param type token type
	 * @return tokenized text
	 */
	private static String tokenize(String text, TokenType type) {
		String token = "";

		switch (type) {
		case ID:
			token = "id";
			break;
		case QUESTION:
			token = "q";
			break;
		case ANSWER:
			token = "a";
			break;
		default:
			break;
		}

		return "<" + token + ">" + text + "</" + token + ">";
	}
}