package com.arkinem.jobrep.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Implementation of simple txt file based database.
 * @author Blazej Golinski
 *
 */
public class TextFileDb {
	private static final String currentDir = System.getProperty("user.dir");
	private static final Path dataDirPath = Paths.get(currentDir, Config.getDataDir());
	private static Path filePath;
	private static String resultString = "";

	/**
	 * initialises TextFileDb with txt file named the same as provided name
	 * @param name name for txt file
	 */
	public TextFileDb(String name) {
		filePath = Paths.get(dataDirPath.toString(), name + ".txt");
		this.createFileIfNotExist(filePath);
		;

	}

	/**
	 * creates file if it not exists
	 * @param path path to the file
	 */
	private void createFileIfNotExist(Path path) {
		if (Files.notExists(path)) {
			File yourFile = new File(path.toString());
			yourFile.getParentFile().mkdirs();
			try {
				yourFile.createNewFile();
				System.out.println("File created");
				System.out.println(path.toString());
			} catch (IOException e) {
				System.out.println("Failed to create a new file");
				System.out.println(path.toString());
				e.printStackTrace();
			}
		}
	}

	/**
	 * append TextFileDb with new record
	 * @param data data to save
	 */
	public void append(List<String> data) {
		try {
			Files.write(filePath, data, StandardCharsets.ISO_8859_1, StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Filed to write to the file");
			System.out.println(filePath.toString());
			e.printStackTrace();
		}
	}

	/**
	 * read all from TextFileDb
	 * @return filestring
	 */
	public String readAll() {
		resultString = "";
		try (Stream<String> stream = Files.lines(filePath)) {
			stream.forEach(forEachRow);
		} catch (IOException e) {
			System.out.println("Filed to read from the file");
			System.out.println(filePath.toString());
			e.printStackTrace();
		}

		return resultString;
	}
	
	private Consumer<String> forEachRow = s -> {
		resultString += s;
	};
}
