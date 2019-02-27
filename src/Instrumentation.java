import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Instrumentation {
	private Stack<Long> startTime = new Stack<Long>();
	private Stack<Long> startTimeCopy = new Stack<Long>();
	private ArrayList<String> logContent = new ArrayList<>();
	private static boolean activated = false;

	private static Instrumentation instance = new Instrumentation();

	private Instrumentation() {
	}

	public static Instrumentation Instance() {
		return instance;
	}

	public void startTiming(String comment) {
		if (activated) {
			long time = System.nanoTime();
			startTime.push(time);
			startTimeCopy.push(time);
			logContent.add("STARTTIMING: " + comment + "\n");
		}
	}

	public void stopTiming(String comment) {
		if (activated) {
			long end = System.nanoTime();
			logContent.add("STOPTIMING: " + comment + " " + (end - startTime.pop()) + " ns\n");
		}
	}

	public void comment(String comment) {
		if (activated) {
			logContent.add("COMMENT: " + comment + "\n");
		}
	}

	public void dump(String filename) {
		long current = System.nanoTime();
		logContent.add("TOTAL TIME: " + (current - startTimeCopy.get(0)) + " ns\n");

		Logger logger = Logger.getLogger("MyLog");
		FileHandler fh;
		try {
			if (filename == null) {
				SimpleDateFormat f = new SimpleDateFormat("yyMMddHHmmss");
				filename = f.format(new Date()) + ".log";
			}
			fh = new FileHandler(filename);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.setLevel(Level.CONFIG);
			for (int i = 0; i < logContent.size(); i++) {
				logger.config(logContent.get(i));
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void activate(boolean onoff) {
		activated = onoff;
	}
}
