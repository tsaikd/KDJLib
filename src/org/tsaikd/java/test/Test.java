package org.tsaikd.java.test;

import org.apache.log4j.Logger;
import org.tsaikd.java.utils.ConsoleUtils;

public class Test {
	static Logger log = Logger.getLogger(Test.class);

	public static final String[] config_var_list = {
		"AAA",
	};
	public static final Object[] config_def_list = {
		"BBB",
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		args = new String[] { "-h" };
		log.debug("Start");
		ConsoleUtils.parseArgs(args, config_var_list, config_def_list);
		log.debug("End");
	}

}
