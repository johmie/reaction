package de.miemietz.reaction.reactor;

import de.miemietz.reaction.action.Action;
import de.miemietz.reaction.action.Future;
import de.miemietz.reaction.executor.AsyncExecutor;
import javassist.util.proxy.Proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReactorImpl implements Reactor {

    private final AsyncExecutor asyncExecutor;

    public ReactorImpl(AsyncExecutor asyncExecutor) {

        this.asyncExecutor = asyncExecutor;
    }

    @Override
    public void react(Action<?> action) {

        Proxy proxy = (Proxy) action.getProxy();
        Method method = action.getMethod();
        Object[] args = action.getArgs();
        Future<Object> result = (Future<Object>) action.getResult();

        result.reset();
        proxy.setHandler(new FutureResultMethodHandler(result));
        asyncExecutor.execute(
                () -> {
                    try {
                        result.complete(
                                method.invoke(proxy, args)
                        );
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
