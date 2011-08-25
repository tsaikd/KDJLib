package org.tsaikd.java.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tsaikd.java.utils.ArgParser;

public class ConsoleExample {

	static Log log = LogFactory.getLog(ConsoleExample.class);
	static String version = "1.0";

	public static final String config_example = "org.tsaikd.java.example.consoleexample";
	public static final String config_example_default = "";

	public static final ArgParser.Option[] opts = {
		new ArgParser.Option(null, config_example, true, config_example_default, ""),
	};

	public static final Class<?>[] optDep = {
	};

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//args = new String[] { "--help" };
		ArgParser argParser = new ArgParser(version, ConsoleExample.class);
		argParser.parse(args);

		// write program start from here
	}

}
