package algosrc;

import descry.Descry;
import descry.internal.VisualDebugger;

import java.util.Arrays;

//Author: Derek (stinky poo poo)
public class InsertionSort {

    private static VisualDebugger _debugger;

    public static void main(String[] args) {
        Descry.visualize(controller -> {
            _debugger = controller;
            run();
        });
    }

    private static void run() {
        int[] arr = {3, 2, 6, 4};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[i]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = arr[i];
        }
    }
}
