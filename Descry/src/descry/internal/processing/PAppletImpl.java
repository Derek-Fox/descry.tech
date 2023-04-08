package descry.internal.processing;

import descry.internal.Application;
import processing.core.PApplet;

public class PAppletImpl extends PApplet {

    public static int CanvasSizeX = 800;
    public static int CanvasSizeY = 600;
    private static Application _app;

    public static void configure(Application app, int sizeX, int sizeY) {
        _app = app;
        CanvasSizeX = sizeX;
        CanvasSizeY = sizeY;
    }

    public static void main(String[] args) {
        PApplet.main(PAppletImpl.class);
    }

    @Override
    public void settings() {
        size(CanvasSizeX, CanvasSizeY);
    }

    @Override
    public void setup() {
        _app.initialize();
    }

    @Override
    public void draw() {
        PGraphicsWrapper graphics = new PGraphicsWrapper(getGraphics());
        _app.renderFrame(graphics);
    }
}
