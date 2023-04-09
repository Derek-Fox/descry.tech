package descry.internal.abstraction;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public abstract class Coroutine {

    private final Thread _thread;
    private final CyclicBarrier _cycle;

    public Coroutine() {
        _thread = new Thread(this::run);
        _cycle = new CyclicBarrier(2);
    }

    public final boolean isRunning() {
        return _thread.isAlive();
    }

    public final void start() {
        if (isRunning()) {
            throw new RuntimeException("Coroutine is already running.");
        }
        _thread.start();
    }

    public void update() {
        passBarrier();
        passBarrier();
    }

    private void passBarrier() {
        try {
            _cycle.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    protected final void beginUpdate() {
        passBarrier();
    }

    protected final void endUpdate() {
        passBarrier();
    }

    protected abstract void run();

}
