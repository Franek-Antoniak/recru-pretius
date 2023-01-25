package org.file.system.strategy.xml;

import org.file.system.strategy.FileMoveStrategy;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class XMLFileMoveStrategy implements FileMoveStrategy {
	@Override
	public String moveFile(Path filePath, LocalDateTime dateTime) {
		Path nonRel = Paths.get("HOME" + File.separator + filePath.getFileName());
		nonRel.toFile()
				.renameTo(new File("DEV" + File.separator + filePath.getFileName()));
		return "DEV";
	}
}
