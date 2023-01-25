package org.file.system;

import lombok.extern.java.Log;
import org.file.system.directory.DirectoryCreator;
import org.file.system.event.DirectoryObserver;
import org.file.system.event.FileListenerService;
import org.file.system.manager.FileManagerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

@Log
public class FileManagerApplication {
	public static void run(String[] args) {
		log.info("Application started");
		try {
			start();
		} catch (InterruptedException e) {
			log.info("Application interrupted");
		}
		log.info("Application finished");
	}

	private static void start() throws InterruptedException {
		DirectoryCreator directoryCreator = new DirectoryCreator();
		directoryCreator.createDirectories();
		Path path = Paths.get("HOME");
		FileManagerFactory factory = FileManagerFactory.getINSTANCE();
		FileListenerService fileListenerService = new FileListenerService(new DirectoryObserver(path),
				directoryCreator, factory.createXMLFileManager(), factory.createJarFileManager());
		fileListenerService.start();
	}
}
