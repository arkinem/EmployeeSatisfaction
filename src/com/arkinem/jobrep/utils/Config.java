package com.arkinem.jobrep.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

	public static String getPasswordSalt() {
		return props.getProperty("admin_password_salt");
	}

	public static void setPasswordSalt(String salt) {
		props.setProperty("admin_password_salt", salt);
		saveConfig();
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