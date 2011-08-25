package org.tsaikd.java.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClassUtils {

	static Log log = LogFactory.getLog(ClassUtils.class);

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
