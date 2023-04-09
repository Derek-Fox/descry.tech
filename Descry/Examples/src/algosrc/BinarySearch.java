package algosrc;

import descry.Constants;
import descry.Descry;
import descry.VisualAlgorithm;
import descry.internal.VisualDebugger;
import descry.utility.Mathf;
import descry.utility.Random;

import java.util.Arrays;

public class BinarySearch implements VisualAlgorithm {

    private VisualDebugger _graphics;
    private final int[] _values = new int[24];
    private final Cell[] _cells = new Cell[24];
    private final Pointer _rangeMin = new Pointer();
    private final Pointer _rangeMax = new Pointer();
    private final Pointer _midPoint = new Pointer();
    private int _targetValue;

    public static void main(String[] args) {
        Descry.visualize(new BinarySearch());
    }

    @Override
    public void run(VisualDebugger graphics) {

        _graphics = graphics;

        while (true) {

            for (int i = 0; i < _values.length; ++i) {
                _values[i] = Random.range(_values.length * 2);
            }
            Arrays.sort(_values);

            _targetValue = Random.choice(_values);
            initializeRenderers();
            binarySearch(_values, _targetValue);
        }
    }

    private void initializeRenderers() {

        float boardSizeX = _graphics.getSizeX() * 0.8f;
        float boardSizeY = _graphics.getSizeY() * 0.2f;
        float boardLocalCenterX = boardSizeX * 0.5f;
        float boardLocalCenterY = boardSizeY * 0.5f;
        float boardGlobalCenterX = _graphics.getSizeX() * 0.5f;
        float boardGlobalCenterY = _graphics.getSizeY() * 0.5f;
        float rowLowerX = boardGlobalCenterX - boardLocalCenterX;
        float rowLowerY = boardGlobalCenterY - boardLocalCenterY;
        float cellSizeX = boardSizeX / _values.length;

        for (int i = 0; i < _values.length; ++i) {
            float cellLowerX = rowLowerX + i * cellSizeX;
            _cells[i] = new Cell(i, _values[i], cellLowerX, rowLowerY, cellSizeX, boardSizeY);
        }
    }

    private void update(int rangeMin, int rangeMax) {

        int midPoint = (rangeMin + rangeMax) / 2;
        Cell midPointCell = _cells[midPoint];
        Cell rangeMinCell = _cells[rangeMin];
        Cell rangeMaxCell = _cells[rangeMax];
        _midPoint.animateTo(midPoint, midPointCell.x + midPointCell.sizeX * 0.5f, midPointCell.y - 100f);
        _rangeMin.animateTo(rangeMin, rangeMinCell.x + midPointCell.sizeX * 0.5f, rangeMinCell.y - 100f);
        _rangeMax.animateTo(rangeMax, rangeMaxCell.x + midPointCell.sizeX * 0.5f, rangeMaxCell.y - 100f);

        _rangeMin.update(1f);
        _rangeMax.update(1f);

        float t = 0f;
        float tStep = 0.01f;
        while (t < 1f) {

            t += tStep;
            if (t > 1f) {
                t = 1f;
            }

            _midPoint.update(t);
            renderFrame();
        }

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderFrame() {

        _graphics.beginFrame();
        _graphics.background(200);

        _graphics.strokeColor(0);
        _graphics.downArrow(_midPoint.X, _midPoint.Y + 30f, _midPoint.X, _midPoint.Y);
        _graphics.downArrow(_rangeMin.X, _rangeMin.Y + 30f, _rangeMin.X, _rangeMin.Y);
        _graphics.downArrow(_rangeMax.X, _rangeMax.Y + 30f, _rangeMax.X, _rangeMax.Y);

        for (int i = 0; i < _cells.length; ++i) {

            Cell cell = _cells[i];

            if (_rangeMin.Index <= i && i <= _rangeMax.Index) {
                _graphics.fillColor(255);
            } else {
                _graphics.fillColor(220);
            }
            _graphics.rectangle(cell.x, cell.y, cell.sizeX, cell.sizeY);

            if (_values[i] == _targetValue) {
                _graphics.fillColor(0, 255, 0);
            } else {
                _graphics.fillColor(0);
            }

            float cellCenterX = cell.x + cell.sizeX * 0.5f;
            float cellCenterY = cell.y + cell.sizeY * 0.5f;

            _graphics.textAlign(Constants.CENTER, Constants.CENTER);
            _graphics.textSize(20);
            _graphics.text(Integer.toString(_values[i]), cellCenterX, cellCenterY);
        }

        _graphics.endFrame();
    }

    private int binarySearch(int[] values, int target) {
        return binarySearch(values, target, 0, values.length - 1);
    }

    private int binarySearch(int[] values, int target, int low, int high) {

        update(low, high);
        if (high < low) {
            return -low; // value would be inserted at index "low"
        }

        int mid = (low + high) / 2;
        if (values[mid] > target) {
            return binarySearch(values, target, low, mid - 1);
        } else if (values[mid] < target) {
            return binarySearch(values, target, mid + 1, high);
        }

        return mid;
    }

    private record Cell(int index, int value, float x, float y, float sizeX, float sizeY) {
    }

    private static final class Pointer {

        public int Index = -1;
        public float X;
        public float Y;
        private float _sourceX;
        private float _sourceY;
        private float _targetX;
        private float _targetY;

        public void update(float time01) {
            X = Mathf.lerp(_sourceX, _targetX, time01);
            Y = Mathf.lerp(_sourceY, _targetY, time01);
        }
        
        public void animateTo(int index, float targetX, float targetY) {
            Index = index;
            _sourceX = X;
            _sourceY = Y;
            _targetX = targetX;
            _targetY = targetY;
        }
    }
}
