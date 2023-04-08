package descry.internal.abstraction;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public abstract class Coroutine {

    private final Thread _thread;
    private final CyclicBarrier _barrier;

    public Coroutine() {
        _thread = new Thread(this::run);
        _barrier = new CyclicBarrier(2);
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
            _barrier.await();
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


    public static void main(String[] args) {
        CoroutineTest.runTest();
    }

    public class CoroutineTest {

        private static void runTest() {

            AsyncLooper looper = new AsyncLooper();
            looper.start();

            int index = 0;
            while (looper.isRunning()) {
                looper.update();
                System.out.println(looper.getIndex() + " " + index);
                index += 1;
            }
        }

        private static void hiccup() {
            long sleepTimeMS = (long)(Math.random() * 1000.0d);
            try {
                Thread.sleep(sleepTimeMS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        private static class AsyncLooper extends Coroutine {

            private int _index;

            public int getIndex() {
                return _index;
            }

            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    beginUpdate();
                    hiccup();
                    _index = i;
                    endUpdate();
                }
            }
        }
    }

}
