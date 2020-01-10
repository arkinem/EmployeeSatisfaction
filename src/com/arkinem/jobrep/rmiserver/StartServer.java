package com.arkinem.jobrep.rmiserver;

import java.io.Console;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.arkinem.jobrep.rmiinterface.RemoteAuthentication;
import com.arkinem.jobrep.rmiinterface.RemoteQuestions;
import com.arkinem.jobrep.utils.Config;
import com.arkinem.jobrep.utils.PasswordManager;

/**
 * Entry point of the server. 
 * @author Blazej Golinski
 *
 */
public class StartServer {
	private static int port =  Config.getPort();
	private static String hostAddress = Config.getHostAddress();

	/**
	 * Main method makes sure that administrator password exists and starts 
	 * server setup. If startup is successful it prints message with host address
	 * and port number
	 * @param args args are ignored
	 */
	public static void main(String[] args) {
		setUpAdministrator();

		try {
			setUpServer();
			System.out.println("RMI Server is listening on " + hostAddress + " port " + port);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Creates servers instances and binds them to RMI registry
	 * @throws RemoteException
	 */
	private static void setUpServer() throws RemoteException {
		RemoteQuestions questions = new QuestionServer();
		RemoteAuthentication authentication = new AuthenticationServer();
		Registry reg = LocateRegistry.createRegistry(port);

		reg.rebind("QuestionService", questions);
		reg.rebind("AuthenticationService", authentication);
	}

	/**
	 * Before server starts it makes sure that admin password exist using 
	 * PasswordManager class. If password is not present it handles the interaction
	 * with a user to set up a new password. 
	 */
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
