package org.tsaikd.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigUtils {

	static Log log = LogFactory.getLog(ConfigUtils.class);

	static final String defaultResPath = "/config.properties";
	static ConfigUtils instance = new ConfigUtils();

	public class PropInfo {
		Properties prop = null;
		String path = null;
		long modTime = 0;
		boolean autoReaload = false;
	}

	List<PropInfo> propList = new LinkedList<PropInfo>();
	Collection<String> pathList = new HashSet<String>();

	protected ConfigUtils() {
		try {
			addClassResource(defaultResPath);
		} catch (Exception e) {
		}
	}

	public static ConfigUtils addProperties(PropInfo info) {
		if (info == null) {
			return instance;
		}
		instance.propList.add(0, info);
		instance.pathList.add(info.path);
		return instance;
	}

	public static ConfigUtils addClassResource(String path, boolean autoReload) throws Exception {
		if (instance.pathList.contains(path)) {
			return instance;
		}

		PropInfo info = getPropInfoByPath(path);
		info.autoReaload = autoReload;
		return addProperties(info);
	}

	public static ConfigUtils addClassResource(String path) throws Exception {
		return addClassResource(path, false);
	}

	public static void set(final String key, final String value) throws Exception {
		System.setProperty(key, value);
	}

	public static String get(final String key, final String defaultValue) throws Exception {
		if (key == null) {
			return null;
		}
		String value = System.getProperty(key);
		if (value != null) {
			return value;
		}

		for (PropInfo info : instance.propList) {
			if (info.autoReaload && (info.path != null)) {
				File file = new File(info.path);
				if (file.exists() && file.canRead()) {
					if ((file.lastModified() != info.modTime) || (info.prop == null)) {
						if (info.prop == null) {
							info.prop = new Properties();
						} else {
							info.prop.clear();
						}
						info.prop.load(new FileInputStream(file));
						info.modTime = file.lastModified();
						log.debug("Reload file: " + info.path);
					}
				}
			}

			if (info.prop == null) {
				continue;
			}

			value = info.prop.getProperty(key);
			if (value != null) {
				return value;
			}
		}

		return defaultValue;
	}

	public static String get(final String key) throws Exception {
		return get(key, null);
	}

	public static void set(final String key, final int value) throws Exception {
		System.setProperty(key, String.valueOf(value));
	}

	public static int getInt(final String key, final int defaultValue) throws Exception {
		String value = get(key, null);
		if (value == null) {
			return defaultValue;
		} else {
			return Integer.parseInt(value);
		}
	}

	public static int getInt(final String key) throws Exception {
		return getInt(key, 0);
	}

	public static void set(final String key, final long value) throws Exception {
		System.setProperty(key, String.valueOf(value));
	}

	public static long getLong(final String key, final long defaultValue) throws Exception {
		String value = get(key, null);
		if (value == null) {
			return defaultValue;
		} else {
			return Integer.parseInt(value);
		}
	}

	public static long getLong(final String key) throws Exception {
		return getLong(key, 0);
	}

	public static void set(final String key, final boolean value) throws Exception {
		System.setProperty(key, String.valueOf(value));
	}

	public static boolean getBool(final String key, final boolean defaultValue) throws Exception {
		String value = get(key, null);
		if (value == null) {
			return defaultValue;
		} else {
			value = value.toLowerCase().trim();
			if (value.equals("true"))
				return true;
			if (value.equals("yes"))
				return true;
			return false;
		}
	}

	public static boolean getBool(final String key) throws Exception {
		return getBool(key, false);
	}

	public static PropInfo getPropInfoByPath(String path) throws Exception {
		PropInfo info = instance.new PropInfo();
		InputStream is;

		info.path = path;

		is = Thread.currentThread().getClass().getResourceAsStream(path);

		if (is == null) {
			File file = new File(path);
			if (file.exists() && file.canRead()) {
				is = new FileInputStream(file);
				info.modTime = file.lastModified();
			}
		}

		if (is != null) {
			info.prop = new Properties();
			info.prop.load(is);
			is.close();
		} else {
			log.debug("Can not load file: " + path);
		}

		return info;
	}

}
