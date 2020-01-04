package com.arkinem.jobrep.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TextFile {
	private static final String currentDir = System.getProperty("user.dir");
	private static final String dataDirPath = currentDir + File.separator + Config.getDataDir();
	private String fileName = "questions.txt";

	public void write(String text) {
		List<String> lines = Arrays.asList("The first line", "The second line");
		Path file = Paths.get(dataDirPath, fileName);
		try {
			Files.write(file, lines, StandardCharsets.UTF_8);
		} catch (NoSuchFileException e) {
			System.out.println("File doesn't exist. trying to create...");
			File yourFile = new File(file.toString());
			yourFile.getParentFile().mkdirs();
			try {
				yourFile.createNewFile();
				Files.write(file, lines, StandardCharsets.UTF_8);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		
		} catch(IOException e) {
			e.printStackTrace();
		}
		//Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
	}
	
	public void read() {
		Path file = Paths.get(dataDirPath, fileName);

		try (Stream<String> stream = Files.lines(file)) {
	        stream.forEach(testFn);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
	
	Consumer<String> testFn = s -> {
	    System.out.println("test:" + s);
	};
	
}
