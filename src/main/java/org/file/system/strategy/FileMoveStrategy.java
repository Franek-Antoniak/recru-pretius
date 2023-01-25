package org.file.system.strategy;

import java.nio.file.Path;
import java.time.LocalDateTime;

public interface FileMoveStrategy {
	String moveFile(Path filePath, LocalDateTime dateTime);
}
