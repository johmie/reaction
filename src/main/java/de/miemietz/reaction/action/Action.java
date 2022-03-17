package de.miemietz.reaction.action;

import java.lang.reflect.Method;

public class Action<T> {

    private final T proxy;
    private final Method method;
    private final Object[] args;
    private final Future<?> result;

    public Action(T proxy, Method method, Object[] args, Future<?> result) {

        this.proxy = proxy;
        this.method = method;
        this.args = args;
        this.result = result;
    }

    public T getProxy() {

        return proxy;
    }

    public Method getMethod() {

        return method;
    }

    public Object[] getArgs() {

        return args;
    }

    public Future<?> getResult() {

        return result;
    }
}
