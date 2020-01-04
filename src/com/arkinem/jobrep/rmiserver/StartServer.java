package com.arkinem.jobrep.rmiserver;

import com.arkinem.jobrep.utils.Config;

public class StartServer {
	/**
	 * Entry point of the server.
	 * 
	 * @param args not used.s
	 */
	public static void main(String[] args) {
		System.out.println("Started");

		Config config = new Config();
		System.out.println(config.getPort());
	}
}
