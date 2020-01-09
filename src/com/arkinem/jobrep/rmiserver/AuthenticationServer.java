package com.arkinem.jobrep.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.arkinem.jobrep.rmiinterface.RemoteAuthentication;
import com.arkinem.jobrep.utils.PasswordManager;

public class AuthenticationServer extends UnicastRemoteObject implements RemoteAuthentication {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8752767335298018183L;

	AuthenticationServer() throws RemoteException {
		super();
		System.out.println("Authentication Server created");
	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public boolean authenticateAdmin(String password) throws RemoteException {
		return PasswordManager.authenticateAdmin(password);
	}

}
