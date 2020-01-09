package com.arkinem.jobrep.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * RMI interface to provide the way to authenticate the administrator
 * @author Blazej Golinski
 *
 */
public interface RemoteAuthentication extends Remote {	
	/**
	 * @param password the password provided during authentication process
	 * @return a flag that indicates whether the provided password is correct
	 * @throws RemoteException
	 */
	public boolean authenticateAdmin(String password) throws RemoteException;
}
