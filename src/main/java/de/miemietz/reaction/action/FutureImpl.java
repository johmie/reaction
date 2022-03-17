package de.miemietz.reaction.action;

public class FutureImpl<T> implements Future<T> {

    private T value = null;
    private boolean isDone = false;

    @Override
    public void complete(T value) {

        this.value = value;
        isDone = true;
    }

    @Override
    public T get() {

        while (!isDone()) {
        }

        return value;
    }

    @Override
    public boolean isDone() {

        return isDone;
    }

    @Override
    public void reset() {

        isDone = false;
        value = null;
    }
}
