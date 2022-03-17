package de.miemietz.reaction.proxy;

import de.miemietz.reaction.loop.ActionLoop;
import de.miemietz.reaction.object.Constructor;
import de.miemietz.reaction.object.NullObjectFactory;
import javassist.util.proxy.Proxy;

public class ProxyFactoryImpl implements ProxyFactory {

    private final ActionLoop actionLoop;
    private final Constructor constructor;
    private final NullObjectFactory nullFactory;

    public ProxyFactoryImpl(ActionLoop actionLoop, Constructor constructor, NullObjectFactory nullFactory) {

        this.actionLoop = actionLoop;
        this.constructor = constructor;
        this.nullFactory = nullFactory;
    }

    @Override
    public <T> T build(Class<? extends T> anyClass) {

        javassist.util.proxy.ProxyFactory factory = new javassist.util.proxy.ProxyFactory();
        factory.setSuperclass(anyClass);
        factory.setFilter(
                method -> !method.getName().equals("toString")
                        && !method.getName().equals("hashCode")
                        && !method.getName().equals("finalize")
                        && !method.getName().equals("equals")
                        && !method.getName().equals("clone"));

        Class<?> proxyClass = factory.createClass();
        Proxy proxy = (Proxy) constructor.construct(proxyClass);
        proxy.setHandler(new RecursionMethodHandler(actionLoop, this, nullFactory));

        return (T) proxy;
    }
}
