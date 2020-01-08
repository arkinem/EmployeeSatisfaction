package com.arkinem.jobrep.utils;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public final class PasswordManager {

  public static boolean checkIfAdminPasswordExist() {
    String password = Config.getAdminPassword();
    String salt = Config.getPasswordSalt();

    if (password != null && salt != null)
      return true;

    return false;
  }

  public static void setAdminPassword(String password) {
    try {
      byte[] salt = Cryptography.getSalt();
      String hashedPassword = Cryptography.getSecurePassword(password, salt);
      Config.setAdminPassword(hashedPassword);
      Config.setPasswordSalt(salt.toString());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchProviderException e) {
      e.printStackTrace();
    }

  }

  public static boolean authenticateAdmin(String password) {
    String storedHash = Config.getAdminPassword();
    byte[] salt = Config.getPasswordSalt().getBytes();
    String passwordHash = Cryptography.getSecurePassword(password, salt);

    if (passwordHash.equals(storedHash))
      return true;

    return false;
  }

  public static boolean validatePasswordCandidate(String password) {
    if (password.length() >= 8)
      return true;

    return false;
  }

}
