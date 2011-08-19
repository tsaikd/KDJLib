package org.tsaikd.java.utils;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

public class ArgParser {

	static Logger log = Logger.getLogger(ArgParser.class);
	public String version = "1.0";
	Collection<Option> opts = new LinkedList<ArgParser.Option>();
	CommandLine cmd = null;
	private int helpWidth = 80;

	static public class Option {
		String opt = null;
		String longOpt = null;
		boolean hasArg = false;
		String description = null;

		public Option(String opt, String longOpt, boolean hasArg, Object defaultArg, String description) {
			this.opt = opt;
			this.longOpt = longOpt;
			this.hasArg = hasArg;

			String desc;
			if (description != null) {
				desc = description;
			} else {
				desc = "";
			}
			if (hasArg && (defaultArg != null)) {
				if (desc.length() > 0) {
					desc += "\n";
				}
				if (defaultArg.toString().isEmpty()) {
					desc += "Default: \"\"";
				} else {
					desc += "Default: " + defaultArg.toString();
				}
			}
			if (desc.length() < 1) {
				desc = null;
			}
			this.description = desc;
		}

		public Option(String opt, String longOpt, String description) {
			this.opt = opt;
			this.longOpt = longOpt;
			this.description = description;
		}

	}

	public ArgParser() {
		this.opts.add(new Option("h", "help", "Show help message"));
	}

	public ArgParser(String version) {
		this.opts.add(new Option("h", "help", "Show help message"));
		this.version = version;
	}

	public ArgParser(String version, Option[] opts) {
		this.opts.add(new Option("h", "help", "Show help message"));
		for (Option opt : opts) {
			this.opts.add(opt);
		}
		this.version = version;
	}

	public ArgParser parse(String[] args) throws Exception {
		Options options = new Options();

		for (Option opt : opts) {
			options.addOption(opt.opt, opt.longOpt, opt.hasArg, opt.description);
		}

		cmd = new PosixParser().parse(options, args);

		if (getCmd().hasOption("h")) {
			HelpFormatter helpFmt = new HelpFormatter();
			helpFmt.setWidth(getHelpWidth());
			helpFmt.printHelp(ClassUtils.getClassName(), "Version: "
				+ version, options, null, true);
			System.exit(0);
			return this;
		}

		for (Option opt : opts) {
			if (!opt.hasArg) {
				continue;
			}
			String key = opt.opt;
			if (key != null && getCmd().hasOption(key)) {
				String value = getCmd().getOptionValue(key);
				ConfigUtils.set(key, value);
			}
			key = opt.longOpt;
			if (key != null && getCmd().hasOption(key)) {
				String value = getCmd().getOptionValue(key);
				ConfigUtils.set(key, value);
			}
		}

		return this;
	}

	public CommandLine getCmd() {
		return cmd;
	}

	public int getHelpWidth() {
		return helpWidth;
	}

	public void setHelpWidth(int helpWidth) {
		this.helpWidth = helpWidth;
	}

}
