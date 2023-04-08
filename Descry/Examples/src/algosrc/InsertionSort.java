package algosrc;

import descry.Descry;
import descry.internal.VisualDebugger;

import java.util.Arrays;

public class InsertionSort {
    private static VisualDebugger graphics;
    private static final int[] arr = {3, 2, 6, 4};

    public static void main(String[] args) {
        Descry.visualize(controller -> {
            graphics = controller;
            run();
        });
    }

    private static void run() {
        insertionSort(arr);

    }

    private static void drawArray() {
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
            graphics.rectangle(cellX, lowerY, cellSizeX, sizeY);
            graphics.text(Integer.toString(arr[i]), cellX, lowerY);
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            graphics.beginFrame();
            drawArray();
            graphics.endFrame();
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[i]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = arr[i];
        }
    }
}
