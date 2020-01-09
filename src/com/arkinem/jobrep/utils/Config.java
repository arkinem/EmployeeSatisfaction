package com.arkinem.jobrep.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public final class Config {
	private static final String currentDir = System.getProperty("user.dir");
	private static final String configPath = currentDir + "/config.properties";
	private static Properties props = readConfig();

	private static Properties readConfig() {
		try (InputStream input = new FileInputStream(configPath)) {

			Properties prop = new Properties();

			// load a properties file
			prop.load(input);

			return prop;

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static int getPort() {
		return Integer.parseInt(props.getProperty("port"));
	}

	public static String getDataDir() {
		return props.getProperty("data_dir");
	}

	public static String getAdminPassword() {
		return props.getProperty("admin_password");
	}

	public static void setAdminPassword(String password) {
		props.setProperty("admin_password", password);
		saveConfig();
	}

	public static byte[] getPasswordSalt() {
		String salt = props.getProperty("admin_password_salt");
		// remove [ ]
		if (salt != null) {
			salt = salt.substring(1, salt.length() - 1);
			// split by coma
			String[] splitted = salt.split(",");
			// create array for results
			byte[] result = new byte[splitted.length];

			// iterate over splitted array, convert strings into bytes
			for (int i = 0; i < splitted.length; i++) {
				int number = Integer.parseInt(splitted[i].replaceAll("\\s+", ""));
				result[i] = (byte) number;
			}

			return result;
		}

		return null;
	}

	public static void setPasswordSalt(String salt) {
		props.setProperty("admin_password_salt", salt);
		saveConfig();
	}

	public static String getHostAddress() {
		String hostAddress = "";

		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return hostAddress;
	}

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