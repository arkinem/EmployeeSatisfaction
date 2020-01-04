package com.arkinem.jobrep.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static final String currentDir = System.getProperty("user.dir");
	private static final String configPath = currentDir + "/config.properties";
	private int port;

	public Config() {
		Properties props = readConfig();

		if (props != null) {
			port = Integer.parseInt(props.getProperty("port"));
		} else {
			System.out.println("Something went wrong while reading config file.");
		}
	}

	private Properties readConfig() {
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

	public int getPort() {
		return port;
	}
}
