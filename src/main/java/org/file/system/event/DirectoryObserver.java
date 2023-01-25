package org.file.system.event;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DirectoryObserver {
	private final WatchService watchService;
	private final List<Listener> listeners = new ArrayList<>();

	public DirectoryObserver(Path toWatch){
		try {
			this.watchService = FileSystems.getDefault()
					.newWatchService();
			toWatch.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void startObserving() throws InterruptedException {
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					WatchKey key = watchService.take();
					for (WatchEvent<?> event : key.pollEvents()) {
						if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
							Path file = (Path) event.context();
							notifyListeners(new Event(event, file, LocalDateTime.now()));
						}
					}
					key.reset();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		thread.join();
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	private void notifyListeners(Event event) {
		for (Listener listener : listeners) {
			listener.onEvent(event);
		}
	}
}
