package org.file.system.strategy.jar;

import lombok.extern.java.Log;
import org.file.system.strategy.FileMoveStrategy;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Log
public class JarFileMoveStrategy implements FileMoveStrategy {
	@Override
	public String moveFile(Path filePath, LocalDateTime dateTime) {
		Path nonRel = Paths.get("HOME" + File.separator + filePath.getFileName());
		if (dateTime.getHour() % 2 == 0) {
			nonRel.toFile()
					.renameTo(new File("DEV" + File.separator + filePath.getFileName()));
			return "DEV";
		} else {
			nonRel.toFile()
					.renameTo(new File("DEV" + File.separator + filePath.getFileName()));
			return "TEST";
		}
	}
}
