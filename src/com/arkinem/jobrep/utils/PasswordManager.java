package com.arkinem.jobrep.utils;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;

public final class PasswordManager {

  public static boolean checkIfAdminPasswordExist() {
    String password = Config.getAdminPassword();
    byte[] salt = Config.getPasswordSalt();

    if (password != null && salt != null)
      return true;

    return false;
  }

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

  public static boolean authenticateAdmin(String password) {
    String storedHash = Config.getAdminPassword();
    byte[] salt = Config.getPasswordSalt();

    return Cryptography.verify(password, storedHash, salt);
  }

  public static boolean validatePasswordCandidate(String password) {
    if (password.length() >= 8)
      return true;

    return false;
  }

}
