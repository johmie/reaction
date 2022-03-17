package de.miemietz.reaction.proxy;

public interface ProxyFactory {

    <T> T build(Class<? extends T> anyClass);
}
