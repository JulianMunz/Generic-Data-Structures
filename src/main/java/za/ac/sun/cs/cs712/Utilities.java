package za.ac.sun.cs.cs712;

import java.util.Comparator;


public final class Utilities {
    private static final int SORT_THRESHOLD = 35;

    @SuppressWarnings("unchecked")
    public static <T> void sort(T[] data, Comparator<? super T> cmp) {
        if (data.length <= SORT_THRESHOLD) {
            insertionSort(data, 0, data.length-1, cmp);
        } else {
            T[] aux = (T[]) new Object[data.length];
            mergeSort(data, aux, cmp, 0, data.length-1);
        }
    }

    public static <T extends Comparable<? super T>> void sort(T[] data) {
        Comparator<T> comparator = Comparator.naturalOrder();
        sort(data, comparator);
    }

    private static <T> void insertionSort (T[] data, int low, int high, Comparator<? super T> cmp) {

        for (int i = low+1; i <= high; i++) {
            T val = data[i];
            int j = i - 1;

            while (j >= low && lessThan(val, data[j], cmp)) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = val;
        }

    }

    private static <T> void mergeSort (T[] data, T[] aux, Comparator<? super T> cmp, int low, int high) {
        if (high - low <= SORT_THRESHOLD) {
            insertionSort(data,low,high,cmp);
        } else {
            int mid = low + (high+1 - low) / 2;
            mergeSort(data, aux, cmp, low, mid);
            mergeSort(data, aux, cmp,mid + 1, high);
            merge(data, aux, cmp, low, mid, high);
        }
    }

    private static <T> void merge (T[] data, T[] aux, Comparator<? super T> cmp, int low, int mid, int high) {
        System.arraycopy(data, low, aux, low, high + 1 - low);
        int i = low, j = mid+1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                data[k] = aux[j++];
            } else if (j > high) {
                data[k] = aux[i++];
            } else if (lessThan(aux[j], aux[i], cmp)) {
                data[k] = aux[j++];
            } else {
                data[k] = aux[i++];
            }
        }
    }

    private static <T> boolean lessThan(T first, T second, Comparator<? super T> cmp ) {
        return cmp.compare(first, second) < 0;
    }

    public static <T> boolean isSorted(T[] data, Comparator<? super T> cmp) {
        for (int i = 1; i < data.length; i++)
            if (lessThan(data[i], data[i-1], cmp)) return false;
        return true;
    }


}