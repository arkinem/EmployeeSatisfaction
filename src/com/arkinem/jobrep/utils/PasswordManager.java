package com.arkinem.jobrep.utils;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;

/**
 * This class helps with password management
 * 
 * @author Blazej Golinski
 *
 */
public final class PasswordManager {

	/**
	 * Checks if administrator password exist
	 * @return true if exists, false if it doesn't
	 */
	public static boolean checkIfAdminPasswordExist() {
		String password = Config.getAdminPassword();
		byte[] salt = Config.getPasswordSalt();

		if (password != null && salt != null)
			return true;

		return false;
	}

	/**
	 * set administrator password 
	 * @param password password to persist
	 */
	public static void setAdminPassword(String password) {
		try {
			byte[] salt = Cryptography.getSalt();
			String hashedPassword = Cryptography.getSecurePassword(password, salt);
			Config.setAdminPassword(hashedPassword);
			Config.setPasswordSalt(Arrays.toString(salt));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}

	}

	/**
	 * authenticates user by verifying provided password
	 * @param password password to verify
	 * @return true if valid, otherwise false
	 */
	public static boolean authenticateAdmin(String password) {
		String storedHash = Config.getAdminPassword();
		byte[] salt = Config.getPasswordSalt();

		return Cryptography.verify(password, storedHash, salt);
	}

	/**
	 * validates if text can become a password
	 * @param password text to validate
	 * @return true if text is valid, otherwise false
	 */
	public static boolean validatePasswordCandidate(String password) {
		if (password.length() >= 8)
			return true;

		return false;
	}

}
