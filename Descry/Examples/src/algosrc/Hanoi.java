package algosrc;

import descry.Descry;
import descry.VisualAlgorithm;
import descry.internal.VisualDebugger;
import descry.utility.Log;
import descry.utility.Mathf;

public class Hanoi implements VisualAlgorithm {

    private VisualDebugger _graphics;

    private final int _disks;
    private final Tower _pegA;
    private final Tower _pegB;
    private final Tower _pegC;

    public static void main(String[] args) {
        Descry.visualize(new Hanoi(8));
    }

    public Hanoi(int disks) {
        _disks = disks;
        _pegA = new Tower(_disks, true);
        _pegB = new Tower(_disks, false);
        _pegC = new Tower(_disks, false);
    }

    @Override
    public void run(VisualDebugger graphics) {
        Log.info("Hanoi", "Script started.");
        _graphics = graphics;
        drawTowers();
        hanoi(_pegA, _pegB, _pegC, _disks);
        Log.info("Hanoi", "Script ended.");
    }

    private void hanoi(Tower source, Tower target, Tower auxiliary, int n) {
        if (n > 0) {
            hanoi(source, auxiliary, target, n - 1);
            Tower.moveDisk(source, target);
            drawTowers();
            hanoi(auxiliary, target, source, n - 1);
        }
    }

    private void drawTowers() {

        float canvasCenterY = _graphics.getSizeY() * 0.5f;
        float canvasCenterX = _graphics.getSizeX() * 0.5f;
        float towerSizeY = _graphics.getSizeY() * 0.85f;
        float pegBaseY = canvasCenterY - towerSizeY * 0.5f;
        float diskHeight = towerSizeY / (float) _disks;
        float pegSpacing = (_graphics.getSizeX() / 3f) * 0.75f;
        float pegAX = canvasCenterX - pegSpacing;
        float pegCX = canvasCenterX + pegSpacing;
        float diskMinSizeX = pegSpacing * 0.1f;
        float diskMaxSizeX = pegSpacing * 0.9f;

        _graphics.beginFrame();
        _graphics.rectangleMode(CENTER);
        _graphics.setFrameRate(8);
        _graphics.background(200);
        _pegA.render(_graphics, pegAX, pegBaseY, diskMinSizeX, diskMaxSizeX, diskHeight);
        _pegB.render(_graphics, canvasCenterX, pegBaseY, diskMinSizeX, diskMaxSizeX, diskHeight);
        _pegC.render(_graphics, pegCX, pegBaseY, diskMinSizeX, diskMaxSizeX, diskHeight);
        _graphics.endFrame();
    }

    private static class Tower {

        private final int[] _disks;
        private final int _diskLimit;
        private int _diskOnPegCount;

        public Tower(int diskLimit, boolean isSource) {
            _diskLimit = diskLimit;
            _diskOnPegCount = isSource ? diskLimit : 0;
            _disks = new int[diskLimit];
            if (isSource) {
                for (int d = 0; d < diskLimit; ++d) {
                    _disks[d] = diskLimit - d;
                }
            }
        }

        public void render(VisualDebugger g, float baseX, float baseY, float diskMinSizeX, float diskMaxSizeX, float diskSizeY) {
            g.pushMatrix();
            g.translate(baseX, baseY);
            for (int d = 0; d < _diskOnPegCount; ++d) {
                float diskSizeX = Mathf.map(_disks[d], 0, _diskLimit, diskMinSizeX, diskMaxSizeX);
                int rgb = (int) Mathf.map((float) _disks[d], 0, _diskLimit, 0, 255); //TODO: Fix this BS
                g.fillColor(rgb, 100, 100, 255);
                g.rectangle(0f, (_diskLimit - d) * diskSizeY, diskSizeX, diskSizeY);
            }
            g.popMatrix();
        }

        public static void moveDisk(Tower source, Tower target) {
            int sourceTop = source._diskOnPegCount - 1;
            int targetTop = target._diskOnPegCount;
            target._disks[targetTop] = source._disks[sourceTop];
            source._disks[sourceTop] = 0;
            source._diskOnPegCount -= 1;
            target._diskOnPegCount += 1;
        }
    }
}
