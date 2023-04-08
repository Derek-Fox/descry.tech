package descry;

import descry.internal.VisualDebugger;

public class Descry {

    public static void visualize(VisualAlgorithm algorithm) {
        //TODO: Everything :)
        System.out.println("[Descry]: Starting script....");
        algorithm.run(VisualDebugger.getInstance());
        System.out.println("[Descry]: Script done!");
    }

}
