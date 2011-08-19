package org.tsaikd.java.example;

import org.apache.log4j.Logger;
import org.tsaikd.java.utils.ConsoleUtils;

public class ConsoleExample {

	protected static Logger log = Logger.getLogger(ConsoleExample.class);
	protected static String version = "1.0";

	public static final String config_example = "org.tsaikd.java.example.consoleutil";
	public static final String config_example_default = "";

	public static final String[] config_var_list = {
		config_example,
	};
	public static final Object[] config_def_list = {
		config_example_default,
	};

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// args = new String[] { "--org.tsaikd.java.example.consoleutil", "example" };
		ConsoleUtils.version = version;
		ConsoleUtils.parseArgs(args, config_var_list, config_def_list);

		// write program start from here
	}

}
