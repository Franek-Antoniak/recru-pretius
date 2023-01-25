package org.file.system;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.file.system.counter.FileCounter;
import org.file.system.directory.DirectoryCreator;
import org.file.system.manager.FileManager;

import java.nio.file.WatchService;

@Slf4j
@RequiredArgsConstructor
public class FileListenerService {
	private final FileManager fileManager;
	private final FileCounter fileCounter;
	private final FileListener fileListener;
	private final DirectoryCreator directoryCreator;
	private final WatchService watchService;


	public void start() {

	}
}
