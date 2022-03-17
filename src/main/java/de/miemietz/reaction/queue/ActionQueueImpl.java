package de.miemietz.reaction.queue;

import de.miemietz.reaction.action.Action;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ActionQueueImpl implements ActionQueue {

    private final Queue<Action<?>> queue = new ConcurrentLinkedQueue<>();

    @Override
    public Action<?> get() {

        return queue.poll();
    }

    @Override
    public void put(Action<?> action) {

        queue.add(action);
    }
}
