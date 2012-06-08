package org.tsaikd.java.utils;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessEstimater {

	static Log log = LogFactory.getLog(ProcessEstimater.class);

	private long max = 0;
	private long num = 0;
	private long time = 0;
	private long rest = 0;
	private String outputFormat = "%1$s";

	public ProcessEstimater(long max) {
		this.max = max;
	}

	public ProcessEstimater setNum(long num) {
		if (this.time > 0) {
			long now = System.currentTimeMillis();
			long diffTime = now - this.time;
			long restNum = max - num;
			this.rest = diffTime * restNum / num;
		} else {
			this.time = System.currentTimeMillis();
		}
		this.num = num;
		return this;
	}

	public long getNum() {
		return num;
	}

	public ProcessEstimater addNum(long add) {
		return setNum(num + add);
	}

	public ProcessEstimater addNum() {
		return addNum(1);
	}

	public ProcessEstimater setRestNum(long rest) {
		long num = max - rest;
		return setNum(num);
	}

	public String getRestString() {
		long rest = this.rest;

		int msec = (int) (rest % 1000);
		rest /= 1000;

		int sec = (int) (rest % 60);
		rest /= 60;

		int min = (int) (rest % 60);
		rest /= 60;

		int hr = (int) rest;

		if (hr > 0) {
			return String.format("%1$02d:%2$02d:%3$02d", hr, min, sec, msec);
		} else if (min > 0) {
			return String.format("%2$02d:%3$02d", hr, min, sec, msec);
		} else {
			return String.format("%3$02d.%4$03d", hr, min, sec, msec);
		}
	}

	/**
	 * Set format of output string <BR/>
	 *   %1$s   : time <BR/>
	 *   %2$d   : num <BR/>
	 *   %3$d   : rest <BR/>
	 *   %4$d   : max <BR/>
	 *   %5$.2f : 100.0 * num / max <BR/>
	 *   %6$.2f : 100.0 * rest / max <BR/>
	 *   default : "%1$s" <BR/>
	 *   example : "%2$d / %4$d (%5$.2f%%) , Rest time: %1$s"
	 * @param format
	 * @return
	 */
	public ProcessEstimater setFormat(String format) {
		this.outputFormat = format;
		return this;
	}

	@Override
	public String toString() {
		return String.format(outputFormat, getRestString(), num, rest, max, (float) 100.0 * num / max, (float) 100.0 * rest / max);
	}

	public String toString1(Object args) {
		return String.format(outputFormat, getRestString(), num, rest, max, (float) 100.0 * num / max, (float) 100.0 * rest / max, args);
	}

	private Date prevMsg = null;
	public ProcessEstimater debug(Log log, long minTime) {
		if (minTime > 0 && prevMsg != null && prevMsg.after(new Date())) {
			return this;
		}
		prevMsg = new Date(new Date().getTime() + minTime);
		log.debug(toString());
		return this;
	}
}
