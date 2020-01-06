package com.arkinem.jobrep.client;

import java.rmi.Naming;
import java.rmi.RemoteException;

import com.arkinem.jobrep.rmiinterface.RemoteAuthentication;

/**
 * Represents the authentication locally. All requests from peripherals will be
 * through this class.
 * 
 * @author arkinem
 */
public class Authentication {

	RemoteAuthentication authentication;

	/**
	 * Retrieves the questions from the server. To do: connect to different servers,
	 * make configurable.
	 */
	public Authentication() {
		super();
		try {
			authentication = (RemoteAuthentication) Naming.lookup("rmi://localhost/AuthenticationService");
		} catch (Exception e) {
			System.out.println("A problem occured: " + e.toString());
			e.printStackTrace();
			System.out.println("Is your server running?");
		}
	}

	/**
	 * Authenticate admin using provided password
	 * 
	 * @return authentication successful/unsuccessful
	 */
	public boolean authenticateAdmin(String password) {
		try {
			return authentication.authenticateAdmin(password);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	

}
