public class Test {
	public static void main(String[] args) {
		Instrumentation ins = Instrumentation.Instance();
		ins.activate(true);
		ins.startTiming("main");
		
		int arraySize = 150;
		int[] a = populateArray(ins, arraySize);

		int[] bubble = bubbleSort(ins, a);
		int[] quick = quickSort(ins, a);
		int[] merge = mergeSort(ins, a);
		int[] selection = selectionSort(ins, a);

		ins.comment("this is an example of comment");
		ins.stopTiming("main");
		ins.dump("Question3_" + arraySize + " numbers.log");

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
}
