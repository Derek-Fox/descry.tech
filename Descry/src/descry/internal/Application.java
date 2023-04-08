package descry.internal;

import descry.VisualAlgorithm;
import descry.internal.abstraction.Coroutine;
import descry.internal.abstraction.InterfaceBinding;
import descry.internal.processing.PAppletImpl;
import processing.core.PApplet;

public class Application {

    private final ScriptAdapter _script;

    public Application(VisualAlgorithm algorithm, int sizeX, int sizeY) {
        PAppletImpl.configure(this, sizeX, sizeY);
        _script = new ScriptAdapter(algorithm);
    }

    public void launch() {
        PApplet.main(PAppletImpl.class);
    }

    public void initialize() {
        _script.start();
    }

    public void renderFrame(Graphics g) {
        _script.renderFrame(g);
    }

    private static class ScriptAdapter extends Coroutine implements FrameContext {

        private final InterfaceBinding _binding;
        private final VisualAlgorithm _script;

        public ScriptAdapter(VisualAlgorithm script) {
            _binding = new InterfaceBinding(FrameContext.class, Graphics.class, VisualDebugger.class);
            _binding.setInvocationTarget(FrameContext.class, this);
            _script = script;
        }

        public void renderFrame(Graphics g) {
            _binding.setInvocationTarget(Graphics.class, g);
            update(); // Yield control to script's thread.
        }

        @Override
        public void beginFrame() {
            beginUpdate();
        }

        @Override
        public void endFrame() {
            endUpdate();
        }

        @Override
        protected void run() {
            VisualDebugger debugger = (VisualDebugger) _binding.newProxyInstance();
            _script.run(debugger);
        }
    }
}
