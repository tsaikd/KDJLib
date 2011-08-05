package org.tsaikd.java.utils;

import java.net.URL;

import org.apache.log4j.xml.DOMConfigurator;

public class LoggerLoader {
	private static LoggerLoader instance = null;

	public LoggerLoader() {
		if (System.getProperty("log4j.configuration") == null) {
			URL url = this.getClass().getResource("/log4j.xml");
			DOMConfigurator.configure(url);
		}
	}

	public static LoggerLoader getInstance() {
		if (instance == null) {
			instance = new LoggerLoader();
		}
		return instance;
	}
}
