package org.tsaikd.java.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Test {

	static Log log = LogFactory.getLog(Test.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		log.debug("Start");
		log.info("End");
	}

}
