package com.arkinem.jobrep.rmiserver;

import java.io.Console;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.arkinem.jobrep.rmiinterface.RemoteAuthentication;
import com.arkinem.jobrep.rmiinterface.RemoteQuestions;
import com.arkinem.jobrep.utils.Config;
import com.arkinem.jobrep.utils.PasswordManager;

public class StartServer {

	private static void setUpAdministrator() {
		Console console = System.console();
		if (!PasswordManager.checkIfAdminPasswordExist()) {
			boolean validPassword = false;

			console.printf("Administrator password is not set. You will need to do it only during first launch.\n");
			while (!validPassword) {
				console.printf("New password:\n");
				String password = new String(console.readPassword());
				console.printf("Repeat password:\n");
				String repeatedPassword = new String(console.readPassword());
				console.printf(password);
				console.printf(repeatedPassword);
				if (password.equals(repeatedPassword)) {

					console.printf("eqal");
				} else {
					console.printf("not eqal");
				}
				if (!PasswordManager.validatePasswordCandidate(password)) {
					console.printf("Password needs to be minimum 8 characters long.");
					continue;
				}

				if (password.equals(repeatedPassword)) {
					PasswordManager.setAdminPassword(password.toString());
					validPassword = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		setUpAdministrator();

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
