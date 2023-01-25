package org.file.system.event;

import lombok.SneakyThrows;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileCreationListener {
	private final WatchService watchService;
	private final List<Listener> listeners = new ArrayList<>();
	@SneakyThrows
	public FileCreationListener(Path toWatch) {
		this.watchService = FileSystems.getDefault()
				.newWatchService();
		toWatch.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
	}
	public void startListening() {
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					WatchKey key = watchService.take();
					for (WatchEvent<?> event : key.pollEvents()) {
						if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
							Path file = (Path) event.context();
							notifyListeners(new Event(event, file, new ));
						}
					}
					key.reset();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
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
