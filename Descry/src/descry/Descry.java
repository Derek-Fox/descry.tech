package descry;

import descry.internal.Application;
import descry.utility.Log;

public class Descry {

    public static void visualize(VisualAlgorithm algorithm) {
        Log.info("Descry", "Starting script....");
        Application app = new Application(algorithm, 800, 600);
        app.run();
        Log.info("Descry", "Script done!");
    }
}
