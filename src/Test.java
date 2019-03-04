public class Test {
	public static void main(String[] args) {
		Instrumentation ins = Instrumentation.Instance();
		ins.activate(true);
		ins.startTiming("main");
		// question 3 and 4 under test drive section of the project
		ins.comment("===== Question 3 & 4 =====");
		int[] arraySize = { 100, 200, 300, 400, 500, 1000, 2000, 3000, 4000, 6000, 8000, 10000 };

		for (int i = 0; i < arraySize.length; i++)
			fourSortingAlgs(ins, arraySize[i]);
		ins.stopTiming("main");
		ins.dump("TestDriveSec");
	}

	public static int[] populateArray(Instrumentation ins, int arraySize) {
		ins.startTiming("populateArray()");
		int[] array = new int[arraySize];
		for (int i = 0; i < arraySize; i++) {
			array[i] = (int) (Math.random() * 99999 + 1);
		}
		ins.stopTiming("populateArray()");
		return array;
	}

	public static void bubbleSort(Instrumentation ins, int[] array, boolean testInstrumentedAlgorithm) {
		ins.startTiming("bubbleSort()");
		SortAlgorithm b = null;
		if (testInstrumentedAlgorithm) {
			b = new BubbleSort2AlgorithmInstrumented();
		} else {
			b = new BubbleSort2Algorithm();
		}
		b.sort(array);
		ins.stopTiming("bubbleSort()");
	}

	public static void quickSort(Instrumentation ins, int[] array, boolean testInstrumentedAlgorithm) {
		ins.startTiming("quickSort()");
		SortAlgorithm b = null;
		if (testInstrumentedAlgorithm) {
			b = new NaiveQuickSortAlgorithmInstrumented();
		} else {
			b = new NaiveQuickSortAlgorithm();
		}
		b.sort(array);
		ins.stopTiming("quickSort()");
	}

	public static void mergeSort(Instrumentation ins, int[] array, boolean testInstrumentedAlgorithm) {
		ins.startTiming("mergeSort()");
		SortAlgorithm b = null;
		if (testInstrumentedAlgorithm) {
			b = new ExtraStorageMergeSortAlgorithmInstrumented();
		} else {
			b = new ExtraStorageMergeSortAlgorithm();
		}
		b.sort(array);
		ins.stopTiming("mergeSort()");
	}

	public static void selectionSort(Instrumentation ins, int[] array, boolean testInstrumentedAlgorithm) {
		ins.startTiming("selectionSort()");
		SortAlgorithm b = null;
		if (testInstrumentedAlgorithm) {
			b = new SelectionSortAlgorithmInstrumented();
		} else {
			b = new SelectionSortAlgorithm();
		}
		b.sort(array);
		ins.stopTiming("selectionSort()");
	}

	// test method for Question 3 & 4
	public static void fourSortingAlgs(Instrumentation ins, int arraySize) {
		ins.comment("array size: " + arraySize);
		ins.startTiming("fourSortingAlgs()");

		int[] a1 = populateArray(ins, arraySize);
		int[] a2 = a1.clone();
		int[] a3 = a1.clone();
		int[] a4 = a1.clone();
		int[] a5 = a1.clone();
		ins.startTiming("test with NOT instrumented classes");
		bubbleSort(ins, a1, false);
		quickSort(ins, a2, false);
		mergeSort(ins, a3, false);
		selectionSort(ins, a4, false);
		ins.stopTiming("test with NOT instrumented classes");

		if (arraySize == 4000 || arraySize == 8000) {
			int[] a6 = a5.clone();
			int[] a7 = a5.clone();
			int[] a8 = a5.clone();
			ins.startTiming("test with instrumented classes");
//			bubbleSort(ins, a5, true);
//			quickSort(ins, a6, true);
//			mergeSort(ins, a7, true);
//			selectionSort(ins, a8, true);
			ins.stopTiming("test with instrumented classes");
		}

		ins.stopTiming("fourSortingAlgs()");
	}

}
