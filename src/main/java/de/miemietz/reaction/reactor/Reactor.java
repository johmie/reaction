package de.miemietz.reaction.reactor;

import de.miemietz.reaction.action.Action;

public interface Reactor {

    void react(Action<?> action);
}
