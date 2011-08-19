package org.tsaikd.java.example;

import org.apache.log4j.Logger;
import org.tsaikd.java.utils.ArgParser;

public class ConsoleExample {

	static Logger log = Logger.getLogger(ConsoleExample.class);
	static String version = "1.0";

	public static final String config_example = "org.tsaikd.java.example.consoleexample";
	public static final String config_example_default = "";

	static ArgParser.Option[] opts = {
		new ArgParser.Option(null, config_example, true, config_example_default, ""),
	};

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//args = new String[] { "--help" };
		ArgParser argParser = new ArgParser(version, opts);
		argParser.parse(args);

		// write program start from here
	}

}
