package org.file.system;

import lombok.Getter;
import lombok.extern.java.Log;

@Log
public class FileManagerFactory {
	@Getter
	private final static FileManagerFactory INSTANCE = new FileManagerFactory();

	private FileManagerFactory() {
		log.info("FileManagerFactory created");
	}

	public static FileManager createXMLFileManager() {
		return new FileManager(new XMLFileMoveStrategy());
	}

	public static FileManager createJarFileManager() {
		return new FileManager(new JarFileMoveStrategy());
	}

	public static FileManager createDefaultFileManager() {
		return new FileManager(filePath -> System.out.println("File " + filePath + " was moved"));
	}
}
