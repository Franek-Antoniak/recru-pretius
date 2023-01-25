package org.file.system.utils;

import org.file.system.type.FileType;

import java.nio.file.Path;

public class PathUtils {
	public static FileType getFileExtension(Path path) {
		String fileName = path.getFileName()
				.toString();
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		switch (extension.toLowerCase()) {
			case "xml" -> {
				return FileType.XML;
			}
			case "jar" -> {
				return FileType.JAR;
			}
			default -> {
				return FileType.OTHER;
			}
		}
	}
}