package org.file.system.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.file.system.directory.DirectoryCreator;
import org.file.system.manager.FileManager;
import org.file.system.utils.PathUtils;

import java.nio.file.StandardWatchEventKinds;

@Log
@RequiredArgsConstructor
public class FileListenerService implements Listener {
	private final DirectoryObserver directoryObserver;
	private final DirectoryCreator directoryCreator;
	private final FileManager xmlFileManager;
	private final FileManager jarFileManager;


	public void start() throws InterruptedException {
		directoryObserver.addListener(this);
		directoryObserver.startObserving();
	}

	@Override
	public void onEvent(Event event) {
		if (event.watchEvent()
				.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
			switch (PathUtils.getFileExtension(event.file())) {
				case XML -> xmlFileManager.onNewFile(event.file(), event.time());
				case JAR -> jarFileManager.onNewFile(event.file(), event.time());
			}
		}
	}
}
