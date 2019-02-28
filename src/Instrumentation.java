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
			String s = String.format("%.3f", (float) (end - startTime.pop()) / 1000000);
			logContent.add("STOPTIMING: " + comment + " " + s + " ms\n");
		}
	}

	public void comment(String comment) {
		if (activated) {
			logContent.add("COMMENT: " + comment + "\n");
		}
	}

	public void dump(String filename) {
		long current = System.nanoTime();
		String s = String.format("%.3f", (float) (current - startTimeCopy.get(0)) / 1000000);
		logContent.add("TOTAL TIME: " + s + " ms\n");

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
