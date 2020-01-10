package com.arkinem.jobrep.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * Thia class is a wraper around config.properties files,
 * it allows to access them in static way
 */
public final class Config {
	private static final String currentDir = System.getProperty("user.dir");
	private static final String configPath = currentDir + "/config.properties";
	private static Properties props = readConfig();

	/**
	 * reads config from file
	 * @return properties from config.properties
	 */
	private static Properties readConfig() {
		try (InputStream input = new FileInputStream(configPath)) {
			Properties prop = new Properties();
			prop.load(input);

			return prop;
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * what is number of port in the config file?
	 * @return port number
	 */
	public static int getPort() {
		return Integer.parseInt(props.getProperty("port"));
	}

	/**
	 * what is the directory for data?
	 * @return directory 
	 */
	public static String getDataDir() {
		return props.getProperty("data_dir");
	}

	/**
	 * what is the administrator password?
	 * @return administrator password hash from config
	 */
	public static String getAdminPassword() {
		return props.getProperty("admin_password");
	}

	/**
	 * sets administrator password in config. Password is not stored
	 * as a plain text, so it won't be possible to retrieve it back.
	 * @param password password to save
	 */
	public static void setAdminPassword(String password) {
		props.setProperty("admin_password", password);
		saveConfig();
	}

	/**
	 * what is the administrator password salt?
	 * @return salt
	 */
	public static byte[] getPasswordSalt() {
		String salt = props.getProperty("admin_password_salt");
		
		if (salt != null) {
			salt = salt.substring(1, salt.length() - 1); // remove [ ]
			String[] splitted = salt.split(","); 
			byte[] result = new byte[splitted.length];

			for (int i = 0; i < splitted.length; i++) {
				int number = Integer.parseInt(splitted[i].replaceAll("\\s+", ""));
				result[i] = (byte) number;
			}

			return result;
		}

		return null;
	}

	/**
	 * set password salt
	 * @param salt admin password salt
	 */
	public static void setPasswordSalt(String salt) {
		props.setProperty("admin_password_salt", salt);
		saveConfig();
	}

	/**
	 * get local host address
	 * @return localhost address
	 */
	public static String getHostAddress() {
		String hostAddress = "";

		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return hostAddress;
	}

	/**
	 * saves any changes to config object into file. 
	 */
	private static void saveConfig() {
		try {
			props.store(new FileOutputStream("config.properties"), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}