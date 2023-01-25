package org.file.system.directory;

import lombok.extern.java.Log;

import java.nio.file.Path;

@Log
public class DirectoryCreator {
	public void createDirectories() {
		createDirectories("HOME", "DEV", "TEST");
	}

	private void createDirectories(String... directories) {
		for (String directory : directories) {
			createDirectory(directory);
		}
	}

	private void createDirectory(String directory) {
		log.info("Creating directory: " + directory);
		Path path = Path.of(directory);
		if (!path.toFile()
				.exists()) {
			boolean isCreated = path.toFile()
					.mkdir();
			if (isCreated) {
				log.info("Directory created: " + directory);
			} else {
				log.info("Directory not created: " + directory);
			}
		}
	}
}
