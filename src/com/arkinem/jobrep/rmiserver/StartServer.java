package com.arkinem.jobrep.rmiserver;

import com.arkinem.jobrep.utils.Config;
import com.arkinem.jobrep.utils.TextFile;

public class StartServer {
	/**
	 * Entry point of the server.
	 * 
	 * @param args not used.s
	 */
	public static void main(String[] args) {
		System.out.println("Started");

		System.out.println("Port: " + Config.getPort());
		
		TextFile t = new TextFile();
		
		t.write("hehe");
		t.read();
	}
}
