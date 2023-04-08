package algosrc;

import descry.Descry;
import descry.internal.VisualDebugger;

import java.util.Arrays;

public class InsertionSort {
    private static VisualDebugger graphics;
    static int[] arr = {3, 2, 6, 4, 10};

    public static void main(String[] args) {
        Descry.visualize(controller -> {
            graphics = controller;
            run();

        });
    }

    private static void run() {
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void drawArray(int[] arr) {
        float sizeX = graphics.getSizeX() * 0.8f;
        float sizeY = graphics.getSizeY() * 0.2f;
        float localCenterX = sizeX * 0.5f;
        float localCenterY = sizeY * 0.5f;
        float globalCenterX = graphics.getSizeX() * 0.5f;
        float globalCenterY = graphics.getSizeY() * 0.5f;
        float lowerX = globalCenterX - localCenterX;
        float lowerY = globalCenterY - localCenterY;
        graphics.rectangle(lowerX, lowerY, sizeX, sizeY);

        int cellCount = arr.length;
        float cellSizeX = sizeX / cellCount;

        for (int i = 0; i < cellCount; i++) {
            float cellX = i * cellSizeX * lowerX;
            float cellY = lowerY;
            graphics.rectangle(cellX, cellY, cellSizeX, sizeY);
        }
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
