package org.file.system;

import java.nio.file.Path;

public interface FileMoveStrategy {
	void moveFile(Path filePath);
}
