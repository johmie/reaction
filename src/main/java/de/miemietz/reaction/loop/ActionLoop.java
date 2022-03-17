package de.miemietz.reaction.loop;

import de.miemietz.reaction.action.Action;

public interface ActionLoop {

    void addAction(Action<?> action);

    void run() throws InterruptedException;
}
