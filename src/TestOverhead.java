
public class TestOverhead {
	public static void overhead() {
		Instrumentation ins = Instrumentation.Instance();
		ins.activate(true);
		for (int i = 0; i < 10000; i++) {
			ins.startTiming("instrumentation inside loop");
			ins.stopTiming("instrumentation inside loop");
		}
	}

	public static void main(String[] args) {
		Instrumentation ins = Instrumentation.Instance();

		// measure the instrumentation overhead
		ins.activate(true);
		ins.startTiming("instruemenation outside loop");
		overhead();
		ins.stopTiming("instruemenation outside loop");
		ins.dump("with_instrumentation.log");
	}
}