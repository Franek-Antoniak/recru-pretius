package org.file.system.commons;

public class PathCommon {
	public static String substringAfterLast(String str, String separator) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		if (separator == null) {
			return "";
		}
		int pos = str.lastIndexOf(separator);
		if (pos == -1 || pos == str.length() - separator.length()) {
			return "";
		}
		return str.substring(pos + separator.length());
	}
}
