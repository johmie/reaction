package de.miemietz.reaction.app;

public interface Application {

    <T, S extends T> T make(Class<S> anyClass);

    void run() throws InterruptedException;
}
