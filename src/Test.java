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

	public static int[] bubbleSort(Instrumentation ins, int[] array) {
		ins.startTiming("bubbleSort()");
		BubbleSort2Algorithm b = new BubbleSort2Algorithm();
		b.sort(array);
		ins.stopTiming("bubbleSort()");
		return array;
	}

	public static int[] quickSort(Instrumentation ins, int[] array) {
		ins.startTiming("quickSort()");
		NaiveQuickSortAlgorithm b = new NaiveQuickSortAlgorithm();
		b.sort(array);
		ins.stopTiming("quickSort()");
		return array;
	}

	public static int[] mergeSort(Instrumentation ins, int[] array) {
		ins.startTiming("mergeSort()");
		ExtraStorageMergeSortAlgorithm b = new ExtraStorageMergeSortAlgorithm();
		b.sort(array);
		ins.stopTiming("mergeSort()");
		return array;
	}

	public static int[] selectionSort(Instrumentation ins, int[] array) {
		ins.startTiming("selectionSort()");
		SelectionSortAlgorithm b = new SelectionSortAlgorithm();
		b.sort(array);
		ins.stopTiming("selectionSort()");
		return array;
	}

	// test method for Question 3 & 4
	public static void fourSortingAlgs(Instrumentation ins, int arraySize) {
		ins.comment("array size: " + arraySize);
		ins.startTiming("fourSortingAlgs()");
		int[] a = populateArray(ins, arraySize);
		int[] bubble = bubbleSort(ins, a);
		int[] quick = quickSort(ins, a);
		int[] merge = mergeSort(ins, a);
		int[] selection = selectionSort(ins, a);
		ins.stopTiming("fourSortingAlgs()");
	}

}
