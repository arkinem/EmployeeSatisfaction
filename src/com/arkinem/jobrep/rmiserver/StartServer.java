package com.arkinem.jobrep.rmiserver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.arkinem.jobrep.rmiinterface.RemoteAuthentication;
import com.arkinem.jobrep.rmiinterface.RemoteQuestions;
import com.arkinem.jobrep.utils.Config;

public class StartServer {

	public static void main(String[] args) {
		System.out.println("Started");

		// System.out.println("Port: " + Config.getPort());

		try {
			RemoteQuestions questions = new QuestionServer();
			RemoteAuthentication authentication = new AuthenticationServer();
			
			Registry reg = LocateRegistry.createRegistry(Config.getPort());
			reg.rebind("QuestionService", questions);
			reg.rebind("AuthenticationService", authentication);
		} catch (RemoteException e) {
			System.out.println("An error occured: " + e.toString());
			e.printStackTrace();
		}

	}
}
