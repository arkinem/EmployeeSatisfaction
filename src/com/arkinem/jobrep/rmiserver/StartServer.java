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

	public static void main(String[] args) {
		setUpAdministrator();

		try {
			RemoteQuestions questions = new QuestionServer();
			RemoteAuthentication authentication = new AuthenticationServer();
			int port = Config.getPort();
			String hostAddress = Config.getHostAddress();
			Registry reg = LocateRegistry.createRegistry(port);

			reg.rebind("QuestionService", questions);
			reg.rebind("AuthenticationService", authentication);

			System.out.println("RMI Server is listening on " + hostAddress + " port " + port);
		} catch (RemoteException e) {
			System.out.println("An error occured: " + e.toString());
			e.printStackTrace();
		}

	}

	private static void setUpAdministrator() {
		if (!PasswordManager.checkIfAdminPasswordExist()) {
			Console console = System.console();
			boolean validPassword = false;

			System.out.println("Administrator password is not set. You will need to do it only during first launch.");
			while (!validPassword) {
				System.out.println("New password:");
				String password = new String(console.readPassword());
				System.out.println("Repeat password:");
				String repeatedPassword = new String(console.readPassword());

				if (!PasswordManager.validatePasswordCandidate(password)) {
					System.out.println("Password needs to be minimum 8 characters long.");
					continue;
				}

				if (password.equals(repeatedPassword)) {
					PasswordManager.setAdminPassword(password);
					validPassword = true;
				}
			}
		}
	}

}
