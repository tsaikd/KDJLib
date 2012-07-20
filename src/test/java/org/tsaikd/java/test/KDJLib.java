package org.tsaikd.java.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class KDJLib {

	static Log log = LogFactory.getLog(KDJLib.class);

	public static void testDependProject() throws ClassNotFoundException {
		Class.forName("org.apache.commons.logging.LogFactory");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		log.debug("Start");
		log.info("End");
	}

}
