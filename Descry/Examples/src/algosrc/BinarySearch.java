package algosrc;

import descry.Descry;
import descry.internal.VisualDebugger;

import java.util.Arrays;

public class BinarySearch {

    private static VisualDebugger graphics;
    private static final int[] arr = {1, 2, 3, 4, 5, 6};
    public static void main(String[] args) {
        Descry.visualize(controller -> {
            graphics = controller;
            run();
        });
    }

    private static void run() {
        System.out.println(binarySearch(arr, 3));

    }
    private static void drawArray() {
        graphics.beginFrame();
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
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

            float cellLowerX = i * cellSizeX + lowerX;
            float cellCenterX = cellLowerX + (cellSizeX * 0.5f);
            float cellCenterY = lowerY + (sizeY * 0.5f);

            graphics.fillColor(255);
            graphics.rectangle(cellLowerX, lowerY, cellSizeX, sizeY);
            graphics.fillColor(0);
            graphics.textSize(20);
            graphics.text(Integer.toString(arr[i]), cellCenterX, cellCenterY);

        }
        graphics.endFrame();
    }

    private static void drawArray(int low, int high) {

        graphics.beginFrame();

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

            float cellLowerX = i * cellSizeX + lowerX;
            float cellCenterX = cellLowerX + (cellSizeX * 0.5f);
            float cellCenterY = lowerY + (sizeY * 0.5f);

            graphics.fillColor(255);
            graphics.rectangle(cellLowerX, lowerY, cellSizeX, sizeY);

            if(arr[i] == low || arr[i] == high) {
                graphics.fillColor(255, 0 ,0);
            } else {
                graphics.fillColor(0);
            }
            graphics.textSize(20);
            graphics.text(Integer.toString(arr[i]), cellCenterX, cellCenterY);

        }
        graphics.endFrame();
    }


    private static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length - 1 );
    }

    private static int binarySearch(int[] arr, int target, int low, int high) {
        drawArray(arr[low], arr[high]);
        if (high < low)
            return -low; // value would be inserted at index "low"
        int mid = (low + high) / 2;
        if (arr[mid] > target)
            return binarySearch(arr, target, low, mid - 1);
        else if (arr[mid] < target)
            return binarySearch(arr, target, mid + 1, high);
        else
            return mid;
        }
    }
