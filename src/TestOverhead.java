
public class TestOverhead {
	public static void doSomething() {
		Instrumentation ins = Instrumentation.Instance();
		ins.activate(true);
		ins.startTiming("doSomething()");
		System.out.println("Hi there!");
		ins.stopTiming("doSomething()");
	}

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
		ins.activate(true);

		ins.startTiming("main");
		ins.startTiming("loop");

		for (int i = 0; i < 5; i++) {
			doSomething();
		}

		ins.stopTiming("loop");
		ins.comment("this is an example of a comment!");
		ins.stopTiming("main");

		// measure the instrumentation overhead
		ins.activate(true);
		ins.startTiming("With Instruemenation");
		overhead(false);
		ins.activate(true);
		ins.stopTiming("With Instruemenation");
		ins.dump("overhead.log");

		overhead(true);
	}
}
