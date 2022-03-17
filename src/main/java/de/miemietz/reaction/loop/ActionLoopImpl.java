package de.miemietz.reaction.loop;

import de.miemietz.reaction.action.Action;
import de.miemietz.reaction.queue.ActionQueue;
import de.miemietz.reaction.queue.ActionQueueImpl;
import de.miemietz.reaction.reactor.Reactor;

public class ActionLoopImpl implements ActionLoop {

    private final ActionQueue actionQueue = new ActionQueueImpl();
    private final Reactor reactor;

    public ActionLoopImpl(Reactor reactor) {
        this.reactor = reactor;
    }

    @Override
    public void addAction(Action<?> action) {

        actionQueue.put(action);
    }

    @Override
    public void run() throws InterruptedException {

        Action<?> action;
        do {
            action = actionQueue.get();
            if (action != null) {
                reactor.react(action);
            } else {
                Thread.sleep(1);
            }
        } while (true);
    }
}
