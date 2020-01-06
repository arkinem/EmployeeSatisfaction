package com.arkinem.jobrep.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Authentication extends Remote {
	/**
	 * Checks if provided password is the same as the one stored in config.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public boolean authenticateAdmin(String password) throws RemoteException;
}
