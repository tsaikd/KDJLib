package org.tsaikd.java.utils;

public class ClassUtils {

	public static String getClassName() {
		return getClassName(false);
	}

	public static String getClassName(boolean full) {
		String name;
		StackTraceElement[] stackTraceElement = new Throwable().getStackTrace();
		name = stackTraceElement[stackTraceElement.length-1].getClassName();
		if (!full) {
			String[] names = name.split("\\.");
			name = names[names.length-1];
		}
		return name;
	}

}
