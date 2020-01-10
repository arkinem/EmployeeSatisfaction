package com.arkinem.jobrep.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
 * + my verify method
 */

/**
 * This class allows to get secure password format from given plain text
 * @author Blazej Golinski
 *
 */
public class Cryptography {

	/**
	 * get secure password
	 * @param passwordToHash password to make secure
	 * @param salt generated salt
	 * @return secure password
	 */
  public static String getSecurePassword(String passwordToHash, byte[] salt) {
    String generatedPassword = null;
    try {
      // Create MessageDigest instance for MD5
      MessageDigest md = MessageDigest.getInstance("MD5");
      // Add password bytes to digest
      md.update(salt);
      // Get the hash's bytes
      byte[] bytes = md.digest(passwordToHash.getBytes());
      // This bytes[] has bytes in decimal format;
      // Convert it to hexadecimal format
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      // Get complete hashed password in hex format
      generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedPassword;
  }

  /**
   * get random salt 
   * @return random salt
   * @throws NoSuchAlgorithmException
   * @throws NoSuchProviderException
   */
  public static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
    // Always use a SecureRandom generator
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
    // Create array for salt
    byte[] salt = new byte[16];
    // Get a random salt
    sr.nextBytes(salt);
    // return salt
    return salt;
  }

  /**
   * verifies if given password matches with stored hash
   * @param password password to check
   * @param storedHash hash stored in config file
   * @param salt salt used to generate original hash
   * @return true if successful, false if failure
   */
  public static boolean verify(String password, String storedHash, byte[] salt) {
    String passwordHash = getSecurePassword(password, salt);
    if (passwordHash.equals(storedHash))
      return true;

    return false;
  }
}