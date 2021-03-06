package main.sorting.divideAndConquer;

import main.sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int middleIndex;

		if (validation(array, leftIndex, rightIndex)){
			middleIndex = (leftIndex + rightIndex) / 2;
			this.sort(array, leftIndex, middleIndex);
			this.sort(array, middleIndex + 1, rightIndex);
			this.merge(array, leftIndex, middleIndex, rightIndex);
		}
	}

	private boolean validation(T[] array, int leftIndex, int rightIndex) {
		boolean isValid = true;

		if (array == null || array.length <= 0) {
			isValid = false;
		} else if ((leftIndex >= rightIndex) || (leftIndex < 0) || (rightIndex <= 0)) {
			isValid = false;
		} else if ((rightIndex > array.length - 1) || leftIndex >= array.length) {
			isValid = false;
		}

		return isValid;
	}

	private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex){
		int leftStart = leftIndex;
		int leftEnd = middleIndex;
		int rightStart = middleIndex + 1;
		int rightEnd = rightIndex;
		int index = leftIndex;

		T[] temp = Arrays.copyOf(array, array.length);

		while (leftStart <= leftEnd && rightStart <= rightEnd){
			if (temp[leftStart].compareTo(temp[rightStart]) < 0){
				array[index] = temp[leftStart];
				leftStart++;
			} else {
				array[index] = temp[rightStart];
				rightStart++;
			}

			index++;
		}

		if (leftStart <= leftEnd){
			System.arraycopy(temp, leftStart, array, index, leftEnd - leftStart + 1);
		} else if (rightStart <= rightEnd) {
			System.arraycopy(temp, rightStart, array, index, rightEnd - rightStart + 1);
		}

	}
}
