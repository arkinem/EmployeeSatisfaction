package com.arkinem.jobrep.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.arkinem.jobrep.rmiinterface.Authentication;
import com.arkinem.jobrep.utils.Config;

public class AuthenticationServer extends UnicastRemoteObject implements Authentication {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8752767335298018183L;

	AuthenticationServer() throws RemoteException {
		super();
		System.out.println("Authentication Server created.");
	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public boolean authenticateAdmin(String password) throws RemoteException {
		String storedPassword = Config.getPassword();
		
		if(password == storedPassword)
			return true;
		
		return false;
	}

}
