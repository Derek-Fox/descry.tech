package algosrc;

import descry.Constants;
import descry.Descry;
import descry.VisualAlgorithm;
import descry.internal.VisualDebugger;
import descry.utility.Log;
import descry.utility.Random;

import java.util.Arrays;

public class BinarySearch implements VisualAlgorithm {

    private VisualDebugger _graphics;
    private final int[] _values = new int[16];

    public static void main(String[] args) {
        Descry.visualize(new BinarySearch());
    }

    @Override
    public void run(VisualDebugger graphics) {

        _graphics = graphics;
        _graphics.setFrameRate(1f);
        int j = 0;

        while (true) {

            for (int i = 0; i < _values.length; ++i) {
                _values[i] = Random.range(_values.length * 2);
            }
            Arrays.sort(_values);

            int target = Random.choice(_values);
            binarySearch(_values, target);
            System.out.println(j++);
        }
    }

    private void drawArray() {

        _graphics.beginFrame();
        float sizeX = _graphics.getSizeX() * 0.8f;
        float sizeY = _graphics.getSizeY() * 0.2f;
        float localCenterX = sizeX * 0.5f;
        float localCenterY = sizeY * 0.5f;
        float globalCenterX = _graphics.getSizeX() * 0.5f;
        float globalCenterY = _graphics.getSizeY() * 0.5f;
        float lowerX = globalCenterX - localCenterX;
        float lowerY = globalCenterY - localCenterY;
        _graphics.rectangle(lowerX, lowerY, sizeX, sizeY);

        int cellCount = _values.length;
        float cellSizeX = sizeX / cellCount;

        for (int i = 0; i < cellCount; i++) {

            float cellLowerX = i * cellSizeX + lowerX;
            float cellCenterX = cellLowerX + (cellSizeX * 0.5f);
            float cellCenterY = lowerY + (sizeY * 0.5f);

            _graphics.fillColor(255);
            _graphics.rectangle(cellLowerX, lowerY, cellSizeX, sizeY);
            _graphics.fillColor(0);
            _graphics.textSize(20);
            _graphics.text(Integer.toString(_values[i]), cellCenterX, cellCenterY);

        }
        _graphics.endFrame();
    }

    private void drawArray(int low, int high, int target) {

        _graphics.beginFrame();
        _graphics.clear();

        float sizeX = _graphics.getSizeX() * 0.8f;
        float sizeY = _graphics.getSizeY() * 0.2f;
        float localCenterX = sizeX * 0.5f;
        float localCenterY = sizeY * 0.5f;
        float globalCenterX = _graphics.getSizeX() * 0.5f;
        float globalCenterY = _graphics.getSizeY() * 0.5f;
        float lowerX = globalCenterX - localCenterX;
        float lowerY = globalCenterY - localCenterY;
        _graphics.rectangle(lowerX, lowerY, sizeX, sizeY);

        int cellCount = _values.length;
        float cellSizeX = sizeX / cellCount;

        for (int i = 0; i < cellCount; i++) {

            float cellLowerX = i * cellSizeX + lowerX;
            float cellCenterX = cellLowerX + (cellSizeX * 0.5f);
            float cellCenterY = lowerY + (sizeY * 0.5f);

            if (i == low || i == high) {
                _graphics.fillColor(220);
            } else {
                _graphics.fillColor(255);
            }
            _graphics.rectangle(cellLowerX, lowerY, cellSizeX, sizeY);

            if (_values[i] == target) {
                _graphics.fillColor(0, 255, 0);
            } else {
                _graphics.fillColor(0);
            }

            _graphics.textAlign(Constants.CENTER, Constants.CENTER);
            _graphics.textSize(20);
            _graphics.text(Integer.toString(_values[i]), cellCenterX, cellCenterY);
        }

        _graphics.endFrame();
    }

    private int binarySearch(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length - 1);
    }

    private int binarySearch(int[] arr, int target, int low, int high) {

        drawArray(arr[low], arr[high], target);
        if (high < low) {
            return -low; // value would be inserted at index "low"
        }

        int mid = (low + high) / 2;
        if (arr[mid] > target) {
            return binarySearch(arr, target, low, mid - 1);
        } else if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, high);
        }

        return mid;
    }
}
