package de.miemietz.reaction.app;

import de.miemietz.reaction.loop.ActionLoop;
import de.miemietz.reaction.proxy.ProxyFactory;

public class ApplicationImpl implements Application {

    private final ActionLoop actionLoop;
    private final ProxyFactory proxyFactory;

    public ApplicationImpl(ActionLoop actionLoop, ProxyFactory proxyFactory) {

        this.actionLoop = actionLoop;
        this.proxyFactory = proxyFactory;
    }

    @Override
    public <T, S extends T> T make(Class<S> anyClass) {

        return proxyFactory.build(anyClass);
    }

    @Override
    public void run() {

        actionLoop.run();
    }
}
