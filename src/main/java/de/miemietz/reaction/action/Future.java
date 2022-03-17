package de.miemietz.reaction.action;

public interface Future<T> {

    boolean isDone();

    void complete(T value);

    T get();

    void reset();
}
