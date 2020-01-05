package com.arkinem.jobrep.rmiserver;

import java.util.ArrayList;
import java.util.UUID;

import com.arkinem.jobrep.repository.QuestionsRepository;
import com.arkinem.jobrep.repository.ResultsRepository;
import com.arkinem.jobrep.rmiinterface.Answer;
import com.arkinem.jobrep.rmiinterface.Question;
import com.arkinem.jobrep.utils.Config;

public class StartServer {
	
	public static void main(String[] args) {
		System.out.println("Started");

		System.out.println("Port: " + Config.getPort());
		
		QuestionsRepository questions = new QuestionsRepository();
		ResultsRepository results = new ResultsRepository();
		
		results.submitResult(UUID.randomUUID());
		results.submitResult(UUID.randomUUID());
		results.submitResult(UUID.randomUUID());
		results.submitResult(UUID.randomUUID());
		results.submitResult(UUID.randomUUID());
		results.submitResult(UUID.randomUUID());
		
		if(questions.getAllQuestions().size() == 0) {
			ArrayList<Answer> answers = new ArrayList<Answer>(); 
			answers.add(new Answer("Odpowiedz A"));
			answers.add(new Answer("Odpowiedz B"));
			answers.add(new Answer("Odpowiedz C"));
			answers.add(new Answer("Odpowiedz D"));
			String questionText = "Pytanie numer 1 cos tam cos tam";
			Question question = new Question(questionText, answers);
			
			questions.addQuestion(question);
		}
	
		System.out.println(questions.getAllQuestions().size());
		System.out.println(results.getResults());
	}
}
