package de.miemietz.reaction.app;

import de.miemietz.reaction.executor.ThreadExecutor;
import de.miemietz.reaction.loop.ActionLoop;
import de.miemietz.reaction.loop.ActionLoopImpl;
import de.miemietz.reaction.object.ConstructorImpl;
import de.miemietz.reaction.object.NullObjectFactoryImpl;
import de.miemietz.reaction.proxy.ProxyFactory;
import de.miemietz.reaction.proxy.ProxyFactoryImpl;
import de.miemietz.reaction.reactor.ReactorImpl;

public class ThreadBootstrap implements Bootstrap {

    public static Application app() {

        Bootstrap bootstrap = new ThreadBootstrap();

        return bootstrap.loadApplication();
    }

    @Override
    public Application loadApplication() {

        ActionLoop actionLoop = new ActionLoopImpl(
                new ReactorImpl(new ThreadExecutor())
        );

        ProxyFactory proxyFactory = new ProxyFactoryImpl(
                actionLoop,
                new ConstructorImpl(),
                new NullObjectFactoryImpl()
        );

        return new ApplicationImpl(actionLoop, proxyFactory);
    }
}
