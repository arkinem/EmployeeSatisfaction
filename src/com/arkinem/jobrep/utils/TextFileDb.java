package com.arkinem.jobrep.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.arkinem.jobrep.rmiinterface.Answer;
import com.arkinem.jobrep.rmiinterface.Question;

public class TextFileDb {
	private static final String currentDir = System.getProperty("user.dir");
	private static final Path dataDirPath = Paths.get(currentDir, Config.getDataDir());
	private static Path filePath;
	private static String resultString = "";

	public enum TokenType {
		ID, QUESTION, ANSWER
	}

	public TextFileDb(String name) {
		filePath = Paths.get(dataDirPath.toString(), name + ".txt");
		this.createFileIfNotExist(filePath);
		;

	}

	private void createFileIfNotExist(Path path) {
		if (Files.notExists(path)) {
			File yourFile = new File(path.toString());
			yourFile.getParentFile().mkdirs();
			try {
				yourFile.createNewFile();
				System.out.println("File created");
				System.out.println(path.toString());
			} catch (IOException e) {
				System.out.println("Failed to create a new file");
				System.out.println(path.toString());
				e.printStackTrace();
			}
		}
	}

	public void append(List<String> data) {
		try {
			Files.write(filePath, data, StandardCharsets.ISO_8859_1, StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Filed to write to the file");
			System.out.println(filePath.toString());
			e.printStackTrace();
		}
	}

	public String readAll() {
		resultString = "";
		try (Stream<String> stream = Files.lines(filePath)) {
			stream.forEach(forEachRow);
		} catch (IOException e) {
			System.out.println("Filed to read from the file");
			System.out.println(filePath.toString());
			e.printStackTrace();
		}

		return resultString;
	}

	Consumer<String> forEachRow = s -> {
		resultString += s;
	};

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
