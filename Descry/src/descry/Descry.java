package descry;

import descry.internal.Application;

public class Descry {

    public static void visualize(VisualAlgorithm algorithm) {
        visualize(algorithm, 800, 600);
    }

    public static void visualize(VisualAlgorithm algorithm, int canvasSizeX, int canvasSizeY) {
        Application app = new Application(algorithm, canvasSizeX, canvasSizeY);
        app.launch();
    }
}
