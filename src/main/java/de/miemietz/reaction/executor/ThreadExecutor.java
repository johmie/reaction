package de.miemietz.reaction.executor;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ThreadExecutor implements AsyncExecutor {

    private final int limit = Runtime.getRuntime().availableProcessors();
    private final Deque<Thread> pool = new ConcurrentLinkedDeque<>();

    public void execute(Runnable action) {

        if (pool.size() < limit) {
            addThread(action);
        } else {
            Thread thread = pool.pollFirst();
            while (thread.isAlive()) {
            }
            addThread(action);
        }
        pool.getLast().start();
    }

    private void addThread(Runnable action) {

        Thread thread = new Thread(action);
        pool.add(thread);
    }
}
