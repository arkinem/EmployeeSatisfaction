package com.arkinem.jobrep.rmiserver;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.Properties;

import com.arkinem.jobrep.rmiinterface.RemoteAuthentication;
import com.arkinem.jobrep.rmiinterface.RemoteQuestions;
import com.arkinem.jobrep.utils.Config;
import com.arkinem.jobrep.utils.Cryptography;
import com.arkinem.jobrep.utils.PasswordManager;

public class StartServer {

	public static void WritePropertiesFile() {
		try {
			try {
				byte[] test = Cryptography.getSalt();
				Properties configProperty = new Properties();
				configProperty.setProperty("pass", Arrays.toString(test));
				File file = new File(System.getProperty("user.dir") + "/config.properties");
				FileOutputStream fileOut;
				fileOut = new FileOutputStream(file);
				configProperty.store(fileOut, "sample properties");
				fileOut.close();

				try (InputStream input = new FileInputStream(System.getProperty("user.dir") + "/config.properties")) {

					Properties prop = new Properties();

					// load a properties file
					prop.load(input);

					System.out.println("result: " + test.equals(prop.getProperty("pass")));
					System.out.println("result: " + Arrays.toString(test));
					System.out.println("result: " + Arrays.toString(prop.getProperty("pass").getBytes()));

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		setUpAdministrator();

		// try {
		// byte[] originalSalt = Cryptography.getSalt();
		// String encPassword = Cryptography.getSecurePassword("enter121",
		// originalSalt);
		// System.out.println("salt: " + Arrays.toString(originalSalt));
		// System.out.println("encPassword: " + encPassword);
		// Config.setAdminPassword(encPassword);
		// Config.setPasswordSalt(Arrays.toString(originalSalt));

		// byte[] saltFromFile = Config.getPasswordSalt();
		// String encPasswordFromFile = Config.getAdminPassword();
		// System.out.println("salt from file: " + Arrays.toString(saltFromFile));
		// System.out.println("encPassword from file: " + encPasswordFromFile);

		// String encPasswordToCheckO = Cryptography.getSecurePassword("enter121",
		// originalSalt);
		// String encPasswordToCheckC = Cryptography.getSecurePassword("enter121",
		// saltFromFile);
		// System.out.println("encPassword to check using orignal salt: " +
		// encPasswordToCheckO);
		// System.out.println("encPassword to check using config salt: " +
		// encPasswordToCheckC);

		// // System.out.println(str);
		// } catch (NoSuchAlgorithmException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (NoSuchProviderException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

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
