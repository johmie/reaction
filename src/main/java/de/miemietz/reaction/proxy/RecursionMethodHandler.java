package de.miemietz.reaction.proxy;

import de.miemietz.reaction.action.Action;
import de.miemietz.reaction.action.Future;
import de.miemietz.reaction.action.FutureImpl;
import de.miemietz.reaction.loop.ActionLoop;
import de.miemietz.reaction.object.NullObject;
import de.miemietz.reaction.object.NullObjectFactory;
import javassist.util.proxy.MethodHandler;

import java.lang.reflect.Method;

public class RecursionMethodHandler implements MethodHandler {

    private final ActionLoop actionLoop;
    private final ProxyFactory proxyFactory;
    private final NullObjectFactory nullFactory;

    RecursionMethodHandler(ActionLoop actionLoop, ProxyFactory proxyFactory, NullObjectFactory nullFactory) {

        this.actionLoop = actionLoop;
        this.proxyFactory = proxyFactory;
        this.nullFactory = nullFactory;
    }

    @Override
    public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) {

        Object next;
        Future<Object> result = new FutureImpl<>();
        Class<?> returnType = thisMethod.getReturnType();

        if (!((next = nullFactory.build(returnType)) instanceof NullObject)) {
            result.complete(next);
        } else {
            next = proxyFactory.build(returnType);
        }

        Action<?> action = new Action<>(self, proceed, args, result);
        actionLoop.addAction(action);

        return next;
    }
}
