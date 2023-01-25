package org.file.system.manager;

import lombok.RequiredArgsConstructor;
import org.file.system.counter.FileCounter;
import org.file.system.strategy.FileMoveStrategy;

import java.nio.file.Path;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class FileManager {
	private final FileMoveStrategy fileMoveStrategy;
	private final FileCounter fileCounter = FileCounter.getINSTANCE();

	public void onNewFile(Path file, LocalDateTime time) {
		fileCounter.increment(fileMoveStrategy.moveFile(file, time));
	}
}
