package de.miemietz.reaction.queue;

import de.miemietz.reaction.action.Action;

public interface ActionQueue {

    Action<?> get();

    void put(Action<?> action);
}
