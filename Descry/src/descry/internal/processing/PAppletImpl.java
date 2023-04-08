package descry.internal.processing;

import descry.internal.Application;
import processing.core.PApplet;

public class PAppletImpl extends PApplet implements Application {

    public static void main(String[] args) {
        PApplet.main(PAppletImpl.class, args);
    }

    @Override
    public void settings() {
        size(CanvasSizeX, CanvasSizeY);
    }
}
