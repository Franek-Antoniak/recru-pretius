package org.file.system.manager;

import lombok.Getter;
import lombok.extern.java.Log;
import org.file.system.strategy.jar.JarFileMoveStrategy;
import org.file.system.strategy.xml.XMLFileMoveStrategy;

@Log
public class FileManagerFactory {
	@Getter
	private final static FileManagerFactory INSTANCE = new FileManagerFactory();

	private FileManagerFactory() {
		log.info("FileManagerFactory created");
	}

	public FileManager createXMLFileManager() {
		return new FileManager(new XMLFileMoveStrategy());
	}

	public FileManager createJarFileManager() {
		return new FileManager(new JarFileMoveStrategy());
	}
}
