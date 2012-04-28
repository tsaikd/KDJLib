package org.tsaikd.java.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProcessEstimater {

	static Log log = LogFactory.getLog(ProcessEstimater.class);

	protected long max = 0;
	protected long num = 0;
	protected long time = 0;
	protected long rest = 0;

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

	public ProcessEstimater setRestNum(long rest) {
		long num = max - rest;
		return setNum(num);
	}

	public long getNum() {
		return num;
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

	@Override
	public String toString() {
		return getRestString();
	}
}
