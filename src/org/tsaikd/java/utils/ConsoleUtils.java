package org.tsaikd.java.utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;
import org.tsaikd.java.utils.ClassUtils;
import org.tsaikd.java.utils.ConfigUtils;

public class ConsoleUtils {

	protected static Logger log = Logger.getLogger(ConsoleUtils.class);
	public static String version = "1.0";

	@SuppressWarnings("static-access")
	public static void parseArgs(String[] args, String[] config_var_list, Object[] config_def_list) throws Exception {
		Options options = new Options();
		options.addOption("h", "help", false, "Show help message");

		if (config_var_list != null) {
			for (int i = 0; i < config_var_list.length; i++) {
				OptionBuilder optb = OptionBuilder.withLongOpt(config_var_list[i]);
				optb = optb.hasArg();
				if (config_def_list[i] != null) {
					optb = optb.withDescription("Default: " + config_def_list[i].toString());
				}
				options.addOption(optb.create());
			}
		}

		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(options, args);

		if (cmd.hasOption("h")) {
			new HelpFormatter().printHelp(ClassUtils.getClassName(), "Version: "
					+ version, options, null, true);
			System.exit(0);
			return;
		}

		if (config_var_list != null) {
			for (int i = 0; i < config_var_list.length; i++) {
				if (cmd.hasOption(config_var_list[i])) {
					String value = cmd.getOptionValue(config_var_list[i]);
					ConfigUtils.set(config_var_list[i], value);
				}
			}
		}
	}

}
