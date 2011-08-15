package org.tsaikd.java.example;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;
import org.tsaikd.java.utils.ConfigUtils;

public class ConsoleUtil {

	protected static Logger log = Logger.getLogger(ConsoleUtil.class);
	protected static String version = "1.0";

	public static final String config_example = "org.tsaikd.java.example.consoleutil";
	public static final String config_example_default = "";

	public static final String[] config_var_list = {
		config_example,
	};
	public static final Object[] config_def_list = {
		config_example_default,
	};

	@SuppressWarnings("static-access")
	protected static void parseArgs(String[] args) throws Exception {
		Options options = new Options();
		options.addOption("h", "help", false, "Show help message");

		for (int i = 0; i < config_var_list.length; i++) {
			OptionBuilder optb = OptionBuilder.withLongOpt(config_var_list[i]);
			optb = optb.hasArg();
			if (config_def_list[i] != null) {
				optb = optb.withDescription("Default: " + config_def_list[i].toString());
			}
			options.addOption(optb.create());
		}

		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(options, args);

		if (cmd.hasOption("h")) {
			new HelpFormatter().printHelp("PatentClassMakeIndex", "Version: "
					+ version, options, null, true);
			return;
		}

		for (int i = 0; i < config_var_list.length; i++) {
			if (cmd.hasOption(config_var_list[i])) {
				String value = cmd.getOptionValue(config_var_list[i]);
				ConfigUtils.set(config_var_list[i], value);
			}
			// log.debug(config_var_list[i]+": "+ConfigUtils.get(config_var_list[i]));
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// args = new String[] { "--org.tsaikd.java.example.consoleutil", "example" };
		parseArgs(args);

		// write program start from here
	}

}
