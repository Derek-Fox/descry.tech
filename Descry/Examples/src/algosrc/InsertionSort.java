package algosrc;

import descry.Descry;
import descry.internal.VisualDebugger;
import descry.Constants;

import javax.swing.*;
import java.util.Arrays;

public class InsertionSort {
    private static VisualDebugger graphics;
    private static final int[] arr = {3, 2, 6, 4, 5, 1, 16, 7, 11, 12, 19, 10};
    private static final int BGCOLOR = 100;

    public static void main(String[] args) {
        Descry.visualize(controller -> {
            graphics = controller;
            graphics.setFrameRate(1);
            run();
        });
    }

    private static void run() {
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void drawArray(int first, int compare) {
        graphics.beginFrame();
        graphics.background(BGCOLOR);
        graphics.strokeWeight(2);
        graphics.strokeColor(0);
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
            graphics.strokeColor(0);
            graphics.rectangle(cellLowerX, lowerY, cellSizeX, sizeY);

            if (i == first) {
                graphics.fillColor(255, 0, 0);
                graphics.strokeColor(255, 0 , 0);
                graphics.arrow(cellCenterX, cellCenterY - sizeY, cellCenterX, (cellCenterY - sizeY) - 30);
            } else if (i == compare) {
                graphics.fillColor(0, 0, 255);
                graphics.strokeColor(0, 0 , 255);
                graphics.arrow(cellCenterX, cellCenterY - sizeY, cellCenterX, cellCenterY - sizeY - 30);
            } else {
                graphics.fillColor(0);
            }
            graphics.textSize(20);
            graphics.textAlign(Constants.CENTER, Constants.CENTER);
            graphics.text(Integer.toString(arr[i]), cellCenterX, cellCenterY);

        }
        graphics.endFrame();
    }

    private static void drawArray(int valueHighlight) {
        graphics.beginFrame();
        graphics.background(BGCOLOR);

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

            if (arr[i] == valueHighlight) {
                graphics.fillColor(255, 0, 0);
            } else {
                graphics.fillColor(0);
            }
            graphics.textSize(20);
            graphics.text(Integer.toString(arr[i]), cellCenterX, cellCenterY);

        }
        graphics.endFrame();
    }

    private static void drawArray() {
        graphics.beginFrame();
        graphics.background(BGCOLOR);

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
    public static void insertionSort(int[] A) {
        for (int i = 1; i < A.length; i++) {
            drawArray(A[i]);
            int value = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] > value) {
                drawArray(i, j);
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = value;
            drawArray(A[i]);
        }
        drawArray();
    }
}
