package org.file.system;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.nio.file.Path;

@RequiredArgsConstructor
public class FileManager {
	private final FileMoveStrategy fileMoveStrategy;
	public void moveFile(Path file) {
		System.out.println("File " + file + " was moved");
	}
}
