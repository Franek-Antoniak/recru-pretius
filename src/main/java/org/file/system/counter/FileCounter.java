package org.file.system.counter;

import lombok.Getter;
import lombok.Synchronized;

import java.io.*;
import java.util.HashMap;


public class FileCounter {
	@Getter
	private final static FileCounter INSTANCE = new FileCounter();

	private FileCounter() {
	}

	@Synchronized
	public void increment(String catalogName)  {
		File counter = createIfNotExists();
		// Get whole file as String
		try (FileInputStream stream = new FileInputStream(counter)) {
			String content = new String(stream.readAllBytes());
			HashMap<String, Integer> map = new HashMap<>();
			content.lines()
					.forEach(line -> {
						String[] split = line.split("=");
						map.put(split[0], Integer.parseInt(split[1]));
					});
			map.put(catalogName, map.getOrDefault(catalogName, 0) + 1);
			map.put("TOTAL", map.getOrDefault("TOTAL", 0) + 1);
			// Write to file
			try (BufferedWriter outputStream = new BufferedWriter(new FileWriter(counter))) {
				map.forEach((key, value) -> {
					try {
						outputStream.write(key + "=" + value + "\n");
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private File createIfNotExists() {
		File file = new File("HOME" + File.separator + "count.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException("Can't create counter file");
			}
		}
		return file;
	}
}
