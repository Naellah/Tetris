package Modele;

public class OrdonnanceurSimple extends Thread {

    private final Object lock = new Object();
    private boolean isPaused = false;

    public Runnable monRunnable;

    public OrdonnanceurSimple(Runnable _monRunnable) {
        monRunnable = _monRunnable;
    }

    public void pause() {
        isPaused = true;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void resumeThread() {
        synchronized (lock) {
            isPaused = false;
            lock.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(400);
                synchronized (lock) {
                    while (isPaused) {
                        lock.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monRunnable.run();
        }
    }
}
