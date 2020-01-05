package com.arkinem.jobrep.rmiserver;

import java.util.ArrayList;
import java.util.List;

import com.arkinem.jobrep.rmiinterface.Answer;
import com.arkinem.jobrep.rmiinterface.Question;
import com.arkinem.jobrep.utils.Config;
import com.arkinem.jobrep.utils.TextFileDb;

public class StartServer {
	
	public static void main(String[] args) {
		System.out.println("Started");

		System.out.println("Port: " + Config.getPort());
		
		TextFileDb questions = new TextFileDb("questions");
	
		ArrayList<Answer> answers = new ArrayList<Answer>(); 
		answers.add(new Answer("Odpowiedz A"));
		answers.add(new Answer("Odpowiedz B"));
		answers.add(new Answer("Odpowiedz C"));
		answers.add(new Answer("Odpowiedz D"));
		String questionText = "Pytanie numer 1 cos tam cos tam";
		Question question = new Question(questionText, answers);
		
//		questions.write(TextFileDb.convertQuestionToFilestring(question));
		String fileContent = questions.read();
		questions.convertFilestringToQuestions(fileContent);
		 System.exit(0);
	}
}
