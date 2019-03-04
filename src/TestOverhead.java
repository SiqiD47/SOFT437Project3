
public class TestOverhead {
	public static void overhead(boolean activate) {
		Instrumentation ins = Instrumentation.Instance();
		ins.activate(activate);
		ins.startTiming("Without instrumentation");
		int j = 0;
		for (int i = 0; i < 100000; i++) {
			if (i > 0) {
				j += i;
			}
		}
		ins.stopTiming("Without instrumentation");
		ins.dump("without_instrumentation.log");
	}

	public static void main(String[] args) {
		Instrumentation ins = Instrumentation.Instance();

		// measure the instrumentation overhead
		ins.activate(true);
		ins.startTiming("With instruemenation");
		overhead(false);
		ins.activate(true);
		ins.stopTiming("With instruemenation");
		ins.dump("with_instrumentation.log");
		
		//ins = Instrumentation.Instance();
		overhead(true);
	}
}
